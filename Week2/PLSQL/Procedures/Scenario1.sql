-- Create Customers table
CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE
);

-- Create Accounts table
CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20),
    Balance NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);


INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) 
VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) 
VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);


INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) 
VALUES (1, 1, 'Savings', 1000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) 
VALUES (2, 2, 'Checking', 1500, SYSDATE);

select AccountID,Balance,LastModified from Accounts;


DECLARE
    PROCEDURE ProcessMonthlyInterest IS
        CURSOR savings_accounts IS
            SELECT AccountID, Balance
            FROM Accounts
            WHERE AccountType = 'Savings';

        v_balance Accounts.Balance%TYPE;
        v_interest_rate CONSTANT NUMBER := 0.01;
    BEGIN
        FOR r_account IN savings_accounts LOOP
            v_balance := r_account.Balance + (r_account.Balance * v_interest_rate);

            UPDATE Accounts
            SET Balance = v_balance,
                LastModified = SYSDATE
            WHERE AccountID = r_account.AccountID;
            DBMS_OUTPUT.PUT_LINE('---------------------------------------------------------');
            DBMS_OUTPUT.PUT_LINE('Updated AccountID: ' || r_account.AccountID || ' with new Balance: ' || v_balance);
            DBMS_OUTPUT.PUT_LINE('---------------------------------------------------------');
        END LOOP;

        COMMIT;
    END;

BEGIN
    ProcessMonthlyInterest;
END;
/
select AccountID,Balance,LastModified from Accounts;


/*
Output:


 ACCOUNTID    BALANCE LASTMODIF
---------- ---------- ---------
	 1	 1000 06-AUG-24
	 2	 1500 06-AUG-24
---------------------------------------------------------
Updated AccountID: 1 with new Balance: 1010
---------------------------------------------------------

 ACCOUNTID    BALANCE LASTMODIF
---------- ---------- ---------
	 1	 1010 06-AUG-24
	 2	 1500 06-AUG-24

 ACCOUNTID    BALANCE LASTMODIF
---------- ---------- ---------
	 1	 1010 06-AUG-24
	 2	 1500 06-AUG-24

*/
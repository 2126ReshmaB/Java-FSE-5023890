CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE
);


CREATE TABLE Accounts (
AccountID NUMBER PRIMARY KEY,
CustomerID NUMBER,
AccountType VARCHAR2(20),
Balance NUMBER,
LastModified DATE,
FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Customers (CustomerID, Name, DOB) VALUES (1, 'John Doe', TO_DATE('1955-05-15', 'YYYY-MM-DD'));
INSERT INTO Customers (CustomerID, Name, DOB) VALUES (2, 'Jane Smith', TO_DATE('1940-07-20', 'YYYY-MM-DD'));

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (1, 1, 'Savings', 1000, SYSDATE);
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (2, 2, 'Checking', 1500, SYSDATE);


DECLARE
    CURSOR cur_Accounts IS
        SELECT AccountID, Balance
        FROM Accounts;
        
    acc_ID NUMBER;
    balance NUMBER;
    annualFee NUMBER := 100;
BEGIN
    OPEN cur_Accounts;
    
    LOOP
        FETCH cur_Accounts INTO acc_ID,balance;
        EXIT WHEN cur_Accounts%NOTFOUND;
        
        UPDATE Accounts
        SET Balance = Balance - annualFee,
        LastModified = SYSDATE
        WHERE AccountID = acc_ID;
        
        DBMS_OUTPUT.PUT_LINE('----------------------------------------------');
        DBMS_OUTPUT.PUT_LINE('Account ID: ' || acc_ID);
        DBMS_OUTPUT.PUT_LINE('Balance: ' || (balance - annualFee));
        DBMS_OUTPUT.PUT_LINE('----------------------------------------------');
    END LOOP;
    
    CLOSE cur_Accounts;
END;
/

/*

Output:

----------------------------------------------
Account ID: 1
Balance: 900
----------------------------------------------
----------------------------------------------
Account ID: 2
Balance: 1400
----------------------------------------------

 ACCOUNTID CUSTOMERID ACCOUNTTYPE	      BALANCE LASTMODIF
---------- ---------- -------------------- ---------- ---------
	 1	    1 Savings			  900 07-AUG-24
	 2	    2 Checking			 1400 07-AUG-24

 ACCOUNTID CUSTOMERID ACCOUNTTYPE	      BALANCE LASTMODIF
---------- ---------- -------------------- ---------- ---------
	 1	    1 Savings			  900 07-AUG-24
	 2	    2 Checking			 1400 07-AUG-24

*/
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

-- Create the AccountOperations package specification
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(p_AccountID NUMBER, p_CustomerID NUMBER, p_AccountType VARCHAR2, p_Balance NUMBER);
    PROCEDURE CloseAccount(p_AccountID NUMBER);
    FUNCTION GetTotalBalance(p_CustomerID NUMBER) RETURN NUMBER;
END AccountOperations;
/

-- Create the AccountOperations package body
CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE OpenAccount(p_AccountID NUMBER, p_CustomerID NUMBER, p_AccountType VARCHAR2, p_Balance NUMBER) IS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_AccountID, p_CustomerID, p_AccountType, p_Balance, SYSDATE);
        DBMS_OUTPUT.PUT_LINE('Account opened successfully.');
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error opening account: ' || SQLERRM);
    END OpenAccount;
    
    PROCEDURE CloseAccount(p_AccountID NUMBER) IS
    BEGIN
        DELETE FROM Accounts
        WHERE AccountID = p_AccountID;
        DBMS_OUTPUT.PUT_LINE('Account closed successfully.');
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error closing account: ' || SQLERRM);
    END CloseAccount;
    
    FUNCTION GetTotalBalance(p_CustomerID NUMBER) RETURN NUMBER IS
        v_TotalBalance NUMBER := 0;
    BEGIN
        SELECT SUM(Balance) INTO v_TotalBalance
        FROM Accounts
        WHERE CustomerID = p_CustomerID;
        
        RETURN v_TotalBalance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 0;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error calculating total balance: ' || SQLERRM);
            RETURN 0;
    END GetTotalBalance;
END AccountOperations;
/

BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB) VALUES (1, 'John Doe', TO_DATE('1955-05-15', 'YYYY-MM-DD'));
    INSERT INTO Customers (CustomerID, Name, DOB) VALUES (2, 'Jane Smith', TO_DATE('1940-07-20', 'YYYY-MM-DD'));
END;
/

BEGIN
    AccountOperations.OpenAccount(1, 1, 'Savings', 1000);
    AccountOperations.OpenAccount(2, 1, 'Checking', 1500);
    AccountOperations.OpenAccount(3, 2, 'Savings', 2000);
END;
/

BEGIN
    AccountOperations.CloseAccount(1);
END;
/

DECLARE
    v_TotalBalance NUMBER;
BEGIN
    v_TotalBalance := AccountOperations.GetTotalBalance(1);
    DBMS_OUTPUT.PUT_LINE('Total balance for CustomerID 1: ' || v_TotalBalance);
END;
/

SELECT * FROM Accounts;
/

/*

Output:

Account opened successfully.
Account opened successfully.
Account opened successfully.
Account closed successfully.
Total balance for CustomerID 1: 1500

 ACCOUNTID CUSTOMERID ACCOUNTTYPE	      BALANCE LASTMODIF
---------- ---------- -------------------- ---------- ---------
	 2	    1 Checking			 1500 07-AUG-24
	 3	    2 Savings			 2000 07-AUG-24

 ACCOUNTID CUSTOMERID ACCOUNTTYPE	      BALANCE LASTMODIF
---------- ---------- -------------------- ---------- ---------
	 2	    1 Checking			 1500 07-AUG-24
	 3	    2 Savings			 2000 07-AUG-24
   
*/
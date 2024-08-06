CREATE TABLE Accounts (
AccountID NUMBER PRIMARY KEY,
CustomerID NUMBER,
AccountType VARCHAR2(20),
Balance NUMBER,
LastModified DATE,
FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (1, 1, 'Savings', 1000, SYSDATE);
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (2, 2, 'Checking', 1500, SYSDATE);

CREATE OR REPLACE FUNCTION HasSufficientBalance(accountID IN number,amount IN number) RETURN BOOLEAN IS
    balance number;
BEGIN
    SELECT Balance INTO balance
    FROM Accounts
    WHERE accountID = AccountID;

    IF balance >= amount THEN
        RETURN TRUE;
    ELSE 
        RETURN FALSE;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 0; 
END;
/

DECLARE
    isSufficient BOOLEAN;
BEGIN
    isSufficient:= HasSufficientBalance(1,500);
    DBMS_OUTPUT.PUT_LINE('Balance: ' || isSufficient);
END;
/


/*
Balance: TRUE
*/
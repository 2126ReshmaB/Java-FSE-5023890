CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE 
INSERT 
ON Transactions
FOR EACH ROW
DECLARE
    current_balance NUMBER;
BEGIN
    
    SELECT Balance INTO current_balance
    FROM Accounts
    WHERE AccountID = :NEW.AccountID;

   
    IF :NEW.TransactionType = 'Withdrawal' AND :NEW.Amount > current_balance THEN
        RAISE_APPLICATION_ERROR(-20001, 'Withdrawal exceeds account balance.');
    END IF;

    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.');
    END IF;
END;
/

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (1, 1, SYSDATE, 500, 'Deposit');
--Transaction successfully inserted.

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (2, 1, SYSDATE, 200, 'Withdrawal');
--Transaction successfully inserted.

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (3, 1, SYSDATE, 3000, 'Withdrawal');
--ORA-20001: Withdrawal exceeds account balance.

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (4, 1, SYSDATE, -100, 'Deposit');
--ORA-20002: Deposit amount must be positive.


/*
Transaction Table

TRANSACTIONID  ACCOUNTID  TRANSACTIONDATE  AMOUNT  TRANSACTIONTYPE
--------------------------------------------------------------------
1               1          (current date)   500     Deposit
2               1          (current date)   200     Withdrawal


*/
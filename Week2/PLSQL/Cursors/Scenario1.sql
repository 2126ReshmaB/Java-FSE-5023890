CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE
);

CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER,
    CustomerID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(20),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);


INSERT INTO Customers (CustomerID, Name, DOB) VALUES (1, 'John Doe', TO_DATE('1955-05-15', 'YYYY-MM-DD'));
INSERT INTO Customers (CustomerID, Name, DOB) VALUES (2, 'Jane Smith', TO_DATE('1940-07-20', 'YYYY-MM-DD'));

INSERT INTO Transactions (TransactionID, AccountID, CustomerID, TransactionDate, Amount, TransactionType) 
VALUES (1, 1, 1, SYSDATE, 500, 'Deposit');
INSERT INTO Transactions (TransactionID, AccountID, CustomerID, TransactionDate, Amount, TransactionType) 
VALUES (2, 1, 1, SYSDATE, 200, 'Withdrawal');
INSERT INTO Transactions (TransactionID, AccountID, CustomerID, TransactionDate, Amount, TransactionType) 
VALUES (3, 1, 2, SYSDATE, 300, 'Deposit');


DECLARE
    CURSOR cur_Transactions IS
        SELECT c.CustomerID, c.Name, t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Transactions t ON c.CustomerID = t.CustomerID
        WHERE TO_CHAR(t.TransactionDate, 'MM-YYYY') = TO_CHAR(SYSDATE, 'MM-YYYY');
        
    v_CustomerID NUMBER;
    v_Name VARCHAR2(100);
    v_TransactionDate DATE;
    v_Amount NUMBER;
    v_TransactionType VARCHAR2(20);
BEGIN
    OPEN cur_Transactions;
    
    LOOP
        FETCH cur_Transactions INTO v_CustomerID, v_Name, v_TransactionDate, v_Amount, v_TransactionType;
        EXIT WHEN cur_Transactions%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE('----------------------------------------------');
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || v_CustomerID);
        DBMS_OUTPUT.PUT_LINE('Customer Name: ' || v_Name);
        DBMS_OUTPUT.PUT_LINE('Transaction Date: ' || v_TransactionDate);
        DBMS_OUTPUT.PUT_LINE('Transaction Amount: ' || v_Amount);
        DBMS_OUTPUT.PUT_LINE('Transaction Type: ' || v_TransactionType);
        DBMS_OUTPUT.PUT_LINE('----------------------------------------------');
    END LOOP;
    
    CLOSE cur_Transactions;
END;
/


/*


Output:

----------------------------------------------
Customer ID: 1
Customer Name: John Doe
Transaction Date: 07-AUG-24
Transaction Amount: 500
Transaction Type: Deposit
----------------------------------------------
----------------------------------------------
Customer ID: 1
Customer Name: John Doe
Transaction Date: 07-AUG-24
Transaction Amount: 200
Transaction Type: Withdrawal
----------------------------------------------
----------------------------------------------
Customer ID: 2
Customer Name: Jane Smith
Transaction Date: 07-AUG-24
Transaction Amount: 300
Transaction Type: Deposit
----------------------------------------------


*/
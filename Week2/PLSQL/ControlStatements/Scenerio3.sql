CREATE TABLE Customers (
CustomerID NUMBER PRIMARY KEY,
Name VARCHAR2(100),
DOB DATE,
Balance NUMBER,
LastModified DATE
);

CREATE TABLE Loans (
LoanID NUMBER PRIMARY KEY,
CustomerID NUMBER,
LoanAmount NUMBER,
InterestRate NUMBER,
StartDate DATE,
EndDate DATE,
FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (1, 'John Doe', TO_DATE('1955-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (2, 'Jane Smith', TO_DATE('1940-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (3, 'Jhonny', TO_DATE('2000-07-20', 'YYYY-MM-DD'), 1300, SYSDATE);
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (1, 1, 5000, 5, SYSDATE, SYSDATE + 30);
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (2, 2, 10000, 3, SYSDATE, SYSDATE + 20);
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (3, 3, 70000, 5, SYSDATE, SYSDATE + 50);


DECLARE
    CURSOR customers IS
        SELECT c.customerID, l.LoanID, l.EndDate,c.Name
        FROM customers c JOIN Loans l ON l.CustomerID = c.CustomerID WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    dbms_output.put_line('**************************************************************');
    FOR r_loan IN customers LOOP
    DBMS_OUTPUT.PUT_LINE('Reminder: Customer ' || r_loan.Name || 
                             ' (CustomerID: ' || r_loan.CustomerID || 
                             ') has a loan (LoanID: ' || r_loan.LoanID || 
                             ') due on ' || TO_CHAR(r_loan.EndDate, 'YYYY-MM-DD') || '.');
    END LOOP;
    COMMIT; 
END;
/

/*

**************************************************************
Reminder: Customer John Doe (CustomerID: 1) has a loan (LoanID: 1) due on 2024-09-05.
Reminder: Customer Jane Smith (CustomerID: 2) has a loan (LoanID: 2) due on 2024-08-26.

*/
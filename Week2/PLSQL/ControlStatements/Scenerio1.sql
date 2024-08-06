
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
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (2, 2, 10000, 3, SYSDATE, ADD_MONTHS(SYSDATE, 60));
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (3, 3, 70000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));

SELECT c.customerID, c.DOB, l.InterestRate, l.LoanID FROM customers c JOIN Loans l ON l.CustomerID = c.CustomerID;
        
DECLARE
    CURSOR customers IS
        SELECT c.customerID, c.DOB, l.InterestRate, l.LoanID
        FROM customers c JOIN Loans l ON l.CustomerID = c.CustomerID;
    discount_rate NUMBER := 0.01;
    age Number;
BEGIN
    dbms_output.put_line('**************************************************************');
    FOR customer IN customers LOOP
    age := FLOOR(MONTHS_BETWEEN(SYSDATE, customer.DOB) / 12);
    IF age > 60 THEN
        UPDATE Loans
        SET InterestRate = InterestRate - (InterestRate * discount_rate)
        WHERE LoanID = customer.LoanID;
    END IF;
    END LOOP;
    COMMIT; 
END;
/

SELECT c.customerID, c.DOB, l.InterestRate, l.LoanID FROM customers c JOIN Loans l ON l.CustomerID = c.CustomerID;


---------------------------------------OUTPUT-----------------------------------------------------------------

Output:

/*


CUSTOMERID DOB	     INTERESTRATE     LOANID
---------- --------- ------------ ----------
	 1      15-MAY-55		5	             1
	 2      20-JUL-40		3	             2
	 3      20-JUL-00		5	             3

**************************************************************

CUSTOMERID DOB	     INTERESTRATE     LOANID
---------- --------- ------------ ----------
	 1        15-MAY-55	     4.95	   1
	 2        20-JUL-40	     2.97	   2
	 3        20-JUL-00		      5	   3

CUSTOMERID DOB	     INTERESTRATE     LOANID
---------- --------- ------------ ----------
	 1        15-MAY-55	     4.95	   1
	 2        20-JUL-40	     2.97	   2
	 3        20-JUL-00		     5	   3
     

*/
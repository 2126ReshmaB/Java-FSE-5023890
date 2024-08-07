CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE
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

INSERT INTO Customers (CustomerID, Name, DOB) VALUES (1, 'John Doe', TO_DATE('1955-05-15', 'YYYY-MM-DD'));
INSERT INTO Customers (CustomerID, Name, DOB) VALUES (2, 'Jane Smith', TO_DATE('1940-07-20', 'YYYY-MM-DD'));

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (1, 1, 5000, 4, SYSDATE, ADD_MONTHS(SYSDATE, 60));
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (2, 1, 5000, 15, SYSDATE, ADD_MONTHS(SYSDATE, 60));

DECLARE
    CURSOR cur_Loans IS
        SELECT LoanID, InterestRate
        FROM Loans;
        
    loan_ID NUMBER;
    interestrate NUMBER;
    newinterestrate NUMBER;
BEGIN
    OPEN cur_Loans;
    
    LOOP
        FETCH cur_Loans INTO loan_ID,interestrate;
        EXIT WHEN cur_Loans%NOTFOUND;
        
        IF interestrate < 5 then
            newinterestrate := interestrate + 0.5;
        ELSE 
            newinterestrate := interestrate + 0.8;
        END IF;
        UPDATE Loans
        SET InterestRate = newinterestrate
        WHERE LoanID = loan_ID;
        
        DBMS_OUTPUT.PUT_LINE('----------------------------------------------');
        DBMS_OUTPUT.PUT_LINE('Loan ID: ' || loan_ID);
        DBMS_OUTPUT.PUT_LINE('InterestRate: ' || newinterestrate);
        DBMS_OUTPUT.PUT_LINE('----------------------------------------------');
    END LOOP;
    
    CLOSE cur_Loans;
END;
/

SELECT * FROM Loans;

/*

Output:

----------------------------------------------
Loan ID: 1
InterestRate: 4.5
----------------------------------------------
----------------------------------------------
Loan ID: 2
InterestRate: 15.8
----------------------------------------------

    LOANID CUSTOMERID LOANAMOUNT INTERESTRATE STARTDATE ENDDATE
---------- ---------- ---------- ------------ --------- ---------
	 1	    1	    5000	  4.5 07-AUG-24 07-AUG-29
	 2	    1	    5000	 15.8 07-AUG-24 07-AUG-29

    LOANID CUSTOMERID LOANAMOUNT INTERESTRATE STARTDATE ENDDATE
---------- ---------- ---------- ------------ --------- ---------
	 1	    1	    5000	  4.5 07-AUG-24 07-AUG-29
	 2	    1	    5000	 15.8 07-AUG-24 07-AUG-29
   
*/
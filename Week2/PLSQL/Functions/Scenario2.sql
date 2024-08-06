CREATE TABLE Loans (
LoanID NUMBER PRIMARY KEY,
CustomerID NUMBER,
LoanAmount NUMBER,
InterestRate NUMBER,
StartDate DATE,
EndDate DATE,
FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(loan_id IN NUMBER) RETURN NUMBER IS
    loan_amount NUMBER;
    interest_rate NUMBER;
    loan_duration NUMBER;
    monthly_installment NUMBER;
    monthly_rate NUMBER;
    total_months NUMBER;
BEGIN
    SELECT LoanAmount, InterestRate, MONTHS_BETWEEN(EndDate, StartDate) INTO loan_amount, interest_rate, loan_duration
    FROM Loans
    WHERE LoanID = loan_id;

    monthly_rate := interest_rate / 100 / 12;
    total_months := loan_duration;

   
    monthly_installment := (loan_amount * monthly_rate) / (1 - POWER(1 + monthly_rate, -total_months));

    RETURN monthly_installment;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 0; 
END;
/

DECLARE
    installment NUMBER;
BEGIN
    installment := CalculateMonthlyInstallment(1);
    DBMS_OUTPUT.PUT_LINE('Monthly Installment: ' || installment);
END;
/


/*
Monthly Installment: 116.45
*/
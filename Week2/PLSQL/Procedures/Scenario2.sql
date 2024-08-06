CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary NUMBER,
    Department VARCHAR2(50),
    HireDate DATE
);

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));
INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));

SELECT * FROM Employees;

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(dept IN VARCHAR2, bonus IN NUMBER) IS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * (bonus / 100))
    WHERE Department = dept; -- Corrected this line
    DBMS_OUTPUT.PUT_LINE('---------------------------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Updated Salaries for department: ' || dept);
    DBMS_OUTPUT.PUT_LINE('---------------------------------------------------------');
    
    COMMIT;
END;

BEGIN
    UpdateEmployeeBonus('IT', 10);
END;
/

SELECT * FROM Employees;

/*

 ACCOUNTID    BALANCE      LASTMODIF
---------- ----------      ---------
	 1	      70000           06-AUG-24
	 2	      60000           06-AUG-24

---------------------------------------------------------
Updated Salaries for department: 2 
---------------------------------------------------------

 ACCOUNTID    BALANCE LASTMODIF
---------- ---------- ---------
	 1	 70000   06-AUG-24
	 2	 66000   06-AUG-24

*/
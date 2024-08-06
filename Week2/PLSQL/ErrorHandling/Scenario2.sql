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

select EmployeeID,Name,Salary from Employees;

DECLARE
   id integer := 1;
   percentage integer := 10;
   curr_salary integer;
   new_salary integer;

BEGIN
  BEGIN
   SELECT Salary INTO curr_salary FROM Employees WHERE id = EmployeeID;
   
   new_salary := curr_salary + (curr_salary * percentage / 100);
   
   UPDATE Employees
   SET Salary = new_salary
   WHERE EmployeeID = id;
   
   

   dbms_output.put_line('----------------------------------------------');
   dbms_output.put_line('Salary Updated successfull!!!');
   dbms_output.put_line('----------------------------------------------');

EXCEPTION 
    WHEN no_data_found THEN
     ROLLBACK TO before_transfer;
     
      dbms_output.put_line('----------------------------------------------');
      DBMS_OUTPUT.PUT_LINE('EmployeeID: ' || id || ' is not available!!!');
      dbms_output.put_line('----------------------------------------------');
END;
   END;
/

select EmployeeID,Name,Salary from Employees;

/*
--- when EmployeeID to increase salary is present

Output:


EMPLOYEEID     NAME        SALARY

	1       Alice Johnson   70000

	2       Bob Brown       60000

----------------------------------------------
Salary Updated successfull!!!
----------------------------------------------

EMPLOYEEID     NAME        SALARY

	1       Alice Johnson   77000

	2       Bob Brown       60000



---when EmployeeID is not present

----------------------------------------------
EmployeeID: 3 is not available!!!
----------------------------------------------

/*
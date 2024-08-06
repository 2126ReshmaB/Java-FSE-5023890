CREATE TABLE Customers (
CustomerID NUMBER PRIMARY KEY,
Name VARCHAR2(100),
DOB DATE,
Balance NUMBER,
LastModified DATE
);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);

select CustomerID, Name, DOB, Balance, LastModified from Customers;

DECLARE
   cust_id NUMBER := 3;
   cust_Name varchar(20) := 'Abhi';
   cust_DOB DATE := SYSDATE;
   cust_Balance NUMBER := 1000;
   cust_lastModified DATE := SYSDATE;
   

BEGIN
  BEGIN
   INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) 
   VALUES (cust_id, cust_Name, cust_DOB, cust_Balance, cust_lastModified);
   
   

   dbms_output.put_line('----------------------------------------------');
   dbms_output.put_line('Customer Added successfull!!!');
   dbms_output.put_line('----------------------------------------------');

EXCEPTION 
     WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || cust_id || ' already exists. Insertion failed.');
     END;
   END;
/

select CustomerID, Name, DOB, Balance, LastModified from Customers;

/*

---when new customer is not present in table


CUSTOMERID    NAME      DOB	        BALANCE    LASTMODIFIED

	 1        John Doe    15-MAY-85	   1000      06-AUG-24
   2        Jane Smith  20-JUL-90	   1500      06-AUG-24

----------------------------------------------
Customer Added successfull!!!
----------------------------------------------

CUSTOMERID    NAME      DOB	        BALANCE    LASTMODIFIED

	 1        John Doe    15-MAY-85	   1000      06-AUG-24
   2        Jane Smith  20-JUL-90	   1500      06-AUG-24
   3        Abhi        06-AUG-24    1000      06-AUG-24


---when new customer already exists

Error: Customer ID 1 already exists. Insertion failed.

*/
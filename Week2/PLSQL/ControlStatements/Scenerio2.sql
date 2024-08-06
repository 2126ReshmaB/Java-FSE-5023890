
CREATE TABLE Customers (
CustomerID NUMBER PRIMARY KEY,
Name VARCHAR2(100),
DOB DATE,
Balance NUMBER,
LastModified DATE
);

ALTER TABLE Customers ADD isVIP VARCHAR(5);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified,isVIP) VALUES (1, 'John Doe', TO_DATE('1955-05-15', 'YYYY-MM-DD'), 1000, SYSDATE,"FALSE");
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified,isVIP) VALUES (2, 'Jane Smith', TO_DATE('1940-07-20', 'YYYY-MM-DD'), 1500, SYSDATE,"FALSE");
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified,isVIP) VALUES (3, 'Jhonny', TO_DATE('2000-07-20', 'YYYY-MM-DD'), 1300, SYSDATE,"FALSE");



SELECT customerID, Name, Balance, isVIP FROM Customers;
        
DECLARE
    CURSOR c_customers IS
        SELECT CustomerID, Balance
        FROM Customers;
BEGIN
    FOR r_customer IN c_customers LOOP
        IF r_customer.Balance > 1000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = r_customer.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/


SELECT customerID, Name, Balance, isVIP FROM Customers;




/*
Output:


CUSTOMERID   NAME     BALANCE   ISVIP
--------------------------------------------------------------------------------
	 1     John Doe      1000     FALSE

	 2     Jane Smith    1500     FALSE

	 3     Jhonny        1300     FALSE
--------------------------------------------------------------------------------
CUSTOMERID   NAME     BALANCE   ISVIP
--------------------------------------------------------------------------------
	 1     John Doe      1000     FALSE

	 2     Jane Smith    1500     TRUE

	 3     Jhonny        1300     TRUE
--------------------------------------------------------------------------------

*/

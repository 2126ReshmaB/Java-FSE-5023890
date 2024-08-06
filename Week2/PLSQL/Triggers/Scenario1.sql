CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE
);


INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) 
VALUES (1, 'John Doe', TO_DATE('1955-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) 
VALUES (2, 'Jane Smith', TO_DATE('1940-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);


CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE 
UPDATE
ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/


UPDATE Customers
SET Name = 'John Updated'
WHERE CustomerID = 1;


SELECT * FROM Customers WHERE CustomerID = 1;


/*
CustomerID  Name         DOB         Balance    LastModified
------------------------------------------------------------
1            John Doe    1955-05-15  1000       2024-08-06 12:00:00
2            Jane Smith  1940-07-20  1500       2024-08-06 12:00:00


CustomerID   Name            DOB        Balance   LastModified
--------------------------------------------------------------
1           John Updated    1955-05-15  1000    2024-08-06 22:41:00  -- (updated)
2           Jane Smith      1940-07-20  1500    2024-08-06 12:00:00

*/
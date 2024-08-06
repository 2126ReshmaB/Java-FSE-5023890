CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE
);

CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20),
    Balance NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (1, 'John Doe', TO_DATE('1955-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (2, 'Jane Smith', TO_DATE('1940-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (3, 'Johnny', TO_DATE('2000-07-20', 'YYYY-MM-DD'), 1300, SYSDATE);
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (1, 1, 'Savings', 2000, SYSDATE);
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (2, 2, 'Checking', 1500, SYSDATE);
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (3, 3, 'Savings', 3000, SYSDATE);

select AccountId,Balance, LastModified from Accounts;

DECLARE
   source_balance integer;
   target_balance integer;
   source_account_id integer := 1;
   target_account_id integer := 2;
   amount integer := 500;
BEGIN
   SAVEPOINT before_transfer;
   
   SELECT Balance INTO source_balance FROM Accounts WHERE source_account_id = AccountID;
   IF source_balance < amount THEN 
      RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in source account.');
   END IF;
   
   UPDATE Accounts
   SET Balance = Balance - amount,
   LastModified = SYSDATE
   WHERE AccountID = source_account_id;
   
      UPDATE Accounts
   SET Balance = Balance + amount,
   LastModified = SYSDATE
   WHERE AccountID = target_account_id;
   
   COMMIT;
   dbms_output.put_line('----------------------------------------------');
   dbms_output.put_line('Transaction successfull!!!');
   dbms_output.put_line('----------------------------------------------');
EXCEPTION 
    WHEN others THEN
     ROLLBACK TO before_transfer;
     
      dbms_output.put_line('----------------------------------------------');
      DBMS_OUTPUT.PUT_LINE('Error during transfer: ' || SQLERRM);
      dbms_output.put_line('----------------------------------------------');
END;
/

select AccountId,Balance, LastModified from Accounts;


/*
-- when Amount to be transfered is 500;

output:


 ACCOUNTID    BALANCE LASTMODIF
---------- ---------- ---------
	 1	 2000 06-AUG-24
	 2	 1500 06-AUG-24
	 3	 3000 06-AUG-24
----------------------------------------------
Transaction successfull!!!
----------------------------------------------

 ACCOUNTID    BALANCE LASTMODIF
---------- ---------- ---------
	 1	 1500 06-AUG-24
	 2	 2000 06-AUG-24
	 3	 3000 06-AUG-24

 ACCOUNTID    BALANCE LASTMODIF
---------- ---------- ---------
	 1	 1500 06-AUG-24
	 2	 2000 06-AUG-24
	 3	 3000 06-AUG-24

-- when Amount to be transfered is 500;

Output:


 ACCOUNTID    BALANCE LASTMODIF
---------- ---------- ---------
	 1	 2000 06-AUG-24
	 2	 1500 06-AUG-24
	 3	 3000 06-AUG-24
----------------------------------------------
Error during transfer: ORA-20001: Insufficient funds in source account.
----------------------------------------------

 ACCOUNTID    BALANCE LASTMODIF
---------- ---------- ---------
	 1	 2000 06-AUG-24
	 2	 1500 06-AUG-24
	 3	 3000 06-AUG-24

 ACCOUNTID    BALANCE LASTMODIF
---------- ---------- ---------
	 1	 2000 06-AUG-24
	 2	 1500 06-AUG-24
	 3	 3000 06-AUG-24
*/
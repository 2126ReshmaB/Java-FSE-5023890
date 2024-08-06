CREATE OR REPLACE PROCEDURE TransferFunds(
    source_account_id IN NUMBER,
    target_account_id IN NUMBER,
    amount IN NUMBER
) IS
    source_balance NUMBER;
BEGIN
    SAVEPOINT before_transfer;

    
    SELECT Balance INTO source_balance FROM Accounts WHERE AccountID = source_account_id;

    
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
    DBMS_OUTPUT.PUT_LINE('----------------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Transaction successful! Amount transferred: ' || amount);
    DBMS_OUTPUT.PUT_LINE('----------------------------------------------');
EXCEPTION 
    WHEN OTHERS THEN
        ROLLBACK TO before_transfer;
        DBMS_OUTPUT.PUT_LINE('----------------------------------------------');
        DBMS_OUTPUT.PUT_LINE('Error during transfer: ' || SQLERRM);
        DBMS_OUTPUT.PUT_LINE('----------------------------------------------');
END;
/


BEGIN
    TransferFunds(1, 2, 100);
END;
/


SELECT AccountID, Balance, LastModified FROM Accounts;

/*

----------------------------------------------
Transaction successful! Amount transferred: 100
----------------------------------------------

 ACCOUNTID    BALANCE LASTMODIF
---------- ---------- ---------
	 1	 1900 06-AUG-24
	 2	 1600 06-AUG-24
	 3	 3000 06-AUG-24


*/

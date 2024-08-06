CREATE OR REPLACE FUNCTION CalculateAge(dob IN DATE) RETURN NUMBER IS
    age NUMBER;
BEGIN
    age := FLOOR(MONTHS_BETWEEN(SYSDATE, dob) / 12);
    RETURN age;
END;
/

DECLARE
    customer_dob DATE := TO_DATE('1955-05-15', 'YYYY-MM-DD');
    customer_age NUMBER;
BEGIN
    customer_age := CalculateAge(customer_dob);
    DBMS_OUTPUT.PUT_LINE('Customer Age: ' || customer_age);
END;
/


/*

Output:

Customer Age: 69

*/
package EmployeeManagement;

import java.util.*;

class Employee {
  int empId;
  String Name;
  String position;
  int salary;

  public Employee(int empId, String Name, String position, int salary) {
    this.empId = empId;
    this.Name = Name;
    this.position = position;
    this.salary = salary;
  }
}

public class Main {
  public static void delete(Employee[] e, String s) {
    for (int j = 0; j < e.length; j++) {
      if (e[j].Name.equals(s)) {
        e[j] = new Employee(e[j].empId, "", "", e[j].salary);
        System.out.println("-------Deleted suceesfuuly----------");
        break;
      }
    }
  }

  public static void print(Employee e[], int x) {
    for (int j = 0; j < x; j++) {
      Employee i = e[j];
      if (!i.Name.equals("")) {
        System.out.println("---------------------------------");
        System.out.println(i.empId + " " + i.Name + " " + i.position + " " + i.salary);
        System.out.println("---------------------------------");
      }
    }
  }

  public static void search(Employee e[], String S) {
    for (Employee i : e) {
      if (i.Name.equals(S)) {
        System.out.println("---------------------------------");
        System.out.println(i.empId + " " + i.Name + " " + i.position + " " + i.salary);
        System.out.println("---------------------------------");
      }
    }
  }

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    Employee e[] = new Employee[2];
    while (true) {
      System.out.println("Enter 1 to add employee:");
      System.out.println("Enter 2 to delete employee");
      System.out.println("Enter 3 to print employees");
      System.out.println("Enter 4 to search:");

      int n = sc.nextInt();

      switch (n) {
        case 1:
          System.out.println("Enter the employees:");
          for (int i = 0; i < 2; i++) {
            e[i] = new Employee(sc.nextInt(), sc.next(), sc.next(), sc.nextInt());
          }
          break;
        case 2:
          System.out.println("Enter employee name to delete---");
          String s = sc.next();
          delete(e, s);
          break;
        case 3:
          print(e, 2);
          break;
        case 4:
          System.out.print("Enter employee Name to search:");
          String S = sc.next();
          search(e, S);
          break;
        default:
          break;
      }
    }
  }

}

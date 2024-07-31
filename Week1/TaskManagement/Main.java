package Week1.TaskManagement;

import java.util.*;

class Task {
  public int taskId;
  public String taskName;
  public String status;

  Task(int taskId, String taskName, String status) {
    this.taskId = taskId;
    this.taskName = taskName;
    this.status = status;
  }
}

class Node {
  Task p;
  Node next;

  Node(Task p, Node next) {
    this.p = p;
    this.next = next;
  }
}

public class Main {
  public static void print(Node head) {
    Node t1 = head;
    System.out.println("------------------------------");
    while (t1 != null) {
      System.out.println(t1.p.taskId + " " + t1.p.taskName + " " + t1.p.status);
      t1 = t1.next;
    }
    System.out.println("------------------------------");
  }

  public static void delete(Node head, String s) {
    Node t = head;
    if (t.p.taskName.equals(s)) {
      head = head.next;
    }
    while (t.next != null) {
      if (t.next.p.taskName.equals(s)) {
        t.next = t.next.next;
      }
      t = t.next;
    }
    System.out.println("-------------------Deleted successfully--------------");
  }

  public static void search(Node head, String S) {
    Node temp = head;
    System.out.println("------------------------------");
    while (temp != null) {
      if (temp.p.taskName.equals(S)) {
        System.out.println(temp.p.taskId + " " + temp.p.taskName + " " + temp.p.status);
        break;
      }
      temp = temp.next;
    }
    System.out.println("------------------------------");
  }

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    Node head = new Node(new Task(1, "clean", "done"), null);
    while (true) {
      System.out.print("1.Add  ");
      System.out.print("2.Search  ");
      System.out.print("3.Delete  ");
      System.out.print("4.print  ");
      int n = sc.nextInt();

      switch (n) {
        case 1:
          Task t = new Task(sc.nextInt(), sc.next(), sc.next());
          Node temp = head;
          while (temp.next != null) {
            temp = temp.next;
          }
          temp.next = new Node(t, null);
          break;
        case 2:
          System.out.println("Enter task to search:");
          String S = sc.next();
          search(head, S);

          break;
        case 3:
          System.out.println("Enter task to delete:");
          String s = sc.next();
          delete(head, s);
          break;
        case 4:
          print(head);
          break;

      }

    }
  }

}

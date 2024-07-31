package Week1.SortingCustomerOrders;

import java.util.*;

public class Main {
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);

    Sorting s = new Sorting();

    Orders p1 = new Orders(1,"table",120);
    Orders p2 = new Orders(2,"book",50);
    Orders p3 = new Orders(3,"iphone",60);
    Orders p4 = new Orders(4,"cookie",700);
    Orders p5 = new Orders(5,"ring",150);

    s.addProduct(p1,0);
    s.addProduct(p2,1);
    s.addProduct(p3,2);
    s.addProduct(p4,3);
    s.addProduct(p5,4);

    System.out.println("----bubble sort----------");
    s.bubbleSort();
    System.out.println("----Quick sort----------");
    s.quicksort();

  }
}

package Week1.ECommerce;

import java.util.*;
public class Main{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);

    Sorting s = new Sorting();

    Product p1 = new Product(1,"table","Furniture");
    Product p2 = new Product(2,"book","Books");
    Product p3 = new Product(3,"iphone","Mobile");
    Product p4 = new Product(4,"cookie","Food");
    Product p5 = new Product(5,"ring","jwellery");
    
    s.addProduct(p1);
    s.addProduct(p2);
    s.addProduct(p3);
    s.addProduct(p4);
    s.addProduct(p5);

    System.out.println("Enter the product name to be searched: ");
    String search = sc.nextLine();
    System.out.println("------------Linear search-------------");
    s.linearSearch(search);
    System.out.println("------------Binary search-------------");
    s.binarySearch(search);

  }
}

import java.util.*;

public class Main {
  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);

    Inventary in = new Inventary();

    while (true) {
      System.out.println("Enter 1 to add product -->");
      System.out.println("Enter 2 to delete product -->");
      System.out.println("Enter 3 to update product -->");
      System.out.println("Enter 4 to display products -->");

      int n = sc.nextInt();
      switch (n) {
        case 1:
          System.out.println("---------------------------");
          System.out.println("Enter product Id: ");
          int pid = sc.nextInt();
          System.out.println("Enter product Name: ");
          String pname = sc.next();
          System.out.println("Enter product Price: ");
          int pprice = sc.nextInt();
          System.out.println("Enter product quantity: ");
          int quan = sc.nextInt();
          System.out.println("---------------------------");

          Product p = new Product(pid, pname, pprice, quan);
          in.addProduct(p);
          break;

        case 2:
          System.out.println("---------------------------");
          System.out.println("Enter product Id to delete:");
          int d = sc.nextInt();
          in.deleteProduct(d);
          System.out.println("-----------Deleted successfull------------");
          break;

        case 3:
          System.out.println("---------------------------");
          System.out.println("Enter product details again to Update:");
          System.out.println("---------------------------");
          System.out.println("Enter product Id to update: ");
          int pid1 = sc.nextInt();
          System.out.println("Enter 1 to update product Name: ");
          System.out.println("Enter 2 to update product Price: ");
          System.out.println("Enter 3 to update product Quantity: ");
          int n1 = sc.nextInt();
          sc.nextLine();

          if (n1 == 1) {
            String pname1 = sc.nextLine();
            in.updateName(pid1, pname1);
          } else if (n1 == 2) {
            int pprice1 = sc.nextInt();
            in.updatePrice(pid1, pprice1);
          } else {
            int pquan1 = sc.nextInt();
            in.updateQuantity(pid1, pquan1);
          }

          System.out.println("-----------Updated successfull------------");
          break;

        case 4:
          System.out.println("---------------------------");
          in.printProduct();
          System.out.println("---------------------------");
          break;

        default:
          break;
      }
    }
  }
}
package LibraryManagement;

import java.util.*;
class Book{
  int bookId;
  String title;
  String author;
  Book(int bookId,String title,String author){
    this.bookId = bookId;
    this.title = title;
    this.author = author;
  }
}
public class Main {
  public static void linearSearch(List<Book> book,String s){
    System.out.println("-------------Linear Search-------------");
    for(Book b : book){
      if(b.title.equals(s)){
        System.out.println(b.bookId+" "+b.title+" "+b.author);
      }
    }
    System.out.println("-------------------------------------");
  }
  public static void binarySearch(List<Book> book,String s){
    int l = 0,r = book.size()-1;
    System.out.println("-----------Binary Search-------------");
    while(l <= r){
      int mid = (l+r)/2;
      int x = s.compareTo(book.get(mid).title);
      if(x == 0){
        System.out.println(book.get(mid).bookId+" "+book.get(mid).title+" "+book.get(mid).author);
        break;
      }
      else if(x > 0){
        l = mid + 1;
      }
      else{
        r = mid - 1;
      }
    }
    System.out.println("-------------------------------------");
  }
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);

    List<Book> book = new ArrayList<>();
    Book b1 = new Book(1,"Alchemist","charles dickens");
    Book b2 = new Book(2,"Good Minds","abc");
    Book b3 = new Book(3,"Oliver Twist","def");
    Book b4 = new Book(4,"Who are U?","ghi");
    book.add(b1);book.add(b2);book.add(b3);book.add(b4);

    System.out.println("Enter book title to search:");
    String s = sc.next();
    linearSearch(book,s);
    binarySearch(book, s);
  }
}

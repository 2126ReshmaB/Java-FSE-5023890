package Week1.ECommerce;

import java.util.*;
public class Searching{
  public ArrayList<Product> product;

  public Searching(){
    product = new ArrayList<>();
  }
  public void addProduct(Product p) {
    product.add(p);
  }
  int found = 0;
  public void linearSearch(String s){
    for(Product p : product){
      if(p.getProdName().equals(s)){
         print(p);
         found = 1;
      }
    }
    if(found == 0){
      System.out.println("No items found :)");
    }
  }
  int f = 0;
  public void binarySearch(String s){
    int l = 0,r = product.size()-1;
    while(l <= r){
      int mid = (l + r) / 2;
      int x = s.compareTo(product.get(mid).getProdName());
      if(x == 0){
        print(product.get(mid));
        f = 1;
        break;
      }
      else if(x > 0){
        l = mid + 1;
      }
      else{
        r = mid - 1;
      }
    }
    if(f == 0){
      System.out.println("No Item found :)");
    }
  }
  public void print(Product p){
    System.out.println("--------------------------");
    System.out.println("Product ID:"+p.getProdId()+" Product Name:"+p.getProdName()+" Category:"+p.getCategory());
    System.out.println("--------------------------");

  }
}

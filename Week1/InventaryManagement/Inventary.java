import java.util.HashMap;
import java.util.Map.Entry;

public class Inventary {
  public HashMap<Integer, Product> product;

  public Inventary() {
    product = new HashMap<>();
  }

  public void addProduct(Product p) {
    product.put(p.getProdId(), p);
  }

  public void deleteProduct(int id) {
    product.remove(id);
  }

  public void updateName(int id, String name) {
    product.put(id, new Product(id, name, product.get(id).getProdPrice(), product.get(id).getQuantity()));
  }

  public void updatePrice(int id, int price) {
    product.put(id, new Product(id, product.get(id).getProdName(), price, product.get(id).getQuantity()));
  }

  public void updateQuantity(int id, int quantity) {
    product.put(id, new Product(id, product.get(id).getProdName(), product.get(id).getProdPrice(), quantity));
  }

  public void printProduct() {
    for (Entry<Integer, Product> i : product.entrySet()) {
      System.out.println("ProductID: " + i.getKey() + " ProductName: " + i.getValue().getProdName() + " ProductPrice: "
          + i.getValue().getProdPrice() + " quantity: " + i.getValue().getQuantity());
    }
  }
}

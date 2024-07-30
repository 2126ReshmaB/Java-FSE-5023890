public class Product {
  private int prodId;
  private String prodName;
  private int prodPrice;
  private int quantity;

  public Product(int prodId, String prodName, int prodPrice, int quantity) {
    this.prodId = prodId;
    this.prodName = prodName;
    this.prodPrice = prodPrice;
    this.quantity = quantity;
  }

  public int getProdId() {
    return prodId;
  }

  public String getProdName() {
    return prodName;
  }

  public int getProdPrice() {
    return prodPrice;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setProdId(int prodId) {
    this.prodId = prodId;
  }

  public void setProdName(String prodName) {
    this.prodName = prodName;
  }

  public void setProdPrice(int prodPrice) {
    this.prodPrice = prodPrice;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}

package ECommerce;

public class Product{
  private int prodId;
  private String prodName;
  private String category;

  public Product(int prodId,String prodName,String category){
    this.prodId = prodId;
    this.prodName = prodName;
    this.category = category;
  }
  public int getProdId() {
    return prodId;
  }

  public String getProdName() {
    return prodName;
  }
  public String getCategory(){
    return category;
  }
  public void setProdId(int prodId) {
    this.prodId = prodId;
  }

  public void setProdName(String prodName) {
    this.prodName = prodName;
  }
  public void setCategory(String category){
    this.category = category;
  }
  public int compareTo(String s) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
  }

}
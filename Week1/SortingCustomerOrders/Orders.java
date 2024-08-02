package SortingCustomerOrders;

public class Orders {
  private int orderId;
  private String custName;
  private int price;
  public Orders(int orderId,String custName,int price){
    this.orderId = orderId;
    this.custName = custName;
    this.price = price;
  }

  public int getOrderId(){
    return orderId;
  } 
  public String getcustName(){
    return custName;
  }
  public int getprice(){
    return price;
  }
  public void setOrderId(int orderId){
    this.orderId = orderId;
  }
  public void setCustName(String custName){
    this.custName = custName;
  }
  public void setPrice(int price){
    this.price = price;
  }

}

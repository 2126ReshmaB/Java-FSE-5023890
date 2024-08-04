package BuilderDP;

public class Builder {
  public static void main(String args[]){
    // Computer c = new Computer("Intel Core i5-1135G7", "8GB LPDDR4x", "256GB SSD");
    System.out.println("----------------------------------------");
    Computer c = new ComputerBuilder().setCpu("Intel Core i5-1135G7").getComputer();
    System.out.println(c.toString());
    System.out.println("----------------------------------------");
  }
}
/* 

when we use normal constructor there should be proper datatype in correct order and every time we need to give all attributes
so builderpattern is used to give values only what we want.

 javac BuilderDP/*.java 
 java BuilderDP.Builder 



 */
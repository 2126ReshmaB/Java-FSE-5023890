package SingletonDP;

public class Singleton {
  public static void main(String args[]){
    Logger user1 = Logger.getInstance();
    Logger user2 = Logger.getInstance();

    user1.log("First user");
    user2.log("second user");

    if (user1 == user2) {
      System.out.println("Both logger1 and logger2 are the same instance.");
  } else {
      System.out.println("logger1 and logger2 are different instances.");
  }
  }
}

package StrategyDP;

interface PaymentStrategy {
  void pay(double amount);
}

// Concrete Strategy: CreditCardPayment
class CreditCardPayment implements PaymentStrategy {
  private String cardNumber;
  private String cardHolderName;
  private String cvv;
  private String expiryDate;

  public CreditCardPayment(String cardNumber, String cardHolderName, String cvv, String expiryDate) {
      this.cardNumber = cardNumber;
      this.cardHolderName = cardHolderName;
      this.cvv = cvv;
      this.expiryDate = expiryDate;
  }

  @Override
  public void pay(double amount) {
      System.out.println("Paid $" + amount + " using Credit Card.");
  }
}

// Concrete Strategy: PayPalPayment
class PayPalPayment implements PaymentStrategy {
  private String email;
  private String password;

  public PayPalPayment(String email, String password) {
      this.email = email;
      this.password = password;
  }

  @Override
  public void pay(double amount) {
      System.out.println("Paid $" + amount + " using PayPal.");
  }
}

// Context Class
class PaymentContext {
  private PaymentStrategy paymentStrategy;

  public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
      this.paymentStrategy = paymentStrategy;
  }

  public void executePayment(double amount) {
      paymentStrategy.pay(amount);
  }
}

// Test Class
public class StrategyPatternExample {
  public static void main(String[] args) {
      PaymentContext paymentContext = new PaymentContext();

      // Pay using Credit Card
      PaymentStrategy creditCardPayment = new CreditCardPayment("1234567890123456", "John Doe", "123", "12/25");
      paymentContext.setPaymentStrategy(creditCardPayment);
      paymentContext.executePayment(100.00);

      // Pay using PayPal
      PaymentStrategy payPalPayment = new PayPalPayment("john.doe@example.com", "password");
      paymentContext.setPaymentStrategy(payPalPayment);
      paymentContext.executePayment(200.00);
  }
}

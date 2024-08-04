package AdapterDP;

public class PayPalAdapter implements PaymentProcessor {
  private Paypal paypal;

  public PayPalAdapter(Paypal paypal) {
      this.paypal = paypal;
  }

  @Override
  public void processPayment(double amount) {
      paypal.sendPayment(amount);
  }
}

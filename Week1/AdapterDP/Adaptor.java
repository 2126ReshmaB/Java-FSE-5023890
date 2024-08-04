package AdapterDP;

public class Adaptor {
  public static void main(String args[]){
        Paypal payPal = new Paypal();
        Stripe stripe = new Stripe();
        Square square = new Square();

        // Creating adapter instances
        PaymentProcessor payPalProcessor = new PayPalAdapter(payPal);
        PaymentProcessor stripeProcessor = new StripeAdapter(stripe);
        PaymentProcessor squareProcessor = new SquareAdapter(square);

        // Processing payments through adapters
        payPalProcessor.processPayment(100.0);
        stripeProcessor.processPayment(200.0);
        squareProcessor.processPayment(300.0);
  }
}


/*
 ex: charger 

_____________
|           |
|    tool   |            ------      ADAPTOR (converts to JSON)   -------     xml file
|___________|
 supports only JSON format


 javac AdapterDP/*.java
 java AdapterDP.Adaptor
 
 */

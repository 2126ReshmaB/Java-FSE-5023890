package DecoratorDP;

// Notifier interface
interface Notifier {
  void send(String message);
}

// EmailNotifier class
class EmailNotifier implements Notifier {
  @Override
  public void send(String message) {
      System.out.println("Sending email notification: " + message);
  }
}

// Abstract Decorator class
abstract class NotifierDecorator implements Notifier {
  protected Notifier wrappedNotifier;

  public NotifierDecorator(Notifier notifier) {
      this.wrappedNotifier = notifier;
  }

  @Override
  public void send(String message) {
      wrappedNotifier.send(message);
  }
}

// SMSNotifierDecorator class
class SMSNotifierDecorator extends NotifierDecorator {
  public SMSNotifierDecorator(Notifier notifier) {
      super(notifier);
  }

  @Override
  public void send(String message) {
      super.send(message);
      sendSMS(message);
  }

  private void sendSMS(String message) {
      System.out.println("Sending SMS notification: " + message);
  }
}

// SlackNotifierDecorator class
class SlackNotifierDecorator extends NotifierDecorator {
  public SlackNotifierDecorator(Notifier notifier) {
      super(notifier);
  }

  @Override
  public void send(String message) {
      super.send(message);
      sendSlackMessage(message);
  }

  private void sendSlackMessage(String message) {
      System.out.println("Sending Slack notification: " + message);
  }
}

// LoggedNotificationDecorator class
class LoggedNotificationDecorator extends NotifierDecorator {
  public LoggedNotificationDecorator(Notifier notifier) {
      super(notifier);
  }

  @Override
  public void send(String message) {
      log(message);
      super.send(message);
  }

  private void log(String message) {
      System.out.println("Logging: " + message);
  }
}

// EncryptedNotificationDecorator class
class EncryptedNotificationDecorator extends NotifierDecorator {
  public EncryptedNotificationDecorator(Notifier notifier) {
      super(notifier);
  }

  @Override
  public void send(String message) {
      String encryptedMessage = encrypt(message);
      super.send(encryptedMessage);
  }

  private String encrypt(String message) {
      // Simple encryption logic for demonstration (you would use a proper encryption algorithm)
      return "encrypted(" + message + ")";
  }
}

// Test class to demonstrate the Decorator Pattern
public class DecoratorPatternExample {
  public static void main(String[] args) {
      // Creating a basic email notifier
      Notifier emailNotifier = new EmailNotifier();

      // Enhancing the email notifier with SMS functionality
      Notifier smsNotifier = new SMSNotifierDecorator(emailNotifier);

      // Further enhancing the notifier with Slack functionality
      Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);

      // Adding logging functionality
      Notifier loggedNotifier = new LoggedNotificationDecorator(slackNotifier);

      // Adding encryption functionality
      Notifier encryptedNotifier = new EncryptedNotificationDecorator(loggedNotifier);

      // Sending a notification via email, SMS, Slack, with logging and encryption
      encryptedNotifier.send("Hello, User!");
  }
}

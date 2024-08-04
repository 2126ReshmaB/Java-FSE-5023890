package ObserverDP;

import java.util.ArrayList;
import java.util.List;

// Subject Interface
interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}

// Concrete Subject
class StockMarket implements Stock {
    private List<Observer> observers;
    private String stockName;
    private double stockPrice;

    public StockMarket(String stockName, double stockPrice) {
        this.observers = new ArrayList<>();
        this.stockName = stockName;
        this.stockPrice = stockPrice;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, stockPrice);
        }
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }
}

// Observer Interface
interface Observer {
    void update(String stockName, double stockPrice);
}

// Concrete Observer: MobileApp
class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockName, double stockPrice) {
        System.out.println(appName + " received notification: " + stockName + " stock price updated to $" + stockPrice);
    }
}

// Concrete Observer: WebApp
class WebApp implements Observer {
    private String appName;

    public WebApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockName, double stockPrice) {
        System.out.println(appName + " received notification: " + stockName + " stock price updated to $" + stockPrice);
    }
}

// Test class
public class ObserverPattern
{
    public static void main(String[] args) {
        // Create stock market and observers
        StockMarket googleStock = new StockMarket("Google", 1500.00);

        Observer mobileApp1 = new MobileApp("MobileApp1");
        Observer webApp1 = new WebApp("WebApp1");

        // Register observers
        googleStock.registerObserver(mobileApp1);
        googleStock.registerObserver(webApp1);

        // Change stock price and notify observers
        googleStock.setStockPrice(1520.00);

        // Deregister an observer and update stock price
        googleStock.deregisterObserver(webApp1);
        googleStock.setStockPrice(1550.00);
    }
}


package Week1.Financialforcasting;

public class Main {
  public static void main(String[] args) {
    double intialValue = 1000.0;
    double growthRate = 0.05;
    int years = 5;

    double futureValue = predictedValue(intialValue,growthRate,years);
    System.out.println("The Predicted Vlaue is:"+futureValue);
  }
  public static double predictedValue(double init,double growth,int y){
    if(y == 0){
      return init;
    }
    double nextValue = init * (1 + growth);
    return predictedValue(nextValue, growth, y-1);
  }
}

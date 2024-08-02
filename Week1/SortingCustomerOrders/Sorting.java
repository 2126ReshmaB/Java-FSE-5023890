package SortingCustomerOrders;

public class Sorting {
  public Orders[] order;

  public Sorting() {
    order = new Orders[5];
  }

  public void addProduct(Orders p, int c) {
    order[c] = p;
  }

  public void bubbleSort() {
    for (int i = 0; i < order.length; i++) {
      for (int j = 0; j < order.length - i - 1; j++) {
        if (order[j].getprice() > order[j + 1].getprice()) {
          Orders t = order[j];
          order[j] = order[j + 1];
          order[j + 1] = t;
        }
      }
    }
    print(order);
  }

  public void quicksort() {
    solve(order, 0, 4);
    print(order);
  }

  public void solve(Orders order[], int i, int j) {
    if (i < j) {
      int pi = find(order, i, j);

      solve(order, i, pi - 1);
      solve(order, pi + 1, j);
    }
  }

  public int find(Orders order[], int i, int j) {
    int pivot = order[j].getprice();
    int l = i - 1;
    for (int r = i; r <= j - 1; r++) {
      if (pivot > order[r].getprice()) {
        l++;
        Orders t = order[l];
        order[l] = order[r];
        order[r] = t;
      }
    }
    Orders t = order[l + 1];
    order[l + 1] = order[j];
    order[j] = t;
    return l + 1;
  }

  public void print(Orders[] order) {
    System.out.print("[");
    for (int i = 0; i < 5; i++) {
      System.out.print(order[i].getprice() + ",");
    }
    System.out.println("]");
  }

}

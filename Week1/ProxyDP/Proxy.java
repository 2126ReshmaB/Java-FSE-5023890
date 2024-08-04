interface Image {
  void display();
}

// Real Subject Class
class RealImage implements Image {
  private String fileName;

  public RealImage(String fileName) {
      this.fileName = fileName;
      loadFromRemoteServer();
  }

  private void loadFromRemoteServer() {
      System.out.println("Loading " + fileName + " from remote server...");
  }

  @Override
  public void display() {
      System.out.println("Displaying " + fileName);
  }
}

// Proxy Class
class ProxyImage implements Image {
  private String fileName;
  private RealImage realImage;

  public ProxyImage(String fileName) {
      this.fileName = fileName;
  }

  @Override
  public void display() {
      if (realImage == null) {
          realImage = new RealImage(fileName);
      }
      realImage.display();
  }
}

// Test Class
public class Proxy{
  public static void main(String[] args) {
      Image image1 = new ProxyImage("photo1.jpg");
      Image image2 = new ProxyImage("photo2.jpg");

      // The first call to display() will load the image from the remote server
      image1.display();

      // The second call to display() will use the cached image
      image1.display();

      // The first call to display() will load the image from the remote server
      image2.display();
  }
}

package BuilderDP;

public class Computer {
  private String cpu;
  private String ram;
  private String storage;

  public Computer(String cpu,String ram,String storage){
    this.cpu = cpu;
    this.ram = ram;
    this.storage = storage;
  }

  public String toString(){
    return "Computer: "+cpu+" "+ram+" "+storage;
  }
}

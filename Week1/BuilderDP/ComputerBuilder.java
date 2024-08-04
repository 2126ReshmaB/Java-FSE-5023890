package BuilderDP;

public class ComputerBuilder {
  private String cpu;
  private String ram;
  private String storage;

  public ComputerBuilder setCpu(String cpu){
    this.cpu = cpu;
    return this;
  }
  public ComputerBuilder setRam(String ram){
    this.ram = ram;
    return this;
  }
  public ComputerBuilder setStorage(String storage){
    this.storage = storage;
    return this;
  }
  public Computer getComputer(){
    return new Computer(cpu,ram,storage);
  }
}

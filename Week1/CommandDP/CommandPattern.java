package CommandDP;

// Command Interface
interface Command {
  void execute();
}

// Receiver Class
class Light {
  public void turnOn() {
      System.out.println("The light is on.");
  }

  public void turnOff() {
      System.out.println("The light is off.");
  }
}

// Concrete Command: LightOnCommand
class LightOnCommand implements Command {
  private Light light;

  public LightOnCommand(Light light) {
      this.light = light;
  }

  @Override
  public void execute() {
      light.turnOn();
  }
}

// Concrete Command: LightOffCommand
class LightOffCommand implements Command {
  private Light light;

  public LightOffCommand(Light light) {
      this.light = light;
  }

  @Override
  public void execute() {
      light.turnOff();
  }
}

// Invoker Class
class RemoteControl {
  private Command command;

  public void setCommand(Command command) {
      this.command = command;
  }

  public void pressButton() {
      command.execute();
  }
}

// Test Class
public class CommandPattern {
  public static void main(String[] args) {
      // Receiver
      Light livingRoomLight = new Light();

      // Concrete Commands
      Command lightOn = new LightOnCommand(livingRoomLight);
      Command lightOff = new LightOffCommand(livingRoomLight);

      // Invoker
      RemoteControl remote = new RemoteControl();

      // Turn on the light
      remote.setCommand(lightOn);
      remote.pressButton();

      // Turn off the light
      remote.setCommand(lightOff);
      remote.pressButton();
  }
}

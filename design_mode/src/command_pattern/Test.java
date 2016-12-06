package command_pattern;

public class Test {

	public static void main(String[] args){
		Light light = new  Light();
		Command command = new LightOnCommand(light);
		SimpleRemoteControl src = new  SimpleRemoteControl(command);
		src.buttonWasPressed();
	}
}

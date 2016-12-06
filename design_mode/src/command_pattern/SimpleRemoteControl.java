package command_pattern;

public class SimpleRemoteControl {

	Command command;
	SimpleRemoteControl(){
		
	}
	SimpleRemoteControl(Command command){
		this.command = command;
	}
	
	public void buttonWasPressed(){
		command.excute();
	}
}

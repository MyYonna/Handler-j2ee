package command_pattern;
/**
 * 将命令的执行者置于命令中，命令的调用者调用excute方法后会执行命令执行者的方法
 * 命令对象将动作和接收者包进对象中。定义了动作和接收者之间的绑定关系
 * @author zhangpeng
 *
 */
public class LightOnCommand implements Command {

	Light light;
	
	public LightOnCommand(Light light){
		this.light = light;
	}
	@Override
	public void excute() {
		// TODO Auto-generated method stub
		light.lightOn();
	}

}

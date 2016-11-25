package strategy_pattern;

//定义了鸭子飞行的接口，并在其实现类中进行单独的封装，让他们之间可以相互替换
public interface FlyBehavior {

	public void fly();
}

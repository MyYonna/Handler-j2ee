package strategy_pattern;

public class Test {
	//策略模式：定义了算法族，分别封装起来，让它们之间可以相互替换，此模式让算法的变化独立于使用算法的客户
	//FlyBehavior 定义了算法族，其子类进行实现并可以相互替换
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Duck duck = new RedheadDuck();
		FlyBehavior fb = new FlyWithWings();
		duck.setFb(fb);
		duck.display();
		duck.swim();
		duck.performFly();
		//动态地改变行为
		fb = new FlyWithNoWings();
		duck.setFb(fb);
		duck.performFly();
	}

}

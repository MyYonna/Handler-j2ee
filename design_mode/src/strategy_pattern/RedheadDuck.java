package strategy_pattern;

public class RedheadDuck extends Duck {

	public RedheadDuck() {
		super();
		//不要在客户类中绑定实现类，这样不利于解耦
		//this.fb = fb;
	}

	@Override
	public void performFly() {
		// TODO Auto-generated method stub

		fb.fly();
	}
	
}

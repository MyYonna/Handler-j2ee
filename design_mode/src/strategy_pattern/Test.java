package strategy_pattern;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Duck duck = new RedheadDuck();
		FlyBehavior fb = new FlyWithWings();
		duck.setFb(fb);
		duck.display();
		duck.swim();
		duck.performFly();
		//��̬�ظı���Ϊ
		fb = new FlyWithNoWings();
		duck.setFb(fb);
		duck.performFly();
	}

}

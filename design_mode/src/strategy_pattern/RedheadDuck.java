package strategy_pattern;

public class RedheadDuck extends Duck {

	public RedheadDuck() {
		super();
		//��Ҫ�ڿͻ����а�ʵ���࣬���������ڽ���
		//this.fb = fb;
	}

	@Override
	public void performFly() {
		// TODO Auto-generated method stub

		fb.fly();
	}
	
}

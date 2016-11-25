package strategy_pattern;

public class Test {
	//����ģʽ���������㷨�壬�ֱ��װ������������֮������໥�滻����ģʽ���㷨�ı仯������ʹ���㷨�Ŀͻ�
	//FlyBehavior �������㷨�壬���������ʵ�ֲ������໥�滻
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

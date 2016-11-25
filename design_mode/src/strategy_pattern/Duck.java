package strategy_pattern;

public abstract class Duck {

	//����ӿڱ�̣�����������ʱ��̬�ظı�������Ϊ
	//������ϣ����ü̳�
	FlyBehavior fb;
		
	public void swim(){
		System.out.println("i can swimming...");
	}
	
	public void display(){
		System.out.println("i am a duck");
	}
	public FlyBehavior getFb() {
		return fb;
	}
	public void setFb(FlyBehavior fb) {
		this.fb = fb;
	}
	//��ȡ���仯�Ĳ��ֲ����е�����װ
	public abstract void performFly();
	
}

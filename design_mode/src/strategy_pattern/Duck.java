package strategy_pattern;

public abstract class Duck {

	//面向接口编程，可以在运行时动态地改变它的行为
	//多用组合，少用继承
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
	//提取出变化的部分并进行单独封装
	public abstract void performFly();
	
}

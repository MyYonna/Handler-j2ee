package decorator_pattern;
/**
 * 在io包里面 FilterInputStream 类做为抽象装饰者来装饰InputStream 抽象被装饰者
 *  装饰者模式：动态地将责任附加到对象上。若要扩展功能，装饰者提供了比继承更具弹性的替代方案。
 * 装饰者模式是开放-闭合原则的体现
 * @author zhangpeng
 *
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//饮料作为被装饰者
		Beverage beverage = new Espresso();
		//调料作为装饰者
		Beverage beverage1 = new Mocha(beverage);
		System.out.println(beverage1.getDescription()+":"+beverage1.cost());
	
		//装饰后的对象再次作为被装饰者传入装饰器
		beverage1 = new Mocha(beverage1);
		System.out.println(beverage1.getDescription()+":"+beverage1.cost());
	}

}

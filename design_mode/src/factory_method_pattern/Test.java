package factory_method_pattern;
/**
 * 工厂方法用来处理对象的创建，并将这样的行为封装在子类中
 * 工厂方法必须返回一个产品。超类中定义的方法通常使用到工厂方法的返回值
 * 将客户和实际创建的具体产品的代码分隔开来
 * 
 * 所有工厂模式都是用来封装对象的创建
 * 工厂方法模式通过让子类决定该创建的对象是什么来达到。
 * 选择了使用哪种子类，自然就决定了实际创建的产品是什么
 * @author zhangpeng
 *
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//PizzaFactory pizzaFactory = new NYPizzaFactory();
		//提供了一种 默认的创建pizza的工厂
		PizzaStore ps = new NYPizzaStore();
		ps.orderPizza("cheese");
	}

}

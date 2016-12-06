package simple_factory_pattern;

/**
 * 简单工厂只是一种编程习惯，并不能作为一种设计模式,他只是对对象创建的一种封装
 * @author zhangpeng
 *
 */
public class PizzaStore {

	SimplePizzaFactory factory;
	PizzaStore(SimplePizzaFactory factory){
		this.factory = factory;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//我们常把工厂定义成静态的
		SimplePizzaFactory factory = new SimplePizzaFactory();
		PizzaStore ps = new PizzaStore(factory);
		ps.orderPizza("cheese");
		ps.orderPizza("clam");
	}

	public Pizza orderPizza(String type){
		Pizza pizza;
		pizza = factory.createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}

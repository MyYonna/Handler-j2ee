package factory_method_pattern;

/**
 * 将对象的创建交给其子类，而对象的使用却牢牢的控制在自己的手中
 * @author zhangpeng
 *
 */
public abstract class PizzaStore {

	PizzaFactory pizzaFactory = new SimplePizzaFactory();
	
	public final Pizza orderPizza(String type){
		Pizza pizza;
		pizza = createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
	/**
	 * 工厂方法
	 */
	public abstract Pizza createPizza(String type);
}

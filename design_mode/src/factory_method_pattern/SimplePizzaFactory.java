package factory_method_pattern;

public class SimplePizzaFactory implements PizzaFactory{

	public Pizza createPizza(String type){
		Pizza pizza = null;
		if(type.equals("cheese")){
			pizza = new CheesePizza();
		}else if(type.equals("clam")){
			pizza = new ClamPizza();
		}
		return pizza;
	}
}

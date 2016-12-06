package factory_method_pattern;


public class NYPizzaFactory implements PizzaFactory {

	@Override
	public Pizza createPizza(String type) {
		// TODO Auto-generated method stub
		Pizza pizza = null;
		if(type.equals("cheese")){
			pizza = new NYCheesePizza();
		}else if(type.equals("clam")){
			pizza = new NYClamPizza();
		}
		return pizza;
	}

}

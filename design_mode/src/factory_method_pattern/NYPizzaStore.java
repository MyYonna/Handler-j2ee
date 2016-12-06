package factory_method_pattern;


public class NYPizzaStore extends PizzaStore {

	NYPizzaStore(){
		
	}
	NYPizzaStore(PizzaFactory pizzaFactory){
		this.pizzaFactory =  pizzaFactory;
	}
	@Override
	public Pizza createPizza(String type) {
		// TODO Auto-generated method stub
		return pizzaFactory.createPizza(type);
	}

}

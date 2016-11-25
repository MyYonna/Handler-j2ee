package decorator_pattern;
/**
 * 具体的装饰者
 * @author zhangpeng
 *
 */
public class Mocha extends CondimentDecorator {

	Beverage beverage;
	
	
	public Mocha(Beverage beverage) {
		super();
		// TODO Auto-generated constructor stub
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return beverage.getDescription() + ",Mocha";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 20 + beverage.cost();
	}

}

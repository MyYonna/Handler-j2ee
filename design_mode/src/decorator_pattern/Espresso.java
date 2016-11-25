package decorator_pattern;
/**
 * 具体的呗装饰者，即具体组件
 * @author zhangpeng
 *
 */
public class Espresso extends Beverage {

	
	public Espresso() {
		super();
		// TODO Auto-generated constructor stub
		description = "Espresso";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 1.99;
	}

}

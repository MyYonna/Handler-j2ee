package decorator_pattern;
/**
 * 具体的被装饰者（具体组件）
 * @author zhangpeng
 *
 */
public class HouseBlend extends Beverage {

	
	public HouseBlend() {
		super();
		// TODO Auto-generated constructor stub
		description = "House Blend Coffee";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 0.89;
	}

}

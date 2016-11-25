package decorator_pattern;
/**
 * 被装饰者的基类，即抽象组件
 * @author zhangpeng
 *
 */
public abstract class Beverage {

	String description  = "Unknow Beverage";

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public abstract double cost();
}

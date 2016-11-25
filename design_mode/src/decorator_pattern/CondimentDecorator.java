package decorator_pattern;
/**
 * 必须让condiment decorator能够取代Beverage,所以将condiment decorator 扩展自Beverage类
 * 装饰者的基类，装饰者必须和被装饰者拥有同样抽象行为，这个通过继承或者
 * @author zhangpeng
 *
 */
public abstract class CondimentDecorator extends Beverage {

public abstract String getDescription();
}

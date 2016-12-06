package simple_factory_pattern;

public class ClamPizza implements Pizza {

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		System.out.println("Clam 面包准备中。。。。");
	}

	@Override
	public void bake() {
		// TODO Auto-generated method stub
		System.out.println("Clam 面包烘焙中。。。。");
	}

	@Override
	public void cut() {
		// TODO Auto-generated method stub
		System.out.println("Clam 面包切片中。。。。");
	}

	@Override
	public void box() {
		// TODO Auto-generated method stub
		System.out.println("Clam 面包装盒中。。。。");
	}

}

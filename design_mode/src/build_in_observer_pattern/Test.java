package build_in_observer_pattern;

import java.util.Observer;

//观察者模式：定义了对象之间的一对多依赖，这样一来，当一个对象改变状态时，它的所有依赖者多会收到通知并自动更新
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeatherData wd = new WeatherData();
		Observer o1 = new CurrentConditionsDisplay();
		Observer o2 = new StatisticsDisplay();
		wd.addObserver(o1);
		wd.addObserver(o2);
		
		wd.setMeasurements(20, 30, 40);
		System.out.println("第一次结束。。。");
		//运行时改变对象的行为
		wd.deleteObserver(o2);
		wd.setMeasurements(10, 20, 60);
		System.out.println("第二次结束。。。");
		wd.deleteObservers();
		wd.setMeasurements(30, 40, 50);
		System.out.println("第三次结束。。。");
		
	}

}

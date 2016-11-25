package build_in_observer_pattern;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 * @author zhangpeng
 *
 */
public class CurrentConditionsDisplay implements DisplayElement, Observer {

	private float temperature;
	private float humidity;
	private Observable observable;
	@Override
	public void update(Observable observable,Object args) {
		// TODO Auto-generated method stub

		this.observable = observable;
		if(observable instanceof WeatherData){
			WeatherData wd = (WeatherData) observable;
			this.temperature = wd.getTemperature();
			this.humidity = wd.getHumidity();
			
		}
		display();
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("Current Conditioni:"+temperature +"F degrees and "+humidity +"% humidity");

	}

}

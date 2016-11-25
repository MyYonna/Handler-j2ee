package observer_pattern;
/**
 * 观察者
 * @author zhangpeng
 *
 */
public class StatisticsDisplay implements DisplayElement, Observer {

	private float temperature;
	private float humidity;
	private float pressure;
	private Subject weatherData;
	@Override
	public void update(Subject weatherData) {
		// TODO Auto-generated method stub

		this.weatherData = weatherData;
		if(weatherData instanceof WeatherData){
			WeatherData wd = (WeatherData) weatherData;
			this.temperature = wd.getTemperature();
			this.humidity = wd.getHumidity();
			this.pressure = wd.getPressure();
			
		}
		display();
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("Statistic Display:"+temperature +"F degrees and "+humidity +"% humidity and press "+pressure+"Mpa");

	}

	public void cancelRegister(){
		weatherData.removeObserver(this);
	}
}

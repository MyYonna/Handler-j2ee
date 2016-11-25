package observer_pattern;
/**
 * π€≤Ï’ﬂ
 * @author zhangpeng
 *
 */
public class CurrentConditionsDisplay implements DisplayElement, Observer {

	private float temperature;
	private float humidity;
	private Subject weatherData;
	@Override
	public void update(Subject weatherData) {
		// TODO Auto-generated method stub

		this.weatherData = weatherData;
		if(weatherData instanceof WeatherData){
			WeatherData wd = (WeatherData) weatherData;
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
	public void cancelRegister(){
		weatherData.removeObserver(this);
	}

}

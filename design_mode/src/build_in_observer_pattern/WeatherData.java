package build_in_observer_pattern;

import java.util.Observable;
/**
 * 主题
 * @author zhangpeng
 *
 */
public class WeatherData extends Observable {

	private float temperature;
	private float humidity;
	private float pressure;
	
	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public float getPressure() {
		return pressure;
	}

	public void setPressure(float pressure) {
		this.pressure = pressure;
	}


	
	public void measurementsChanged(){
		notifyObservers();
	}

	public void setMeasurements(float temperature,float humidity,float pressure){
		this.pressure = pressure;
		this.temperature = temperature;
		this.humidity = humidity;
		this.setChanged();
		measurementsChanged();
	}
}

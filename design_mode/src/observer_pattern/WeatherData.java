package observer_pattern;

import java.util.ArrayList;

public class WeatherData implements Subject {

	private ArrayList<Observer> observers = new ArrayList<Observer>();
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

	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);

	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		int i = observers.indexOf(o);
		observers.remove(i);

	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(int i=0;i<observers.size();i++){
			Observer observer = observers.get(i);
			observer.update(this);
		}
		
	}
	
	public void measurementsChanged(){
		notifyObservers();
	}

	public void setMeasurements(float temperature,float humidity,float pressure){
		this.pressure = pressure;
		this.temperature = temperature;
		this.humidity = humidity;
		 measurementsChanged();
	}
}

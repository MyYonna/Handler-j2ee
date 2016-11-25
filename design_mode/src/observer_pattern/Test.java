package observer_pattern;

//�۲���ģʽ�������˶���֮���һ�Զ�����������һ������һ������ı�״̬ʱ���������������߶���յ�֪ͨ���Զ�����
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeatherData wd = new WeatherData();
		Observer o1 = new CurrentConditionsDisplay();
		Observer o2 = new StatisticsDisplay();
		wd.registerObserver(o1);
		wd.registerObserver(o2);
		wd.setMeasurements(20, 30, 40);
		//����ʱ�ı�������Ϊ
		o1.cancelRegister();
		wd.setMeasurements(10, 20, 60);

		wd.removeObserver(o2);
		wd.setMeasurements(30, 40, 50);
		
	}

}

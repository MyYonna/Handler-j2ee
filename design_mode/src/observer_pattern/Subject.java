package observer_pattern;
/**
 * ����ӿ�
 * @author zhangpeng
 *
 */
public interface Subject {

	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}

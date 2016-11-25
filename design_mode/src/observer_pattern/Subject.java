package observer_pattern;
/**
 * 主题接口
 * @author zhangpeng
 *
 */
public interface Subject {

	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}

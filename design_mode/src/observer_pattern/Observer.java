package observer_pattern;
/**
 * 观察者接口
 * @author zhangpeng
 *
 */
public interface Observer {

	public void update(Subject subject);
	
	public void cancelRegister();
}

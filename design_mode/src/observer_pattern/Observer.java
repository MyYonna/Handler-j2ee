package observer_pattern;
/**
 * �۲��߽ӿ�
 * @author zhangpeng
 *
 */
public interface Observer {

	public void update(Subject subject);
	
	public void cancelRegister();
}

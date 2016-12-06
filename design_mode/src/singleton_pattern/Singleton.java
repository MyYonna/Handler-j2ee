package singleton_pattern;

public class Singleton {

	private static Singleton uniqueInstance;
	private Singleton(){
		
	}
	/**
	 * 双重检查加锁
	 * @return
	 */
	public static Singleton getInstance(){
		if(uniqueInstance == null){
			synchronized(Singleton.class){
				if(uniqueInstance == null){
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}
}

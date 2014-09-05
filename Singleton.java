public class Singleton {

	private Singleton(){}
	
	private static class SingletonHolder{
		public static final Singleton singleton = new Singleton();
	}

	public static Singleton getInstance(){
		return Singleton.SingletonHolder.singleton;
	}
}

/*线程安全的单例模式，利用java虚拟机的机制保证线程安全*/
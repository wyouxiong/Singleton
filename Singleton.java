/*双重检锁机制，但做不到线程安全*/
public class Singleton{
	//线程不安全
	private static Singleton singleton;
	//线程安全
	//private static volatile Singleton singleton;
	private Singleton(){}

	public static Singleton getInstance(){
		if ( null == singleton ) {
			synchronized (Singleton.class){
				if (null == singleton) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
}
/*上述代码之所以做不到线程安全，是因为在java多线程中，为了效率通常会将主存中的变量拷贝一份到当前线程中
，在某个不确定的时机会将两者同步，这就是所谓的“无序写入”，那么我们可以给singleton变量加以volatile修饰
就可以做到线程安全*/



/*线程安全的单例模式，利用java虚拟机的机制保证线程安全*/
public class Singleton {

	private Singleton() {
		System.out.println("singleton");
	}

	private static class SingletonHolder {
		public static final Singleton singleton = new Singleton();
	}

	public static Singleton getInstance() {
		return Singleton.SingletonHolder.singleton;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			Thread thread = new Thread() {
				public void run() {
					getInstance();
					System.out.println(getInstance().hashCode());
				}
			};

			thread.start();

			Thread.yield();

		}

	}
}


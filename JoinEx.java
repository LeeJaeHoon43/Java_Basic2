package ljh.java.exam04;

class MyRunnableTwo implements Runnable{
	public void run() {
		System.out.println("run");
		first();
	}
	public void first() {
		System.out.println("first");
		second();
	}
	public void second() {
		System.out.println("second");
	}
}

public class JoinEx {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " start");
		Runnable r = new MyRunnableTwo();
		Thread myThread = new Thread(r);
		myThread.start();
		try {
			myThread.join(); // join() 메서드는 join메서드를 호출한 스레드가 종료할 때까지 현재의 스레드를 기다리게 된다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " end");
	}
}

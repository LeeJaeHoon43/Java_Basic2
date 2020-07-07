package ljh.java.exam03;
// A가 먼저 찍힐까 1이 먼저 찍힐까.
class ThreadSub extends Thread{
	public void run() {
		for (char ch = 'A';ch <= 'z';ch++){
			System.out.println(ch);
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {}
		}
	}
}
public class ThreadEx1 {
	public static void main(String[] args) {
		ThreadSub ts = new ThreadSub();
		ts.start(); // 쓰레드 실행.
//		ts.run(); // 쓰레드가 아닌 메서드 실행.
		for (int i = 1; i < 60; i++) {
			System.out.println(i);
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
			}
		}
	}
}

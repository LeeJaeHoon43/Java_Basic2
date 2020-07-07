package ljh.java.exam02;

public class SingleRunableEx implements Runnable{ // Runalbe 인터페이스를 상속받아서 구현하기.
	private int temp[];

	public SingleRunableEx() {
		temp = new int[10];
		for (int start = 0; start < 10; start++) {
			temp[start] = start;
		}
	}
	
	@Override
	public void run() {
		for (int start : temp) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("쓰레드 이름 : %s, ", Thread.currentThread().getName());
			System.out.printf("temp value : %d %n",start);
		}
	}
	
	public static void main(String[] args) {
		SingleRunableEx srt = new SingleRunableEx();
		Thread t = new Thread(srt, "첫번째"); // Runable객체를 이용해서 Thread객체를 생성한다.
		t.start();
	}	
}

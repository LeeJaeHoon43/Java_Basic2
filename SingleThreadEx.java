package ljh.java.exam01;

public class SingleThreadEx extends Thread{ // Thread 클래스를 상속받아서 구현하기.
	// 멤버
	private int[] temp;
	
	// 셍성자
	public SingleThreadEx(String threadname) {
		super(threadname);
		temp = new int[10];
		for (int start = 0; start < temp.length; start++) {
			temp[start] = start;
		}
	}

	public void run() {
		for (int start : temp) {
			try {
				sleep(1000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			System.out.printf("쓰레드 이름 : %s, ",currentThread().getName());
			System.out.printf("temp value : %d %n",start);
		}
	}

	public static void main(String[] args) {
		SingleThreadEx st = new SingleThreadEx("첫번째");
		st.start();
	}
}

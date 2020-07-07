package ljh.java.exam05;

class ThreadSub extends Thread{
	private String name;
	private String lastname;
	
	// 생성자.
	public ThreadSub(String a, String b, int x) {
		name = a;
		lastname = b;
		setPriority(x); // 우선순위 결정 (1 ~ 10) 
		setDaemon(true); // main 종료 시 Thread 종료.
		// 우선순위 설정과 setDaemon 같은 경우는 반드시 start이전에 설정해야 한다.

	}
	@Override
	public void run() {
		System.out.println("현재 실행 중인 쓰레드의 개수 : " + Thread.activeCount()); // 현재 실행중인 쓰레드의 개수 반환.
		int xx = Thread.activeCount();
		Thread[] th = new Thread[xx]; // 현재 실행 중인 쓰레드의 개수 크기의 쓰레드 배열 선언.
		Thread.enumerate(th); // 쓰레드의 	정보를 저장.

		for (int i = 0; i < th.length; i++) {
			System.out.println(th[i].getName() + " : " + th[i].getPriority() + " : " + th[i].isDaemon() + " : " + th[i].isAlive());
		}
		while(true) {
			System.out.println("이름 : " + name);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
			System.out.println("\t이름2 : " + lastname);
		}
	}
}

public class ThreadEx2 {
	public static void main(String[] args) {
		ThreadSub ts = new ThreadSub("길동", "홍", 1);
		ts.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}
		ThreadSub ts1 = new ThreadSub("오공", "손", 5);
		ts1.start();
		try {
			Thread.sleep(300); // 메인을 종료하지 않기 위해.
		} catch (InterruptedException e2) {}
	}
}

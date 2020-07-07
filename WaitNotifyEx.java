package ljh.java.exam07;

class ATMTwo implements Runnable{
	
	private long depositeMoney = 10000;

	public void run() {
		synchronized (this) { // 현재 실행중인 즉, 먼저 실행된 쓰레드가 작업을 수행할 동안 다른 쓰레드가 수행하지 못한다.(대기 상태)
			for (int i = 0; i < 10; i++) {
				if (getDepositeMoney() <= 0){
					break;
				}
				withDraw(1000);
				
				if (getDepositeMoney() == 2000 || getDepositeMoney() == 4000 || getDepositeMoney() == 6000 || getDepositeMoney() == 8000) {
					try {
						this.wait(); // 해당 조건이 되면, 동기화된 블록 안에서 스레드간의 통신, 제어권을 넘긴다.
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					this.notify(); // 해당 조건이 되면, 동기화된 블록 안에서 스레드간의 통신, 제어권을 넘긴다.
				}
			}
		}
	}
	public void withDraw(long howMuch) {
		if (getDepositeMoney() > 0) {
			depositeMoney -= howMuch;
			System.out.print(Thread.currentThread().getName() + " , ");
			System.out.printf("잔액 : %,d원 %n",getDepositeMoney());
		}else {
			System.out.print(Thread.currentThread().getName() + " , ");
			System.out.println("잔액이 부족합니다.");
		}
	}
	
	public long getDepositeMoney() {
		return depositeMoney;
	}
}
public class WaitNotifyEx {
	public static void main(String[] args) {
		ATMTwo atm = new ATMTwo();
		Thread mother = new Thread(atm, "mother");
		Thread son = new Thread(atm, "son");
		mother.start();
		son.start();
	}
}

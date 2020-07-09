package ljh.java.exam07;

import java.io.BufferedReader;
import java.io.IOException;

public class MultiClientThread extends Thread{
	private BufferedReader in;
	// 객체 생성 시에 서버가 전달한 메시지를 읽을 수 있는 BufferedReader를 전달받는다.
	public MultiClientThread(BufferedReader in) {
		this.in = in;
	}
	@Override
	public void run() {
		try {
			while(true) {
				// 서버가 전송한 메시지 읽기.
				String text = in.readLine();
				System.out.println("수신메시지 : " + text);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

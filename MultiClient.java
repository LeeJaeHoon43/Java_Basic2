package ljh.java.exam07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiClient {
	// 서버랑 연결된 Socket.
	private Socket socket;
	// 서버가 보낸 메시지를 읽어들일 스트림.
	private BufferedReader in;
	// 서버로 메시지를 전송할 스트림.
	private PrintWriter out;
	// 키보드 입력내용을 읽어들을 스트림.
	private BufferedReader keyboard;
	
	public MultiClient() {
		try {
			socket = new Socket("localhost",5000);
			System.out.println("Server Connec Sussess");
			// 스트림 연결.
			keyboard = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 서버가 전송한 메시지를 읽어들일 읽기 전용 Thread를 생성.
			// MultiClientThread 생성 시에 서버가 전송한 메시지를 읽어들일 수 있는 스트림을 전달.
			MultiClientThread t = new MultiClientThread(in);
			t.start();
			// 키보드로 입력한 내용을 읽어와서 서버로 전송하기.
			while(true) {
				String text = keyboard.readLine();
				out.println(text);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new MultiClient();
	}
}

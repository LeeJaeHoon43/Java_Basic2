package ljh.java.exam06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UnicastServer {
	public UnicastServer() {
		ServerSocket server = null;
		try {
			// 서버 소켓 생성.
			server = new ServerSocket(5000);
		} catch (IOException e) {
			System.out.println("해당 포트가 사용중입니다.");
			System.exit(0);
		}
		try {
			while(true) {
				System.out.println("Server Ready...");
				// 클라이언트 연결요청이 접수되면 해당 클라이언트와 통신할 Socket을 생성.
				Socket socket = server.accept();
				// 클라이언트와 메시지를 송수신할 UnicastServerThread 생성.
				// UnicastServerThread 생성 시 Socket을 전달함.
				UnicastServerThread thread = new UnicastServerThread(socket);
				/// UnicastServerThread의 start()를 호출함.
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new UnicastServer();
	}
}

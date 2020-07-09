package ljh.java.exam07;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiServer {
	// 특정 클라이언트가 보낸 메시지를 모든 클라이언트에게 전달하기 위해 클라이언트와 메시지 송/수신할 수 있는 기능을 가진 
	// MultiServerThread객체를 저장할 자료구조를 선언.
	private ArrayList<MultiServerThread> clientList = new ArrayList<MultiServerThread>();
	private ServerSocket server = null;
	
	public MultiServer() {
		try {
			// 5000번 포트번호에서 클라이언트의 접속 요청을 기다리고 있는 서버 소켓 생성.
			server = new ServerSocket(5000);
			System.out.println("서버 시작됨");
			while(true) {
				// 클라이언트의 연결 요청을 대기하고 있다가 연결요청이 접수되면 해당 클라이언트랑 통신할 수 있는 소켓을 만들어서 반환한다.
				Socket socket = server.accept();
				// 소켓으로부터 ip주소와 호스트명을 가지고 있는 InetAddress 객체 얻기.
				InetAddress ip = socket.getInetAddress();
				// InetAddress객체로부터 ip주소 얻기.
				String ipAddress = ip.getHostAddress();
				System.out.println(ipAddress + "들어옴.");
				// MultiServerThread를 저장하고 있는 clientList와 해당 클라이언트와 연결된 Socket객체를 전달해서
				// MultiServerThread객체를 생성한다.
				MultiServerThread t = new MultiServerThread(clientList, socket);
				// 스레드 시작함.
				t.start();
				// 생성된 MultiServerThread 객체를 clientList에 저장.
				clientList.add(t);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) {
		new MultiServer();
	}
}

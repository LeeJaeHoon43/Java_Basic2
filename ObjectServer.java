package ljh.java.exam08;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectServer {
	public ObjectServer() {
		try {
			// 서버 소켓 생성.
			ServerSocket serverSocket = new ServerSocket(5000);
			System.out.println("서버 기동됨");
			// 클라이언트의 연결 요청을 청취한다.
			// 클라이언트의 연결 요청이 접수되면 클라이언트와 통신할 Socket 생성.
			Socket socket = serverSocket.accept();
			System.out.println("클라이언트 연결 접수됨.");
			System.out.println("[client]" + socket.getInetAddress());
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			// 객체를 송수신하기 위해 ObjectInputStream과 ObjectOutputStream을 inputStream과 OutputStream에 연결한다.
			ObjectInputStream ois = new ObjectInputStream(is);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			// readObject는 ObjectInputStream을 통해서 전달받은 직렬화된 객체를 역직렬화 과정.
			// 객체를 생성, 전달받은 직렬화된 객체의 값으로 채우기를 통해서 client가 전송한 객체와 똑같은 객체를 새로 만든다.
			MyMember member = (MyMember)ois.readObject();
			System.out.println("클라이언트 정보 수집됨.");
			System.out.println("아이디 : " + member.getId());
			System.out.println("비밀번호 : " + member.getPwd());
			// 클라이언트에 메시지 전달.
			oos.writeObject("[" + member.getId() + "] 님 로그인 성공");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ObjectServer();
	}
}

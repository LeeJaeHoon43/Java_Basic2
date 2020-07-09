package ljh.java.exam05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public EchoServer() {
		try {
			// 1. 서버소켓 생성. (3000번 포트를 열고 대기한다)
			ServerSocket 서버소켓 = new ServerSocket(3000);

			// 2. 클라이언트의 접속을 기다림(청취함.)
			// 서버소켓은 클라이언트의 연결요청이 접수되면 해당 클라이언트의 소켓과 송수신할 수 있는 소켓을 생성해낸다.
			// 그리고, 해당 소켓을 이용해서 접속된 클라이언트의 ip정보를 지니고 있는 InetAddress객체를 얻을 수도 있다.
			// accept() 메소드는 클라이언트의 연결요청이 있을때까지 대기한다. ----> block method임.

			System.out.println("서버 기동됨............");
			Socket 소켓 = 서버소켓.accept();
			System.out.println("클라이언트와 연결할 소켓생성됨...");
			// 접속한 클라이언트의 정보 얻기
			InetAddress 클라이언트ip정보 = 소켓.getInetAddress(); // 클라이언트의 IP주소 얻기.
			String ip = 클라이언트ip정보.getHostAddress(); // ip주소를 얻어서 문자열로 반환한다.
			System.out.println("[" + ip + "]가 접속됨");
			// 3. 소켓으로부터 inputStream과 outputStream 얻기.
			InputStream is = 소켓.getInputStream();
			OutputStream os = 소켓.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			// 4. client가 전송한 메세지 읽기.
			while(true) {
				String message = br.readLine();
				System.out.println("[메세지] " + message);
				// 5. client로 메세지 전송하기
				bw.write(message + "\n");
				bw.flush();
			}
		} catch (IOException e) {
			// 서버소켓 생성시 지정된 port가 다른 프로그램에서
			// 사용중일때 예외발생한다.
			System.out.println("해당 port는 사용 중입니다.");
		}
	}

	public static void main(String[] args) {
		new EchoServer();
	}
}
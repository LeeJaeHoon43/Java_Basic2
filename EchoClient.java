package ljh.java.exam05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
	public EchoClient() {
		try {
			// 1. 서버와 통신할 소켓 생성.
			// 연결한 서버의 ip와 port번호를 지정한다.
			// 소켓을 생성하는 순간 해당 ip의 port번호로 연결을 요청한다.
			Socket 소켓 = new Socket("localhost", 3000);
			// 2. 소켓으로부터 inputStream과 outputStream 얻기
			OutputStream os = 소켓.getOutputStream();
			InputStream is = 소켓.getInputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			// 3. 서버에 메세지 전송하기
			while(true) {
				System.out.print("전송할 메시지 :");
				String msg = keyboard.readLine();
				bw.write(msg + "\n");
				bw.flush();
				// 4. 서버가 보낸 메세지 읽기
				String message = br.readLine();
				System.out.println("[서버가 보낸 메세지]" + message);
			}
		} catch (UnknownHostException e) {
			// ip에 해당하는 서버를 찾을 수 없을 경우
			System.out.println("서버를 찾을 수 없습니다.");
		} catch (IOException e) {
			// 해당 port번호에 어떤 서비스도 존재하지 않는 경우
			System.out.println("사용되지 않는 port번호입니다.");
		}
	}
	public static void main(String[] args) {
		new EchoClient();
	}
}
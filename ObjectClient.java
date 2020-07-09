package ljh.java.exam08;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ObjectClient {
	public ObjectClient() {
		try {
			Socket socket = new Socket("localhost", 5000);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			// 사용자 id와 pwd가 저장된 Member 객체를 생성.
			MyMember member = new MyMember();
			member.setId("hong");
			member.setPwd("1111");
			// 생성된 Object 객체를 Server로 전송.
			oos.writeObject(member);
			// 서버가 전송한 메시지 전달받기.
			String message = (String)ois.readObject();
			System.out.println(message);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ObjectClient();
	}
}

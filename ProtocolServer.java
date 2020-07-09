package ljh.java.exam09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ProtocolServer {
	/*
	100:아이디 -> 입장.
	200:아이디 -> 퇴장.
	300:아이디 : 메시지 -> 대화방에 접속한 모든 사람들에게 메시지 전달.
	400:아이디1:아이디2:메시지 -> 아이디1이 아이디2에게 귓속말.  
	*/
	ServerSocket ss = null;
	Socket s = null;
	BufferedReader br = null;
	BufferedWriter bw = null;
	
	public ProtocolServer()throws IOException{
		ss = new ServerSocket(5000);
		s = ss.accept();
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		while(true) {
			// 클라이언트가 전송한 문자열 읽기.
			String text = br.readLine();
			// 읽어들인 문자열을 구분자를 기준으로 분리하기.
			String[] words = text.split(":");
			Protocol protocol = new Protocol();
			if (words[0].equals(protocol.ENTER)) {
				// 입장시키기.
				enter(words);
			}else if (words[0].equals(protocol.EXIT)) {
				exit(words);
			}else if (words[0].equals(protocol.SEND_MESSAGE)) {
				sendMessage(words);
			}else if (words[0].equals(protocol.SECRET_MESSAGE)) {
				sendSecretMessage(words);
			}else {
				bw.write("잘못된 요청입니다.");
				bw.flush();
			}
		}
	}
	public void enter(String[] arr)throws IOException{
		// 100:아이디
		System.out.println(arr[1]+"님이 입장하였습니다.");
		bw.write(arr[1]+"님이 입장하였습니다.\n");
		bw.flush();
	}
	
	public void exit(String[] arr)throws IOException{
		// 200:아이디
		System.out.println(arr[1]+"님이 퇴장하였습니다.");
		bw.write(arr[1]+"님이 퇴장하였습니다.\n");
		bw.flush();
	}
	public void sendMessage(String[] arr)throws IOException{
		// 300:아이디:메시지
		System.out.println("["+arr[1]+"]"+arr[2]);
		bw.write("["+arr[1]+"]"+arr[2]+"\n");
		bw.flush();
	}
	public void sendSecretMessage(String[] arr)throws IOException{
		System.out.println("<"+arr[1]+">"+arr[3]);
		bw.write("<"+arr[1]+">"+arr[3]+"\n");
		bw.flush();
	}
	public static void main(String[] args) {
		try {
			new ProtocolServer();
		} catch (IOException e) {}
	}
}
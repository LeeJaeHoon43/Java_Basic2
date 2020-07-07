package ljh.java.exam08;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStreamEx {
	public static void main(String[] args) {
		// DataOutputStream은 기본 자료형 타입 그대로 스트림을 통해서 기록할 수 있다.
		// DataOutputStream은 1차 스트림이 아니기 때문에 다른 스트림을 이용해서 특정 디바이스와 연결될 수 있다.
		DataOutputStream dos = null;
		FileOutputStream fos = null;
		
		try {
			// DataOutputStream과 연결된 FileOutputStream 객체 생성.
			fos = new FileOutputStream("c:/myProject/data.txt");
			// 기본 자료형의 타입을 유지한 채 기록할 수 있는 DataOutputStream 객체 생성.
			dos = new DataOutputStream(fos);
			int i = 10;
			double d = 3.14;
			String s = "JAVA프로그램";
			// DataOutputStream은 기본 자료형별로 write매서드가 존재.
			dos.writeInt(i);
			dos.writeDouble(d);
			dos.writeUTF(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e2) {}
			try {
				if (dos != null) {
					dos.close();
				}
			} catch (IOException e2) {}
		}
	}
}

package ljh.java.exam04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamEx {
	public static void main(String arg[]) {
		FileInputStream fis = null;
		byte _read[] = new byte[2000];
		byte console[] = new byte[100];
		try {
			System.out.print("파일명 : "); // C:\myProject\myWork\MabangjinEx.java
			System.in.read(console);
			String file = new String(console).trim();// Byte[] 배열을 -> String으로 바꾸는 방법.
			// String -> byte[] : str.getBytes()
			fis = new FileInputStream(file);
//			int readByte = -1;
//			while((readByte = fis.read()) != -1) {
//				System.out.print((char) readByte);
//			}
			fis.read(_read, 0, _read.length); // 배열 데이터를 0부터 그 배열의 길이만큼 읽어서 배열의 0번 위치에 저장하고 읽은 수 만큼 반환.
			System.out.println(new String(_read).trim()); // 공백을 제거하고 문자열로 출력.
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}

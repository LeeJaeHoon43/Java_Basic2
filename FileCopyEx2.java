package ljh.java.exam07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyEx2 {
	public static void main(String[] args) {
		System.out.println("파일 복사 시작");
		long start = System.currentTimeMillis(); // 시작 밀리초를 저장.
		FileInputStream src = null; 
		FileOutputStream dest = null;
		try {
			src = new FileInputStream("c:/Users/dltjs/Desktop/JAVA_MyProject/JAVA_Memo.txt");
			dest = new FileOutputStream("c:/myProject/dest2.txt");
			// 임시 저장소에 저장된 data의 전체 개수를 저장할 변수.
			int length = 0;
			// 임시 저장소로 사용될 byte[크기] 배열 선언. 1024 * 8 = 8Byte.
			byte[] buffer = new byte[1024*8]; // 8Byte
			// inputStream을 통해서 읽어 들인 data를 임시 저장소에 쌓고 저장된 data의 갯수를 length에 저장한다.
			while((length = src.read(buffer)) != -1) {
				// outputStream을 통해서 임시 저장소에 쌓여있는 data를 length만큼 파일에 기록한다.
				dest.write(buffer, 0, length);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (dest != null) {
					dest.close();
				}
			} catch (IOException e) {}
			try {
				if (src != null) {
					src.close();
				}
			} catch (IOException e) {}
		}
		long end = System.currentTimeMillis();
		long copyTime = (end - start);
		System.out.println("걸린 시간 : " + copyTime + " 밀리초");
	}
}

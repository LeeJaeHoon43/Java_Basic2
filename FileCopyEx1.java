package ljh.java.exam06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyEx1 {
	public static void main(String[] args) {
		System.out.println("파일 복사 시작");
		long start = System.currentTimeMillis(); // 시작 밀리초를 저장.
		FileInputStream src = null; 
		FileOutputStream dest = null;
		try {
			// 원본 파일을 읽기 위한 FileInputStream 객체 생성.
			src = new FileInputStream(new File("c:/Users/dltjs/Desktop/JAVA_MyProject/JAVA.txt"));
			// 복사본 파일을 읽기 위한 FileOutputStream 객체 생성.
			dest = new FileOutputStream(new File("c:/myProject/dest.txt"));
			// FileInputStream을 통해서 읽어들인 값을 저장할 변수.
			int readValue = 0;
			// FileInputStream의 read()메서드를 통해서 읽어들인 값을 readValue애 저장하고 그 값이 -1이 아니면
			while((readValue = src.read()) != -1) {
				// readValue에 저장된 값을 FileOutputStream의 write()메소드를 통해서 파일에 기록.
				dest.write(readValue);
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
			} catch (IOException e2) {}
			
			try {
				if (src != null) {
					src.close();
				}
			} catch (IOException e2) {}
		}
		long end = System.currentTimeMillis(); // 종료 밀리초 저장.
		long copyTime = (end - start); // 작업하는데 걸린 시간을 계산한다.
		System.out.println("걸린 시간 : " + copyTime + " 밀리초");
	}
}

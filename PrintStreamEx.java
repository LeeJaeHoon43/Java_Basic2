package ljh.java.exam14;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamEx {
	public static void main(String[] args) {
		// 다른 스트림과는 다르게 플러쉬 기능을 자동으로 처리할 수 있는 생성자를 가지고 있다.
		// 모든 메서드의 예외 처리를 하지 않는다.
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		PrintStream ps = null;
		try {
			fos = new FileOutputStream("c:/myProject/printStream.txt",true); //true : 이어쓰기
			bos = new BufferedOutputStream(fos);
			ps = new PrintStream(bos, true); // true : Outo flush()를 할 수 있다.
			ps.println("홍길동");
			ps.println(1234);
			ps.println(true);
			ps.println('a');
		} catch (IOException ie) {
			ie.printStackTrace();
		}finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (Exception ioe ) {
				ioe.printStackTrace();
			}
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}
			if (ps != null) {
				ps.close();
			}
		}
	}
}

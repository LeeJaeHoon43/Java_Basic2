package ljh.java.exam05;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamEx {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("c:\\myProject\\fileout.txt", true); // append가 true면 이어쓰기, false면 덮어쓰기.
			String message = "Hello FileOutputStream!!";
			fos.write(message.getBytes());
			fos.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}

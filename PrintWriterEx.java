package ljh.java.exam15;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterEx {
	public static void main(String[] args) {
		PrintWriter pw = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			fos = new FileOutputStream("c:/myProject/printWriter.txt");
			bos = new BufferedOutputStream(fos);
			pw = new PrintWriter(bos,true); // PrintWriter의 자동 flush() -> true
			pw.println("안녕하세여!!");
			pw.println("또 만났네요.");
			pw.println(100.0);
			pw.println(new Boolean(true));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			if (pw != null) {
				pw.close();
			}
		}
	}
}

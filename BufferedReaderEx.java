package ljh.java.exam13;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderEx {
	public static void main(String[] args) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream("c:/myProject/hello.txt");
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			// 한 라인 씩 읽어들인 문자열을 저장할 변수.
			String str = null;
			/// readLine() : 개행 문자를 만나면 개행 이전까지의 문자열을 반환하고 스트림의 끝에 도달하면 null을 반환.
			while((str = br.readLine()) != null) {
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e2) {}
			try {
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e2) {}
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e2) {}
		}
	}
}

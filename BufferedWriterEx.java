package ljh.java.exam12;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterEx { // 텍스트에 문자 기록하기.
	public static void main(String[] args) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("c:/myProject/hello.txt");
			bw = new BufferedWriter(fw);
			bw.write("BufferedWriter 테스트입니다.");
			bw.newLine(); // 한 줄 개행.
			bw.write("안녕하세요" + System.getProperty("line.separator"));
			bw.write("반갑습니다.");
			bw.flush(); // 배열이 아니면 무조건 해야 한다.
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e2) {}
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e2) {}
		}
	}
}

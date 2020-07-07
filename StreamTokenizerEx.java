package ljh.java.exam08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class StreamTokenizerEx {
	public static void main(String[] args) {
		BufferedReader br = null;
		StreamTokenizer st = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter("c:/myProject/streamToken.txt");
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw, true);
			pw.println(10000);
			pw.println("안녕하세요");
			pw.println("ddddd@naver.com");
			pw.println("I am a Instructor");
			pw.println("~^!@#S");
			br = new BufferedReader(new FileReader("c:/myProject/streamToken.txt"));
			st = new StreamTokenizer(br);
			while(st.nextToken() != StreamTokenizer.TT_EOF) {
				switch (st.ttype) {
				case StreamTokenizer.TT_WORD:
					System.out.println("Word => " + st.sval);
					break;
				case StreamTokenizer.TT_NUMBER:
					System.out.println("Number => " + (int)st.nval);
					break;
				default:
					System.out.println("No Word, No Number => " + (char)st.ttype);
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
				if (pw != null) {
					pw.close();
				}
			} catch (Exception e3) {
				e3.printStackTrace();
			}
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}

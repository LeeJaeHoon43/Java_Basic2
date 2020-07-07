package ljh.java.exam03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ScannerEx3 {
	public static void main(String[] args) {
		// URL로부터 읽어오기.
		URLConnection urlCon = null;
		Scanner scan = null;
		try {
			urlCon = new URL("http://www.kgitbank.co.kr/").openConnection();
			scan = new Scanner(new BufferedReader(new InputStreamReader(urlCon.getInputStream())));
			scan.useDelimiter("\\Z"); // 매개변수 문자열로 생성된 정규 표현식으로 구분 패턴을 지정한다. Z는 문자열의 끝을 의미한다.
			String text = scan.next();
			System.out.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (scan != null) {
				scan.close();
			}
		}
	}
}

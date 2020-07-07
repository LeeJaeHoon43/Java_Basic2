package ljh.java.exam02;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ScannerEx2 {
	public static void main(String[] args) {
		Scanner scan = null;
		try {
			scan = new Scanner(new File("c:\\myProject\\scan.txt"));
			while(scan.hasNextDouble()) { // Scanner객체에 다음 토큰이 Double값이 있으면 true를 반환한다.
				// Scanner객체로부터 다음 토큰을 double값으로 반환.
				System.out.printf("스캔 double : %,.2f %n",scan.nextDouble()); 
			}
			scan.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if(scan != null) {
				scan.close();
			}
		}
	}
}

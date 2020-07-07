package ljh.java.exam04;

import java.util.Scanner;

public class ScannerEx4 {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String str = "1 and 2 and animal	and lion and tiger";
		Scanner scan = new Scanner(str);
		scan = scan.useDelimiter("\\s*and\\s*"); // and를 기준으로 모든 문자열을 나눈다.
		int firstToken = scan.nextInt();
		int secondToken = scan.nextInt();
		String thirdToken = scan.next();
		String fourthToken = scan.next();
		String fifthToken = scan.next();
		System.out.printf("%d,%d,%s,%s,%s",firstToken,secondToken,thirdToken,fourthToken,fifthToken);
		scan.close();
	}
}

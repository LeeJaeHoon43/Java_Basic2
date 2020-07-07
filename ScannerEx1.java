package ljh.java.exam01;

import java.util.Scanner;

public class ScannerEx1 {
	public static void main(String[] args) {
		System.out.print("입력 : ");
		Scanner scan = new Scanner(System.in);
		int number = scan.nextInt(); // 1234 \r\n , 숫자로 입력받을 땐 엔터값 처리를 한다.
		System.out.printf("스캔 : %d%n",number);
		
		scan.nextLine(); // \r\n을 날려줘야 한다. 엔터값 처리.
		System.out.print("입력 문자1 : ");
		String str = scan.nextLine(); // 문자열을 입력받을 때는 next()보단 nextLine()을 쓰는 것을 추천한다. 엔터값을 무시한다.
		System.out.println("스캔 문자 : " + str);
		// scan.nextLine(); // 위에서 입력을 next()로 받을 시에는 반드시 엔터값 처리를 해주기 위해 이 코드를 실행해야 한다.
		// 만약 위에서 입력을 받을 때 nextLine()이 아닌 next()였다면 엔터값처리를 해주지 않으면  다음 밑에서 입력을 받을 기회를 얻지 못한다.
		System.out.print("입력 문자2 : ");
		String mystr = scan.nextLine();
		System.out.println("스캔 문자 : " + mystr);
		scan.close();
	}
}

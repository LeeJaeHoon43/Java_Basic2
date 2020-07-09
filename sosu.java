package test1;
public class sosu {
	public static void main(String[] args) {
		System.out.println("======================");
		System.out.println("미래크립토 소수 판단 프로그램");
		System.out.println("\t-홍길동");
		System.out.println("======================");
		int count = 0; // 소수의 개수를 카운트할 변수.
		int max = 0; // 가장 큰 소수를 저장할 변수.
		int min = 1000; // 가장 작은 소수를 저장할 변수.
		boolean flag = true; // 소수 여부를 판별할 변수.
		for (int i = 0; i < 100; i++) { // 100번 반복한다.
			int num = (int)(Math.random() * 1001) + 1; // 1 ~ 1000이하의 랜덤 정수를 뽑는다.
			for(int j = 2; j < num; j++){ // 2부터 num보다 작을 때까지 num을 j로 나눈다.
				if(num % j == 0){ // 나머지가 0이면 소수가 아니므로 flag를 false로.
					flag = false; 
					break;  
				} else { // 나머지가 0이 아니면 flag를 true로.
					flag = true; 
				}
			}
			if(flag){ // flag가 true, 즉 소수일 경우 출력.
				System.out.println("소수발견 : " + num);
				count++; // 개수 카운트.
				if(max < num) max = num; // 가장 큰 소수를 변수에 저장.
				if(min > num) min = num; // 가장 작은 소수를 변수에 저장.
			}
		}
		// 최종 결과 출력.
		System.out.println("----------------------");
		System.out.println("# 최종 결과 #");
		System.out.println("소수 개수 : " + count);
		System.out.println("최소 소수 : " + min);
		System.out.println("최대 소수 : " + max);
	}
}

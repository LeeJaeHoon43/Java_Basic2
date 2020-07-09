package test2;
import java.io.*; // 입력 - 1
public class JuminNumberExample{
	public static void main(String[] ar)throws IOException{ // 입력 - 2
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 - 3
		int juminNumber[]; // 주민등록번호를 저장할 배열.
		String jumin; // 입력값으로 받을 주민등록번호.
		int lastNumber = 0; // 주민등록번호 검증번호.맨마지막 부분.
		int count = 0; // 모든 검사 횟수.
		int successCount = 0; // 성공 검사 횟수.
		int failCount = 0; // 실패 검사 횟수.
		int unusualnessCount = 0; // 비정상 검사 횟수.
		float[] rule = {2.f, 3.f, 4.f, 5.f, 6.f, 7.f, 8.f, 9.f, 2.f, 3.f, 4.f, 5.f, 0f}; // 주민등록번호 확인을 위한 공식에 필요한 변수들.
		float hap = 0; // 주민등록번호 확인을 위한 공식에 필요한 변수들.
		float success = 0; // 정상 퍼센트.
		float fail = 0; // 오류 퍼센트 
		float unusualness = 0; // 비정상 퍼센트
		boolean search; // 주민번호 체크하기 위한 변수.

		System.out.println("======================================");
		System.out.println("미래크립토 주민등록번호 검증 및 집계 프로그램");
		System.out.println("\t\t-홍길동");
		System.out.println("======================================");
		System.out.println("* 주민번호 입력 (oooooo-ooooooo 하이픈 포함 14자리, exit 입력시 종료)");

		while(true) {
			System.out.print(">> 데이터 입력 : ");
			jumin = br.readLine(); // 주민등록번호를 입력받는다.(String)
			if (jumin.equals("exit")) { // 입력값이 exit면 반복문 탈출하고 최종 결과를 출력한다.
				break;
			}else if(jumin.length() != 14) { // 입력값의 길이가 14가 아니면 아래의 메시지 출력.
				System.out.println("주민번호는 oooooo-ooooooo 하이픈 포함 14자리 입니다!!");
				count++; // 횟수 추가.
				unusualnessCount++; // 비정상 횟수 추가.
				continue; // continue를 하지 않으면 Exception이 발생함.
			}
			// 문자가 들어있을 경우, NumberFormatException이 발생함. try-catch문으로 감싸야 한다.
			try {
				juminNumber = new int[jumin.length()-1]; // 주민등록번호를 저장할 배열의 크기 선언. 하이픈을 제외한 크기로.
				String jumin1 = jumin.substring(0,6); // 주민등록번호 앞자리를 자른다.
				String jumin2 = jumin.substring(7); // 주민등록번호 뒷자리를 자른다.
				juminNumber = getJumin(jumin1, jumin2); // 입력받은 문자열 주민등록번호를 int형으로 변환시켜주는 메서드 호출.
				search = juminCheck(juminNumber); // int형 주민등록번호가 올바른 번호인지 확인해주는 메서드 호출.
				if(search == false){ // 확인 결과가 false면 오류 횟수 추가.
					System.out.println("[검증 실패] 주민 번호 수치상의 오류가 있습니다.");
					count++;
					failCount++;
				}else{ // 확인결과가 true면 성공 횟수 추가.
					System.out.println("[검증 성공] 정상적인 주민번호이며 오류가 없습니다.");
					count++;
					successCount++;
				}
			} catch (NumberFormatException e) { // 예외가 발생하면 비정상 횟수 추가.
				System.out.println(" '-' 을 제외하고 모두 0 ~ 9 숫자여야 합니다!!");
				count++;
				unusualnessCount++;
				continue;
			}
		}
		success = (100 / count) * successCount; // 정상 비율 계산.
		fail = (100 / count) * failCount; // 오류 비율 계산.
		unusualness = (100 / count) * unusualnessCount; // 비정상 비율 계산.
		System.out.println("------------------------------");
		System.out.println("# 최종 결과 #");
		System.out.println("- 총 입력 건수 : " + count);
		System.out.println("- 정상 입력 건수 : " + successCount + "건 (" + success + "%)");
		System.out.println("- 오류 입력 건수 : " + failCount + "건 (" + fail + "%)");
		System.out.println("- 비정상 입력 건수 : " + unusualnessCount + "건 (" + unusualness + "%)");	
	}
	// int형 주민등록번호를 얻어오는 메서드.
	private static int[] getJumin(String jumin1, String jumin2) { 
		int jumin[] = new int[13];
		int f = Integer.parseInt(jumin1);
		int l = Integer.parseInt(jumin2);
		jumin[0] = f / 100000;
		f %= 100000;
		jumin[1] = f / 10000;
		f %= 10000;
		jumin[2] = f / 1000;
		f %= 1000;
		jumin[3] = f / 100;
		f %= 100;
		jumin[4] = f / 10;
		f %= 10;
		jumin[5] = f;

		jumin[6] = l / 1000000;
		l %= 1000000;
		jumin[7] = l / 100000;
		l %= 100000;
		jumin[8] = l / 10000;
		l %= 10000;
		jumin[9] = l / 1000;
		l %= 1000;
		jumin[10] = l / 100;
		l %= 100;
		jumin[11] = l / 10;
		l %= 10;
		jumin[12] = l;
		return jumin;
	}

	// 주민등록번호를 확인하는 메서드. 주민등록번호를 확인하는 공식.
	private static boolean juminCheck(int[] jumin) {
		float temp = 0.f;
		float total = 0.f;
		float ct = 2.f;
		float hap = 0.f;
		for(int i=0; i<jumin.length-1; i++){
			if(ct == 10.f) {
				ct = 2.f;
			}
			hap += jumin[i] * ct;
			ct++;
		}
		temp = 11.f * (int)(hap/11.f) + 11.f - hap;
		total = temp - 10.f * (int)(temp/10.f);
		if((int)total != jumin[jumin.length-1]){
			return false;
		}else {
			return true;
		}
	}
}

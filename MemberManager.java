package ljh.java.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import ljh.java.vo.MyMember;

public class MemberManager {
	
	// 멤버 변수
	private BufferedReader br;
	private ArrayList<MyMember> data;
	private boolean loop;
	private int position;
	
	// 생성자 : 멤버 변수 초기화하는 역할.
	public MemberManager() {
		br = new BufferedReader(new InputStreamReader(System.in));
		data = new ArrayList<MyMember>();
		loop = true;
		position = -1;
	}

	// 메서드
	// 프로그램을 종료하는 메서드.
	public void stop() throws IOException {
		System.out.println();
		System.out.print("정말로 종료하시겠습니까? (Y/N) : ");
		String result = br.readLine();
		if (result.equals("Y") || result.equals("y")) {
			System.out.println("프로그램을 종료합니다.");
			loop = false;
		}else {
			System.out.println("프로그램 종료를 취소합니다.");
			loop = true;
		}
	}
	
	// 회원 등록 메서드
	public void addMember() throws IOException {
		System.out.println();
		System.out.print("이름 : ");
		String name = br.readLine();
		System.out.print("나이 : ");
		int age = Integer.parseInt(br.readLine());
		System.out.print("전화 번호 : ");
		String tel = br.readLine();
		System.out.print("주소 : ");
		String address = br.readLine();

		MyMember member = new MyMember(name, age, tel, address);

		data.add(member);
		System.out.println("성공적으로 등록되었습니다.");
	}
	
	// 회원 검색 메서드.
	public void search() throws IOException { 
		System.out.println();
		System.out.print("찾으시는 이름 : ");
		String name = br.readLine();
		position = -1; // 연속 검색의 경우 앞의 position을 기억하기 때문에 다시 초기화.
		for (int i = 0; i < data.size(); i++) {
			MyMember member = data.get(i);
			if (name.equals(member.getName())) {
				position = i;
			}
		}
		if (position >= 0) {
			System.out.println(name + "님의 정보 검색 성공");
		}else {
			System.out.println(name + "님의 정보 검색 실패 : 회원 가입을 하세요.");
		}
	}

	// 회원 정보를 수정하는 메서드.
	public void updateMember() throws IOException { 
		System.out.println();
		if (position < 0) {
			System.out.println("회원 검색을 먼저 수행하셔야 합니다.");
			return;
		}

		MyMember member = data.get(position);

		boolean status = true;

		int menu = -1;
		while(status) {
			System.out.println(member.getName() + "님의 정보 수정");
			System.out.println("1. 전화번호 수정");
			System.out.println("2. 주소 수정");
			System.out.println("0. 수정 취소");
			System.out.print("메뉴 선택 : ");

			try {
				menu = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				menu = -1;
			}

			switch (menu) {
			case 1:
				System.out.println();
				System.out.print("수정할 전화번호 : ");
				String tel = br.readLine();
				member.setTel(tel);
				System.out.println(tel + "로 성공적으로 전화번호를 수정하였습니다.");
				status = false;
				break;
			case 2:
				System.out.println();
				System.out.print("수정할 주소 : ");
				String address = br.readLine();
				member.setAddress(address);
				System.out.println(address + "로 성공적으로 전화번호를 수정하였습니다.");
				status = false;
				break;
			case 0:
				System.out.println();
				System.out.println(member.getName() + "님의 정보 수정을 취소합니다.");
				status = false;
				break;
			}
			System.out.println();
		}
	}
	
	// 회원 정보를 삭제하는 메서드.
	public void deleteMember() throws IOException { 
		System.out.println();
		if (position < 0) {
			System.out.println("회원 검색을 먼저 수행하셔야 합니다.");
			return;
		}

		MyMember member = data.get(position);

		System.out.print(member.getName() + "정말로 삭제하시겠습니까? (Y/N) : ");
		String result = br.readLine();
		if (result.equals("Y") || result.equals("y")) {
			data.remove(position);
			System.out.println(member.getName() + "님의 정보를 성공적으로 삭제하였습니다.");
			position = -1;
		}else {
		}
	}

	public void display() { // 회원 전체 목록 보기.
		System.out.println();
		for (MyMember member : data) {
			System.out.println(member.toString());
		}
	}
	
	// 프로그램을 시작하는 메서드.
	public void start() throws IOException { 
		int menu = -1;
		while(loop) {
			System.out.println("1. 회원 가입");
			System.out.println("2. 회원 검색");
			System.out.println("3. 정보 수정");
			System.out.println("4. 회원 정보 삭제");
			System.out.println("5. 회원 목록");
			System.out.println("0. 프로그램 종료");
			System.out.print("메뉴 선택 : ");
			try {
				menu = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				menu = -1;
			}

			switch (menu) {
			case 0: stop(); break;
			case 1: addMember(); break;
			case 2: search(); break;
			case 3: updateMember(); break;
			case 4: deleteMember(); break;
			case 5: display(); break;
			default: 
				System.err.println("메뉴 입력 오류 : 메뉴를 확인하시고 다시 입력해주세요."); // syserr + Ctrl + Enter
				break;
			}
			System.out.println();
		}
	}
	// 메인 메서드.
	public static void main(String[] args) {
		MemberManager manager = new MemberManager();
		try {
			manager.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
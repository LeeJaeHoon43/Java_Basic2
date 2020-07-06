package ljh.java.exam02;

import java.awt.Button;
import java.awt.Frame;

public class MyFrame extends Frame { // 상속에 의한 프레임을 만드는 방식.
	// 멤버
	private static final long serialVersionUID = 1L; // 버전 관리.
	private Button bt = new Button("OK");
	private Button bt1 = new Button("Cancel");
	// 생성자
	public MyFrame()  { 
		super("Test");
		add(bt);
		add(bt1);
		setSize(300, 200); // this가 생략되어 있다.
		setVisible(true); 
	}
	
	// 메서드
	public static void main(String[] args) {
		new MyFrame();
	}
}

package ljh.java.exam03;

import java.awt.*;
/*
 * 내부 클래스(이너 클래스)를 통해서 이벤트 리스너를 작성하고 객체를 생성시켜서 연결하는 방식.
 * 장점 : 이너 클래스를 통해 파일 관리의 어려움을 해결할 수 있다.
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowEventEx3 extends Frame{
	// 멤버
	private static final long serialVersionUID = 1L;
	
	
	class MyEvent extends WindowAdapter{ // 이너 클래스로 이벤트를 구현한다.
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);;
		}
	}
	
	// 생성자
	public WindowEventEx3() {
		super("세 번째 방법");
		addWindowListener(new MyEvent()); // 객체를 생성하고 연결한다.
		setSize(300, 200);
		setVisible(true);
	}
	// 메서드
	public static void main(String[] args) {
		new WindowEventEx3();
	}
}

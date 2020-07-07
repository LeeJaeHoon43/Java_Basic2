package ljh.java.exam04;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*
 * 내부 익명 클래스(익명 이너 클래스)를 통해서 이벤트 리스너를 작성하고 객체를 생성시켜서 연결하는 방식.
 */

public class WindowEventEx4 extends Frame{
	// 멤버
	private static final long serialVersionUID = 1L;

	// 생성자
	public WindowEventEx4() {
		super("네 번째 방법");
		addWindowListener(new WindowAdapter() { // 객체 이름이 없다. 내부 익명 이너 클래스.
			@Override // 오버라이딩.
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setSize(300, 200);
		setVisible(true);
	}
	
	// 메서드
	public static void main(String[] args) {
		new WindowEventEx4();
	}
}

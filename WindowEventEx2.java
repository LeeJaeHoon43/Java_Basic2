package ljh.java.exam02;
/*
 * 이벤트 리스너를 프레임 자체에서 구현하는 방법.(상속을 받아서 해결한다.)
 * 장점 : 클래스 파일이 하나라서 파일 관리가 편하다.
 * 단점 : 어떤 메서드가 얽혀있는지 알기 쉽지가 않다.
 */
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowEventEx2 extends Frame implements WindowListener{ 
	// 멤버
	private static final long serialVersionUID = 1L;

	// 생성자
	public WindowEventEx2() {
		super("두 번째 연습");
		addWindowListener(this); // 상속을 받으면 객체를 생성하지 않아도 된다.
		setSize(300, 200);
		setVisible(true);
	}
	
	// 메서드
	public static void main(String[] args) {
		new WindowEventEx2();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
}

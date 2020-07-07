package ljh.java.exam01;

import java.awt.*;
import java.awt.event.*;
/*
 * 이벤트 리스너를 구현한 클래스를 작성 후, - WindowAdapter, WindowListener
 * 객체를 생성시켜서 연결한다.
 * 장점 : 객체에 바로 바로 연결할 수 있다.
 * 단점 : 이벤트가 많아서 파일 관리가 어렵다.
 */
class MyEvent extends WindowAdapter{
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);;
	}
}
public class WindowEventEx1 {
	public static void main(String[] args) {
		Frame frame = new Frame();
//		WinEvent we = new MyEvent(); 객체를 생성해서 연결해도 된다.
		frame.addWindowListener(new MyEvent());
		frame.setSize(300,200);
		frame.setVisible(true);
	}
}

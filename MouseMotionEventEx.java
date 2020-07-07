package ljh.java.exam10;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionEventEx extends Frame implements MouseMotionListener{
	// 멤버
	private static final long serialVersionUID = 1L;
	
	// 생성자
	public MouseMotionEventEx() {
		super("Move Test");
		addMouseMotionListener(this);
		setSize(500, 500);
		setVisible(true);
	}
	
	// 메서드
	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getSource() == this) {
			System.out.println("X = " + e.getX() + ", Y = " + e.getY());
		}	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (e.getSource() == this) {
			System.out.println("X = " + e.getX() + ", Y = " + e.getY());
		}	
	}
	
	public static void main(String[] args) {
		new MouseMotionEventEx();
	}

}

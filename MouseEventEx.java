package ljh.java.exam09;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEventEx extends Frame implements MouseListener{

	// 멤버
	private static final long serialVersionUID = 1L;
	private Button bt = new Button("확인"); 
	private Button bt1 = new Button("취소");

	// 생성자
	public MouseEventEx() {
		super("Mouse Test");
		setLayout(new FlowLayout());
		add(bt);
		add(bt1);
		setEvent();
		setSize(500, 500);
		setVisible(true);
	}
	private void setEvent() {
		bt.addMouseListener(this);
		bt1.addMouseListener(this);
	}
	// 메서드
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == bt) {
			System.out.println("확인 버튼");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == bt1) {
			System.out.println("취소 버튼");
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == bt1) {
			System.out.println("취소 버튼을 눌렀다 놓았다.");
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == bt || e.getSource() == bt1) {
			System.out.println("마우스가 버튼 위에 있지롱~~");
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == bt || e.getSource() == bt1){
			System.out.println("마우스가 버튼에서 내려왔당");
		}
	}
	public static void main(String[] args) {
		new MouseEventEx();
	}
}

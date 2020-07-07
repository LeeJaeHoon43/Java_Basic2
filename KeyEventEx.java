package ljh.java.exam14;

import java.awt.*;
import java.awt.event.*;

public class KeyEventEx extends Frame implements KeyListener{

	// 멤버 변수.
	private static final long serialVersionUID = 1L;
	Label la = new Label("주민번호 : ", Label.RIGHT);
	Label la1 = new Label("-", Label.CENTER);
	TextField jumin1 = new TextField(10);
	TextField jumin2 = new TextField(10);
	Button bt = new Button("확인");
	
	// 생성자
	public KeyEventEx() {
		super("KeyEventEx");
		setForm();
		setEvent();
		pack();
		setVisible(true);
	}
	
	
	private void setEvent() {
		jumin1.addKeyListener(this);
		jumin2.addKeyListener(this);
	}


	private void setForm() {
		setLayout(new BorderLayout());
		add("West", la);
		Panel p = new Panel(new FlowLayout(FlowLayout.LEFT));
		p.add("West",jumin1);
		p.add("Center", la1);
		p.add("East", jumin2);
		add("Center",p);
		Panel p1 = new Panel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(bt);
		add("South",p1);	
	}
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == jumin1) {
			if (jumin1.getText().trim().length() == 6) {
				jumin2.requestFocus();
			}
		}
		if (e.getSource() == jumin2) {
			if (jumin2.getText().trim().length() == 7) {
				bt.requestFocus();
			}
		}
	}
	public static void main(String[] args) {
		new KeyEventEx();
	}
	

}

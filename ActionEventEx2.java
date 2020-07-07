package ljh.java.exam06;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEventEx2 extends Frame implements ActionListener{
	// 멤버
	private static final long serialVersionUID = 1L;
	private Button bt = new Button("확인");
	// 생성자
	public ActionEventEx2() {
		super("ActionEventEx2");
		bt.addActionListener(this);
		setLayout(new FlowLayout());
		add(bt);
		setSize(500, 500);
		setVisible(true);
	}
	
	// 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
	
	public static void main(String[] args) {
		new ActionEventEx2();
	}
}

package ljh.java.exam05;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEventEx1 extends Frame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	// 멤버
	private TextField tf = new TextField(10);
	
	// 생성자
	public ActionEventEx1() {
		super("ActionEventEx1");
		setLayout(new FlowLayout());
		add(tf);
		tf.addActionListener(this);
		setSize(300, 200);
		setVisible(true);
	}
	
	// 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tf) {
			String imsi = tf.getText();
			System.out.println("메시지 : " + imsi);
			tf.setText("");
		}
		
	}
	
	public static void main(String[] args) {
		new ActionEventEx1();
	}
}

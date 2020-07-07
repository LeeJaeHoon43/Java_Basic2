package ljh.java.exam07;

import java.awt.*;
import java.awt.event.*;

public class ActionEventEx3 extends Frame implements ActionListener{
	
	// 멤버
	private static final long serialVersionUID = 1L;
	private MenuBar mb = new MenuBar();
	private Menu file = new Menu("File");
	private MenuItem now = new MenuItem("Now");
	private MenuItem exit = new MenuItem("Exit");
	
	// 생성자
	public ActionEventEx3() {
		super("ActionEventEx3");
		setMenuBar(mb);
		mb.add(file);
		file.add(now);
		file.addSeparator();
		file.add(exit);
		exit.addActionListener(this);
		setSize(500,500);
		setVisible(true);
	}

	// 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
	
	public static void main(String[] args) {
		new ActionEventEx3();
	}
}

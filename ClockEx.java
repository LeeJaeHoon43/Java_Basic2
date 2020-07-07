package ljh.java.exam08;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.Date;

public class ClockEx extends Frame implements ActionListener, Runnable, WindowListener{

	private static final long serialVersionUID = 1L;
	
	private Button bt = new Button("시작");
	
	public ClockEx() {
		super("시계");
		setLayout(new BorderLayout());
		add("South", bt);
		addWindowListener(this);
		bt.addActionListener(this);
		setSize(600,200);
		setVisible(true);
	}

	@Override
	public void run() {
		while(true) {
			repaint(); // 쓰고 다시 그린다.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt) {
			bt.setEnabled(false); // 버튼을 다시 누를 수 없게 한다.
			Thread tt = new Thread(this); // 쓰레드 객체 생성.
			tt.start(); 
		}
	}
	
	public void paint(Graphics g) {
		Calendar ca = Calendar.getInstance();
		Date d = ca.getTime();
		g.setFont(new Font("굴림체", Font.BOLD, 30));
		g.drawString(d.toString(), 50, 100);
	}
	
	public static void main(String[] args) {
		new ClockEx();
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}

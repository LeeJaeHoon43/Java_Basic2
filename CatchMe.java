package ljh.java.exam11;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.*;

public class CatchMe extends Frame implements MouseListener{

	private static final long serialVersionUID = 1L;
	private Button bt = new Button("메롱");
	
	// 생성자
	public CatchMe() {
		super("나 잡아봐라");
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(bt);
		bt.addMouseListener(this);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		setSize(500,500);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new CatchMe();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == this) {
			bt.setLocation(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == bt) {
			int x = (int)(Math.random()*530)+10;
			int y = (int)(Math.random()*430)+30;
			bt.setLocation(x, y);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {}
}

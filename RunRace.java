package ljh.java.exam10;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class RunRace extends Frame implements ActionListener, WindowListener{

	private static final long serialVersionUID = 1L;
	
	private Button bt = new Button("출발");
	private int count;
	private Racer[] ra;
	
	public RunRace(int k, String[] hn) {
		super("말 경주");
		count = k;
		setLayout(new BorderLayout());
		ra = new Racer[count];
		addWindowListener(this);
		Panel p = new Panel(new GridLayout(count, 1));
		for (int i = 0; i < count; i++) {
			ra[i] = new Racer(hn[i + 1]);
			p.add(ra[i]);
		}
		add("Center", p);
		
		Panel p1 = new Panel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(bt);
		bt.addActionListener(this);
		add("South", p1);
		setSize(600,400);
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt) {
			Thread[] th = new Thread[count];
			for (int i = 0; i < count; i++) {
				th[i] = new Thread(ra[i]);
				th[i].setName(ra[i].return_name());
				th[i].setPriority((int)(Math.random()*10)+1);
			}
			for (int i = 0; i < count; i++) {
				th[i].start();
			}
		}
	}
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Usage : java RacerRun count name1, ...");
			System.exit(0);
		}
		int xx = Integer.parseInt(args[0]);
		if (xx + 1 != args.length) {
			System.out.println("Error = (count != name count)");
			System.exit(0);			
		}
		RunRace r = new RunRace(xx, args);
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

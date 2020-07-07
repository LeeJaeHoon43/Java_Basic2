package ljh.java.exam02;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GraphicEx2 extends Frame{

	private static final long serialVersionUID = 1L;
	Button bt1, bt2, bt3;
	Panel north_p;
	Canvas can;
	private Color color;
	int x, y;
	
	public GraphicEx2() {
		north_p = new Panel();
		north_p.add(bt1 = new Button("Red")); 
		north_p.add(bt2 = new Button("Blue")); 
		north_p.add(bt3 = new Button("Green"));
		bt1.setBackground(Color.red);
		bt2.setBackground(Color.blue);
		bt3.setBackground(Color.green);
		bt1.setForeground(Color.white);
		bt2.setForeground(Color.white);
		
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("red");
				color = Color.red;
			}
		});
		
		bt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("blue");
				color = Color.blue;
			}
		});
		
		bt3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("green");
				color = Color.green;
			}
		});
		
		add(north_p, "North");
		add(can = new Canvas() {
			public void update(Graphics g) {
				paint(g);
			}
			public void paint(Graphics g) {
				g.setColor(color);
				g.fillOval(x-2, y-2, 4, 4);
			}
		});
		
		can.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				can.repaint(); // --> update()는 지우는 일이다.
			}
		});
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		setBounds(200,200,600,600);
		setVisible(true);
	}
	public static void main(String[] args) {
		new GraphicEx2();
	}
}

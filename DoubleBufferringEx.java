package ljh.java.exam09;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DoubleBufferringEx extends	Frame implements KeyListener, Runnable{

	private static final long serialVersionUID = 1L;
	
	Image image;
	int x, y, mx, my, sel;
	Graphics bufferGraphics;
	Image bufferImage;
	
	public DoubleBufferringEx() {
		super("더블 버퍼링 테스트");
		image = Toolkit.getDefaultToolkit().getImage("src/images/packman.jpg");
		x = 225; y = 255; mx = 0; my = 0; sel = 0;
		Thread t = new Thread(this);
		t.start();
		addKeyListener(this);
		setBounds(200, 200, 500, 500);
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		if (bufferImage != null) {
			g.drawImage(bufferImage, 0, 0, this);
		}
	}
	
	public void update(Graphics g) {
		Dimension d = getSize();	
		if (bufferGraphics == null) {
			bufferImage = createImage(d.width,d.height);
			bufferGraphics = bufferImage.getGraphics();
		}
		
		bufferGraphics.setColor(getBackground());
		bufferGraphics.fillRect(0, 0, 500, 500);
		bufferGraphics.drawImage(image, x, y, x+50, y+50, sel*50, 0, sel*50+50, 50, this);
		paint(g);
	}

	@Override
	public void run() {
		while(true) {
			x += mx;
			y += my;
			if (sel % 2 == 0) {
				sel++;
			}else {
				sel--;
			}
			if (x <= -50) {
				x = 500;
			}else if (x >= 500) {
				x = 0;
			}else if (y <= - 50) {
				y = 500;
			}else if (y >= 500) {
				y = 0;
			}
			try {
				repaint();
				Thread.sleep(100);
			} catch (Exception e) {}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_LEFT) {
			mx = -10;
			my = 0;
			sel = 0;
		}else if (e.getKeyCode() == e.VK_RIGHT) {
			mx = 10;
			my = 0;
			sel = 2;
		}else if (e.getKeyCode() == e.VK_UP) {
			mx = 0;
			my = -10;
			sel = 4;
		}else if (e.getKeyCode() == e.VK_DOWN) {
			mx = 0;
			my = 10;
			sel = 6;
		}else if (e.getKeyCode() == e.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	public static void main(String[] args) {
		new DoubleBufferringEx();
	}
}

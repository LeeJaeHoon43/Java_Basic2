package ljh.java.exam03;

import java.awt.*;

public class LabelEx extends Frame {

	private static final long serialVersionUID = 1L;
	
	Label la1;
	Label la2;
	Label la3;
	Label la4;
	
	Font f1;
	Font f2;
	
	LabelEx(){
		super("Label Exam");
		f1 = new Font("굴림체", Font.BOLD, 20);
		f2 = new Font("바탕체", Font.ITALIC, 25);
		la1 = new Label("안녕하세요!");
		la2 = new Label("홍길동입니다.",Label.CENTER);
		la3 = new Label("학생입니다.",Label.RIGHT);
		la4 = new Label("방가방가");
		setLayout(new GridLayout(4,1));
		la1.setFont(f1);
		la2.setFont(f2);
		la3.setFont(f2);
		la4.setFont(f2);
		add(la1);
		add(la2);
		add(la3);
		add(la4);
		la1.setBackground(Color.BLACK);
		la2.setBackground(Color.BLUE);
		la3.setBackground(Color.CYAN);
		la4.setBackground(Color.DARK_GRAY);
		setSize(300,200);
		setVisible(true);
	}
	public static void main(String[] args) {
		LabelEx ls = new LabelEx();
	}
}

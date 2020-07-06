package ljh.java.exam01;

import java.awt.Button;
import java.awt.Frame; // 1단계

public class FrameEx1 {
	// -Dfile.encoding=MS949 한글 깨짐 
	public static void main(String[] args) {
		// 객체를 생성해서 프레임을 만드는 방식.
		Frame frame = new Frame(); // 2단계
		Button bt = new Button("확인");
		frame.add(bt);
		Button bt1 = new Button("취소");
		frame.add(bt1);
		frame.setSize(300, 200); // 3단계
		frame.setVisible(true); // 4단계
	}
}

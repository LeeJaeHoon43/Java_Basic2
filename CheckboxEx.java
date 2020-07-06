package ljh.java.exam09;

import java.awt.*;

public class CheckboxEx extends Frame{
	private static final long serialVersionUID = 1L;
	private Label lb = new Label("좋아하는 과일은 ? ");
	private Checkbox[] cb = {new Checkbox("바나나"),
			new Checkbox("딸기", true), new Checkbox("파인애플")};
	private Button bt = new Button("확인");
	public CheckboxEx() {
		super("Checkbox");
		setLayout(new GridLayout(5, 1));
		add(lb);
		for (int i = 0; i < 3; i++) {
			add(cb[i]);
		}
		add(bt);
		pack();
		setVisible(true);
	}
	public static void main(String[] args) {
		CheckboxEx cs = new CheckboxEx();
	}
}

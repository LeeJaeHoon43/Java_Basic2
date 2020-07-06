package ljh.java.exam06;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;

public class TextEx extends Frame{
	private static final long serialVersionUID = 1L;
	private TextField tf = new TextField("제목 : ");
	private TextArea ta = new TextArea("메모", 5, 10);
	private Button bt = new Button("확인");
	private Label la = new Label("내용");
	
	public TextEx() {
		super("TextExtField");
		setLayout(new BorderLayout());
		add("North", tf);
		add("South", bt);
		add("Center", ta);
		add("West", la);
		setSize(300, 200);
		setVisible(true);
	}
	public static void main(String[] args) {
		TextEx te = new TextEx();
	}
}

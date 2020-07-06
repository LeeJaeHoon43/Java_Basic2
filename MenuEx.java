package ljh.java.exam11;

import java.awt.*;

public class MenuEx extends Frame{
	private static final long serialVersionUID = 1L;
	private MenuBar mb = new MenuBar();
	private Menu file = new Menu("파일");
	private Menu edit = new Menu("도구");
	private Menu view = new Menu("보기");
	private Menu help = new Menu("도움말");
	private MenuItem fnew = new MenuItem("새 파일");
	private MenuItem fopen = new MenuItem("파일 열기");
	private MenuItem fsave = new MenuItem("파일 저장");
	private MenuItem fsaves = new MenuItem("다른 이름으로 저장");
	private MenuItem fexit = new MenuItem("나가기");
	private Menu ecolor = new Menu("색상");
	private Menu esize = new Menu("크기");
	private CheckboxMenuItem ecred = new CheckboxMenuItem("빨강");
	private CheckboxMenuItem ecgreen = new CheckboxMenuItem("초록");
	private CheckboxMenuItem ecblue = new CheckboxMenuItem("파랑");
	private CheckboxMenuItem es10 = new CheckboxMenuItem("10");
	private CheckboxMenuItem es20 = new CheckboxMenuItem("20");
	private CheckboxMenuItem es30 = new CheckboxMenuItem("30");
	
	public MenuEx() {
		super("Menu Test");
		setMenuBar(mb);
		mb.add(file);
		mb.add(edit);
		mb.add(view);
		mb.add(help);
		ecolor.add(ecred);
		ecolor.add(ecgreen);
		ecolor.add(ecblue);
		esize.add(es10);
		esize.add(es20);
		esize.add(es30);
		edit.add(ecolor);
		edit.addSeparator();
		edit.add(esize);
		file.add(fnew);
		file.addSeparator();
		file.add(fopen);
		file.add(fsave);			
		file.add(fsaves);
		file.addSeparator();
		file.add(fexit);
		setLayout(new BorderLayout());
		add("North", new Label("수고하셨습니다.", Label.CENTER));
		add("Center", new TextArea());
		Panel p = new Panel(new GridLayout(1,2));
		p.add(new Button("확인"));
		p.add(new Button("취소"));
		add("South", p);
		setSize(300, 200);
		setVisible(true);
	}
	public static void main(String[] args) {
		MenuEx mt = new MenuEx();
	}
}

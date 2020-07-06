package ljh.java.exam14;

import java.awt.*;
public class FriendManager extends Frame{ 
	// -Dfile.encoding=MS949 한글 깨짐 
	// 멤버 변수
	private static final long serialVersionUID = 1L; 
	private MenuBar mb = new MenuBar(); 
	private Menu mfile = new Menu("File"); 
	private MenuItem mfnew = new MenuItem("New File"); 
	private MenuItem mfopen = new MenuItem("File Open"); 
	private MenuItem mfsave = new MenuItem("File Save"); 
	private MenuItem mfsaveas = new MenuItem("File Save As"); 
	private MenuItem mfexit = new MenuItem("Exit"); 
	private Menu mhelp = new Menu("Help"); 
	private MenuItem mhver = new MenuItem("Version Info"); 
	private TextField nametf = new TextField(20); 
	private TextField jumin1tf = new TextField(7); 
	private TextField jumin2tf = new TextField(8); 
	private String[] tstr = {"010","011","016","017","018","019"}; 
	private Choice telch = new Choice(); 
	private TextField tel1tf = new TextField(4); 
	private TextField tel2tf = new TextField(4); 
	private CheckboxGroup cg = new CheckboxGroup(); 
	private Checkbox man = new Checkbox("남자",cg,true); 
	private Checkbox woman = new Checkbox("여자",false,cg); 
	private String[] hstr = {"독서","영화","음악","게임","쇼핑"}; 
	private Checkbox[] hobby = new Checkbox[hstr.length]; 
	private Button addbt = new Button("등록"); 
	private Button dispbt = new Button("분석"); 
	private Button updatebt = new Button("수정"); 
	private Button delbt = new Button("삭제"); 
	private Button inputbt = new Button("입력모드"); 
	private TextArea infota = new TextArea(); 
	private java.awt.List listli = new java.awt.List(); 
	
	//생성자 
	public FriendManager() { 
		super("Friend Manager"); 
		setMenu(); 
		buildGUI(); 
	} 
	//메서드 
	public void setMenu() { 
		setMenuBar(mb); 
		mb.add(mfile); 
		mfile.add(mfnew); 
		mfile.addSeparator(); 
		mfile.add(mfopen); 
		mfile.add(mfsave); 
		mfile.add(mfsaveas); 
		mfile.addSeparator(); 
		mfile.add(mfexit); 
		mb.add(mhelp); 
		mhelp.add(mhver); 
	} 
	public void buildGUI() { 
		setBackground(Color.cyan); 
		setLayout(new BorderLayout(5,5)); 
		add("North", new Label()); 
		add("South", new Label()); 
		add("West", new Label()); 
		add("East", new Label()); 
		Panel mainPanel = new Panel(new BorderLayout(5,5)); 
		Panel cPanel = new Panel(new BorderLayout(5,5)); 
		cPanel.add("North", new Label("개인정보입력", Label.CENTER)); 

		Panel cwPanel = new Panel(new GridLayout(5,1,5,5)); 
		cwPanel.add(new Label("이    름: ", Label.RIGHT)); 
		cwPanel.add(new Label("주민번호: ", Label.RIGHT)); 
		cwPanel.add(new Label("전화번호: ", Label.RIGHT));
		cwPanel.add(new Label("성	별 : ", Label.RIGHT));
		cwPanel.add(new Label("취    미", Label.RIGHT)); 
		cPanel.add("West", cwPanel); 

		cPanel.add("East", new Label()); 

		Panel csPanel = new Panel(new FlowLayout()); 
		csPanel.add(addbt); 
		csPanel.add(dispbt); 
		csPanel.add(updatebt); 
		csPanel.add(delbt); 
		csPanel.add(inputbt); 
		cPanel.add("South",csPanel); 

		Panel ccPanel = new Panel(new GridLayout(5,1,5,5)); 
		Panel p1 = new Panel(new FlowLayout(FlowLayout.LEFT)); 
		p1.add(nametf); 
		ccPanel.add(p1); 

		Panel p2 = new Panel(new FlowLayout(FlowLayout.LEFT)); 
		p2.add(jumin1tf); 
		p2.add(new Label("-", Label.CENTER)); 
		p2.add(jumin2tf); 
		ccPanel.add(p2); 

		Panel p3 = new Panel(new FlowLayout(FlowLayout.LEFT)); 
		for(int i=0; i<tstr.length; i++) 
			telch.add(tstr[i]); 
		p3.add(telch); 
		p3.add(new Label("-", Label.CENTER)); 
		p3.add(tel1tf); 
		p3.add(new Label("-", Label.CENTER)); 
		p3.add(tel2tf); 
		ccPanel.add(p3); 

		Panel p4 = new Panel(new FlowLayout(FlowLayout.LEFT)); 
		p4.add(man); 
		p4.add(woman); 
		ccPanel.add(p4); 

		Panel p5 = new Panel(new FlowLayout(FlowLayout.LEFT)); 
		for(int i=0; i<hstr.length; i++) { 
			hobby[i] = new Checkbox(hstr[i]); 
			p5.add(hobby[i]); 
		} 
		ccPanel.add(p5); 
		cPanel.add("Center", ccPanel); 
		mainPanel.add("Center", cPanel); 

		Panel eastPanel = new Panel(new BorderLayout(5,5)); 
		eastPanel.add("North", new Label("전 체 목 록", Label.CENTER)); 
		eastPanel.add("Center", listli); 
		mainPanel.add("East", eastPanel); 

		Panel southPanel = new Panel(new BorderLayout(5,5)); 
		southPanel.add("North", new Label("개 인 정 보 분 석", Label.CENTER)); 
		southPanel.add("Center", infota); 
		mainPanel.add("South", southPanel); 
		add("Center", mainPanel); 
		setButton(true); 
		pack(); 
		
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize(); 
		Dimension my = getSize(); 
		setLocation(scr.width/2-my.width/2, scr.height/2-my.height/2); 
		nametf.requestFocus(); 
		setVisible(true); 
	} 
	public void setButton(boolean state) { 
		addbt.setEnabled(state); 
		dispbt.setEnabled(!state); 
		updatebt.setEnabled(!state); 
		delbt.setEnabled(!state); 
		inputbt.setEnabled(!state); 
	} 
	public static void main(String[] args) { 
		@SuppressWarnings("unused") 
		FriendManager manager = new FriendManager(); 
	} 
} 

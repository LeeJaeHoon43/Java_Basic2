package ljh.java.exam14;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
//-Dfile.encoding=MS949 : 한글 깨짐 현상 해결
public class FriendManagerEx extends Frame implements ActionListener, KeyListener{
	// 시리얼 넘버.
	private static final long serialVersionUID = 1L;

	// 데이터를 저장할 FriendVO 타입의 리스트.
	private ArrayList<FriendVO> member = new ArrayList<FriendVO>();

	// 멤버 변수.
	private MenuBar mb = new MenuBar();
	private Menu mfile = new Menu("File");
	private MenuItem mfnew = new MenuItem("새 파일");
	private MenuItem mfopen = new MenuItem("열기");
	private MenuItem mfsave = new MenuItem("저장");
	private MenuItem mfsaveas = new MenuItem("다른 이름으로 저장");
	private MenuItem mfexit = new MenuItem("종료");
	private Menu minfo = new Menu("Help");
	private MenuItem miver = new MenuItem("버전 정보");
	private TextField nametf = new TextField(30);
	private TextField jumin1tf = new TextField(7);
	private TextField jumin2tf = new TextField(8);
	private String[] tstr = {"010","011","016","017","018","019"}; 
	private Choice telch = new Choice(); 
	private TextField tel1tf = new TextField(5); 
	private TextField tel2tf = new TextField(5);
	private CheckboxGroup cg = new CheckboxGroup();
	private Checkbox man = new Checkbox("남자", cg, true);
	private Checkbox woman = new Checkbox("여자", cg, false);
	private String[] hstr = {"영화", "독서", "음악", "게임", "쇼핑"};
	private Checkbox[] hobby = new Checkbox[hstr.length];
	private Button addbt = new Button("등록");
	private Button dispbt = new Button("분석");
	private Button updatebt = new Button("수정");
	private Button deletebt = new Button("삭제");
	private Button clearbt = new Button("초기화");
	private TextArea infota = new TextArea();
	private List listli = new List();
	private Dialog d = new Dialog(this, "Program Version", true);
	Button dButton = new Button("확인");
	Label version = new Label("Friend Manager Version 1.0",Label.CENTER);

	// 생성자.
	public FriendManagerEx() {
		super("Friend Manager");
		addWindowListener(new MyEvent());
		setMenu();
		setEvent();
		buildGUI();
	}

	// 윈도우 어댑터를 상속받은 클래스들.
	class MyEvent extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	class MyDialogEvent extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			d.setVisible(false);
		}
	}

	// 메서드.
	private void setDialog() {
		d.setLayout(new BorderLayout());
		d.addWindowListener(new MyDialogEvent());
		Font f1 = new Font("굴림체",Font.BOLD,15);
		version.setFont(f1);
		d.add("South",dButton);
		d.add("Center",version);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension my = d.getSize();
		d.setLocation(screen.width / 2 - my.width / 2, screen.height / 2 - my.height / 2);
		d.setSize(300,200);
		d.setVisible(true);
	}

	private void setMenu() {
		setMenuBar(mb); 
		mb.add(mfile); 
		mfile.add(mfnew); 
		mfile.addSeparator(); 
		mfile.add(mfopen); 
		mfile.add(mfsave); 
		mfile.add(mfsaveas); 
		mfile.addSeparator(); 
		mfile.add(mfexit); 
		mb.add(minfo); 
		minfo.add(miver); 
	}

	public void setEvent() {
		nametf.addActionListener(this);
		jumin1tf.addKeyListener(this);
		jumin2tf.addKeyListener(this);
		tel1tf.addActionListener(this);
		tel2tf.addActionListener(this);
		listli.addActionListener(this);
		mfexit.addActionListener(this);
		miver.addActionListener(this);
		dButton.addActionListener(this);
	}

	private void buildGUI() {
		setBackground(Color.GRAY);
		setLayout(new BorderLayout()); // 5,5
		add("North", new Label());
		add("South", new Label());
		add("West", new Label());
		add("East", new Label());
		Panel mainPanel = new Panel(new BorderLayout()); // 5,5
		Panel centerPanel = new Panel(new BorderLayout()); // 5,5
		centerPanel.add("North", new Label("개인정보 입력",Label.CENTER));

		Panel centerwestPanel = new Panel(new GridLayout(5,1));
		centerwestPanel.add(new Label("이	름 : ", Label.RIGHT));
		centerwestPanel.add(new Label("주민번호 : ", Label.RIGHT));
		centerwestPanel.add(new Label("전화번호 : ", Label.RIGHT));
		centerwestPanel.add(new Label("성	별 : ", Label.RIGHT));
		centerwestPanel.add(new Label("취	미 ", Label.RIGHT));
		centerPanel.add("West",centerwestPanel);
		centerPanel.add("East", new Label());

		Panel centersouthPanel = new Panel(new FlowLayout());
		centersouthPanel.add(addbt);
		centersouthPanel.add(dispbt);
		centersouthPanel.add(updatebt);
		centersouthPanel.add(deletebt);
		centersouthPanel.add(clearbt);
		addbt.setEnabled(true);
		dispbt.setEnabled(false);
		updatebt.setEnabled(false);
		deletebt.setEnabled(false);
		clearbt.setEnabled(false);
		addbt.addActionListener(this);
		dispbt.addActionListener(this);
		updatebt.addActionListener(this);
		deletebt.addActionListener(this);
		clearbt.addActionListener(this);
		centerPanel.add("South",centersouthPanel);

		Panel centercenterPanel = new Panel(new GridLayout(5,1));
		Panel p1 = new Panel(new FlowLayout(FlowLayout.LEFT));
		p1.add(nametf);
		centercenterPanel.add(p1);

		Panel p2 = new Panel(new FlowLayout(FlowLayout.LEFT));
		p2.add(jumin1tf);
		p2.add(new Label("-", Label.CENTER));
		p2.add(jumin2tf);
		centercenterPanel.add(p2);

		Panel p3 = new Panel(new FlowLayout(FlowLayout.LEFT));
		for (int i = 0; i < tstr.length; i++) {
			telch.add(tstr[i]);
		}
		p3.add(telch);
		p3.add(new Label("-", Label.CENTER));
		p3.add(tel1tf);
		p3.add(new Label("-", Label.CENTER));
		p3.add(tel2tf);
		centercenterPanel.add(p3);

		Panel p4 = new Panel(new FlowLayout(FlowLayout.LEFT));
		p4.add(man);
		p4.add(woman);
		centercenterPanel.add(p4);

		Panel p5 = new Panel(new FlowLayout(FlowLayout.LEFT));
		for (int i = 0; i < hstr.length; i++) {
			hobby[i] = new Checkbox(hstr[i]);
			p5.add(hobby[i]);
		}
		centercenterPanel.add(p5);
		centerPanel.add("Center",centercenterPanel);
		mainPanel.add("Center", centerPanel);

		Panel eastPanel = new Panel(new BorderLayout()); // 5,5
		eastPanel.add("North",new Label("전 체 목 록", Label.CENTER));
		eastPanel.add("Center", listli);
		mainPanel.add("East",eastPanel);

		Panel southPanel = new Panel(new BorderLayout()); // 5,5
		southPanel.add("North",new Label("개 인 정 보 분 석", Label.CENTER));
		southPanel.add("Center", infota);
		mainPanel.add("South", southPanel);
		add("Center", mainPanel);
		setSize(600,600);
		setVisible(true);
	}

	// 등록 버튼.
	public void insertData() {
		FriendVO vo = new FriendVO();

		vo.setName(nametf.getText());
		nametf.setText("");

		vo.setJumin1(jumin1tf.getText());
		vo.setJumin2(jumin2tf.getText());
		jumin1tf.setText("");
		jumin2tf.setText("");

		vo.setTel(telch.getSelectedItem());
		vo.setTel1(tel1tf.getText());
		vo.setTel2(tel2tf.getText());
		telch.select(0);
		tel1tf.setText("");
		tel2tf.setText("");

		if(man.getState()) {
			vo.setGender("남성");
		}else {
			vo.setGender("여성");
		}
		man.setState(true);

		String imsi = "";
		for (int i = 0; i < hobby.length; i++) {
			if (hobby[i].getState()) {
				imsi += hobby[i].getLabel() + "-";
			}
		}
		String[] imsiH = imsi.split("-");
		vo.setHobby(imsiH);
		for (int i = 0; i < hobby.length; i++) {
			hobby[i].setState(false);
		}

		nametf.removeKeyListener(this);
		telch.removeKeyListener(this);
		tel1tf.removeKeyListener(this);
		tel2tf.removeKeyListener(this);

		member.add(vo);

		listli.add(vo.getName());

		infota.setText("\n\t성공적으로 등록되었습니다!");
	}

	// 분석 버튼.
	public void analysisData(int position) {
		FriendVO vo = member.get(position);
		FriendCheck check = new FriendCheck();

		int[] jumin = check.getIntJumin(vo.getJumin1(), vo.getJumin2());
		boolean search = check.juminCheck(jumin);
		boolean sex = false;
		if (jumin[6] % 2 == 1) {
			if (vo.getGender().equals("남성")) {
				sex = true;
			}
		}
		if (jumin[6] % 2 == 0) {
			if (vo.getGender().equals("여성")) {
				sex = true;
			}
		}
		if (search == false || sex == false) {
			infota.setText("\n\t잘못된 주민등록번호 입니다.\n\t주민등록번호를 제대로 입력하세요.");
		}else {
			int year, month, day, age;
			String[] area = {"서울","경기","강원","충북","충남","전북","전남","경북","경남","제주"};

			year = jumin[0] * 10 + jumin[1];

			if(jumin[6] == 9 || jumin[6] == 0) {
				year += 1800;
			}else if(jumin[6] == 1 || jumin[6] == 2	) {
				year += 1900;
			}else if(jumin[6] == 3 || jumin[6] == 4) {
				year += 2000;
			}

			month = jumin[2] * 10 + jumin[3];
			day = jumin[4] * 10 + jumin[5];
			age = (Calendar.getInstance().get(Calendar.YEAR) - year + 1);

			infota.setText("\n\t이름 = " + vo.getName() +
					"\n\n\t생년월일 = " + year + "년" + month + " 월" + day + " 일" +
					"\n\n\t나이 = " + age + "세" +
					"\n\n\t성별 = " + vo.getGender() +
					"\n\n\t출생지역 = " + area[jumin[7]] + "지역");
		}
	}

	// 수정 버튼.
	public void updateData(String name) {
		FriendVO vo = new FriendVO();
		for (int i = 0; i < member.size(); i++) {
			if (member.get(i).getName().equals(name)) {
				vo = member.get(i);
				break;
			}
		}

		vo.setName(nametf.getText());
		vo.setJumin1(jumin1tf.getText());
		vo.setJumin2(jumin2tf.getText());
		vo.setTel(telch.getSelectedItem());
		vo.setTel1(tel1tf.getText());
		vo.setTel2(tel2tf.getText());

		if (man.getState()) {
			vo.setGender("남성");
		}else {
			vo.setGender("여성");
		}

		String imsi = "";
		for(int i=0; i<hobby.length; i++) {
			if(hobby[i].getState()) {
				imsi += hobby[i].getLabel() + "-";
			}
		}
		vo.setHobby(null);
		String[] imsiH = imsi.split("-");
		vo.setHobby(imsiH);

		nametf.removeKeyListener(this);
		telch.removeKeyListener(this);
		tel1tf.removeKeyListener(this);
		tel2tf.removeKeyListener(this);

		member.add(vo);

		infota.setText("\n\t성공적으로 수정되었습니다!");
	}

	// 삭제 버튼.
	public void deleteData(int position) {
		FriendVO vo = new FriendVO();
		vo = member.get(position);
		listli.remove(vo.getName());
		member.remove(position);
		clearData();
		infota.setText("\n\t성공적으로 삭제 되었습니다!");
	}

	// 초기화 버튼.
	private void clearData() {
		nametf.setText("");
		jumin1tf.setText("");
		jumin2tf.setText("");
		telch.select(0);
		tel1tf.setText("");
		tel2tf.setText("");
		man.setState(true);
		for (int i = 0; i < hobby.length; i++) {
			hobby[i].setState(false);
		}
		nametf.setEnabled(true);
		jumin1tf.setEnabled(true);
		jumin2tf.setEnabled(true);
		man.setEnabled(true);
		woman.setEnabled(true);
		addbt.setEnabled(true);
		dispbt.setEnabled(false);
		updatebt.setEnabled(false);
		deletebt.setEnabled(false);
		clearbt.setEnabled(false);		
		infota.setText("");
	}

	// 저장된 정보 보여주기.
	public void infoData(int position) {
		FriendVO vo = new FriendVO();
		vo = member.get(position);

		// Text창에 띄우기.
		nametf.setText(vo.getName());
		jumin1tf.setText(vo.getJumin1());
		jumin2tf.setText(vo.getJumin2());
		telch.select(vo.getTel());
		tel1tf.setText(vo.getTel1());
		tel2tf.setText(vo.getTel2());
		if (vo.getGender().equals("남성")) {
			man.setState(true);
		}else {
			woman.setState(true);
		}
		for(int i=0; i<hobby.length; i++) {
			hobby[i].setState(false);
		}
		String[] imsi = vo.getHobby();
		for(int i=0; i<hobby.length; i++) {
			for(int j=0; j<imsi.length; j++) {
				if(imsi[j].equals(hobby[i].getLabel())) {
					hobby[i].setState(true);
				}
			}
		}
		nametf.setEnabled(false);
		jumin1tf.setEnabled(false);
		jumin2tf.setEnabled(false);
		man.setEnabled(false);
		woman.setEnabled(false);
		addbt.setEnabled(false);
		dispbt.setEnabled(true);
		updatebt.setEnabled(true);
		deletebt.setEnabled(true);
		clearbt.setEnabled(true);	
	}

	// 오버라이딩한 액션이벤트 메서드.
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == nametf) {
			nametf.addKeyListener(this);
		}else if(e.getSource() == telch) {
			telch.addKeyListener(this);
		}else if(e.getSource() == tel1tf) {
			tel1tf.addKeyListener(this);
		}else if(e.getSource() == tel2tf) {
			tel2tf.addKeyListener(this);
		}
		if (e.getSource() == mfexit) {
			System.exit(0);
		}
		if (e.getSource() == miver) {
			setDialog();
		}
		if(e.getSource() == dButton) {
			d.setVisible(false);
		}
		if (e.getSource() == addbt) {
			insertData();
		}
		if (e.getSource() == dispbt) {
			for (int i = 0; i < member.size(); i++) {
				if (listli.getSelectedIndex() == i) {
					analysisData(i);
					break;
				}
			}
		}
		if (e.getSource() == updatebt) {
			updateData(nametf.getText());
		}		
		if (e.getSource() == deletebt) {
			for (int i = 0; i < member.size(); i++) {
				if (listli.getSelectedIndex() == i) {
					deleteData(i);

				}
			}

		}
		if (e.getSource() == clearbt) {
			clearData();
		}
		if(e.getSource() == listli) {
			for(int i=0; i<member.size(); i++) {
				if(listli.getSelectedIndex() == i) {
					infoData(i);
					break;
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource() == nametf) {
			jumin1tf.requestFocus();
		}else if(e.getSource() == jumin1tf) {
			if(jumin1tf.getText().trim().length() == 6)
				jumin2tf.requestFocus();
		}else if(e.getSource() == jumin2tf) {
			if(jumin2tf.getText().trim().length() == 7) {
				telch.requestFocus();
			}
		}else if(e.getSource() == telch) {
			tel1tf.requestFocus();
		}else if(e.getSource() == tel1tf) {
			tel2tf.requestFocus();
		}
	}

	// 메인 메서드.
	public static void main(String[] args) {
		new FriendManagerEx();
	}
}
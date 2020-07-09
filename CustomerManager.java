package ljh.java.cms;

import java.awt.*;
import java.awt.event.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import ljh.java.cms.vo.Customer;

public class CustomerManager extends Frame implements ActionListener, KeyListener, ItemListener{
	//멤버
	//--------------------- 화면 관련 컴포넌트 ---------------------------//
	private static final long serialVersionUID = 1L;

	private TextField nametf = new TextField(25);

	private TextField jumin1tf = new TextField(7);
	private TextField jumin2tf = new TextField(8);

	private Choice telch = new Choice();
	private String[] tstr = {"010", "011", "016", "017", "018", "019"};
	private TextField tel1tf = new TextField(4);
	private TextField tel2tf = new TextField(4);

	private CheckboxGroup cg = new CheckboxGroup();
	private Checkbox man = new Checkbox("남성", true, cg);
	private Checkbox woman = new Checkbox("여성", cg, false);

	private String[] hstr = {"독서", "영화", "음악", "게임", "쇼핑"}; 
	private Checkbox[] hobby = new Checkbox[hstr.length];// 배열 초기화

	private Button addbt = new Button("등록");
	private Button dispbt = new Button("분석");
	private Button updatebt = new Button("수정");
	private Button delbt = new Button("삭제");
	private Button modebt = new Button("입력모드");

	private TextArea infota = new TextArea();

	private List listli = new List();

	//--------------------- 메뉴 관련 컴포넌트 ---------------------------//
	private MenuBar mb = new MenuBar();
	private Menu mfile = new Menu("파일");
	private MenuItem mfnew = new MenuItem("새 파일");
	private MenuItem mfopen = new MenuItem("열기");
	private MenuItem mfsave = new MenuItem("저장");
	private MenuItem mfsaveas = new MenuItem("다른 이름으로 저장");
	private MenuItem mfexit = new MenuItem("끝내기");
	private Menu mhelp = new Menu("도움말");
	private MenuItem mhinfo = new MenuItem("버전 정보");
	//-----------------------------------------------파일 관련 컴포넌트 ---------------------------------------------------------//
	private String dfName = "";
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	//--------------------- 다이알로그 관련 컴포넌트 ---------------------------//
	private Dialog dialog = new Dialog(this, "CMS Version", true);
	private Label dlabel = new Label("Customer Manager Versioin 1.0", Label.CENTER);
	private Button dbt = new Button("확인");
	FileDialog fd = new FileDialog(this,"저장",FileDialog.SAVE);

	//--------------------- 기타 프로그램 관련 -----------------------//
	private ArrayList<Customer> data = new ArrayList<Customer>();

	//생성자
	public CustomerManager(){
		super("Customer Manager");
		setDialog(); // 다이알로그 구성
		setMenu(); // 메뉴 구성
		buildGUI(); // 화면 구성
		setEvent(); // 이벤트 구성
		setVisible(true);
	}
	// 메서드
	/*
	public void fileOpen() {

		listli.removeAll();
		data.clear();
		fd = new FileDialog(this,"열기",FileDialog.LOAD);
		fd.setVisible(true);
		if (fd.getFile() == null) {
			infota.setText("\n\t파일 열기를 취소하셨습니다.");
			return;
		}
		dfName = fd.getDirectory() + fd.getFile();
		try {
			reader = new BufferedReader(new FileReader(fdName));
			infota.setText("");
			String line;
			while((line = reader.readLine()) != null) {
				infota.append(line+"\r\n");
			}
			reader.close();
			setTitle(fd.getFile());
		}catch (EOFException eofe) {
			infota.setText("\n\t성공적으로 불러왔습니다.");
			clrscr();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	*/
	
	// 파일 열기 메서드. ObjectInputStream 활용, 역직렬화.
	public void openFile() {
		listli.removeAll();
		data.clear();
		fd = new FileDialog(this,"열기",FileDialog.LOAD);
		fd.setVisible(true);
		if (fd.getFile() == null) {
			infota.setText("\n\t파일 열기를 취소하셨습니다.");
			return;
		}
		dfName = fd.getDirectory() + fd.getFile();
		try {
			// 역직렬화.
			ois = new ObjectInputStream(new FileInputStream(dfName));
			while(true) {
				Customer vo = (Customer)ois.readObject();
				data.add(vo);
				listli.add(vo.toString());
			}
		}catch (EOFException eofe) {
			infota.setText("\n\t성공적으로 불러왔습니다.");
			clrscr();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 파일 저장 메서드. 
	/*
	public void saveFile() {
		listli.removeAll();
		if(dfName.equals("")) {
			saveAsFile();
		}else {
			fd.setVisible(false);
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(dfName));
				for (int i = 0; i < data.size(); i++) {
					writer.write("[이름 : "+data.get(i).getName()+", 주민등록번호 : " + data.get(i).getJumin() + ", 전화번호 : " + 
							data.get(i).getTel() + ", 성별 : " + (data.get(i).isGender()?"남성":"여성") + 
							", 취미 : " + data.get(i).getHobby() + "]\r\n");	
				}
	
				writer.flush();

			} catch (FileNotFoundException fnfe){
				fnfe.printStackTrace();
			} catch (IOException ioe){
				ioe.printStackTrace();
			} finally {
				try {
					if (oos != null) {
						oos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				data.clear();
			} 
		}
	}
	*/
	
	// 파일 저장 메서드. ObjectOutputStream 활용, 역직렬화
	public void saveFile() {
		listli.removeAll();
		if(dfName.equals("")) {
			saveAsFile();
		}else {
			fd.setVisible(false);
			try {
				oos = new ObjectOutputStream(new FileOutputStream(dfName));
				Customer vo = new Customer();
				for (int i = 0; i < data.size(); i++) {
					vo = data.get(i);
					oos.writeObject(vo);
					oos.flush();
				}	
			} catch (FileNotFoundException fnfe){
				fnfe.printStackTrace();
			} catch (IOException ioe){
				ioe.printStackTrace();
			} finally {
				try {
					if (oos != null) {
						oos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				data.clear();
			} 
		}
	}
	/*
	public void fileSaveAs() {
		listli.removeAll();
		fd = new FileDialog(this, "저장", FileDialog.SAVE);
		fd.setVisible(true);
		dfName = fd.getDirectory() + fd.getFile();

		if(fd.getFile() == null) {
			infota.setText("\n\t저장을 취소하셨습니다.");
			return;
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(dfName));
			for (int i = 0; i < data.size(); i++) {
				writer.write("[이름 : "+data.get(i).getName()+", 주민등록번호 : " + data.get(i).getJumin() + ", 전화번호 : " + 
						data.get(i).getTel() + ", 성별 : " + (data.get(i).isGender()?"남성":"여성") + 
						", 취미 : " + data.get(i).getHobby() + "]\r\n");	
			}
			writer.flush();
			writer.close();
		}catch (FileNotFoundException fnfe){
			fnfe.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			data.clear();
		}
	}
	*/

	// 파일 다른 이름으로 저장하는 메서드. ObjectOutputStream 활용.
	public void saveAsFile() {
		listli.removeAll();
		fd = new FileDialog(this, "저장", FileDialog.SAVE);
		fd.setVisible(true);
		dfName = fd.getDirectory() + fd.getFile();

		if(fd.getFile() == null) {
			infota.setText("\n\t저장을 취소하셨습니다.");
			return;
		}
		try {
			oos = new ObjectOutputStream(new FileOutputStream(dfName));
			Customer vo = new Customer();
			for (int i = 0; i < data.size(); i++) {
				vo = data.get(i);
				oos.writeObject(vo);
			}
			dfName = fd.getDirectory()+fd.getFile();	
		}catch (FileNotFoundException fnfe){
			fnfe.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			data.clear();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource() == jumin1tf) {
			if(jumin1tf.getText().trim().length() == 6) {
				jumin2tf.requestFocus();
				return;
			}
		}
		if(e.getSource() == jumin2tf) {
			if(jumin2tf.getText().trim().length() == 7) {
				telch.requestFocus();
				return;
			}
		}
		if(e.getSource() == tel1tf) {
			if(tel1tf.getText().trim().length() == 4) {
				tel2tf.requestFocus();
				return;
			}
		}
		if(e.getSource() == tel2tf) {
			if(tel2tf.getText().trim().length() == 4) {
				man.requestFocus();
				return;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == telch) {
			tel1tf.requestFocus();
			return;
		}
		if(e.getSource() == listli) { // 특정인을 선택.
			int index = listli.getSelectedIndex();

			Customer customer = data.get(index);

			nametf.setText(customer.getName());
			try {
				jumin1tf.setText(customer.getJumin().substring(0, 6));
				jumin2tf.setText(customer.getJumin().substring(6));
			} catch (StringIndexOutOfBoundsException e2) {
				infota.setText("주민등록번호를 정확히 입력해주십시오.");
			}
			try {
				String[] imsiTel = customer.getTel().split("-");
				telch.select(imsiTel[0]);
				tel1tf.setText(imsiTel[1]);
				tel2tf.setText(imsiTel[2]);
			} catch (ArrayIndexOutOfBoundsException e2) {
				infota.setText("전화번호를 정확히 입력해주십시오.");
			}
			man.setState(customer.isGender());
			woman.setState(!customer.isGender());

			for(int i=0; i<hobby.length; i++) {
				hobby[i].setState(false);
			}
			String[] myhobby = customer.getHobby().split("-");
			for(int i=0; i<myhobby.length; i++) {
				for(int j=0; j<hobby.length; j++) {
					if(myhobby[i].equals(hobby[j].getLabel())) {
						hobby[j].setState(true);
						break;
					}
				}
			}
			infota.setText("\n\t" + customer.getName() + "님의 정보를 성공적으로 로딩하였습니다.");
			setForm(false);
			setButton(false);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			infota.setText("");
		}
	}// end Item

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == nametf) {
			if(nametf.getText().trim().length() == 0) {
				nametf.setText("");
				return;
			}
			jumin1tf.requestFocus();
			return;
		}

		if(e.getSource() == addbt) { //등록 버튼 기능.
			String name = nametf.getText().trim();
			String jumin = jumin1tf.getText().trim() + jumin2tf.getText().trim();
			String tel = telch.getSelectedItem() + "-" + tel1tf.getText().trim() + "-" + tel2tf.getText().trim();
			boolean gender = man.getState(); // true 남자 , false 여자
			String myhobby = "";
			for(int i=0; i<hobby.length; i++) {
				if(hobby[i].getState()) {
					myhobby += hobby[i].getLabel() + "-";
				}
			}
			if(myhobby.length() == 0) {
				myhobby = "없음";
			}else {
				myhobby = myhobby.substring(0, myhobby.length()-1);
			}

			Customer customer = new Customer(name, jumin, tel, gender, myhobby);

			data.add(customer);
			infota.setText("\n\t" + name + "님의 정보를 성공적으로 등록하였습니다.");
			listli.add(customer.toString());
			clrscr(); // 화면 지우기(초기화) 메서드.
		}
		if(e.getSource() == modebt) { // 입력 모드 기능
			setForm(true);
			setButton(true);
			clrscr();
			nametf.requestFocus();
			return;
		}
		if(e.getSource() == dispbt) { // 분석 버튼 기능 
			int index = listli.getSelectedIndex();
			Customer customer = data.get(index);
			String jumin = customer.getJumin().trim();
			String jumin1 = jumin.substring(0, 6);
			String jumin2 = jumin.substring(6);
			int[] juminNumber = getJumin(jumin1, jumin2);
			boolean search = juminCheck(juminNumber);
			if (search == false) {
				infota.setText("\n\t잘못된 주민등록번호 입니다.\n\t주민등록번호를 제대로 입력하세요.");
			}else {
				int year, month, day, age;
				String[] area = {"서울","경기","강원","충북","충남","전북","전남","경북","경남","제주"};

				year = juminNumber[0] * 10 + juminNumber[1];

				if(juminNumber[6] == 9 || juminNumber[6] == 0) {
					year += 1800;
				}else if(juminNumber[6] == 1 || juminNumber[6] == 2	) {
					year += 1900;
				}else if(juminNumber[6] == 3 || juminNumber[6] == 4) {
					year += 2000;
				}

				month = juminNumber[2] * 10 + juminNumber[3];
				day = juminNumber[4] * 10 + juminNumber[5];
				age = (Calendar.getInstance().get(Calendar.YEAR) - year + 1);

				infota.setText("\n\t이름 = " + customer.getName() +
						"\n\n\t생년월일 = " + year + "년" + month + " 월" + day + " 일" +
						"\n\n\t나이 = " + age + "세" +
						"\n\n\t성별 = " + (juminNumber[6]%2==0 ? "여성" : "남성") +
						"\n\n\t출생지역 = " + area[juminNumber[7]] + "지역");
			}
			return;
		}

		if(e.getSource() == updatebt) { // 수정 버튼 기능
			String tel = telch.getSelectedItem() + "-" + tel1tf.getText().trim() + "-" + tel2tf.getText().trim();
			String myhobby = "";
			for(int i=0; i<hobby.length; i++) {
				if(hobby[i].getState()) {
					myhobby += hobby[i].getLabel() + "-";
				}
			}
			if(myhobby.length() == 0) {
				myhobby = "없음";
			}else {
				myhobby = myhobby.substring(0, myhobby.length()-1);
			}

			int index = listli.getSelectedIndex();
			Customer customer = data.get(index);

			customer.setTel(tel);
			customer.setHobby(myhobby);

			infota.setText("\n\t" + customer.getName() + "님의 정보를 성공적으로 수정하였습니다.");

			setForm(true);
			setButton(true);
			clrscr();
			return;
		}
		if(e.getSource() == delbt) { // 삭제 버튼 기능
			int index = listli.getSelectedIndex();
			Customer customer = data.get(index);
			listli.remove(index);
			infota.setText("\n\t" + customer.getName() + "님의 정보를 성공적으로 삭제하였습니다.");
			data.remove(index);

			setForm(true);
			setButton(true);
			clrscr();
			return;
		}
		if(e.getSource() == mfexit) { // 메뉴 - 파일 - 끝내기.
			System.exit(0);
		}
		if(e.getSource() == mhinfo) { // 메뉴 - 도움말 - 버전 정보
			dialog.setVisible(true);
			return;
		}
		if(e.getSource() == dbt) { 
			dialog.setVisible(false);
			return;
		}
		if (e.getSource() == mfsave) {
			saveFile();
			saveClr();
			return;
		}
		if (e.getSource() == mfopen) {
			openFile();
			return;
		}
		if (e.getSource() == mfsaveas) {
			saveAsFile();
			saveClr();
			return;
		}
		if (e.getSource() == mfnew) { // 새 파일을 눌렀을 시. (ArrayList, List목록, TextField 등 초기화)
			clrscr();
			listli.removeAll();
			for (int i = 0; i < data.size(); i++) {
				data.remove(i);
			}
			dfName = null;
			return;
		}
	}//end Action

	// 이벤트 처리 메서드.
	private void setEvent() {
		mfnew.addActionListener(this);
		mfsaveas.addActionListener(this);
		mfsave.addActionListener(this);
		mfopen.addActionListener(this);
		dbt.addActionListener(this);
		mhinfo.addActionListener(this);
		mfexit.addActionListener(this);
		delbt.addActionListener(this);
		updatebt.addActionListener(this);
		dispbt.addActionListener(this);
		modebt.addActionListener(this);
		listli.addItemListener(this);
		addbt.addActionListener(this);
		tel2tf.addKeyListener(this);
		tel1tf.addKeyListener(this);
		telch.addItemListener(this);
		jumin2tf.addKeyListener(this);
		jumin1tf.addKeyListener(this);
		nametf.addActionListener(this);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	// 메뉴 세팅 메서드.
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
		mb.add(mhelp);
		mhelp.add(mhinfo);
	}

	// 도움말 -> 버전 정보의 Dialog 구현 메서드.
	private void setDialog() {
		dialog.setBackground(new Color(125, 200, 165));
		dialog.add("North", new Label());
		dialog.add("South", new Label());
		dialog.add("West", new Label());
		dialog.add("East", new Label());

		Panel dcPanel = new Panel(new BorderLayout(3,3));
		dlabel.setFont(new Font("휴먼매직체", Font.BOLD, 15));
		dcPanel.add("Center", dlabel);

		Panel dcsPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
		dcsPanel.add(dbt);
		dcPanel.add("South", dcsPanel);
		dialog.add("Center", dcPanel);

		dialog.pack();
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dsize = dialog.getSize();
		dialog.setLocation(scr.width/2-dsize.width/2, scr.height/2-dsize.height/2);
		dialog.setResizable(false);
	}
	// 주민등록번호를 얻어오는 메서드.
	private int[] getJumin(String jumin1, String jumin2) {
		int jumin[] = new int[13];
		int f = Integer.parseInt(jumin1);
		int l = Integer.parseInt(jumin2);
		jumin[0] = f / 100000;
		f %= 100000;
		jumin[1] = f / 10000;
		f %= 10000;
		jumin[2] = f / 1000;
		f %= 1000;
		jumin[3] = f / 100;
		f %= 100;
		jumin[4] = f / 10;
		f %= 10;
		jumin[5] = f;

		jumin[6] = l / 1000000;
		l %= 1000000;
		jumin[7] = l / 100000;
		l %= 100000;
		jumin[8] = l / 10000;
		l %= 10000;
		jumin[9] = l / 1000;
		l %= 1000;
		jumin[10] = l / 100;
		l %= 100;
		jumin[11] = l / 10;
		l %= 10;
		jumin[12] = l;
		return jumin;
	}

	// 주민등록번호 확인 메서드.
	private boolean juminCheck(int[] jumin) {
		float temp = 0.f;
		float total = 0.f;
		float ct = 2.f;
		float hap = 0.f;
		for(int i=0; i<jumin.length-1; i++){
			if(ct == 10.f) {
				ct = 2.f;
			}
			hap += jumin[i] * ct;
			ct++;
		}
		temp = 11.f * (int)(hap/11.f) + 11.f - hap;
		total = temp - 10.f * (int)(temp/10.f);
		if((int)total != jumin[jumin.length-1]){
			return false;
		}else {
			return true;
		}
	}

	// 화면 설정 메서드.
	private void setForm(boolean status) {
		nametf.setEditable(status);
		jumin1tf.setEditable(status);
		jumin2tf.setEditable(status);
		man.setEnabled(status);
		woman.setEnabled(status);
	}

	// 버튼 설정 메서드.
	private void setButton(boolean status) {
		addbt.setEnabled(status);
		dispbt.setEnabled(!status);
		updatebt.setEnabled(!status);
		delbt.setEnabled(!status);
		modebt.setEnabled(!status);
	}
	// 저장, 다른 이름으로 저장 시 화면 초기화할 코드.
	private void saveClr() {
		setForm(true);
		setButton(true);
		nametf.setText("");
		jumin1tf.setText("");
		jumin2tf.setText("");
		telch.select(0);
		tel1tf.setText("");
		tel2tf.setText("");
		man.setState(true);
		
		for(int i=0;i<hobby.length;i++) {
			hobby[i].setState(false);
		}
		infota.setText("\n\t성공적으로 저장하였습니다.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		infota.setText("");
		nametf.requestFocus();
	}

	// 기본 화면 초기화 메서드.
	private void clrscr() {
		nametf.setText("");
		jumin1tf.setText("");
		jumin2tf.setText("");
		telch.select(0);
		tel1tf.setText("");
		tel2tf.setText("");
		man.setState(true);
		for(int i=0; i<hobby.length; i++)
			hobby[i].setState(false);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		infota.setText("");
		nametf.requestFocus();
	}

	// 화면 구현 메서드.
	private void buildGUI() {
		setBackground(Color.cyan);
		add("North", new Label());
		add("South", new Label());
		add("West", new Label());
		add("East", new Label());

		Panel mainPanel = new Panel(new BorderLayout(3,3));
		Panel eastPanel = new Panel(new BorderLayout(3,3));
		eastPanel.add("North", new Label("전 체 목 록", Label.CENTER));
		eastPanel.add(listli);
		mainPanel.add("East", eastPanel);

		Panel southPanel = new Panel(new BorderLayout(3,3));
		southPanel.add("North", new Label("고 객 정 보 분 석", Label.CENTER));
		southPanel.add(infota);
		mainPanel.add("South", southPanel);

		Panel centerPanel = new Panel(new BorderLayout(3,3));
		centerPanel.add("North", new Label("고 객 정 보 입 력", Label.CENTER));

		Panel cwPanel = new Panel(new GridLayout(5,1,3,3));
		cwPanel.add(new Label("이        름 : ", Label.RIGHT));
		cwPanel.add(new Label("주민번호 : ", Label.RIGHT));
		cwPanel.add(new Label("전화번호 : ", Label.RIGHT));
		cwPanel.add(new Label("성        별 : ", Label.RIGHT));
		cwPanel.add(new Label("취        미 : ", Label.RIGHT));
		centerPanel.add("West", cwPanel);

		centerPanel.add("East", new Label());

		Panel csPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
		csPanel.add(addbt);
		csPanel.add(dispbt);
		csPanel.add(updatebt);
		csPanel.add(delbt);
		csPanel.add(modebt);
		centerPanel.add("South", csPanel);

		Panel ccPanel = new Panel(new GridLayout(5,1,3,3));
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
			telch.add(tstr[i]); //전화번호 등록
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
			hobby[i] = new Checkbox(hstr[i]); //체크박스 초기화
			p5.add(hobby[i]);
		}
		ccPanel.add(p5);
		centerPanel.add("Center", ccPanel);
		mainPanel.add("Center", centerPanel);

		add("Center", mainPanel);

		pack();
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension my = getSize();
		setLocation(scr.width/2-my.width/2, scr.height/2-my.height/2);
		setResizable(false);
		fd.setVisible(false);
		setButton(true);
		setForm(true);
		nametf.requestFocus();
	}

	// 메인 메서드.
	public static void main(String[] args) {
		new CustomerManager();
	}
}
package ljh.java.cms;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import ljh.java.cms.vo.Customer;

public class CustomerManager2 extends Frame implements ActionListener, KeyListener, ItemListener{
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
      private Menu mfile = new Menu("File");
         private MenuItem mfnew = new MenuItem("New File");
         private MenuItem mfopen = new MenuItem("Open File");
         private MenuItem mfsave = new MenuItem("Save File");
         private MenuItem mfsaveas = new MenuItem("SaveAs File");
         private MenuItem mfexit = new MenuItem("Exit");
      private Menu mhelp = new Menu("Help");
         private MenuItem mhinfo = new MenuItem("Version Information");
   
   //--------------------- 다이알로그 관련 컴포넌트 ---------------------------//
   private Dialog dialog = new Dialog(this, "CMS Version", true);
   private Label dlabel = new Label("Customer Manager Versioin 1.0", Label.CENTER);
   private Button dbt = new Button("확인");
   
   //--------------------- 기타 프로그램 관련 -----------------------//
   private ArrayList<Customer> data = new ArrayList<Customer>();
   private FileDialog fd = null;
   private File file = null;   
   
   //생성자
   public CustomerManager2(){
      super("Customer Manager");
      setDialog(); // 다이알로그 구성
      setMenu(); // 메뉴 구성
      buildGUI(); // 화면 구성
      setEvent(); // 이벤트 구성
      setVisible(true);
   }
   
   //메서드
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
   }

   @Override
   public void keyTyped(KeyEvent e) {}
   
   @Override
   public void itemStateChanged(ItemEvent e) {
      if(e.getSource() == telch) {
         tel1tf.requestFocus();
         return;
      }
      if(e.getSource() == listli) {//특정인을 선택
         int index = listli.getSelectedIndex();
         
         Customer customer = data.get(index);
         
         nametf.setText(customer.getName());
         jumin1tf.setText(customer.getJumin().substring(0, 6));
         jumin2tf.setText(customer.getJumin().substring(6));
         String[] imsiTel = customer.getTel().split("-");
         telch.select(imsiTel[0]);
         tel1tf.setText(imsiTel[1]);
         tel2tf.setText(imsiTel[2]);
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
            Thread.sleep(500);
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
      if(e.getSource() == tel1tf) {
         if(tel1tf.getText().trim().length() == 0) {
            tel1tf.setText("");
            return;
         }
         tel2tf.requestFocus();
         return;
      }
      if(e.getSource() == addbt) {//등록버튼
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
      
         clrscr(); // 화면 지우기
      }
      if(e.getSource() == modebt) {//입력 모드
         setForm(true);
         setButton(true);
         clrscr();
         nametf.requestFocus();
         return;
      }
      if(e.getSource() == dispbt) {// 분석 - 주민번호
         // 난 여러분을 믿습니다.
      }
      if(e.getSource() == updatebt) {// 수정
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
      if(e.getSource() == delbt) {// 삭제
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
      if(e.getSource() == mfexit) {
         System.exit(0);
      }
      if(e.getSource() == mhinfo) {
         dialog.setVisible(true);
         return;
      }
      if(e.getSource() == dbt) {
         dialog.setVisible(false);
         return;
      }
      if(e.getSource() == mfsave) {//저장하기
         if(file == null) {
            fd = new FileDialog(this ,"파일저장", FileDialog.SAVE);
            fd.setVisible(true);
            
            String fileName = fd.getFile();
            String folder = fd.getDirectory();
            
            if(fileName == null || folder == null) {
               return;
            }
            
            file = new File(folder, fileName);
         }
         //saveData();
         dataSave(); // ObjectStream 활용
      }
      if(e.getSource() == mfsaveas) {//새이름으로 저장하기
         fd = new FileDialog(this ,"파일저장", FileDialog.SAVE);
         fd.setVisible(true);
         
         String fileName = fd.getFile();
         String folder = fd.getDirectory();
         
         if(fileName == null || folder == null) {
            return;
         }
         
         file = new File(folder, fileName);
         //saveData();//저장처리
         dataSave(); // ObjectStream 활용
      }
      if(e.getSource() == mfopen) {//파일불러오기
         fd = new FileDialog(this ,"파일열기", FileDialog.LOAD);
         fd.setVisible(true);
         
         String fileName = fd.getFile();
         String folder = fd.getDirectory();
         
         if(fileName == null || folder == null) {
            return;
         }
         
         file = new File(folder, fileName);
         // loadData();//파일 불러오기처리
         dataLoad(); // ObjectStream 활용
      }
      if(e.getSource() == mfnew) {
         listli.removeAll();
         data.clear();
         file = null;
         
         infota.setText("\n\t 기존 내용이 초기화 되었습니다.");
         setButton(true);
         setForm(true);
         clrscr();
      }
   }//end Action

   private void setEvent() {
      mfnew.addActionListener(this);
      mfopen.addActionListener(this);
      mfsaveas.addActionListener(this);
      mfsave.addActionListener(this);
      dbt.addActionListener(this);
      mhinfo.addActionListener(this);
      mfexit.addActionListener(this);
      delbt.addActionListener(this);
      updatebt.addActionListener(this);
      dispbt.addActionListener(this);
      modebt.addActionListener(this);
      listli.addItemListener(this);
      addbt.addActionListener(this);
      tel1tf.addActionListener(this);
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
   
   private void loadData() { //파일 불러오기
      listli.removeAll();
      data.clear(); // 기존에 있는 데이터를 삭제
      
      FileReader fr = null;
      BufferedReader br = null;
      try {
         fr = new FileReader(file);
         br = new BufferedReader(fr);
         
         String imsi = null;
         while((imsi = br.readLine()) != null) {
            String[] temp = imsi.split("/");
            boolean gender = false;
            if(temp[3].equals("남성")) gender = true;
            Customer customer = new Customer(temp[0], temp[1], temp[2], gender,temp[4]);
            
            data.add(customer);
            listli.add(customer.toString());
         }
      }catch(FileNotFoundException fnfe) {
         fnfe.printStackTrace();
      }catch(IOException ioe) {
         ioe.printStackTrace();
      }finally {
         try { if(br != null) br.close(); }catch(IOException ioe) { }
         try { if(fr != null) fr.close(); }catch(IOException ioe) { }
      }
      
      infota.setText("\n\t" + file.getName() + "에서 데이터를 성공적으로 로딩하였습니다.");
      try {
         Thread.sleep(500);
      }catch(InterruptedException ie) {
         ie.printStackTrace();
      }
      infota.setText("");
      nametf.requestFocus();
   }
   
   private void saveData() { //파일에 저장
      FileWriter fw = null;
      BufferedWriter bw = null;
      PrintWriter pw = null;
      try {
         fw = new FileWriter(file);
         bw = new BufferedWriter(fw);
         pw = new PrintWriter(bw, true);
         
         for(int i=0; i<data.size(); i++) {
            Customer customer = data.get(i);
            StringBuffer mydata = new StringBuffer(); 
            mydata.append(customer.getName());
            mydata.append("/");
            mydata.append(customer.getJumin());
            mydata.append("/");
            mydata.append(customer.getTel());
            mydata.append("/");
            mydata.append(customer.isGender() ? "남성" : "여성");
            mydata.append("/");
            mydata.append(customer.getHobby());
            pw.println(mydata.toString());
         }
      }catch(FileNotFoundException fnfe) {
         fnfe.printStackTrace();
      }catch(IOException ioe) {
         ioe.printStackTrace();
      }finally {
         if(pw != null) pw.close();
         try { if(bw != null) bw.close(); }catch(IOException ioe) { }
         try { if(fw != null) fw.close(); }catch(IOException ioe) { }
      }
      
      infota.setText("\n\t" + file.getName() + "에 성공적으로 저장하였습니다.");
      try {
         Thread.sleep(500);
      }catch(InterruptedException ie) {
         ie.printStackTrace();
      }
      infota.setText("");
      nametf.requestFocus();
   }
   
   private void dataLoad() {
      FileInputStream fis = null;
      BufferedInputStream bis = null;
      ObjectInputStream ois = null;
      try {
         fis = new FileInputStream(file);
         bis = new BufferedInputStream(fis);
         ois = new ObjectInputStream(bis);
         
         Customer customer = null;
         while((customer = (Customer) ois.readObject()) != null) {
            data.add(customer);
            listli.add(customer.toString());
         }
      }catch(EOFException efe) {
         // 파일의 끝에 왔다.
         infota.setText("\n\t파일의 끝까지 읽었습니다.");
      }catch(ClassNotFoundException cnfe) {
         cnfe.printStackTrace();
      }catch(FileNotFoundException fnfe) {
         fnfe.printStackTrace();
      }catch(IOException ioe) {
         ioe.printStackTrace();
      }finally {
         try { if(ois != null) ois.close(); }catch(IOException ioe) { }
         try { if(bis != null) bis.close(); }catch(IOException ioe) { }
         try { if(fis != null) fis.close(); }catch(IOException ioe) { }
      }
      
      infota.setText("\n\t" + file.getName() + "에서 데이터를 성공적으로 로딩하였습니다.");
      try {
         Thread.sleep(500);
      }catch(InterruptedException ie) {
         ie.printStackTrace();
      }
      infota.setText("");
      nametf.requestFocus();
   }
   
   private void dataSave() {// ObjectOutputStream 활용
      FileOutputStream fos = null;
      BufferedOutputStream bos = null;
      ObjectOutputStream oos = null;
      try {
         fos = new FileOutputStream(file);
         bos = new BufferedOutputStream(fos);
         oos = new ObjectOutputStream(bos);
         
         for(int i=0; i<data.size(); i++) {
            Customer customer = data.get(i);
            oos.writeObject(customer);
         }
         
      }catch(FileNotFoundException fnfe) {
         fnfe.printStackTrace();
      }catch(IOException ioe) {
         ioe.printStackTrace();
      }finally {
         try { if(oos != null) oos.close(); }catch(IOException ioe) { }
         try { if(bos != null) bos.close(); }catch(IOException ioe) { }
         try { if(fos != null) fos.close(); }catch(IOException ioe) { }
      }
      
      infota.setText("\n\t" + file.getName() + "에 성공적으로 저장하였습니다.");
      try {
         Thread.sleep(500);
      }catch(InterruptedException ie) {
         ie.printStackTrace();
      }
      infota.setText("");
      nametf.requestFocus();
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
      mb.add(mhelp);
         mhelp.add(mhinfo);
   }
   
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
   
   private void setForm(boolean status) {// 화면 설정
      nametf.setEditable(status);
      jumin1tf.setEditable(status);
      jumin2tf.setEditable(status);
      man.setEnabled(status);
      woman.setEnabled(status);
   }
   
   private void setButton(boolean status) { // 버튼 설정
      addbt.setEnabled(status);
      dispbt.setEnabled(!status);
      updatebt.setEnabled(!status);
      delbt.setEnabled(!status);
      modebt.setEnabled(!status);
   }
   
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
         Thread.sleep(500);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      
      infota.setText("");
      nametf.requestFocus();
   }
   
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
      
      setButton(true);
      setForm(true);
      nametf.requestFocus();
   }
   
   public static void main(String[] args) {
      new CustomerManager2();
   }
   
}
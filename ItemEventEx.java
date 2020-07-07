package ljh.java.exam12;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ItemEventEx extends Frame implements ItemListener, ActionListener {
	// 멤버
	private static final long serialVersionUID = 1L;
	private Label lb = new Label("혈액형 : ",Label.RIGHT);
	private Label lb1 = new Label("생년월일 : ",Label.RIGHT);
	private Label lb2 = new Label("지역 : ",Label.RIGHT);
	private TextField bType = new TextField();
	private Choice blood = new Choice();
	private TextField birth = new TextField();
	private Choice year = new Choice();
	private Choice month = new Choice();
	private Choice day = new Choice();
	private TextField area = new TextField();
	private List li = new List(3, false);
	private Button end = new Button("끝내기");
	
	// 생성자
	public ItemEventEx() {
		super("ItemEventEx");
		setForm();
		bType.setEditable(false);
		birth.setEditable(false);
		area.setEditable(false);
		blood.addItemListener(this);
		day.addItemListener(this);
		li.addItemListener(this);
		li.addActionListener(this);
		setSize(500, 500);
		setVisible(true);
		blood.requestFocus();
	}

	private void setForm() {
		setLayout(new GridLayout(2,1));
		Panel p = new Panel(new BorderLayout());
		Panel p1 = new Panel(new GridLayout(3, 1));
		p1.add(lb);
		p1.add(lb1);
		p1.add(lb2);
		p.add("West", p1);
		Panel p2 = new Panel(new GridLayout(3,1));
		p2.add(bType);
		p2.add(birth);
		p2.add(area);
		p.add("Center", p2);
		Panel p3 = new Panel(new GridLayout(4, 1));
		p3.add(blood);
		p3.add(year);
		p3.add(month);
		p3.add(day);
		blood.add("A형");
		blood.add("B형");
		blood.add("AB형");
		blood.add("O형");
		for (int i = 1900; i <= 2020; i++) {
			year.add(i + "년");
		}
		for (int i = 1; i <= 12; i++) {
			month.add(i + "월");
		}
		for (int i = 1; i <= 31; i++) {
			day.add(i + "일");
		}
		p.add("East", p3);
		add(p); // 중앙 위
		Panel p4 = new Panel(new BorderLayout());
		p4.add("Center", li);
		li.add("서울"); li.add("인천"); li.add("대전"); li.add("대구");
		li.add("부산"); li.add("울산"); li.add("광주"); li.add("세종");
		p4.add("East", end);
		add(p4); // 중앙 아래
	}
	
	
	// 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == li) {
			int i = li.getSelectedIndex();
			li.remove(i);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == blood) {
			String imsi = blood.getSelectedItem();
			bType.setText(imsi);
		}else if (e.getSource() == day) {
			String imsi = year.getSelectedItem();
			String imsi1 = month.getSelectedItem();
			String imsi2 = day.getSelectedItem();
			birth.setText(imsi + imsi1 + imsi2);
		}else if (e.getSource() == li) {
			String imsi = li.getSelectedItem();
			area.setText(imsi + "지역");
		}
		
	}
	public static void main(String[] args) {
		new ItemEventEx();
	}
	
}

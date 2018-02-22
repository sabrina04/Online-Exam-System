import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class AddExam extends JFrame{
	
	public JLabel l1,l2,l22,l3,l33,l4,l5,l6,l7,l8;
	public JTextField f1,f2,f22,f3,f4,f5,f6,f7,f8;
	public JButton b4,b5;
	public JComboBox c1;
	
	public AddExam(JComboBox c1,JTextField f1,JTextField f2,JTextField f22,JTextField f3,JTextField f4,JTextField f5,JTextField f6,JTextField f7,JTextField f8,JButton b4,JButton b5)
	{
		super("Exam Information");
		Container container = getContentPane();
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
			
		l1 = new JLabel("Course Name :",SwingConstants.LEFT);
		l1.setToolTipText("Enter course name");
				
		l2 = new JLabel("Course Code :",SwingConstants.LEFT);
		l2.setToolTipText("Enter course code");
			
		l22 = new JLabel("Section :",SwingConstants.LEFT);
		l22.setToolTipText("Enter section");
				
		l3 = new JLabel("Student ID :",SwingConstants.LEFT);
		l3.setToolTipText("Enter student ID");
				
		l33 = new JLabel("to",SwingConstants.LEFT);
				
		l4 = new JLabel("Exam No. :",SwingConstants.LEFT);
		l4.setToolTipText("Enter Exam No.");
				
		l5 = new JLabel("Type of exam :",SwingConstants.LEFT);
				
		l6 = new JLabel("Marks of each question :",SwingConstants.LEFT);
		l6.setToolTipText("Enter of marks of each question");
			
		l7 = new JLabel("Total question :",SwingConstants.LEFT);
		l7.setToolTipText("Enter total question number");
				
		l8 = new JLabel("Duration :",SwingConstants.LEFT);
		l8.setToolTipText("Enter duration");
			
		this.f1 = f1;
		this.f2 = f2;
		this.f22 = f22;
		this.f3 = f3;
		this.f4 = f4;
		this.f5 = f5;
		this.f6 = f6;
		this.f7 = f7;
		this.f8 = f8;
		this.b4 = b4;
		this.b5 = b5;
		this.c1 = c1;

		Box bx1 = Box.createHorizontalBox();
		Box bx2 = Box.createHorizontalBox();
		Box bx22 = Box.createHorizontalBox();
		Box bx3 = Box.createHorizontalBox();
		Box bx4 = Box.createHorizontalBox();
		Box bx5 = Box.createHorizontalBox();
		Box bx6 = Box.createHorizontalBox();
		Box bx7 = Box.createHorizontalBox();
		Box bx8 = Box.createHorizontalBox();
		Box bx9 = Box.createHorizontalBox();
			
		bx1.add(Box.createHorizontalStrut(30));
		bx1.add(l1);
		bx1.add(Box.createHorizontalStrut(80));
		bx1.add(f1);
		bx1.add(Box.createHorizontalStrut(50));

		bx2.add(Box.createHorizontalStrut(30));
		bx2.add(l2);
		bx2.add(Box.createHorizontalStrut(85));
		bx2.add(f2);
		bx2.add(Box.createHorizontalStrut(50));

		bx22.add(Box.createHorizontalStrut(30));
		bx22.add(l22);
		bx22.add(Box.createHorizontalStrut(115));
		bx22.add(f22);
		bx22.add(Box.createHorizontalStrut(50));
				
		bx3.add(Box.createHorizontalStrut(30));
		bx3.add(l3);
		bx3.add(Box.createHorizontalStrut(100));
		bx3.add(f3);
		bx3.add(Box.createHorizontalStrut(40));
		bx3.add(l33);
		bx3.add(Box.createHorizontalStrut(40));
		bx3.add(f4);
		bx3.add(Box.createHorizontalStrut(50));

		bx4.add(Box.createHorizontalStrut(30));
		bx4.add(l4);
		bx4.add(Box.createHorizontalStrut(105));
		bx4.add(f5);
		bx4.add(Box.createHorizontalStrut(50));

		bx5.add(Box.createHorizontalStrut(30));
		bx5.add(l5);
		bx5.add(Box.createHorizontalStrut(83));
		bx5.add(c1);
		bx5.add(Box.createHorizontalStrut(50));
				
		bx6.add(Box.createHorizontalStrut(30));
		bx6.add(l6);
		bx6.add(Box.createHorizontalStrut(25));
		bx6.add(f6);
		bx6.add(Box.createHorizontalStrut(50));

		bx7.add(Box.createHorizontalStrut(30));
		bx7.add(l7);
		bx7.add(Box.createHorizontalStrut(77));
		bx7.add(f7);
		bx7.add(Box.createHorizontalStrut(50));
				
		bx8.add(Box.createHorizontalStrut(30));
		bx8.add(l8);
		bx8.add(Box.createHorizontalStrut(110));
		bx8.add(f8);
		bx8.add(Box.createHorizontalStrut(50));
			
		bx9.add(Box.createHorizontalStrut(60));
		bx9.add(b4);
		bx9.add(Box.createHorizontalStrut(120));
		bx9.add(b5);
		bx9.add(Box.createHorizontalStrut(60));

		container.add(Box.createVerticalStrut(15));
		container.add(bx1);
		container.add(Box.createVerticalStrut(30));
		container.add(bx2);
		container.add(Box.createVerticalStrut(30));
		container.add(bx22);
		container.add(Box.createVerticalStrut(30));
		container.add(bx3);
		container.add(Box.createVerticalStrut(30));
		container.add(bx4);
		container.add(Box.createVerticalStrut(30));
		container.add(bx5);
		container.add(Box.createVerticalStrut(30));
		container.add(bx6);
		container.add(Box.createVerticalStrut(30));
		container.add(bx7);
		container.add(Box.createVerticalStrut(30));
		container.add(bx8);
		container.add(Box.createVerticalStrut(30));
		container.add(bx9);
		container.add(Box.createVerticalStrut(10));

		setSize(600,570);
		setLocation(100,0);
		setVisible(true);
	}
}

class AddExamInfo implements Serializable{
			
	public String cn,cc,sec,ctno,stdidfrom,stdidto,examtype;
	public int marks,totalques,time;
	public AddExam a;
			
	public AddExamInfo(String cn,String cc,String sec,String stdidfrom,String stdidto,String ctno,String examtype,int marks,int totalques,int time)
	{
		this.cn = cn;		
		this.cc = cc;		
		this.sec = sec;				
		this.stdidfrom = stdidfrom;	
		this.stdidto = stdidto;	
		this.ctno = ctno;	
		this.examtype = examtype;
		this.marks = marks;
		this.totalques = totalques;	
		this.time = time;	
	}
	
}

class ExamInfo extends JFrame{
	
	public JLabel l11,l12,l13;
	public JTextField f11,f12,f13;
	public JButton b8,b9;
	
	public ExamInfo(JTextField f11,JTextField f12,JTextField f13,JButton b8,JButton b9)
	{
		Container container = getContentPane();
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
			
		l11 = new JLabel("CC ",SwingConstants.LEFT);
		l11.setToolTipText("Enter course code");
		l12 = new JLabel("Sec ",SwingConstants.LEFT);
		l12.setToolTipText("Enter section");
		l13 = new JLabel("CT ",SwingConstants.LEFT);
		l13.setToolTipText("Enter ct no.");
		
		this.f11 = f11;
		this.f12 = f12;
		this.f13 = f13;
		this.b8 = b8;
		this.b9 = b9;			
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
			
		b1.add(Box.createHorizontalStrut(30));
		b1.add(l11);
		b1.add(Box.createHorizontalStrut(30));
		b1.add(f11);
		b1.add(Box.createHorizontalStrut(30));
		b1.add(l12);
		b1.add(Box.createHorizontalStrut(30));
		b1.add(f12);
		b1.add(Box.createHorizontalStrut(30));
		b1.add(l13);
		b1.add(Box.createHorizontalStrut(30));
		b1.add(f13);
		b1.add(Box.createHorizontalStrut(30));
	
		b2.add(Box.createHorizontalStrut(50));
		b2.add(b8);
		b2.add(Box.createHorizontalStrut(80));
		b2.add(b9);
		b2.add(Box.createHorizontalStrut(50));

		container.add(Box.createVerticalStrut(50));
		container.add(b1);
		container.add(Box.createVerticalStrut(50));
		container.add(b2);
		container.add(Box.createVerticalStrut(70));
			
		setSize(500,250);
		setLocation(190,140);
		setVisible(true);
	}
}
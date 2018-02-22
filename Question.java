import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Question extends JFrame{
	
	public Container container = getContentPane();
	public JLabel l14,l114,l15,l16,l17,l18,l19,l20,label,l1,l2,l3,l4,l5;
	public JTextField f14,f15,f16,f17,f18,f19,r;
	public JButton b10,b11;
	public String s;
	
	public Question(String s,JTextField f14,JTextField f15,JTextField f16,JTextField f17,JTextField f18,JTextField f19,JButton b10)
	{
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		l14 = new JLabel("Question ",SwingConstants.LEFT);
		l114 = new JLabel(s,SwingConstants.LEFT);
		l15 = new JLabel("a",SwingConstants.LEFT);
		l16 = new JLabel("b",SwingConstants.LEFT);
		l17 = new JLabel("c",SwingConstants.LEFT);
		l18 = new JLabel("d",SwingConstants.LEFT);
		l19 = new JLabel("Correct Answer",SwingConstants.LEFT);
		this.f14 = f14;
		this.f15 = f15;
		this.f16 = f16;
		this.f17 = f17;
		this.f18 = f18;
		this.f19 = f19;
		this.b10 = b10;
		Box bx1 = Box.createHorizontalBox();
		Box bx2 = Box.createHorizontalBox();
		Box bx3 = Box.createHorizontalBox();
		Box bx4 = Box.createHorizontalBox();
		Box bx5 = Box.createHorizontalBox();
		Box bx6 = Box.createHorizontalBox();
		Box bx7 = Box.createHorizontalBox();
				
		bx1.add(Box.createHorizontalStrut(30));
		bx1.add(l14);
		bx1.add(Box.createHorizontalStrut(10));
		bx1.add(l114);
		bx1.add(Box.createHorizontalStrut(20));
		bx1.add(f14);
		bx1.add(Box.createHorizontalStrut(30));
		
		bx2.add(Box.createHorizontalStrut(30));
		bx2.add(l15);
		bx2.add(Box.createHorizontalStrut(30));
		bx2.add(f15);
		bx2.add(Box.createHorizontalStrut(30));
		
		bx3.add(Box.createHorizontalStrut(30));
		bx3.add(l16);
		bx3.add(Box.createHorizontalStrut(30));
		bx3.add(f16);
		bx3.add(Box.createHorizontalStrut(30));
					
		bx4.add(Box.createHorizontalStrut(30));
		bx4.add(l17);
		bx4.add(Box.createHorizontalStrut(30));
		bx4.add(f17);
		bx4.add(Box.createHorizontalStrut(30));
	
		bx5.add(Box.createHorizontalStrut(30));
		bx5.add(l18);
		bx5.add(Box.createHorizontalStrut(30));
		bx5.add(f18);
		bx5.add(Box.createHorizontalStrut(30));
	
		bx6.add(Box.createHorizontalStrut(30));
		bx6.add(l19);
		bx6.add(Box.createHorizontalStrut(30));
		bx6.add(f19);
		bx6.add(Box.createHorizontalStrut(30));
			
		bx7.add(Box.createHorizontalStrut(120));
		bx7.add(b10);
		bx7.add(Box.createHorizontalStrut(30));
								
		container.add(Box.createVerticalStrut(30));
		container.add(bx1);
		container.add(Box.createVerticalStrut(30));
		container.add(bx2);
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
					
		setSize(500,450);
		setLocation(190,60);
		setVisible(true);
	}
	
	public Question(JLabel label,String s,JLabel l1,JLabel l2,JLabel l3,JLabel l4,JLabel l5,JTextField f19,JButton b10,JButton b11)
	{
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		l14 = new JLabel("Question ",SwingConstants.LEFT);
		l114 = new JLabel(s+" : ",SwingConstants.LEFT);
		l19 = new JLabel("Correct Answer",SwingConstants.LEFT);
		this.label = label;
		this.l1 = l1;
		this.l2 = l2;
		this.l3 = l3;
		this.l4 = l4;
		this.l5 = l5;
		this.f19 = f19;
		this.b10 = b10;
		this.b11 = b11;
		Box bx1 = Box.createHorizontalBox();
		Box bx2 = Box.createHorizontalBox();
		Box bx3 = Box.createHorizontalBox();
		Box bx4 = Box.createHorizontalBox();
		Box bx5 = Box.createHorizontalBox();
		Box bx6 = Box.createHorizontalBox();
		Box bx7 = Box.createHorizontalBox();
		Box bxx = Box.createHorizontalBox();
		
		bxx.add(Box.createHorizontalStrut(30));
		bxx.add(label);
		bxx.add(Box.createHorizontalStrut(10));
				
		bx1.add(Box.createHorizontalStrut(30));
		bx1.add(l14);
		bx1.add(Box.createHorizontalStrut(10));
		bx1.add(l114);
		bx1.add(Box.createHorizontalStrut(20));
		bx1.add(l1);
		bx1.add(Box.createHorizontalStrut(30));
		
		bx2.add(Box.createHorizontalStrut(30));
		bx2.add(l2);
		bx2.add(Box.createHorizontalStrut(30));
		
		bx3.add(Box.createHorizontalStrut(30));
		bx3.add(l3);
		bx3.add(Box.createHorizontalStrut(30));
					
		bx4.add(Box.createHorizontalStrut(30));
		bx4.add(l4);
		bx4.add(Box.createHorizontalStrut(30));
	
		bx5.add(Box.createHorizontalStrut(30));
		bx5.add(l5);
		bx5.add(Box.createHorizontalStrut(30));
	
		bx6.add(Box.createHorizontalStrut(30));
		bx6.add(l19);
		bx6.add(Box.createHorizontalStrut(30));
		bx6.add(f19);
		bx6.add(Box.createHorizontalStrut(30));
			
		bx7.add(Box.createHorizontalStrut(100));
		bx7.add(b10);
		bx7.add(Box.createHorizontalStrut(60));
		bx7.add(b11);
		bx7.add(Box.createHorizontalStrut(100));
		
		container.add(Box.createVerticalStrut(30));
		container.add(bxx);						
		container.add(Box.createVerticalStrut(30));
		container.add(bx1);
		container.add(Box.createVerticalStrut(30));
		container.add(bx2);
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
					
		setSize(550,450);
		setLocation(150,40);
		setVisible(true);
	}

	public Question(String s,JTextField f14,JTextField r,JButton b10)
	{
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		l14 = new JLabel("Question ",SwingConstants.LEFT);
		l114 = new JLabel(s,SwingConstants.LEFT);
		l20 = new JLabel("Enter T for True & F for False :",SwingConstants.LEFT);

		this.f14 = f14;
		this.r = r;
		this.b10 = b10;
		
		Box bx1 = Box.createHorizontalBox();
		Box bx2 = Box.createHorizontalBox();
		Box bx3 = Box.createHorizontalBox();
		
		bx1.add(Box.createHorizontalStrut(30));
		bx1.add(l14);
		bx1.add(Box.createHorizontalStrut(10));
		bx1.add(l114);
		bx1.add(Box.createHorizontalStrut(20));
		bx1.add(f14);
		bx1.add(Box.createHorizontalStrut(30));
		
		bx2.add(Box.createHorizontalStrut(30));
		bx2.add(l20);
		bx2.add(Box.createHorizontalStrut(10));
		bx2.add(r);
		bx2.add(Box.createHorizontalStrut(30));
		
		bx3.add(Box.createHorizontalStrut(30));
		bx3.add(b10);
		bx3.add(Box.createHorizontalStrut(30));
		
		container.add(Box.createVerticalStrut(40));
		container.add(bx1);
		container.add(Box.createVerticalStrut(40));
		container.add(bx2);
		container.add(Box.createVerticalStrut(40));
		container.add(bx3);
		container.add(Box.createVerticalStrut(40));
		
		setSize(500,300);
		setLocation(190,100);
		setVisible(true);
	}
	
	public Question(JLabel label,String s,JLabel l1,JTextField r,JButton b10,JButton b11)
	{
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		l14 = new JLabel("Question ",SwingConstants.LEFT);
		l114 = new JLabel(s,SwingConstants.LEFT);
		l20 = new JLabel("Enter T for True & F for False :",SwingConstants.LEFT);
		this.label = label;
		this.l1 = l1;
		this.r = r;
		this.b10 = b10;
		this.b11 = b11;
		Box bx1 = Box.createHorizontalBox();
		Box bx2 = Box.createHorizontalBox();
		Box bx3 = Box.createHorizontalBox();
		Box bxx = Box.createHorizontalBox();
		
		bxx.add(Box.createHorizontalStrut(30));
		bxx.add(label);
		bxx.add(Box.createHorizontalStrut(10));
		
		bx1.add(Box.createHorizontalStrut(30));
		bx1.add(l14);
		bx1.add(Box.createHorizontalStrut(10));
		bx1.add(l114);
		bx1.add(Box.createHorizontalStrut(20));
		bx1.add(l1);
		bx1.add(Box.createHorizontalStrut(30));
		
		bx2.add(Box.createHorizontalStrut(30));
		bx2.add(l20);
		bx2.add(Box.createHorizontalStrut(10));
		bx2.add(r);
		bx2.add(Box.createHorizontalStrut(30));
		
		bx3.add(Box.createHorizontalStrut(30));
		bx3.add(b10);
		bx3.add(Box.createHorizontalStrut(30));
		bx3.add(b11);
		bx3.add(Box.createHorizontalStrut(30));
	
		container.add(Box.createVerticalStrut(40));
		container.add(bxx);						
		container.add(Box.createVerticalStrut(40));
		container.add(bx1);
		container.add(Box.createVerticalStrut(40));
		container.add(bx2);
		container.add(Box.createVerticalStrut(40));
		container.add(bx3);
		container.add(Box.createVerticalStrut(40));
		
		setSize(500,300);
		setLocation(190,80);
		setVisible(true);
	}
	
}

class AddQ implements Serializable{
		
	public String que,A,B,C,D,ans,cans;
		
	public AddQ(String que,String A,String B,String C,String D,String ans)
	{
		this.que = que;
		this.A = A;
		this.B = B;
		this.C = C;
		this.D = D;
		this.ans = ans;
	}
	
	public AddQ(String que,String cans)
	{
		this.que = que;
		this.cans = cans;

	}

}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditExam extends JFrame{
	
	public JTextField f9,f10,ff11;
	public JButton b6,b7;
	public JLabel l9,ll9,l10;
	
	public EditExam(JTextField f9,JTextField f10,JTextField ff11,JButton b6,JButton b7)
	{
		Container container = getContentPane();
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
			
		l9 = new JLabel("Course Code  :",SwingConstants.LEFT);
		l9.setToolTipText("Enter course no.");
			
		l10 = new JLabel("Section :",SwingConstants.LEFT);
		l10.setToolTipText("Enter section");

		ll9 = new JLabel("Exam No. :",SwingConstants.LEFT);
		ll9.setToolTipText("Enter exam no.");		
			
		
		this.f9 = f9;
		this.f10 = f10;
		this.ff11 = ff11;
		this.b6 = b6;
		this.b7 = b7;
		
		Box bx1 = Box.createHorizontalBox();
		Box bx2 = Box.createHorizontalBox();
		Box bx3 = Box.createHorizontalBox();
		Box bx4 = Box.createHorizontalBox();

		bx1.add(Box.createHorizontalStrut(50));
		bx1.add(l9);
		bx1.add(Box.createHorizontalStrut(70));
		bx1.add(f9);
		bx1.add(Box.createHorizontalStrut(50));

		bx2.add(Box.createHorizontalStrut(50));
		bx2.add(l10);
		bx2.add(Box.createHorizontalStrut(100));
		bx2.add(f10);
		bx2.add(Box.createHorizontalStrut(50));

		bx3.add(Box.createHorizontalStrut(50));
		bx3.add(ll9);
		bx3.add(Box.createHorizontalStrut(90));
		bx3.add(ff11);
		bx3.add(Box.createHorizontalStrut(50));
		
		bx4.add(Box.createHorizontalStrut(50));
		bx4.add(b6);
		bx4.add(Box.createHorizontalStrut(50));
		bx4.add(b7);
		bx4.add(Box.createHorizontalStrut(50));
		
		container.add(Box.createVerticalStrut(70));
		container.add(bx1);
		container.add(Box.createVerticalStrut(70));
		container.add(bx2);
		container.add(Box.createVerticalStrut(70));
		container.add(bx3);
		container.add(Box.createVerticalStrut(70));
		container.add(bx4);
		container.add(Box.createVerticalStrut(70));
			
		setSize(500,500);
		setLocation(170,40);
		setVisible(true);
	}
}
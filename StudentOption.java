import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentOption extends JFrame{
	
	public JButton b1,b2,b3;
	
	public StudentOption(JButton b1,JButton b2,JButton b3)
	{
		Container container = getContentPane();
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		
		this.b1 = b1;
		this.b2 = b2;
		this.b3 = b3;
		Box bx1 = Box.createHorizontalBox();
		Box bx2 = Box.createHorizontalBox();
		Box bx3 = Box.createHorizontalBox();
		
		bx1.add(Box.createHorizontalStrut(50));
		bx1.add(b1);
		bx1.add(Box.createHorizontalStrut(50));
		
		bx2.add(Box.createHorizontalStrut(50));
		bx2.add(b2);
		bx2.add(Box.createHorizontalStrut(50));

		bx3.add(Box.createHorizontalStrut(50));
		bx3.add(b3);
		bx3.add(Box.createHorizontalStrut(50));

		container.add(Box.createVerticalStrut(50));
		container.add(bx1);
		container.add(Box.createVerticalStrut(50));
		container.add(bx2);
		container.add(Box.createVerticalStrut(50));
		container.add(bx3);
		container.add(Box.createVerticalStrut(50));
			
		setSize(350,450);
		setLocation(230,50);
		setVisible(true);
	}
}
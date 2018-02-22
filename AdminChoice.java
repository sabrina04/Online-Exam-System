import java.awt.*;
import javax.swing.*;

public class AdminChoice extends JFrame{
	
	public JButton b1,b2,b3,bb4,bn3,bn4;
	
	public AdminChoice(JButton b1,JButton bn3,JButton b2,JButton bn4,JButton b3,JButton b4)
	{	
		super("Administrator's Choice");
		Container container = getContentPane();
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		
		this.b1 = b1;
		this.bn3 = bn3;
		this.b2 = b2;
		this.bn4 = bn4;
		this.b3 = b3;
		this.bb4 = bb4;
			
		Box bxx1 = Box.createHorizontalBox();
		Box bx1 = Box.createHorizontalBox();
		Box bxx2 = Box.createHorizontalBox();
		Box bxx3 = Box.createHorizontalBox();
		Box bxx4 = Box.createHorizontalBox();
		Box bxx5 = Box.createHorizontalBox();
		
		bxx1.add(Box.createHorizontalStrut(80));
		bxx1.add(b1);
		bxx1.add(Box.createHorizontalStrut(80));
		
		bx1.add(Box.createHorizontalStrut(80));
		bx1.add(bn3);
		bx1.add(Box.createHorizontalStrut(80));
		
					
		bxx2.add(Box.createHorizontalStrut(80));
		bxx2.add(b2);
		bxx2.add(Box.createHorizontalStrut(80));
	
		bxx5.add(Box.createHorizontalStrut(80));
		bxx5.add(bn4);
		bxx5.add(Box.createHorizontalStrut(80));
					
		bxx3.add(Box.createHorizontalStrut(80));
		bxx3.add(b3);
		bxx3.add(Box.createHorizontalStrut(80));

		bxx4.add(Box.createHorizontalStrut(80));
		bxx4.add(b4);
		bxx4.add(Box.createHorizontalStrut(80));
		
		container.add(Box.createVerticalStrut(50));
		container.add(bxx1);
		container.add(Box.createVerticalStrut(50));
		container.add(bx1);
		container.add(Box.createVerticalStrut(50));
		container.add(bxx2);
		container.add(Box.createVerticalStrut(50));
		container.add(bxx5);
		container.add(Box.createVerticalStrut(50));
		container.add(bxx3);	
		container.add(Box.createVerticalStrut(50));
		container.add(bxx4);	
		container.add(Box.createVerticalStrut(50));
					
		setSize(450,550);
		setLocation(190,10);
		setVisible(true);
	}
}
import java.awt.*;
import javax.swing.*;


public class IP_Address extends JFrame{
	
	public JButton bn1,bn2;
	public JLabel label;
	public JTextField field;
	
	public IP_Address(JButton bn1,JButton bn2){
		
		Container container = getContentPane();
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
			
		label = new JLabel("IP address :",SwingConstants.LEFT);
		label.setToolTipText("Enter IP address");
		field = new JTextField();
		
		this.bn1 = bn1;
		this.bn2 = bn2;	

		Box bx1 = Box.createHorizontalBox();
		Box bx2 = Box.createHorizontalBox();

		bx1.add(Box.createHorizontalStrut(50));
		bx1.add(label);
		bx1.add(Box.createHorizontalStrut(50));
		bx1.add(field);
		bx1.add(Box.createHorizontalStrut(50));

		bx2.add(Box.createHorizontalStrut(50));
		bx2.add(bn1);
		bx2.add(Box.createHorizontalStrut(50));
		bx2.add(bn2);
		bx2.add(Box.createHorizontalStrut(50));

		container.add(Box.createVerticalStrut(50));
		container.add(bx1);
		container.add(Box.createVerticalStrut(50));
		container.add(bx2);
		container.add(Box.createVerticalStrut(50));
		
		setSize(350,200);
		setLocation(230,140);
		setVisible(true);
	}
}
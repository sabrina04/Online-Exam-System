import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Admin extends JFrame implements ActionListener{

	private JLabel label,label1,label2;
	private JTextField field1,ff11,f1,f2,f22,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,fl1,fl2,fl22,fl3,fl4,fl5,fl6,fl7,fl8,fl14,fl15,fl16,fl17,fl18,fl19,r;
	private JPasswordField field2;
	private JButton b,button1,button2,bn1,bn2,bn3,bn4,b11,b22,bn10,b1,b2,b3,b4,bb4,b5,b6,b7,b8,b9,b10,b44,b55,bt10,b66,bb6;
	private JComboBox c1,cl1;
	public Container container = getContentPane();
	int j=0,i=0,k=0,l=0,p=0,qn,n=0,mr=-1;
	private Socket client;
	private ObjectOutputStream os;
	private ObjectInputStream is;
	private String from,to,s,fname,mfname;		
	private AdminChoice c;
	private IP_Address ip;
	private AddExam am,ex;
	private AddExamInfo ae,exam;
	private ExamInfo ei;
	private EditExam ed;
	private Question q;
	private AddQ aq,qas[];
	private AddQ temp[];
	private Marks m;
	private MarksTable mt;
	private Stdmark st,sm;
	
	public Admin()
	{
		super("Administrator");
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		
		label1 = new JLabel("Username :",SwingConstants.LEFT);
		label1.setToolTipText("Enter Username");
		
		label2 = new JLabel("Password :",SwingConstants.LEFT);
		label2.setToolTipText("Enter Password");
		
		field1 = new JTextField("Administrator");
		field1.setEnabled(false);
		field2 = new JPasswordField();
		
		button1 = new JButton("OK");
		button2 = new JButton("Cancel");
		button1.addActionListener(this);
		button2.addActionListener(this);
		
		Box bx1 = Box.createHorizontalBox();
		Box bx2 = Box.createHorizontalBox();
		Box bx3 = Box.createHorizontalBox();
		
		bx1.add(Box.createHorizontalStrut(50));
		bx1.add(label1);
		bx1.add(Box.createHorizontalStrut(50));
		bx1.add(field1);
		bx1.add(Box.createHorizontalStrut(50));
		
		bx2.add(Box.createHorizontalStrut(50));
		bx2.add(label2);
		bx2.add(Box.createHorizontalStrut(50));
		bx2.add(field2);		
		bx2.add(Box.createHorizontalStrut(50));
		
		bx3.add(Box.createHorizontalStrut(50));
		bx3.add(button1);
		bx3.add(Box.createHorizontalStrut(50));
		bx3.add(button2);
		bx3.add(Box.createHorizontalStrut(50));
		
		container.add(Box.createVerticalStrut(50));
		container.add(bx1);
		container.add(Box.createVerticalStrut(50));
		container.add(bx2);
		container.add(Box.createVerticalStrut(50));
		container.add(bx3);
		container.add(Box.createVerticalStrut(50));
		
		setSize(350,300);
		setLocation(230,130);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==button2){
			System.exit(0);
		}
	
	/**********************	checking password & taking server's IP address **************/	
	
		if(e.getSource()==button1){
			if(!field2.getText().equals("buet")){
				JOptionPane.showMessageDialog(null,"     Invalid password. Try again ...","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			setVisible(false);
			bn1 = new JButton("OK");
			bn2 = new JButton("Cancel");
			ip = new IP_Address(bn1,bn2);
			bn1.addActionListener(this);
			bn2.addActionListener(this);
			ip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		if(e.getSource() == bn2)
			System.exit(0);
			
	/************* connecting to server  ****************/
		
		if(e.getSource() == bn1){
		
			try{
				client = new Socket(ip.field.getText(),12345);
				System.out.println("Administrator is connected to server...");
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Unable to Connect.\nPlease enter correct IP Address.","Alert",JOptionPane.WARNING_MESSAGE);
				return;
			}
			try{
				os = new ObjectOutputStream(client.getOutputStream());	
				os.flush();
				is = new ObjectInputStream(client.getInputStream());
			}
			catch(IOException ioe){
				JOptionPane.showMessageDialog(null,"Unable to Connect.\nPlease enter correct IP Address.","Alert",JOptionPane.WARNING_MESSAGE);
				return;
			}
			ip.setVisible(false);
			
 /***************  ADMIN  GUI  *********************************/			
			
			b1 = new JButton("Add Exam");
			bn3=new JButton("Add Question");
			b2 = new JButton("Edit Exam");
			bn4 = new JButton("Start Exam");
			b3 = new JButton("View Marks");
			bb4 = new JButton("Cancel");
			c = new AdminChoice(b1,bn3,b2,bn4,b3,bb4);
			b1.addActionListener(this);
			bn3.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			bn4.addActionListener(this);
			bb4.addActionListener(this);
			c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	/************* Disconnecting from the server  ****************/	
	
		if(e.getSource() == bb4){			
			try{
				os.writeObject("disconnect");
				os.flush();
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Unable to diconnect.\nPlease enter correct IP Address.","Alert",JOptionPane.WARNING_MESSAGE);
				return;
			}
			c.setVisible(false);
			System.exit(0);
		}

	/************* Add exam GUI. Take exam information. ****************/
	
		if(e.getSource() == b1){		
			c.setVisible(false);
			f1 = new JTextField();
			f2 = new JTextField();
			f22 = new JTextField();
			f3 = new JTextField();
			f4 = new JTextField();
			f5 = new JTextField();
			f6 = new JTextField();
			f7 = new JTextField();
			f8 = new JTextField();
			String names[] = {"MCQ","True/False"};
			c1 = new JComboBox(names);
			b4 = new JButton("OK");
			b5 = new JButton("Cancel");
			am = new AddExam(c1,f1,f2,f22,f3,f4,f5,f6,f7,f8,b4,b5);
			b4.addActionListener(this);
			b5.addActionListener(this);
			am.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	
  /***************  ADMIN  GUI  *********************************/
  	
		if(e.getSource() == b5){
			am.setVisible(false);
			b1 = new JButton("Add Exam");
			bn3=new JButton("Add Question");
			b2 = new JButton("Edit Exam");
			bn4 = new JButton("Start Exam");
			b3 = new JButton("View Marks");
			bb4 = new JButton("Cancel");
			c = new AdminChoice(b1,bn3,b2,bn4,b3,bb4);
			b1.addActionListener(this);
			bn3.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			bn4.addActionListener(this);
			bb4.addActionListener(this);
			c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	/***************** Asking admin if he/she wants to add question now/later **********/
		
		if(e.getSource() == b4){	
			am.setVisible(false);
			ae = new AddExamInfo(f1.getText(),f2.getText(),f22.getText(),f3.getText(),f4.getText(),f5.getText(),(String)c1.getSelectedItem(),Integer.parseInt(f6.getText()), Integer.parseInt(f7.getText()),Integer.parseInt(f8.getText()));	
			n = ae.totalques;
			qas = new AddQ[n];
			fname = new String(f2.getText().toUpperCase()+"_"+f22.getText().toUpperCase()+"_"+f5.getText().toUpperCase()+".txt");
			from = (String)ae.stdidfrom;
			to = (String)ae.stdidto;
			mt = new MarksTable(from,to);

			container.removeAll();
			container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
			
			label = new JLabel("Do you want to add question now?",SwingConstants.LEFT);
			b11 = new JButton("Yes");
			b22 = new JButton("No");
			b11.addActionListener(this);
			b22.addActionListener(this);
			
			Box bx1 = Box.createHorizontalBox();
			Box bx2 = Box.createHorizontalBox();

			bx1.add(Box.createHorizontalStrut(50));
			bx1.add(label);
			bx1.add(Box.createHorizontalStrut(50));

			bx2.add(Box.createHorizontalStrut(50));
			bx2.add(b11);
			bx2.add(Box.createHorizontalStrut(50));
			bx2.add(b22);
			bx2.add(Box.createHorizontalStrut(50));
			
			container.add(Box.createVerticalStrut(50));
			container.add(bx1);
			container.add(Box.createVerticalStrut(40));
			container.add(bx2);
			container.add(Box.createVerticalStrut(50));

			setSize(300,200);
			setLocation(250,150);
			setVisible(true);	
		}

	/************* Question is created now ***************/
	
		if(e.getSource()==b11){	      
			try{				
				String s = new String("output");
				String s1 = new String("marks_"+fname);
				String s2 = new String("marks");
				os.writeObject(s);
				os.flush();
				os.writeObject(s1);
				os.flush();
				os.writeObject(s2);
				os.flush();
				os.writeObject(mt);
				os.flush();
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Marks file is not created.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}			
			setVisible(false);
			if(c1.getSelectedItem() == "MCQ"){
				j = Integer.parseInt(f7.getText());
				f14 = new JTextField();
				f15 = new JTextField();
				f16 = new JTextField();
				f17 = new JTextField();
				f18 = new JTextField();
				f19 = new JTextField();
				String s = Integer.toString(i+1);
				b =new JButton("Next");
				q = new Question(s,f14,f15,f16,f17,f18,f19,b);
				b.addActionListener(this);
			}		
			if(c1.getSelectedItem() == "True/False"){
				j = Integer.parseInt(f7.getText());
				f14 = new JTextField();
				r = new JTextField();
				String s = Integer.toString(i+1);
				b =new JButton("Next");
				q = new Question(s,f14,r,b);
				b.addActionListener(this);
			}
			i++;
		}
	
		if(e.getSource()==b && i<j-1){		  
			if((String)c1.getSelectedItem() == "MCQ"){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				qas[i-1] = new AddQ(f14.getText(),f15.getText(),f16.getText(),f17.getText(),f18.getText(),f19.getText());
				q.setVisible(false);
				f14 = new JTextField();
				f15 = new JTextField();
				f16 = new JTextField();
				f17 = new JTextField();
				f18 = new JTextField();
				f19 = new JTextField();
				String s = Integer.toString(i+1);
				b =new JButton("Next");
				q = new Question(s,f14,f15,f16,f17,f18,f19,b);
				b.addActionListener(this);
			}
			if((String)c1.getSelectedItem() == "True/False"){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T or F.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				qas[i-1] = new AddQ(f14.getText(),r.getText());
				q.setVisible(false);
				f14 = new JTextField();
				r = new JTextField();
				String s = Integer.toString(i+1);
				b =new JButton("Next");
				q = new Question(s,f14,r,b);	
				b.addActionListener(this);
			}
			i++;
		}
		
		if(e.getSource()==b && i==j-1){
			if((String)c1.getSelectedItem() == "MCQ"){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				qas[i-1] = new AddQ(f14.getText(),f15.getText(),f16.getText(),f17.getText(),f18.getText(),f19.getText());
				q.setVisible(false);
				f14 = new JTextField();
				f15 = new JTextField();
				f16 = new JTextField();
				f17 = new JTextField();
				f18 = new JTextField();
				f19 = new JTextField();
				String s = Integer.toString(i+1);
				b =new JButton("Finish");
				q = new Question(s,f14,f15,f16,f17,f18,f19,b);
				b.addActionListener(this);
			}
			if((String)c1.getSelectedItem() == "True/False"){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T or F.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				qas[i-1] = new AddQ(f14.getText(),r.getText());
				q.setVisible(false);
				f14 = new JTextField();
				r = new JTextField();
				String s = Integer.toString(i+1);
				b =new JButton("Finish");
				q = new Question(s,f14,r,b);	
				b.addActionListener(this);
			}	
			i++;
		}
		
		if(e.getSource()==b && i>j-1){
			if((String)c1.getSelectedItem() == "MCQ"){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				qas[i-1] = new AddQ(f14.getText(),f15.getText(),f16.getText(),f17.getText(),f18.getText(),f19.getText());
			}
			if((String)c1.getSelectedItem() == "True/False"){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T or F.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				qas[i-1] = new AddQ(f14.getText(),r.getText());
			}
			q.setVisible(false);

			try{									//create add exam file with questions
				String s = new String("output");
				String s1 = new String(fname);
				String s2 = new String(Integer.toString(n));
				os.writeObject(s);
				os.flush();
				os.writeObject(s1);
				os.flush();
				os.writeObject(s2);
				os.flush();
				os.writeObject(ae);
				os.flush();
				for(int a=0; a<n; a++){
					os.writeObject(qas[a]);
					os.flush();
				}
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Question file is not created.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}	
			b1 = new JButton("Add Exam");
			bn3=new JButton("Add Question");
			b2 = new JButton("Edit Exam");
			bn4 = new JButton("Start Exam");
			b3 = new JButton("View Marks");
			bb4 = new JButton("Cancel");
			c = new AdminChoice(b1,bn3,b2,bn4,b3,bb4);
			b1.addActionListener(this);
			bn3.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			bn4.addActionListener(this);
			bb4.addActionListener(this);
			c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		if(e.getSource()==b22){					// add exam file with only addexaminfo
			try{
				String s = new String("output");
				String s1 = new String(fname);
				String s2 = new String("Add_Q_Now");
				os.writeObject(s);
				os.flush();
				os.writeObject(s1);
				os.flush();
				os.writeObject(s2);
				os.flush();
				os.writeObject(ae);
				os.flush();
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Exam information file is not created.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			setVisible(false);
			
	/***************  ADMIN  GUI  *********************************/
			
			b1 = new JButton("Add Exam");
			bn3=new JButton("Add Question");
			b2 = new JButton("Edit Exam");
			bn4 = new JButton("Start Exam");
			b3 = new JButton("View Marks");
			bb4 = new JButton("Cancel");
			c = new AdminChoice(b1,bn3,b2,bn4,b3,bb4);
			b1.addActionListener(this);
			bn3.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			bn4.addActionListener(this);
			bb4.addActionListener(this);
			c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		if(e.getSource()==bn3){	   		//taking filename for add question later
			c.setVisible(false);
			f11 = new JTextField();
			f12 = new JTextField();
			f13 = new JTextField();
			b8 = new JButton("OK");
			b9 = new JButton("Cancel");
			ei = new ExamInfo(f11,f12,f13,b8,b9);
			b8.addActionListener(this);
			b9.addActionListener(this);
			ei.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		if(e.getSource() == b8){
			fname = new String(f11.getText().toUpperCase()+"_"+f12.getText().toUpperCase()+"_"+f13.getText().toUpperCase()+".txt");			
			try{
				String s = new String("input");
				String s1 = new String(fname);
				String s2 = new String("info");
				os.writeObject(s);
				os.flush();
				os.writeObject(s1);
				os.flush();
				os.writeObject(s2);
				os.flush();
				ae = (AddExamInfo)is.readObject();
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Exam information file is not found.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try{
				String s = new String("output");
				String s1 = new String("marks_"+fname);
				String s2 = new String("marks");
				os.writeObject(s);
				os.flush();
				os.writeObject(s1);
				os.flush();
				os.writeObject(s2);
				os.flush();
				os.writeObject(mt);
				os.flush();
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Marks file is not created.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			s = ae.examtype;
			n = ae.totalques;
			qas = new AddQ[n];
			ei.setVisible(false);
			if(s.equals("MCQ")){		//  Question is created here.
				j = ae.totalques;
				f14 = new JTextField();
				f15 = new JTextField();
				f16 = new JTextField();
				f17 = new JTextField();
				f18 = new JTextField();
				f19 = new JTextField();
				String s = Integer.toString(i+1);
				b10 =new JButton("Next");
				q = new Question(s,f14,f15,f16,f17,f18,f19,b10);
				b10.addActionListener(this);
			}		
			if(s.equals("True/False")){
				j = Integer.parseInt(f7.getText());
				f14 = new JTextField();
				r = new JTextField();
				String s = Integer.toString(i+1);
				b10 =new JButton("Next");
				q = new Question(s,f14,r,b10);
				b10.addActionListener(this);
			}
			i++;
		}
	
		if(e.getSource()==b10 && i<j-1){
			s = ae.examtype;
			if(s.equals("MCQ")){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				qas[i-1] = new AddQ(f14.getText(),f15.getText(),f16.getText(),f17.getText(),f18.getText(),f19.getText());
				q.setVisible(false);
				f14 = new JTextField();
				f15 = new JTextField();
				f16 = new JTextField();
				f17 = new JTextField();
				f18 = new JTextField();
				f19 = new JTextField();
				String s = Integer.toString(i+1);
				b10 =new JButton("Next");
				q = new Question(s,f14,f15,f16,f17,f18,f19,b10);
				b10.addActionListener(this);
			}
			if(s.equals("True/False")){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T or F.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				qas[i-1] = new AddQ(f14.getText(),r.getText());
				q.setVisible(false);
				f14 = new JTextField();
				r = new JTextField();
				String s = Integer.toString(i+1);
				b10 =new JButton("Next");
				q = new Question(s,f14,r,b10);	
				b10.addActionListener(this);
			}
			i++;
		}
	
		if(e.getSource()==b10 && i==j-1){
			s = ae.examtype;
			if(s.equals("MCQ")){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				qas[i-1] = new AddQ(f14.getText(),f15.getText(),f16.getText(),f17.getText(),f18.getText(),f19.getText());
				q.setVisible(false);
				f14 = new JTextField();
				f15 = new JTextField();
				f16 = new JTextField();
				f17 = new JTextField();
				f18 = new JTextField();
				f19 = new JTextField();
				String s = Integer.toString(i+1);
				b10 =new JButton("Finish");
				q = new Question(s,f14,f15,f16,f17,f18,f19,b10);
				b10.addActionListener(this);
			}
			if(s.equals("True/False")){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T or F.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				qas[i-1] = new AddQ(f14.getText(),r.getText());
				q.setVisible(false);
				f14 = new JTextField();
				r = new JTextField();
				String s = Integer.toString(i+1);
				b10 =new JButton("Finish");
				q = new Question(s,f14,r,b10);	
				b10.addActionListener(this);
			}	
			i++;
		}
		
		if(e.getSource()==b10 && i>j-1){
			s = ae.examtype;
			if(s.equals("MCQ")){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				qas[i-1] = new AddQ(f14.getText(),f15.getText(),f16.getText(),f17.getText(),f18.getText(),f19.getText());
			}
			if(s.equals("True/False")){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T or F.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				qas[i-1] = new AddQ(f14.getText(),r.getText());
			}

			try{									//writing question in the file
				String s = new String("output");
				String s1 = new String(fname);
				String s2 = new String(Integer.toString(n));
				os.writeObject(s);
				os.flush();
				os.writeObject(s1);
				os.flush();
				os.writeObject(s2);
				os.flush();
				os.writeObject(ae);
				os.flush();
				for(int a=0; a<n; a++){
					os.writeObject(qas[a]);
					os.flush();
				}
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Question file is not created.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			q.setVisible(false);
			
	/**************  ADMIN  GUI  *********************/
				
			b1 = new JButton("Add Exam");
			bn3=new JButton("Add Question");
			b2 = new JButton("Edit Exam");
			bn4 = new JButton("Start Exam");
			b3 = new JButton("View Marks");
			bb4 = new JButton("Cancel");
			c = new AdminChoice(b1,bn3,b2,bn4,b3,bb4);
			b1.addActionListener(this);
			bn3.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			bn4.addActionListener(this);
			bb4.addActionListener(this);
			c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	
		if(e.getSource()==b9){				// Cancel of add question
			ei.setVisible(false);
			b1 = new JButton("Add Exam");
			bn3=new JButton("Add Question");
			b2 = new JButton("Edit Exam");
			bn4 = new JButton("Start Exam");
			b3 = new JButton("View Marks");
			bb4 = new JButton("Cancel");
			c = new AdminChoice(b1,bn3,b2,bn4,b3,bb4);
			b1.addActionListener(this);
			bn3.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			bn4.addActionListener(this);
			bb4.addActionListener(this);
			c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	/*********** Taking file name for editing exam *************/
		
		if(e.getSource() == b2){		
			c.setVisible(false);
			f9 = new JTextField();
			f10 = new JTextField();
			ff11 = new JTextField();
			b6 = new JButton("OK");
			b7 = new JButton("Cancel");
			ed = new EditExam(f9,f10,ff11,b6,b7);
			b6.addActionListener(this);
			b7.addActionListener(this);
			ed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		if(e.getSource()==b6 && mr==-1){		// check out whether the exam has been taken or not
			mfname = new String("marks_"+f9.getText().toUpperCase()+"_"+f10.getText().toUpperCase()+"_"+ff11.getText().toUpperCase()+".txt");
			try{
				String s = new String("input");
				String s1 = new String(mfname);
				String s2 = new String("marks");
				os.writeObject(s);
				os.flush();
				os.writeObject(s1);
				os.flush();
				os.writeObject(s2);
				os.flush();
				mt = (MarksTable)is.readObject();
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Marks file is not found.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			sm = mt.arr[0];
			mr = sm.marks;
			if(mr != -1){
				JOptionPane.showMessageDialog(null,"This exam has already been taken.","Error",JOptionPane.ERROR_MESSAGE);	
				ed.setVisible(false);
				b1 = new JButton("Add Exam");
				bn3=new JButton("Add Question");
				b2 = new JButton("Edit Exam");
				bn4 = new JButton("Start Exam");
				b3 = new JButton("View Marks");
				bb4 = new JButton("Cancel");
				c = new AdminChoice(b1,bn3,b2,bn4,b3,bb4);
				b1.addActionListener(this);
				bn3.addActionListener(this);
				b2.addActionListener(this);
				b3.addActionListener(this);
				bn4.addActionListener(this);
				bb4.addActionListener(this);
				c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			}
		}
	
		if(e.getSource() == b7){			// Cancel of Edit Exam
			ed.setVisible(false);
			b1 = new JButton("Add Exam");
			bn3=new JButton("Add Question");
			b2 = new JButton("Edit Exam");
			bn4 = new JButton("Start Exam");
			b3 = new JButton("View Marks");
			bb4 = new JButton("Cancel");
			c = new AdminChoice(b1,bn3,b2,bn4,b3,bb4);
			b1.addActionListener(this);
			bn3.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			bn4.addActionListener(this);
			bb4.addActionListener(this);
			c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		if(e.getSource() == b6 && mr==-1){			// Editing exam information
			fname = new String(f9.getText().toUpperCase()+"_"+f10.getText().toUpperCase()+"_"+ff11.getText().toUpperCase()+".txt");
			try{
				String s = new String("input");
				String s1 = new String(fname);
				String s2 = new String("exam");
				os.writeObject(s);
				os.flush();
				os.writeObject(s1);
				os.flush();
				os.writeObject(s2);
				os.flush();
				ae = (AddExamInfo)is.readObject();
				qas = new AddQ[ae.totalques];
				for(int a=0; a<ae.totalques; a++)
					qas[a] = (AddQ)is.readObject();
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Exam information file is not found.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			ed.setVisible(false);			
			f1 = new JTextField(ae.cn);
			f1.setEnabled(false);
			f2 = new JTextField(ae.cc);
			f2.setEnabled(false);
			f22 = new JTextField(ae.sec);
			f22.setEnabled(false);
			f3 = new JTextField(ae.stdidfrom);
			f3.setEnabled(false);
			f4 = new JTextField(ae.stdidto);
			f4.setEnabled(false);
			f5 = new JTextField(ae.ctno);
			f5.setEnabled(false);
			f6 = new JTextField(Integer.toString(ae.marks));
			f7 = new JTextField(Integer.toString(ae.totalques));
			f8 = new JTextField(Integer.toString(ae.time));
			String names[] = {ae.examtype};
			c1 = new JComboBox(names);
			b44 = new JButton("OK");
			b55 = new JButton("Cancel");
			ex = new AddExam(c1,f1,f2,f22,f3,f4,f5,f6,f7,f8,b44,b55);
			b44.addActionListener(this);
			b55.addActionListener(this);
			ex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			l = ae.totalques;
		}
		
		if(e.getSource() == b55){
			ex.setVisible(false);
			b1 = new JButton("Add Exam");
			bn3=new JButton("Add Question");
			b2 = new JButton("Edit Exam");
			bn4 = new JButton("Start Exam");
			b3 = new JButton("View Marks");
			bb4 = new JButton("Cancel");
			c = new AdminChoice(b1,bn3,b2,bn4,b3,bb4);
			b1.addActionListener(this);
			bn3.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			bn4.addActionListener(this);
			bb4.addActionListener(this);
			c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	
		if(e.getSource()==b44 && mr == -1){
			exam = new AddExamInfo(f1.getText(),f2.getText(),f22.getText(),f3.getText(),f4.getText(),f5.getText(),(String)c1.getSelectedItem(),Integer.parseInt(f6.getText()),Integer.parseInt(f7.getText()),Integer.parseInt(f8.getText()));				
			qn = exam.totalques;
			temp = new AddQ[qn];
			if(qn<l)
				l = qn;
			ex.setVisible(false);			
			String mm = (String)c1.getSelectedItem();
			if(mm.equals("MCQ")){
				f14 = new JTextField(qas[k].que);
				f15 = new JTextField(qas[k].A);
				f16 = new JTextField(qas[k].B);
				f17 = new JTextField(qas[k].C);
				f18 = new JTextField(qas[k].D);
				f19 = new JTextField(qas[k].ans);
				String s = Integer.toString(k+1);
				if(qn!=1)
					bn10 =new JButton("Next");
				else
					bn10 =new JButton("Finish");	
				q = new Question(s,f14,f15,f16,f17,f18,f19,bn10);
				bn10.addActionListener(this);
			}		
			if(mm.equals("True/False")){
				f14 = new JTextField(qas[k].que);
				r = new JTextField(qas[k].cans);
				String s = Integer.toString(k+1);
				bn10 =new JButton("Next");
				q = new Question(s,f14,r,bn10);
				bn10.addActionListener(this);
			}
			k++;
		}
		
		if(e.getSource()==bn10 && mr == -1 &&((k<=l-1 && l<qn) || (k<l-1 && l==qn))){
			String mm = (String)c1.getSelectedItem();
			if(mm.equals("MCQ")){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				temp[p] = new AddQ(f14.getText(),f15.getText(),f16.getText(),f17.getText(),f18.getText(),f19.getText());
				p++;
				q.setVisible(false);
				f14 = new JTextField(qas[k].que);
				f15 = new JTextField(qas[k].A);
				f16 = new JTextField(qas[k].B);
				f17 = new JTextField(qas[k].C);
				f18 = new JTextField(qas[k].D);
				f19 = new JTextField(qas[k].ans);
				String s = Integer.toString(k+1);
				bn10 =new JButton("Next");
				q = new Question(s,f14,f15,f16,f17,f18,f19,bn10);
				bn10.addActionListener(this);
			}
			if(mm.equals("True/False")){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T or F.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				temp[p] = new AddQ(f14.getText(),r.getText());
				p++;
				q.setVisible(false);
				f14 = new JTextField(qas[k].que);
				r = new JTextField(qas[k].cans);
				String s = Integer.toString(k+1);
				bn10 =new JButton("Next");
				q = new Question(s,f14,r,bn10);
				bn10.addActionListener(this);
			}
			k++;
		}
		
		if(e.getSource()==bn10 && mr == -1 && k==l-1 && l==qn){
			String mm = (String)c1.getSelectedItem();
			if(mm.equals("MCQ")){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				temp[p] = new AddQ(f14.getText(),f15.getText(),f16.getText(),f17.getText(),f18.getText(),f19.getText());
				p++;
				q.setVisible(false);
				f14 = new JTextField(qas[k].que);
				f15 = new JTextField(qas[k].A);
				f16 = new JTextField(qas[k].B);
				f17 = new JTextField(qas[k].C);
				f18 = new JTextField(qas[k].D);
				f19 = new JTextField(qas[k].ans);
				String s = Integer.toString(k+1);
				bn10 =new JButton("Finish");
				q = new Question(s,f14,f15,f16,f17,f18,f19,bn10);
				bn10.addActionListener(this);
			}
			if(mm.equals("True/False")){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T or F.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				temp[p] = new AddQ(f14.getText(),r.getText());
				p++;
				q.setVisible(false);
				f14 = new JTextField(qas[k].que);
				r = new JTextField(qas[k].cans);
				String s = Integer.toString(k+1);
				bn10 =new JButton("Finish");
				q = new Question(s,f14,r,bn10);
				bn10.addActionListener(this);
			}
			k++;
		}
		
		if(e.getSource()==bn10 && mr == -1&& l<qn && k<qn-1 && k>l-1){
			String mm = (String)c1.getSelectedItem();
			if(mm.equals("MCQ")){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				temp[p] = new AddQ(f14.getText(),f15.getText(),f16.getText(),f17.getText(),f18.getText(),f19.getText());
				p++;
				q.setVisible(false);
				f14 = new JTextField();
				f15 = new JTextField();
				f16 = new JTextField();
				f17 = new JTextField();
				f18 = new JTextField();
				f19 = new JTextField();
				String s = Integer.toString(k+1);
				bn10 =new JButton("Next");
				q = new Question(s,f14,f15,f16,f17,f18,f19,bn10);
				bn10.addActionListener(this);
			}
			if(mm.equals("True/False")){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T or F.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				temp[p] = new AddQ(f14.getText(),r.getText());
				p++;
				q.setVisible(false);
				f14 = new JTextField();
				r = new JTextField();
				String s = Integer.toString(k+1);
				bn10 =new JButton("Next");
				q = new Question(s,f14,r,bn10);
				bn10.addActionListener(this);
			}	
			k++;
		}
		
		if(e.getSource()==bn10 && mr == -1 && l<qn && k==qn-1){
			String mm = (String)c1.getSelectedItem();
			if(mm.equals("MCQ")){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				temp[p] = new AddQ(f14.getText(),f15.getText(),f16.getText(),f17.getText(),f18.getText(),f19.getText());
				p++;
				q.setVisible(false);
				f14 = new JTextField();
				f15 = new JTextField();
				f16 = new JTextField();
				f17 = new JTextField();
				f18 = new JTextField();
				f19 = new JTextField();
				String s = Integer.toString(k+1);
				bn10 =new JButton("Finish");
				q = new Question(s,f14,f15,f16,f17,f18,f19,bn10);
				bn10.addActionListener(this);
			}
			if(mm.equals("True/False")){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T or F.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				temp[p] = new AddQ(f14.getText(),r.getText());
				p++;
				q.setVisible(false);
				f14 = new JTextField();
				r = new JTextField();
				String s = Integer.toString(k+1);
				bn10 =new JButton("Finish");
				q = new Question(s,f14,r,bn10);
				bn10.addActionListener(this);
			}
			k++;
		}
			
		if(e.getSource()==bn10 &&mr == -1&& k>qn-1){
			String mm = (String)c1.getSelectedItem();
			if(mm.equals("MCQ")){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				temp[p] = new AddQ(f14.getText(),f15.getText(),f16.getText(),f17.getText(),f18.getText(),f19.getText());
				p++;
			}
			if(mm.equals("True/False")){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T or F.","Warning",JOptionPane.WARNING_MESSAGE);	
					return;
				}
				temp[p] = new AddQ(f14.getText(),r.getText());
				p++;
			}
			fname = new String(f9.getText().toUpperCase()+"_"+f10.getText().toUpperCase()+"_"+ff11.getText().toUpperCase()+".txt");
			try{
				String s = new String("output");
				String s1 = new String(fname);
				String s2 = new String(Integer.toString(qn));
				os.writeObject(s);
				os.flush();
				os.writeObject(s1);
				os.flush();
				os.writeObject(s2);
				os.flush();
				os.writeObject(exam);
				os.flush();
				qas = new AddQ[ae.totalques];
				for(int a=0; a<qn; a++){
					os.writeObject(temp[a]);
					os.flush();
				}	
				k = 0;
				p = 0;
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Question file is not created.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			q.setVisible(false);
			b1 = new JButton("Add Exam");
			bn3=new JButton("Add Question");
			b2 = new JButton("Edit Exam");
			bn4 = new JButton("Start Exam");
			b3 = new JButton("View Marks");
			bb4 = new JButton("Cancel");
			c = new AdminChoice(b1,bn3,b2,bn4,b3,bb4);
			b1.addActionListener(this);
			bn3.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			bn4.addActionListener(this);
			bb4.addActionListener(this);
			c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		mr=-1;
	/************* Taking file name for view marks *******************/
	
		if(e.getSource()==b3){
			mr=-1;		
			c.setVisible(false);
			f9 = new JTextField();
			f10 = new JTextField();
			ff11 = new JTextField();
			b66 =new JButton("OK");
			b7 =new JButton("Cancel");
			ed = new EditExam(f9,f10,ff11,b66,b7);
			b66.addActionListener(this);
			b7.addActionListener(this);
			ed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	/****************** Marks file is shown here **************************/
	
		if(e.getSource()==b66){			
			fname = new String("marks_"+f9.getText().toUpperCase()+"_"+f10.getText().toUpperCase()+"_"+ff11.getText().toUpperCase()+".txt");
			try{
				String s = new String("input");
				String s1 = new String(fname);
				String s2 = new String("marks");
				os.writeObject(s);
				os.flush();
				os.writeObject(s1);
				os.flush();
				os.writeObject(s2);
				os.flush();
				mt = (MarksTable)is.readObject();
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Marks file is not found","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			m = new Marks(mt);
		}

	/*************** Taking file name for starting  exam ***************/
		
		if(e.getSource() == bn4){
			mr=-1;	
			c.setVisible(false);
			f9 = new JTextField();
			f10 = new JTextField();
			ff11 = new JTextField();
			bb6 =new JButton("OK");
			b7 =new JButton("Cancel");
			ed = new EditExam(f9,f10,ff11,bb6,b7);
			bb6.addActionListener(this);
			b7.addActionListener(this);
			ed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	/********************* Exam is started now ***************************/
	
		if(e.getSource() == bb6){	
			fname = new String(f9.getText().toUpperCase()+"_"+f10.getText().toUpperCase()+"_"+ff11.getText().toUpperCase()+".txt");
			try{
				String s = new String("input");
				String s1 = new String(fname);
				String s2 = new String("start");
				os.writeObject(s);
				os.flush();
				os.writeObject(s1);
				os.flush();
				os.writeObject(s2);
				os.flush();
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Question file is not found","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			ed.setVisible(false);
			b1 = new JButton("Add Exam");
			bn3=new JButton("Add Question");
			b2 = new JButton("Edit Exam");
			bn4 = new JButton("Start Exam");
			b3 = new JButton("View Marks");
			bb4 = new JButton("Cancel");
			c = new AdminChoice(b1,bn3,b2,bn4,b3,bb4);
			b1.addActionListener(this);
			bn3.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			bn4.addActionListener(this);
			bb4.addActionListener(this);
			c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		}
	}	
	
	public static void main(String args[])
	{
		Admin ad = new Admin();
		ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
	


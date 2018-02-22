import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Student extends JFrame implements ActionListener{
	
	private JLabel label,label1,label2,l1,l2,l3,l4,l5;
	private JTextField field1,f11,f12,f13,f14,f15,f16,f17,f18,f19,r;
	private JPasswordField field2;
	private JButton button1,button2,b1,b2,b3,bb8,bb9,b10,b11,b66,b7,bn1,bn2;
	private IP_Address ip;
	private EditExam ei;
	public  Question q;
	private AddQ aq[];
	private AddExamInfo ae;
	private StudentOption op;
	private TimerThread tt;
	private ObjectOutputStream os,osm;
	private ObjectInputStream is,ism;
	private Socket client;
	private String s,address,ans[];
	private MarksTable mt;
	private Marks m;
	private TimerThread tht;
	public Container container = getContentPane();
	int l=0,k=0,mark=0,sum=0,mr;
//	private Wait ww;
	
	public Student()
	{
		super("Student");
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		
		label1 = new JLabel("Username :",SwingConstants.LEFT);
		label1.setToolTipText("Enter Username");
		
		label2 = new JLabel("Password :",SwingConstants.LEFT);
		label2.setToolTipText("Enter Password");
		
		field1 = new JTextField();
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
		if(e.getSource() == button1){					//  Taking user ID & password		
			if(!field1.getText().equals(field2.getText())){
				JOptionPane.showMessageDialog(null,"Invalid username & password!!!\nTry again ...","Error",JOptionPane.ERROR_MESSAGE);
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
		
		if(e.getSource() == bn2){	// Exit	
			System.exit(0);
		}
		
		if(e.getSource() == bn1){		// Connecting to the server	
			ip.setVisible(false);
			try{
				client = new Socket(ip.field.getText(),12345);
				System.out.println("student is connected to server...");
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Error in connecting to the server.\nPlease try again.","Error",JOptionPane.ERROR_MESSAGE);
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
			b1=new JButton("Appear at the exam");
			b2=new JButton("View marks");
			b3=new JButton("Cancel");
			op = new StudentOption(b1,b2,b3);
			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
		}
		
		if(e.getSource()==b2){			// taking file name for view  marks
			op.setVisible(false);
			f11 = new JTextField();
			f12 = new JTextField();
			f13 = new JTextField();
			b66 =new JButton("OK");
			b7 =new JButton("Cancel");
			ei = new EditExam(f11,f12,f13,b66,b7);
			b66.addActionListener(this);
			b7.addActionListener(this);
		}
		
		if(e.getSource()==b66){				// Marks is shown here	
			try{
				String s = new String("input");
				String s1 = new String("marks_"+f11.getText().toUpperCase()+"_"+f12.getText().toUpperCase()+"_"+f13.getText().toUpperCase()+".txt");
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
			String str = new String(field1.getText().substring(4));
			int temp = Integer.parseInt(str);
			Stdmark st = mt.arr[temp-1];
			mr = st.marks;
			if(mr == -1)
				JOptionPane.showMessageDialog(null,"The exam may not be held yet\nor,you are not eligible for the exam.","Warning",JOptionPane.WARNING_MESSAGE);
			else
				JOptionPane.showMessageDialog(null,"You have got "+Integer.toString(mr),field1.getText(),JOptionPane.INFORMATION_MESSAGE);
		}
		
		if(e.getSource()==b3){			// Disconnecting from the server	
			try{
				os.writeObject("disconnect");
				os.flush();
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Error in disconnecting from the server.","Error",JOptionPane.ERROR_MESSAGE);
			}
			op.setVisible(false);
			System.exit(0);
		}
	
		if(e.getSource()==button2){		
			System.exit(0);
		}
		
		if(e.getSource()==b7){			//		Student's GUI
			ei.setVisible(false);
			b1=new JButton("Appear at the exam");
			b2=new JButton("View marks");
			b3=new JButton("Cancel");
			op = new StudentOption(b1,b2,b3);
			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
		}
		
		if(e.getSource()==b1){		// taking file name for appaering  at  the  exam
			op.setVisible(false);	
			f11 = new JTextField();
			f12 = new JTextField();
			f13 = new JTextField();
			bb8 = new JButton("OK");
			bb9 = new JButton("Cancel");
			ei = new EditExam(f11,f12,f13,bb8,bb9);
			bb8.addActionListener(this);
			bb9.addActionListener(this);			
		}
		
		if(e.getSource()==bb9){
			ei.setVisible(false);
			b1=new JButton("Appear at the exam");
			b2=new JButton("View marks");
			b3=new JButton("Cancel");
			op = new StudentOption(b1,b2,b3);
			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
		}
		
		if(e.getSource()==bb8){
			JOptionPane.showMessageDialog(null,"Please wait a few second ...","Information",JOptionPane.INFORMATION_MESSAGE);
			try{
				String sss = new String("input");
				String ss1 = new String("marks_"+f11.getText().toUpperCase()+"_"+f12.getText().toUpperCase()+"_"+f13.getText().toUpperCase()+".txt");
				String ss2 = new String("marks");
				os.writeObject(sss);
				os.flush();
				os.writeObject(ss1);
				os.flush();
				os.writeObject(ss2);
				os.flush();
				mt = (MarksTable)is.readObject();
			}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null,"Marks file is not found.","Error",JOptionPane.ERROR_MESSAGE);
			}
			String str = new String(field1.getText().substring(4));
			int temp = Integer.parseInt(str);
			Stdmark st = mt.arr[temp-1];
			mr = st.marks;
			if(mr != -1){
				JOptionPane.showMessageDialog(null,"You have already attended in the exam.","Warning",JOptionPane.WARNING_MESSAGE);
				ei.setVisible(false);
				b1=new JButton("Appear at the exam");
				b2=new JButton("View marks");
				b3=new JButton("Cancel");
				op = new StudentOption(b1,b2,b3);
				b1.addActionListener(this);
				b2.addActionListener(this);
				b3.addActionListener(this);
			}
			else{
				try{
					String s = new String("input");
					String s1 = new String(f11.getText().toUpperCase()+"_"+f12.getText().toUpperCase()+"_"+f13.getText().toUpperCase()+".txt");
					String s2 = new String("Q_request");
					os.writeObject(s);
					os.flush();
					os.writeObject(s1);
					os.flush();
					os.writeObject(s2);
					os.flush();	
					ae = (AddExamInfo)is.readObject();
				}		
				catch(Exception ee){
					JOptionPane.showMessageDialog(null,"Question file is not found.","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				sum = ae.marks;			
				l = ae.totalques;
				aq= new AddQ[l];
				ans = new String[l];
				for(int g=0; g<l; g++){
					ans[g] = new String();
				}
				try{
					for(int i=0; i<l; i++){
						aq[i] = (AddQ)is.readObject();
					}
				}
				catch(Exception ee){
					JOptionPane.showMessageDialog(null,"Error reading in the questions","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				ei.setVisible(false);
				label = new JLabel("Time:"+Integer.toString(ae.time)+" min",SwingConstants.LEFT);
				tht = new TimerThread(ae.time,label);
				String mm = (String)ae.examtype;
				if(mm.equals("MCQ")){
					String s = Integer.toString(k+1);			
					l1 = new JLabel(aq[k].que,SwingConstants.LEFT);
					l2 = new JLabel("a : "+aq[k].A,SwingConstants.LEFT);
					l3 = new JLabel("b : "+aq[k].B,SwingConstants.LEFT);
					l4 = new JLabel("c : "+aq[k].C,SwingConstants.LEFT);
					l5 = new JLabel("d : "+aq[k].D,SwingConstants.LEFT);
					f19 = new JTextField();
					b10 =new JButton("Next");
					b11 =new JButton("Back");
					q = new Question(label,s,l1,l2,l3,l4,l5,f19,b10,b11);
					b10.addActionListener(this);
					b11.setEnabled(false);
				}
				if(mm.equals("True/False")){
					l1 = new JLabel(aq[k].que,SwingConstants.LEFT);			
					r = new JTextField();
					String s = Integer.toString(k+1);
					b10 =new JButton("Next");
					b11 =new JButton("Back");
					q = new Question(label,s,l1,r,b10,b11);
					b10.addActionListener(this);
					b11.setEnabled(false);
				}
				k++;
			}		
		}
		
		if(e.getSource()==b10 && mr==-1 && k<l-1){
			String mm = (String)ae.examtype;
			if(mm.equals("MCQ")){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				ans[k-1]=f19.getText();
				q.setVisible(false);
				String s = Integer.toString(k+1);			
				l1 = new JLabel(aq[k].que,SwingConstants.LEFT);
				l2 = new JLabel("a : "+aq[k].A,SwingConstants.LEFT);
				l3 = new JLabel("b : "+aq[k].B,SwingConstants.LEFT);
				l4 = new JLabel("c : "+aq[k].C,SwingConstants.LEFT);
				l5 = new JLabel("d : "+aq[k].D,SwingConstants.LEFT);
				f19 = new JTextField(ans[k]);
				b10 =new JButton("Next");
				b11 =new JButton("Back");
				q = new Question(label,s,l1,l2,l3,l4,l5,f19,b10,b11);
				b10.addActionListener(this);
				b11.addActionListener(this);
			}
			if(mm.equals("True/False")){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T for true and F for false","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				ans[k-1] = r.getText();
				q.setVisible(false);				
				l1 = new JLabel(aq[k].que,SwingConstants.LEFT);			
				r = new JTextField(ans[k]);
				String s = Integer.toString(k+1);
				b10 =new JButton("Next");
				b11 =new JButton("Back");
				q = new Question(label,s,l1,r,b10,b11);
				b10.addActionListener(this);
				b11.addActionListener(this);
			}
			k++;
		}
		
		if(e.getSource()==b10 && mr==-1 && k==l-1){
			String mm = (String)ae.examtype;
			if(mm.equals("MCQ")){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				ans[k-1]=f19.getText();
				q.setVisible(false);
				String s = Integer.toString(k+1);			
				l1 = new JLabel(aq[k].que,SwingConstants.LEFT);
				l2 = new JLabel("a : "+aq[k].A,SwingConstants.LEFT);
				l3 = new JLabel("b : "+aq[k].B,SwingConstants.LEFT);
				l4 = new JLabel("c : "+aq[k].C,SwingConstants.LEFT);
				l5 = new JLabel("d : "+aq[k].D,SwingConstants.LEFT);
				f19 = new JTextField(ans[k]);
				b10 =new JButton("Finish");
				b11 =new JButton("Back");
				q = new Question(label,s,l1,l2,l3,l4,l5,f19,b10,b11);
				b10.addActionListener(this);
				b11.addActionListener(this);
			}
			if(mm.equals("True/False")){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T for true and F for false","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				ans[k-1] = r.getText();
				q.setVisible(false);
				l1 = new JLabel(aq[k].que,SwingConstants.LEFT);			
				r = new JTextField(ans[k]);
				String s = Integer.toString(k+1);
				b10 =new JButton("Finish");
				b11 =new JButton("Back");
				q = new Question(label,s,l1,r,b10,b11);
				b10.addActionListener(this);
				b11.addActionListener(this);
			}
			k++;
		}
		
		if(e.getSource()==b10 && mr==-1 && k>l-1){
			String mm = (String)ae.examtype;
			if(mm.equals("MCQ")){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				ans[k-1]=f19.getText();
				q.setVisible(false);
				for(int i=0; i<l; i++){
					if(ans[i].equalsIgnoreCase(aq[i].ans)){
						mark += sum;
					}
				}
			}
			if(mm.equals("True/False")){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T for true and F for false","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				ans[k-1] = r.getText();
				q.setVisible(false);
				for(int i=0; i<l; i++){
					if(ans[i].equalsIgnoreCase(aq[i].cans)){
						mark += sum;
					}
				}
			}
			tht.stop();
			mt.EditMark(field1.getText(),mark);
			try{
				String s = new String("output");
				String s1 = new String("marks_"+f11.getText().toUpperCase()+"_"+f12.getText().toUpperCase()+"_"+f13.getText().toUpperCase()+".txt");
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
				JOptionPane.showMessageDialog(null,"Marks file is not updated","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			b1=new JButton("Appear at the exam");
			b2=new JButton("View marks");
			b3=new JButton("Cancel");
			op = new StudentOption(b1,b2,b3);
			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			k=0;	
		}

		if(e.getSource()==b11 && mr==-1 && k>2){	
			String mm = (String)ae.examtype;
			if(mm.equals("MCQ")){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				k -= 2;					
				q.setVisible(false);
				String s = Integer.toString(k+1);			
				l1 = new JLabel(aq[k].que,SwingConstants.LEFT);
				l2 = new JLabel("a : "+aq[k].A,SwingConstants.CENTER);
				l3 = new JLabel("b : "+aq[k].B,SwingConstants.LEFT);
				l4 = new JLabel("c : "+aq[k].C,SwingConstants.LEFT);
				l5 = new JLabel("d : "+aq[k].D,SwingConstants.LEFT);
				f19 = new JTextField(ans[k]);
				b10 =new JButton("Next");
				b11 =new JButton("Back");
				q = new Question(label,s,l1,l2,l3,l4,l5,f19,b10,b11);
				b10.addActionListener(this);
				b11.addActionListener(this);
			}
			if(mm.equals("True/False")){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T for true and F for false","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				k -= 2;	
				q.setVisible(false);				
				l1 = new JLabel(aq[k].que,SwingConstants.LEFT);			
				r = new JTextField(ans[k]);
				String s = Integer.toString(k+1);
				b10 =new JButton("Next");
				b11 =new JButton("Back");
				q = new Question(label,s,l1,r,b10,b11);
				b10.addActionListener(this);
				b11.addActionListener(this);
			}
			k++;
		}
		
		if(e.getSource()==b11 && mr==-1 && k==2){		
			String mm = (String)ae.examtype;
			if(mm.equals("MCQ")){
				if(!(f19.getText().equalsIgnoreCase("a") || f19.getText().equalsIgnoreCase("b") || f19.getText().equalsIgnoreCase("c") || f19.getText().equalsIgnoreCase("d"))){
					JOptionPane.showMessageDialog(null,"Please enter a or b or c or d","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				k -= 2;	
				q.setVisible(false);
				String s = Integer.toString(k+1);			
				l1 = new JLabel(aq[k].que,SwingConstants.LEFT);
				l2 = new JLabel("a : "+aq[k].A,SwingConstants.LEFT);
				l3 = new JLabel("b : "+aq[k].B,SwingConstants.LEFT);
				l4 = new JLabel("c : "+aq[k].C,SwingConstants.LEFT);
				l5 = new JLabel("d : "+aq[k].D,SwingConstants.LEFT);
				f19 = new JTextField(ans[k]);
				b10 =new JButton("Next");
				b11 =new JButton("Back");
				b11.setEnabled(false);
				q = new Question(label,s,l1,l2,l3,l4,l5,f19,b10,b11);
				b10.addActionListener(this);
				b11.addActionListener(this);
			}
			if(mm.equals("True/False")){
				if(!(r.getText().equalsIgnoreCase("T") || r.getText().equalsIgnoreCase("F"))){
					JOptionPane.showMessageDialog(null,"Please enter T for true and F for false","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				k -= 2;	
				q.setVisible(false);
				l1 = new JLabel(aq[k].que,SwingConstants.LEFT);			
				r = new JTextField(ans[k]);
				String s = Integer.toString(k+1);
				b10 =new JButton("Next");
				b11 =new JButton("Back");
				b11.setEnabled(false);
				q = new Question(label,s,l1,r,b10,b11);
				b10.addActionListener(this);
				b11.addActionListener(this);
			}
			k++;
		}		
	}
	
	public static void main(String args[])
	{
		Student st = new Student();
		st.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class TimerThread extends Thread{
	
		public int duration;
		public JLabel l1;
    	public  boolean life; 
	
		public TimerThread(int d,JLabel label){
			super("TimerThread");
			duration=d;
			l1=label;
			life=true;
			start();	
		}	
	
		public void run()
		{
			try{
				for(int i=duration-1;i>=0 && life;i--){
					for(int j=59;j>=0;j--){
						if(j>=10)
							l1.setText("Time: "+Integer.toString(i)+":"+Integer.toString(j)+" min");
						else
							l1.setText("Time: "+Integer.toString(i)+":"+"0"+Integer.toString(j)+" min");
						Thread.sleep(1000);
					}
				}
			}
			catch(InterruptedException e){
				System.out.println("Interrupted Exception");
			}
			JOptionPane.showMessageDialog(null,"Time is up.");
			
			q.setVisible(false);
			if(ae.examtype.equals("MCQ")){
				ans[k-1]=f19.getText();
				for(int i=0; i<l; i++){
					if(ans[i].equalsIgnoreCase(aq[i].ans)){
						mark += sum;
					}
				}
			}
			if(ae.examtype.equals("True/False")){
				ans[k-1]=r.getText();
				for(int i=0; i<l; i++){
					if(ans[i].equalsIgnoreCase(aq[i].cans)){
						mark += sum;
					}
				}
			}
			mt.EditMark(field1.getText(),mark);
			try{
					String s = new String("output");
					String s1 = new String("marks_"+f11.getText().toUpperCase()+"_"+f12.getText().toUpperCase()+"_"+f13.getText().toUpperCase()+".txt");
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
					JOptionPane.showMessageDialog(null,"Marks file is not updated");
					return;
			}
			k = 0;
			mark = 0;
			passAction();	
		}	
	}

	public void passAction()
	{
		b1=new JButton("Appear at the exam");
		b2=new JButton("View marks");
		b3=new JButton("Cancel");
		op = new StudentOption(b1,b2,b3);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);	
	}
	
}


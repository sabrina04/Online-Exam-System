import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Marks extends JFrame{
	
	public JTable t1;
	public JScrollPane s1;
	public String str1[][];
	public MarksTable m;
	public String str2[];
	public String f,t;
		
	Marks(MarksTable m)
	{
		Container container = getContentPane();
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));	
		int i = m.n_o_std;
		int j;
		str2=new String[2];
		str1=new String[i][2];
		str2[0]="Student Id";
		str2[1]="Marks";
		for(int h=0;h<i;h++){
			str1[h][0] = m.arr[h].stdid;
			if(m.arr[h].marks!=-1)
				str1[h][1]=Integer.toString(m.arr[h].marks);
			else
				str1[h][1]="Not Appeared";
		}
		t1 = new JTable(str1,str2);
		t1.setEnabled(false);
		s1 = new JScrollPane(t1);
		container.add(s1);
	
		setSize(550,550);
		setLocation(120,10);
		setVisible(true);
	} 
}

class MarksTable implements Serializable{
	
	public Stdmark arr[];
	public String from,to;
	public int n_o_std;
	
	public MarksTable(String from,String to)
	{
		this.from = from;
		this.to = to;
		String f = from.substring(4);
		String t = to.substring(4);
		String str = from.substring(0,4);
		int ff = Integer.parseInt(f);
		int tt = Integer.parseInt(t);
		arr = new Stdmark[tt-ff+1];
		int i;
		int d = tt - ff+1;
		n_o_std= tt-ff+1;
		String st = new String(from);
		for(i=0; i<d; i++){
			arr[i] = new Stdmark(st);
		 	ff++;
		 	String temp = Integer.toString(ff);
		 	if(temp.length()==1)
		 		st = new String(str+"00"+temp);
		 	else if(temp.length()==2)
		 		st = new String(str+"0"+temp);
		 	else
		 		st = new String(str+temp);
		 }
	}
	
	public void EditMark(String id,int m)
	{
		for(int i=0;i<n_o_std;i++){
			if(arr[i].stdid.equals(id)){
				arr[i].marks = m;
			}
		}	
	}
}

class Stdmark implements Serializable{
	
	public String stdid;
	public int marks;
		
	public Stdmark(String stdid)
	{
		this.stdid = stdid;
		marks = -1;
	}
}
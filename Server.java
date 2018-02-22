import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	
public class Server extends JFrame{
 
	private ServerSocket server;
   	private int counter = 0;
   	private boolean start = false;
   	private String start_fn = new String();
   	private User user[];
   
   	public Server()
   	{
      	user = new User[100];
      	try{
      		server = new ServerSocket( 12345, 100 );
        	System.out.println("Waiting for connection.....");
     	}
      	catch(Exception excp){
      		excp.printStackTrace();	
      	}
   	} 
   
	   public void runServer()
	   {
	      try {
	         while (counter<100) {
	            try{
	            	user[counter] = new User(server.accept());
	            	System.out.println("Server is connected to user "+Integer.toString(counter+1));
	            	counter++;
	            }
	            catch (EOFException eofException) {
	               System.err.println( "Server terminated connection" );
	            }
	         } 
	      }
	      catch ( IOException ioException ) {
	         ioException.printStackTrace();
	      }
	
	   } 
	
	public static void main( String args[] )
	{
		Server application = new Server();
	    application.runServer();
	}
   
   	private class User extends Thread{
   	
		private Socket connection;
   		private ObjectOutputStream output,osm;
    	private ObjectInputStream input,ism,istm;
    	String type;
    	
    	public User(Socket socket)
    	{
    		connection = socket;
    		try{
    			output = new ObjectOutputStream(connection.getOutputStream());
      			output.flush(); 
     			input = new ObjectInputStream(connection.getInputStream());
			}
			catch(IOException ioe){
				ioe.printStackTrace();
			}
			start();
		}
		
		public void run()
		{
			while(true){
				
				MarksTable mt;
				AddExamInfo ae;
				AddQ qs[];	
				
				try{
					type = (String)input.readObject();
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
				if(type.equals("output")){
					
					try{
						String filename = (String)input.readObject();
						String m = (String)input.readObject();
						
						if(m.equals("Add_Q_Now")){
							ae = (AddExamInfo)input.readObject();
							FileOutputStream os = new FileOutputStream(filename);
							osm = new ObjectOutputStream(os);
							osm.writeObject(ae);
							osm.flush();
							osm.close();	
						}
						
						else if(m.equals("marks")){
							mt = (MarksTable) input.readObject();
							FileOutputStream os = new FileOutputStream(filename);
							osm = new ObjectOutputStream(os);
							osm.writeObject(mt);
							osm.flush();
							osm.close();	
						}
						
						else{
							int no_of_obj = Integer.parseInt(m);
							ae = (AddExamInfo)input.readObject();
							qs = new AddQ[no_of_obj];
							for(int p=0; p<no_of_obj; p++){
								qs[p] = (AddQ)input.readObject();
							}
							FileOutputStream os = new FileOutputStream(filename);
							osm = new ObjectOutputStream(os);
							osm.writeObject(ae);
							osm.flush();
							for(int p=0; p<no_of_obj; p++){
								osm.writeObject(qs[p]);
								osm.flush();
							}
							osm.close();
						}
					}
					catch(Exception e){
						e.printStackTrace();
						try{	
							output.writeObject("Error");
							output.flush();
						}
						catch(Exception eee){
							eee.printStackTrace();
						}
					}	
				}
				
				if(type.equals("input")){
					
					try{
						String filename = (String)input.readObject();
				    	String m = (String)input.readObject();
						
						if(m.equals("marks")){
							FileInputStream is = new FileInputStream(filename);
							ism = new ObjectInputStream(is);
							mt=(MarksTable) ism.readObject();
							ism.close();
							output.writeObject(mt);
							output.flush(); 
						}
						
						else if(m.equals("exam")){
							FileInputStream is = new FileInputStream(filename);
							istm = new ObjectInputStream(is);
							ae = (AddExamInfo)istm.readObject();
							int a = ae.totalques;
							qs = new AddQ[a];
							for(int p=0; p<a; p++){
								qs[p] = (AddQ)istm.readObject();
							}
							istm.close();
							output.writeObject(ae);
							output.flush();
							for(int q=0; q<a; q++){
								output.writeObject(qs[q]);
								output.flush();
							}
						}
						
						else if(m.equals("info")){
							FileInputStream is = new FileInputStream(filename);
							istm = new ObjectInputStream(is);
							ae = (AddExamInfo)istm.readObject();
							istm.close();
							output.writeObject(ae);
							output.flush();
						}
					
						else if(m.equals("Q_request")){
							
							while(!(start && filename.equals(start_fn))){
								sleep(1000);
							}
							FileInputStream is = new FileInputStream(filename);
							istm = new ObjectInputStream(is);
							ae = (AddExamInfo)istm.readObject();
							int a = ae.totalques;
							qs = new AddQ[a];
							for(int p=0; p<a; p++){
								qs[p] = (AddQ)istm.readObject();
							}
							istm.close();
							output.writeObject(ae);
							output.flush();
							for(int q=0; q<a; q++){
								output.writeObject(qs[q]);
								output.flush();
							}
						}
						else if(m.equals("start")){
							start = true;
							start_fn = new String(filename);
							FileInputStream is = new FileInputStream(filename);
							istm = new ObjectInputStream(is);
							ae = (AddExamInfo)istm.readObject();
							int delay = ae.time * 1000 * 60;
							sleep(delay);
							start = false;
							start_fn = new String();	
						} 
				
					}	
					catch(Exception e){
						e.printStackTrace();
						try{	
							output.writeObject("Error");
							output.flush();
						}
						catch(Exception eee){
							eee.printStackTrace();
						}
					}	
				}
				
				if(type.equals("disconnect")){
					try {
         				output.close();
         				input.close();
        				connection.close();
        				break;
     	 			}
      				catch(IOException ioException) {
         				ioException.printStackTrace();
     				}
				}

			}
			
		}
   	
	}
} 





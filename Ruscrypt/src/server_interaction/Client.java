package server_interaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import gui.GUI;
import user.User;

public class Client {
	private boolean connection = false;
	private int PORT=8080;
	private String IP= "localhost";
	private GUI gui;
	private static BufferedReader in;
	private PrintWriter out;
	private Socket socket;
	private User user;
	private Thread thread;
	public String login;
	public Client(User user,GUI gui){
		this.gui = gui;
		this.user = user;
		connect();
	}
	private void  connect(){
		 socket = null;
		 for(;;){
				 
			 
			try {
				
				 socket = new Socket(IP,PORT);
				 
				
				 connection = true;
				
				 
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				
				
				
				
				 thread = new Thread(()->{
					String txt=null;
					while(connection){
						try {
							 txt = in.readLine();
							if(txt!=null){
								
								System.out.println("Get From Server :"+txt);
								command(txt);
							}
							
						} catch (java.net.SocketException e) {
							// TODO Auto-generated catch block
							connection = false;
							
							e.printStackTrace();
						}
						catch (IOException e) {
							// TODO Auto-generated catch block
							
							e.printStackTrace();
						}
					}
				});
				thread.start();
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
				
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				continue;
			}
		 }
	}
	private void command(String txt){
		if(txt.startsWith("logged/")){
			txt=txt.replace("logged/","");
			
			login = txt;
			gui.logged();
		}else if(txt.equals("unlogged")){
			System.out.println("fuck");
		}else if(txt.startsWith("friends :")){
			txt=txt.replace("friends :","");
			gui.renderFriends(txt);
		}else if(txt.startsWith("online:")){
			txt=txt.replace("online:","");
			gui.updateFriendsOnline(txt);
		}else if(txt.startsWith("offline:")){
			txt=txt.replace("offline:","");
			gui.updateFriendsOffline(txt);
		}else{
			System.out.println(txt);
		}
	}
	public void send(String txt){
		if(!txt.equalsIgnoreCase("exit")){
			out.println(txt);
		
			out.flush();
		}else{
			connection = false;
			try {
				out.println("exit");
				out.flush();
				socket.close();
				thread.destroy();
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

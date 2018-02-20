package server_interaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import encrypt.Diff;
import encrypt.EncrDecr;
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
	public Diff diff=null;
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
			txt=txt.replaceFirst("logged/","");
			
			login = txt;
			gui.logged();
		}else if(txt.equals("unlogged")){
			System.out.println("fuck");
		}else if(txt.startsWith("friends :")){
			txt=txt.replaceFirst("friends :","");
			gui.renderFriends(txt);
		}else if(txt.startsWith("online:")){
			txt=txt.replaceFirst("online:","");
			gui.updateFriendsOnline(txt);
		}else if(txt.startsWith("offline:")){
			txt=txt.replaceFirst("offline:","");
			gui.updateFriendsOffline(txt);
			
		}else if(txt.startsWith("request for mess from :")){
			txt=txt.replaceFirst("request for mess from :","");
			gui.requestForMess(txt);
		}else if(txt.startsWith("request for mess is denied:")){
			txt=txt.replaceFirst("request for mess is denied:", "");
			gui.requestForMessDenied(txt);
		}
		else if(txt.startsWith("Diff:")){
			txt=txt.replaceFirst("Diff:", "");
			String login ="";
			for(int i = 0 ; i < txt.length();i++) {
				if(txt.charAt(i)!='/') {
					login+=txt.charAt(i);
				}else {
					break;
				}
			}
			gui.inChatWith=login;
			gui.updateFriends(login);
			txt=txt.replace(login+'/', "");
			if(diff==null) {
				diff=new Diff(this,txt,gui);
			}else {
				diff.secondIt(txt);
			}
			
		}else if(txt.startsWith("Diff B:")){
			txt=txt.replaceFirst("Diff B:", "");
			diff.generateKey(Integer.parseInt(txt));
		}else if(txt.equals("get b")) {
			diff.repeatSendB();
		}else if(txt.startsWith("alph :")) {
			txt=txt.replaceFirst("alph :", "");
			diff.setAlph(txt);
			
		}else if(txt.startsWith("mess:")) {
			txt=txt.replaceFirst("mess:", "");
			gui.jta.setText(gui.jta.getText()+'\n'+gui.inChatWith+" : "+EncrDecr.decr(txt));
		}
		else{
			
		
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

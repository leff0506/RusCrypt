

package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import encrypt.Alph;
import encrypt.Diff;
import encrypt.DiffManager;
import messanger.Messanger;

public class Connection implements Runnable{
	private BufferedReader in;
	private final Socket user;
	private PrintWriter out;
	public String login="";
	public boolean isBusy;
	private ArrayList<Connection> toRemove= new ArrayList<>();
	public Connection(Socket user){
		this.user=user;

	}
	@Override
	synchronized public void run(){
		try {
			in = new BufferedReader(new InputStreamReader(user.getInputStream(),"utf-8"));
			out = new PrintWriter(new OutputStreamWriter(user.getOutputStream()),true);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean connect = true;
		String txt = "";
		while(connect){
			try {
				txt = in.readLine();
				if(!txt.equals("")){
					if(!txt.equals("exit")){
						serverCommand(txt);
						Server.log(txt);
					}else{
						connect = false;
						Server.log("Client exited");
				}
			}
				
				
			} catch (IOException e) {
				connect=false;
				if(login.equals("")){
					Server.log(user.getInetAddress().getHostAddress()+": exited ");
				}else{
					toRemove.clear();
					for(Connection c :Server.cons) {
						if(c.login.equals(login)) {
							toRemove.add(c);
						}
					}
					Server.cons.removeAll(toRemove);
					Server.log(login+": exited ");
					Server.send("offline:"+login);
					PrintStream printStream;
					try {
						printStream = new PrintStream(new FileOutputStream("data_base/info/"+login+".txt", false), false);
						printStream.println("offline");
						printStream.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				
				break;
			}
			
		}
		
	}
	synchronized public void serverCommand(String txt){
		if(txt.startsWith("authorization:")){
			txt=txt.replaceFirst("authorization:","");
			authorization(txt);
		}else if(txt.startsWith("registration:")){
			txt=txt.replaceFirst("registration:","");
			registration(txt);
		}else if(txt.startsWith("exitchat")){
			txt=txt.replaceFirst("exitchat/","");
			String login1="";
			for(int i = 0 ; i < txt.length();i++) {
				if(!(txt.charAt(i)=='/')) {
					login1+=txt.charAt(i);
				}else {
					break;
				}
			}
			txt=txt.replaceFirst(login1+'/',"");
			setOnline(login1);
			setOnline(txt);
			stopChat(login1);
		}else if(txt.startsWith("get friends")){
			sendFriends();
			
		}else if(txt.startsWith("connect:")){
			txt=txt.replaceFirst("connect:","");
			Messanger.getInstance().beginChat(txt);
		}else if(txt.startsWith("request for mess is denied:")){
			txt=txt.replaceFirst("request for mess is denied:", "");
			Messanger.getInstance().requestForMessDenied(txt);
		}else if(txt.startsWith("request for mess is accepted:")){
			txt=txt.replaceFirst("request for mess is accepted:", "");
			Messanger.getInstance().requestForMessAccepted(txt);
		}else if(txt.startsWith("Diff B:")) {
			txt=txt.replaceFirst("Diff B:", "");
			Server.log("get Diff B : "+txt);
			String idS="";
			for(int i = 0 ; i < txt.length();i++) {
				if(!(txt.charAt(i)=='/')) {
					idS+=txt.charAt(i);
				}else {
					break;
				}
			}
			txt=txt.replaceFirst(idS+"/","");
			Server.log("Diff B (2): "+txt);
			int id = Integer.parseInt(idS);
			
			String login="";
			for(int i = 0 ; i < txt.length();i++) {
				if(!(txt.charAt(i)=='/')) {
					login+=txt.charAt(i);
				}else {
					break;
				}
			}
			txt=txt.replaceFirst(login+"/","");
			Server.log("Diff B(3) : "+txt);
			
			for(DiffManager d:Messanger.diffs) {
				if(d.getId()==id) {
					d.B(login,Integer.parseInt(txt));
					break;
				}
			}
		}else if(txt.equals("get alph")){
			send("alph :"+Alph.getAlph());
		}else if(txt.startsWith("mess:")) {
			txt=txt.replaceFirst("mess:", "");
			String login="";
			for(int i = 0 ; i < txt.length();i++) {
				if(!(txt.charAt(i)=='/')) {
					login+=txt.charAt(i);
				}else {
					break;
				}
			}
			
			txt=txt.replaceFirst(login+'/',"");
			
			String login2="";
			for(int i = 0 ; i < txt.length();i++) {
				if(!(txt.charAt(i)=='/')) {
					login2+=txt.charAt(i);
				}else {
					break;
				}
			}
			
			txt=txt.replaceFirst(login2+'/',"");
			for(Connection c : Server.cons) {
				if(c.login.equals(login)) {
					c.send("mess:"+txt);
				}
			}
		}
		else{
			Server.log(txt);
		}
	}
	public void send(String txt){
		out.println(txt);
		out.flush();
	}
	private static String delim(String logins,int num) {
		String copy = logins;
		copy+='/';
		String login1="";
		String login2="";
		
		for(int i = 0 ; i < copy.length();i++){
			if(copy.charAt(i)!='/'){
				login1+=copy.charAt(i);
			}else{
				break;
			}
			
		}
		copy = copy.replaceFirst(login1+'/', "");
		for(int i = 0 ; i < copy.length();i++){
			if(copy.charAt(i)!='/'){
				login2+=copy.charAt(i);
			}else{
				break;
			}
			
		}
		if(num==1) {
			return login1;
		}else {
			return login2;
		}
	}
	private void authorization(String txt){
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("data_base/autho.txt")));
			String inStr;
			boolean autho=false;
			String inStrCut="";
			int temp =0;
			while((inStr = reader.readLine())!=null){
				inStrCut="";
				temp=0;
				for(int i = 0 ; i < inStr.length();i++){
					if(inStr.charAt(i)!='/'){
						inStrCut+=inStr.charAt(i);
					}else{
						if(temp ==1){
							break;
						}else{
							temp++;
							inStrCut+='/';
						}
						
					}
				}
				
				if(inStrCut.equals(txt)){
					String login="";
					for(int i = 0 ; i < inStr.length();i++){
						if(inStr.charAt(i)!='/'){
							login+=inStr.charAt(i);
						}else{
							break;
						}
					}
					this.login=login;
					BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream("data_base/info/"+login+".txt")));
					String inf = reader1.readLine();
					if(!inf.equals("offline")) {
						send("unlogged");
						return;
					}
					send("logged/"+login);
					autho=true;
					Server.cons.add(this);
					PrintStream printStream = new PrintStream(new FileOutputStream("data_base/info/"+login+".txt", false), false);
					printStream.println("online");
					printStream.close();
					Server.send("online:"+login);
					break;
				}
				
			}
			if(!autho){
				send("unlogged");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void registration(String txt){
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("data_base/autho.txt")));
			String inStr;
			String login ="";
			for(int i = 0 ; i < txt.length();i++){
				if(txt.charAt(i)!='/'){
					login+=txt.charAt(i);
				}else{
					break;
				}
			}
			
			boolean sign_up_error=false;
			while((inStr = reader.readLine())!=null){
				if(inStr.startsWith(login)){
					
					send("sign_up_error");
					sign_up_error=true;
					break;
				}
				
			}
			if(!sign_up_error){
				PrintStream printStream = new PrintStream(new FileOutputStream("data_base/autho.txt", true), true);
				printStream.println(txt);
				printStream.close();
				File file = new File("data_base/friends/"+login+".txt");
				file.createNewFile();
				File file1 = new File("data_base/info/"+login+".txt");
				file1.createNewFile();
				PrintStream printStream1 = new PrintStream(new FileOutputStream(file1, false), false);
				printStream1.println("offline");
				printStream1.close();
				
			}
			
			
			
		
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void sendFriends(){
		String out="";
		File file = new File("data_base/info");
		File[] people = file.listFiles();
		int am = people.length;
		
		for(int i = 0 ; i < am;i++){
			
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(people[i])));
				out+=people[i].getName();
				out=out.replaceAll(".txt","");
				out+='/';
				out+=reader.readLine();
				reader.close();
				out+=';';
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		send("friends :"+out);
	}
	public void setBusy(String login) {
		PrintStream printStream1;
		try {
			printStream1 = new PrintStream(new FileOutputStream("data_base/info/"+login+".txt", false), false);
			printStream1.println("busy");
			printStream1.close();
			sendFriends();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void setOnline(String login) {
		PrintStream printStream1;
		try {
			printStream1 = new PrintStream(new FileOutputStream("data_base/info/"+login+".txt", false), false);
			printStream1.println("online");
			
			printStream1.close();
			sendFriends();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void stopChat(String txt) {
		for(Connection c : Server.cons) {
			if(c.login.equals(txt)) {
				c.send("stop chat");
			}
		}
	}
	
	

}


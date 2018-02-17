package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;



public class Server {
	private ServerSocket serverSocket;
	private final int PORT = 8080;
	private static JTextArea jta;
	public static ArrayList<Connection> cons= new ArrayList<>();
	public Server(JTextArea jta1){
		jta=jta1;
		try{
		serverSocket = new ServerSocket(8080);
		log("server is running on port "+serverSocket.getLocalPort());
		Socket user = null;
		BufferedReader reader = null;
		setOffline();
		while(true){
			user = serverSocket.accept();
			log("accept : "+user.getInetAddress().getHostAddress());
			Connection con = new Connection(user);
			Thread thh = null;
			 thh = new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						try {
							thh.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Server.log(Integer.toString(cons.size()));					}
					
				}
			});
			//thh.start();
			Thread th = new Thread(con);
			th.start();
		}
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	synchronized public static void log(String message){
		
		jta.setText(jta.getText()+message+"\n");
	
    
	}
	public ServerSocket getServer(){
		return serverSocket;
	}
	public static void send(String txt){
		for(Connection c:cons){
			c.send(txt);
		}
	}
	private static void setOffline() {
		File file = new File("data_base/info");
		for(File f : file.listFiles()) {
			try {
				PrintWriter out = new PrintWriter(f);
				out.println("offline");
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}

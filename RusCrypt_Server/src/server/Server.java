package server;

import java.io.BufferedReader;
import java.io.IOException;
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
		while(true){
			user = serverSocket.accept();
			log("accept : "+user.getInetAddress().getHostAddress());
			Connection con = new Connection(user);
			
			Thread th = new Thread(con);
			th.start();
		}
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void log(String message){
		
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
}

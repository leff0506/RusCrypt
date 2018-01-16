package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;



public class Server {
	private ServerSocket serverSocket;
	private final int PORT = 8080;
	private static JTextArea jta;
	public Server(JTextArea jta1){
		jta=jta1;
		try{
		serverSocket = new ServerSocket(8080);
		log("server is running on port "+serverSocket.getLocalPort());
		Socket user = null;
		BufferedReader reader = null;
		
			user = serverSocket.accept();
			log("accept : "+user.getInetAddress().getHostAddress());
			Thread th = new Thread(new Connection(user));
			th.start();
		
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
}

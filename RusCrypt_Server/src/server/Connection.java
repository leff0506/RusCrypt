

package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class Connection implements Runnable{
	private BufferedReader in;
	private final Socket user;
	private PrintWriter out;
	public Connection(Socket user){
		this.user=user;

	}
	@Override
	public void run(){
		try {
			in = new BufferedReader(new InputStreamReader(user.getInputStream(),"utf-8"));
			out = new PrintWriter(user.getOutputStream(),true);
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
						
						Server.log(txt);
					}else{
						connect = false;
						System.out.println("Client exited");
				}
			}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}

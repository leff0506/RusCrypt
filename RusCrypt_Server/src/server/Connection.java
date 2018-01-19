

package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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
						serverCommand(txt);
						Server.log(txt);
					}else{
						connect = false;
						Server.log("Client exited");
				}
			}
				
				
			} catch (IOException e) {
				Server.log("error");
				e.printStackTrace();
				break;
			}
			
		}
		
	}
	public void serverCommand(String txt){
		if(txt.startsWith("authorization:")){
			txt=txt.replace("authorization:","");
			authorization(txt);
		}
	}
	private void send(String txt){
		out.println(txt);
		out.flush();
	}
	private void authorization(String txt){
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("data_base/autho.txt")));
			String inStr;
			boolean autho=false;
			while((inStr = reader.readLine())!=null){
				if(inStr.equals(txt)){
					
					send("logged");
					autho=true;
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
}


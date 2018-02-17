package messanger;

import java.util.ArrayList;

import encrypt.Diff;
import encrypt.DiffManager;
import server.Connection;
import server.Server;

public class Messanger {
	private static Messanger obj=null;
	public static ArrayList<DiffManager> diffs= new ArrayList<>();
	private Messanger(){}
	public static Messanger getInstance(){
		if(obj==null){
			obj= new Messanger();
		}
		return obj;
	}
	public void beginChat(String logins){

		String login1=delim(logins,1);
		String login2=delim(logins,2);

		Server.log("request for connection from #"+login1+"# to #"+login2+"#");
		for(Connection c :Server.cons){
			if(c.login.equals(login2)){
				Server.log("send to "+login2+" : "+"request for mess from :"+login1);
				c.send("request for mess from :"+login1);
				break;
			}
		}
	}
	public void requestForMessDenied(String logins){
		
		String login1=delim(logins,1);
		String login2=delim(logins,2);
		for(Connection c :Server.cons){
			if(c.login.equals(login2)){
				c.send("request for mess is denied:"+login1);
				break;
			}
		}
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
	public void requestForMessAccepted(String logins){
		
		String login1=delim(logins,1);
		String login2=delim(logins,2);
		Server.log("begin chatting (Diff) #"+login1+"#  #"+login2+"#");
		DiffManager diff = new DiffManager(login1,login2);
		diffs.add(diff);
		
	}
}

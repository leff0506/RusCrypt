package messanger;

import server.Server;

public class Messanger {
	private static Messanger obj=null;
	private Messanger(){}
	public static Messanger getInstance(){
		if(obj==null){
			obj= new Messanger();
		}
		return obj;
	}
	public void beginChat(String logins){
		String save = logins;
		logins+='/';
		String login1="";
		String login2="";
		
		for(int i = 0 ; i < logins.length();i++){
			if(logins.charAt(i)!='/'){
				login1+=logins.charAt(i);
			}else{
				break;
			}
			
		}
		logins = logins.replace(login1+'/', "");
		for(int i = 0 ; i < logins.length();i++){
			if(logins.charAt(i)!='/'){
				login2+=logins.charAt(i);
			}else{
				break;
			}
			
		}
		Server.log("request for connection from #"+login1+"# to #"+login2+"#");
	}
}

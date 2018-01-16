package user;

public class User {
	private boolean authorized = false;
	private String login;
	private String password;
	public User(){
		
	}
	
	public boolean isAuthorized() {
		return authorized;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void logIn(String login,String password){
		authorized=true;
		this.login=login;
		this.password=password;
	}
}

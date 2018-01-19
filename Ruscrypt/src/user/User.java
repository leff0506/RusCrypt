package user;

public class User {
	private boolean authorized = false;
	private String login;
	private String password;
	private String name;
	private String surname;

	
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

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}


}

package encrypt;

import math.MathInteraction;
import messanger.Messanger;
import server.Connection;
import server.Server;

public class Diff {
	private int id;
	public void setId(int id) {
		this.id = id;
	}
	private int prime;
	private int antiderivativeRoot;
	private int B1=-1;
	private DiffManager man;
	
	private String login1;
	private String login2;
	public Diff(String login,String login2) {
		this.man=man;
		this.login1=login;
		this.login2=login2;
		

		
		
	}
	synchronized public void setB(int B) {
		B1=B;

	}
	public int getId() {
		return id;
	}
	public String getLogin1() {
		return login1;
	}
	public void sendB(int B) {
		for(Connection c : Server.cons) {
			if(c.login.equals(login1)) {
				c.send("Diff B:"+B);
			}
		}
	}
	public void sendAll() {
		for(Connection c : Server.cons) {
			if(c.login.equals(login1)) {
				c.send("Diff:"+login2+'/'+antiderivativeRoot+'/'+prime+'/'+id);
			}
		}
	}
	public int getB1() {
		return B1;
	}
	public int getPrime() {
		return prime;
	}
	public void setPrime(int prime) {
		this.prime = prime;
	}
	public int getAntiderivativeRoot() {
		return antiderivativeRoot;
	}
	public void setAntiderivativeRoot(int antiderivativeRoot) {
		this.antiderivativeRoot = antiderivativeRoot;
	}
}

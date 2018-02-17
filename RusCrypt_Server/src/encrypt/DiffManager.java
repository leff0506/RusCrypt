package encrypt;

import math.MathInteraction;
import messanger.Messanger;
import server.Server;

public class DiffManager {
	private int id;
	public int getId() {
		return id;
	}
	private Diff diff1;
	private Diff diff2;
	private int prime;
	private int antiderivativeRoot;
	public DiffManager(String login1,String login2) {
		id=Messanger.diffs.size();
		
		diff1=new Diff(login1);
		diff2=new Diff(login2);
		diff1.setId(id);
		diff2.setId(id);
		prime=MathInteraction.getPrime();
		antiderivativeRoot = MathInteraction.antiderivativeRoot(prime);
		diff1.setAntiderivativeRoot(antiderivativeRoot);
		diff2.setAntiderivativeRoot(antiderivativeRoot);
		diff1.setPrime(prime);
		diff2.setPrime(prime);
		Server.log("root "+antiderivativeRoot);
		Server.log("prime "+prime);
		diff2.sendAll();
		diff1.sendAll();
		Thread th = null;
		th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(diff1.getB1()==-1||diff2.getB1()==-1) {
					try {
						th.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				diff1.sendB(diff2.getB1());
				diff2.sendB(diff1.getB1());
				
				
			}
		});
		th.start();
	}
	public void B(String login,int B) {
		if(login.equals(diff1.getLogin1())) {
			diff1.setB(B);
		}
		if(login.equals(diff2.getLogin1())) {
			diff2.setB(B);
		}
	}
}

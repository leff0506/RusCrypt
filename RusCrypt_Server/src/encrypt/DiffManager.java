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
	private Diff diff3=null;
	private Diff diff4=null;
	private int prime;
	private int antiderivativeRoot;
	private int prime1;
	private int antiderivativeRoot1;
	public DiffManager(String login1,String login2) {

		id=Messanger.diffs.size();
		
		diff1=new Diff(login1,login2);
		diff2=new Diff(login2,login1);
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
						th.sleep(2000);
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
		Thread th3=null;
		th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					th3.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				diff3 = new Diff(login1,login2);
				diff4= new Diff(login2,login1);
				diff3.setId(id);
				diff4.setId(id);
				prime1=MathInteraction.getPrime();
				antiderivativeRoot1 = MathInteraction.antiderivativeRoot(prime1);
				diff3.setAntiderivativeRoot(antiderivativeRoot1);
				diff4.setAntiderivativeRoot(antiderivativeRoot1);
				diff3.setPrime(prime1);
				diff4.setPrime(prime1);
				Server.log("root "+antiderivativeRoot1);
				Server.log("prime "+prime1);
				diff3.sendAll();
				diff4.sendAll();
				Thread th1 = null;
				th1 = new Thread(new Runnable() {
					
					@Override
					public void run() {
						while(diff3.getB1()==-1||diff4.getB1()==-1) {
							try {
								th.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						diff3.sendB(diff4.getB1());
						diff4.sendB(diff3.getB1());
						
						
					}
				});
				th1.start();
				
			}
		});
		th3.start();
		
	}
	synchronized public void B(String login,int B) {
		if(login.equals(diff1.getLogin1())) {
			if(diff1.getB1()==-1) {
				diff1.setB(B);
			}else {
				if(diff3!=null) {
					diff3.setB(B);
				}
				
			}
			
		}
		if(login.equals(diff2.getLogin1())) {
			
			if(diff2.getB1()==-1) {
				diff2.setB(B);
			}else {
				if(diff4!=null) {
				diff4.setB(B);
				}
			}
		}
	}
}

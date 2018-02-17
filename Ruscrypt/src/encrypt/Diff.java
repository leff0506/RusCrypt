package encrypt;

import gui.GUI;
import math.MathInteraction;
import server_interaction.Client;

public class Diff {
	private int id;
	private int prime;
	private int antiderivativeRoot;
	private int A;
	private int B;
	private int K=-1;
	private int rand;
	private Client client;
	public Diff(Client client,String data) {
		id=Integer.parseInt(delim(data,3));
		System.out.println("id Diff = "+id);
		this.client= client;
		GUI.inChat=true;
		antiderivativeRoot = Integer.parseInt(delim(data,1));
		prime =Integer.parseInt(delim(data,2));
		rand = (int)(Math.random()*1000);
		System.out.println("rand = "+rand);
		System.out.println("root = "+antiderivativeRoot);
		System.out.println("prime = "+prime);
		
		A =MathInteraction.sqrQMod(antiderivativeRoot, rand, prime);
		System.out.println("A = "+A);
		client.send("Diff B:"+id+"/"+client.login+"/"+A);
		Thread th= null;
		th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(K==-1) {
					try {
						th.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(K==-1) {
						repeatSendB();
					}
				}
				
				
			}
		});
		th.start();
	}
	public void generateKey(int b) {
		B=b;
		K=MathInteraction.sqrQMod(B, rand, prime);
		System.out.println(K);
	}
	public void repeatSendB() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.send("Diff B:"+id+"/"+client.login+"/"+A);
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
	private static String delim(String logins,int num) {
		String copy = logins;
		copy+='/';
		String login1="";
		String login2="";
		String str3="";
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
		copy = copy.replaceFirst(login2+'/', "");
		for(int i = 0 ; i < copy.length();i++){
			if(copy.charAt(i)!='/'){
				str3+=copy.charAt(i);
			}else{
				break;
			}
			
		}
		if(num==1) {
			return login1;
		}else if(num==2){
			return login2;
		}else {
			return str3;
		}
	}
}

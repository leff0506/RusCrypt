package encrypt;

import gui.GUI;
import math.MathInteraction;
import server_interaction.Client;

public class Diff {
	private int id;
	private int idMan;
	private int prime;
	private int antiderivativeRoot;
	private int A;
	private int B=-1;
	private int id2;
	private int prime2;
	private int antiderivativeRoot2;
	private int A2;
	private int B2;
	private int K1=-1;
	private int K2=-1;
	private int rand;
	private int rand2;
	private GUI gui;
	private Client client;
	private EncrDecr encrDecr;
	public Diff(Client client,String data,GUI gui) {
		id=Integer.parseInt(delim(data,3));
		this.gui=gui;
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
				while(K1==-1) {
					try {
						th.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(K1==-1) {
						repeatSendB();
					}
				}
				
				
			}
		});
		th.start();
	}
	public void generateKey(int b) {
		if(B==-1) {
			B=b;
		}else {
			B2=b;
		}
		
		if(K1==-1) {
			K1=MathInteraction.sqrQMod(B, rand, prime);
			System.out.println("k1 "+ K1);
		}else {
			K2=MathInteraction.sqrQMod(B2, rand2, prime2);
			System.out.println("k2 "+K2);
			encrDecr =new EncrDecr();
			
			
			client.send("get alph");
		}
		
		
	}
	public void setAlph(String txt) {
		encrDecr.setAlph(txt);
		encrDecr.setMultiplicative(K1);
		encrDecr.setAdditive(K2);
		gui.chatting();
		
		
		
	}
	public void secondIt(String data) {
		id2=Integer.parseInt(delim(data,3));
		System.out.println("id Diff = "+id2);
		antiderivativeRoot2 = Integer.parseInt(delim(data,1));
		prime2 =Integer.parseInt(delim(data,2));
		rand2 = (int)(Math.random()*1000);
		System.out.println("rand = "+rand2);
		System.out.println("root = "+antiderivativeRoot2);
		System.out.println("prime = "+prime2);
		
		A2 =MathInteraction.sqrQMod(antiderivativeRoot2, rand2, prime2);
		System.out.println("A = "+A2);
		client.send("Diff B:"+id2+"/"+client.login+"/"+A2);
		Thread th= null;
		th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(K2==-1) {
					try {
						th.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(K2==-1) {
						repeatSendB2();
					}
				}
				
				
			}
		});
		th.start();
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
	public void repeatSendB2() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.send("Diff B:"+id2+"/"+client.login+"/"+A2);
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
		String str4="";
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
		copy = copy.replaceFirst(str3+'/', "");
		for(int i = 0 ; i < copy.length();i++){
			if(copy.charAt(i)!='/'){
				str4+=copy.charAt(i);
			}else{
				break;
			}
			
		}
		if(num==1) {
			return login1;
		}else if(num==2){
			return login2;
		}else if(num==3){
			return str3;
		}else {
			return str4;
		}
	}
}

package encrypt;

import java.util.ArrayList;

import javax.swing.text.Position;


import server_interaction.Client;

public class EncrDecr {
	private static int k1;//mult
	private static int k2;//addit 
	private static String alph;
	public static void setAlph(String alph1) {
		alph = alph1;
	}
	public static String encr(String input) {
		String res="";
		int temp;
		for(int i = 0;i<input.length();i++) {
			temp = posOfChar(input.charAt(i));
			temp = (temp*k1+k2)%alph.length();
			res+=charOfPos(temp);
		}
		return res;
		
	}
	
	public static String decr(String input) {
		String res ="";
		int multiplicative = multiplicative(k1);
		int additive = additive(k2);
		int temp;
		for(int i = 0;i<input.length();i++) {
			temp = posOfChar(input.charAt(i));
			temp = (temp*multiplicative+additive)%alph.length();
			res+=charOfPos(temp);
		}
		
		return res;
	}
	private static int additive(int in) {
		return alph.length()-in;
	}
	private static int multiplicative(int in) {
		ArrayList<MLine> data = new ArrayList<>();
		MLine line = new MLine();
		boolean isFirst = true;
		MLine last = null;
		while(line.b!=0) {
			line = new MLine();
			if(isFirst) {
				line.a=alph.length();
				line.b=in;
				line.d=GCD(line.a,line.b);
				last =  line.clone();
				data.add(line);
				isFirst=false;
			}else {
				line.a=last.b;
				line.b = (last.a%last.b);
				line.d=GCD(line.a,line.b);
				data.add(line);
				last=line.clone();
			}
			
		}
		MLine change = data.get(data.size()-1);
		change.u=0;
		change.v=1;
		last = null;
		
		for(int i = data.size()-2;i>=0;i--) {
			if(i == data.size()-2) {
				data.get(i).u=change.v;
				data.get(i).v= (data.get(i).d-(data.get(i).a*data.get(i).u))/data.get(i).b;
			}else {
				data.get(i).u=data.get(i+1).v;
				data.get(i).v= (data.get(i).d-(data.get(i).a*data.get(i).u))/data.get(i).b;
				
			}
		}
		
		return (data.get(0).v>0)? data.get(0).v:data.get(0).v+alph.length();
	}
	private static int posOfChar(char a) {
		for(int i = 0 ; i < alph.length();i++) {
			if(alph.charAt(i)==a) {
				return i;
			}
		}
		return -1;
	}
	private static char charOfPos(int a) {
		for(int i = 0 ; i < alph.length();i++) {
			if(i==a) {
				return alph.charAt(i);
			}
		}
		return (Character) null;
	}
	private static int GCD(int a,int b) {
		if(b==0) {
			return a;
		}
		return GCD(b,a%b);
	}
	public static void  setMultiplicative(int a) {
		int save = a;
		
		while(GCD(alph.length(),save)!=1) {
			save++;
		}
		k1=save;
	}
	public static void  setAdditive(int a) {
		int save = a;
		save%=alph.length();
		k2=save;
	}
}

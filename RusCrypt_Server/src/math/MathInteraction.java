package math;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MathInteraction {
	private static ArrayList<Integer> primes = null;
	private static int numOfPrime=0;
	private static ArrayList<Integer> dataForAntiderivativeRoot = new ArrayList<>();
	public static int antiderivativeRoot(int prime){
		int res=0;
		for(int i = 2 ; i < prime;i++){
			if(isAntiderivativeRoot(i,prime-1)){
				return i;
			}
		}
		return res;
	}
	private static boolean isAntiderivativeRoot(int q,int f){
		dataForAntiderivativeRoot.clear();
		int last = 1;
		dataForAntiderivativeRoot.add(last);
		for(int i = 0 ; i < f-1;i++){
			
			last=(last*q)%(f+1);
			if(dataForAntiderivativeRoot.contains(last)){
				return false;
			}else{
				dataForAntiderivativeRoot.add(last);
				
			}
			
		}
		return true;
	}
	public static int getPrime(){
		int res=0;
		try {
		
			Scanner sc = new Scanner(new File("data_base/primes.txt"));
			for(int i = 0 ; i < numOfPrime;i++) {
				sc.nextInt();
			}
			res = sc.nextInt();
			numOfPrime++;
			sc.close();
				
					
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return res;
	}
	/**
	 * sqr quick with module division
	 * a^b (mod c)
	 * @return
	 */
	public static int sqrQMod(int a,int b ,int c){
		String binaries = decimalToinary(b);
		int res  =a;
		res*=res;
		res%=c;
		for(int i = 1 ; i <binaries.length();i++){
			if(binaries.charAt(i)=='1'){
				if(i!=binaries.length()-1){
					res*=a;
					res%=c;
					res*=res;
					res%=c;
				}else{
					res*=a;
					res%=c;
				}
				
			}else{
				res*=res;
				res%=c;
			}
		}
		return res;
	}
	
	
	public static String decimalToinary(long vvedenoy_chislo) {
		String odinIliNol = "";
		String peredelany_odinIlinol="";
		long  int_odinIliNol; 
		while (vvedenoy_chislo != 1) {
			if (vvedenoy_chislo % 2 == 1) {
				odinIliNol =odinIliNol+ "1";
				vvedenoy_chislo /=2;
			}
			if (vvedenoy_chislo % 2 == 0) {
				odinIliNol = odinIliNol+"0";
				vvedenoy_chislo /=2;
			}
		}
		odinIliNol =odinIliNol+ "1";
		int_odinIliNol = Integer.parseInt(odinIliNol);
		for (int i = 0 ; i < odinIliNol.length();i++){
			peredelany_odinIlinol += String.valueOf(int_odinIliNol % 10);
			int_odinIliNol /=10;
		}
		return peredelany_odinIlinol;
	}
}

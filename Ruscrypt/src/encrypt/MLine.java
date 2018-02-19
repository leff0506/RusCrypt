package encrypt;



public class MLine {
	public int a,b,d,u,v;
	public MLine clone() {
		MLine temp = new MLine();
		temp.a=a;
		temp.b=b;
		temp.d=d;
		temp.u=u;
		temp.v=v;
		return temp;
	}
}

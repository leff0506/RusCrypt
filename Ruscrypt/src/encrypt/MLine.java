package encrypt;



public class MLine {
	public int a,b,d,u,v;
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public int getU() {
		return u;
	}
	public void setU(int u) {
		this.u = u;
	}
	public int getV() {
		return v;
	}
	public void setV(int v) {
		this.v = v;
	}
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

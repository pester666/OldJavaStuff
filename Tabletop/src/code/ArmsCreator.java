package code;

public class ArmsCreator {

	private int mehrA = 0;
	private int mehrRW = 0;
	private int mehrS = 0;
	private int mehrI = 0;
	
	public ArmsCreator(int a, int rw, int s, int i){
		this.mehrA = a;
		this.mehrRW = rw;
		this.mehrS = s;
		this.mehrI = i;
	}

	public int getMehrA() {
		return mehrA;
	}

	public void setMehrA(int mehrA) {
		this.mehrA = mehrA;
	}

	public int getMehrRW() {
		return mehrRW;
	}

	public void setMehrRW(int mehrRW) {
		this.mehrRW = mehrRW;
	}

	public int getMehrS() {
		return mehrS;
	}

	public void setMehrS(int mehrS) {
		this.mehrS = mehrS;
	}

	public int getMehrI() {
		return mehrI;
	}

	public void setMehrI(int mehrI) {
		this.mehrI = mehrI;
	}
}

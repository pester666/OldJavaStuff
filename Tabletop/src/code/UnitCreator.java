package code;

public class UnitCreator {

	private String name;
	private int kg = 0;
	private int s = 0;
	private int i = 0;
	private int rw = 0;
	private int w = 0;
	private int a = 0;
	private int lp = 0;
	private int mw = 0;
	private boolean angst = false;
	boolean flieht = false;
	
	public UnitCreator(String name, int kg, int s, int w, int lp, int i, int a, int mw, int rw, boolean angst, boolean flieht){
		super();
		this.name = name;
		this.kg = kg;
		this.s = s;
		this.i = i;
		this.rw = rw;
		this.w = w;
		this.a = a;
		this.lp = lp;
		this.mw = mw;
		this.angst = angst;
		this.flieht = flieht;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKg() {
		return kg;
	}

	public void setKg(int kg) {
		this.kg = kg;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getRw() {
		return rw;
	}

	public void setRw(int rw) {
		this.rw = rw;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getLp() {
		return lp;
	}

	public void setLp(int lp) {
		this.lp = lp;
	}

	public int getMw() {
		return mw;
	}

	public void setMw(int mw) {
		this.mw = mw;
	}

	public boolean isAngst() {
		return angst;
	}

	public void setAngst(boolean angst) {
		this.angst = angst;
	}

	public boolean isFlieht() {
		return flieht;
	}

	public void setFlieht(boolean flieht) {
		this.flieht = flieht;
	}
	
	public void setAll(UnitCreator unit){
		this.a = unit.getA();
		this.angst = unit.isAngst();
		this.i = unit.getI();
		this.flieht = unit.isFlieht();
		this.kg = unit.getKg();
		this.lp = unit.getLp();
		this.mw = unit.getMw();
		this.rw = unit.getRw();
		this.s = unit.getS();
		this.w = unit.getW();
	}
}

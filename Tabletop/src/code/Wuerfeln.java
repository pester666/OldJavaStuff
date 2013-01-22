package code;

public class Wuerfeln {

	private int w1 = 0;
	private int w2 = 0;
	private int w3 = 10;
	
	public int ersterWurf(int kgA, int kgV){
		if(kgA == kgV || kgA+1 == kgV || kgA+2 == kgV){
			w1 = 4;
		}else if(kgA > kgV){
			w1 = 3;
			}else if(kgA < kgV){
				w1 = 5;
			}
		return w1;
	}
	
	
	public int zweiterWurf(int s, int w){
		if(s == w){
			w2 = 4;
		}else if(s < w){
				w2 = 4+(w-s);
				if(w2 > 6){
					if(w2 > 8){
						w2 = 10;
					}else{
						w2 = 6;
					}	
				}
			}else if(s > w){
				w2 = 4-(s-w);
				if(w2 < 2){
					w2 = 2;
				}
			}
		return w2;
	}	
	
	public int dritterWurf(int s, int rw){
		w3 = rw;
		if(s >= 4){
			w3 = rw+(s-3);
		}else
		if(w3 == 1){
			w3 = 10;
		}
		return w3;
	}
}

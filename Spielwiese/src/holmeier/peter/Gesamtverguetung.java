package holmeier.peter;

public class Gesamtverguetung {

    static private int[][] sstd = new int[5][2];
    
    public static void main(String[] args) {
        fillSonnenstunden();
        errechneLeistung(50000, 82362, 31, 20, 0.45f);
    }
    
    static private void errechneLeistung(int nL, int plz, int grad, int laufzeit, float ertrag){
        float prozentGrad = grad*0.5f;
        int std = holeSonnenstunden(plz);
        float trueNL = nL*std/1800;
        float gesamtErtrag = 0.0f;
        for (int i = 0; i < laufzeit; i++) {
            prozentGrad = prozentGrad + (i*2);
            trueNL = trueNL * (100-prozentGrad) / 100;
            gesamtErtrag = gesamtErtrag + trueNL*ertrag;
        }
        System.out.println("Man erhält: " + gesamtErtrag + " Euronen");
    }
    
    static private int holeSonnenstunden(int plz){
        for (int i = 0; i < sstd.length; i++) {
            if(sstd[i][0] == plz){
                return sstd[i][1];
            }
        }
        for (int i = 0; i < sstd.length; i++) {
           if(sstd[i][0] > plz){
               return sstd[i-1][1]; 
           }
        }        
        return -1;
    }
    
    static private void fillSonnenstunden(){
        for (int i = 0; i < sstd.length; i++) {
            sstd[i][0] = 82361 + i; 
            sstd[i][1] = (i+1)*100;
        }
    }
}

package holmeier.peter.plusminus;

import java.text.DecimalFormat;
import java.text.ParseException;

public class Stundenrechner {

    public static void main(String[] args) throws ParseException {

        int kommenStd = 7;
        int kommenMin = 48;
        int gehenStd = 16;
        int gehenMin = 0;
        int mussStd = 0;
        int minusStd = 0;
        int minusMin = 0;
        float minusMinProzent = 0;
        String mussMin = null;
        float mussBisMinProzent = 0;
        boolean pauseGemacht = true;
        boolean machtManMinus = false;
        int derzeitigesPlusStd = 8;
        int derzeitigesPlusMinProzent = 2;
        int zukünftigesPlusStd = 0;
        int zukünftigesPlusMinProzent = 0;
        
        mussStd = kommenStd + 7;

        float kommenMinProzent = (float)(kommenMin / 60.0);

        mussBisMinProzent = (float)(kommenMinProzent + 0.7);
        if (mussBisMinProzent > 1.0) {
            mussBisMinProzent = (float)(mussBisMinProzent - 1.0);
            mussStd = mussStd + 1;
        }
        if (pauseGemacht) {
            mussBisMinProzent = (float)(mussBisMinProzent + 0.75);
            if (mussBisMinProzent > 1.0) {
                mussBisMinProzent = (float)(mussBisMinProzent - 1.0);
                mussStd = mussStd + 1;
            }
        }
        if (mussBisMinProzent * 60 < 10.0) {
            mussMin = "0" + (int)(mussBisMinProzent * 60);
        } else {
            mussMin = "" + (int)(mussBisMinProzent * 60);
        }

        minusMin = gehenMin - Integer.parseInt(mussMin);
        if (minusMin < 0 && mussStd > gehenStd) {
            minusMin = minusMin * -1;
            machtManMinus = true;
        }

        minusMinProzent = (float)(minusMin / 60.0);
        minusStd = gehenStd - mussStd;
        if (minusMinProzent < 0) {
            minusMinProzent = minusMinProzent + 1;
            minusStd = minusStd - 1;
        }
        minusMin = (int)(minusMinProzent * 60.0);
        
        if (minusStd < 0) {
            machtManMinus = true;
            minusStd = minusStd * -1;
        }
        float saldoAnteil = 0;
        saldoAnteil = (float)(minusMin/60.0) + minusStd;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Du bist heute um " + kommenStd + ":" + kommenMin + " gekommen.");
        System.out.println("Du musst bis " + mussStd + ":" + mussMin + " arbeiten.");
        System.out.println("Du willst heute um " + gehenStd + ":" + gehenMin + " gehen.");
        if (machtManMinus) {
            System.err.println("Du machst " + minusStd + " Stunden und " + minusMin + " Minuten Minus!");
            System.err.println("Das entsprich " + df.format(saldoAnteil) + " Stunden.");
        } else {
            System.out.println("Du machst " + minusStd + " Stunden und " + minusMin + " Minuten Plus!");
            System.out.println("Das entsprich " + df.format(saldoAnteil) + " Stunden.");
        }
        
        System.out.println("Du hast zur Zeit " + derzeitigesPlusStd + " Stunden und " + derzeitigesPlusMinProzent*60/100 + " Minuten Plus.");
        if(((derzeitigesPlusMinProzent*60/100)+minusMin) > 60){
            zukünftigesPlusMinProzent = (derzeitigesPlusMinProzent*60/100)+minusMin-60;
            zukünftigesPlusStd = (derzeitigesPlusStd+minusStd)+1;
        }else{
            zukünftigesPlusMinProzent = (derzeitigesPlusMinProzent*60/100)+minusMin;
            zukünftigesPlusStd = (derzeitigesPlusStd+minusStd);
        }
        System.out.println("Morgen hast du wenn alles so bleibt " + zukünftigesPlusStd + " Stunden und " + zukünftigesPlusMinProzent + " Minuten Plus");

    }
}

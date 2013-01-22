package de.test;

public class testen {

    private static final int LENGTH_PZN8 = 8;

    public static void main(String[] args) {
//        System.out.println(testen.formatPZN8(123456));
        System.out.println("select /*+ all_rows */ new");
    }
    
    static private String formatPZN8(long pzn) {
        String sPzn = (new Long(pzn)).toString();
        int countLeedingZerosToAdd = LENGTH_PZN8 - sPzn.trim().length();
        if (countLeedingZerosToAdd <= 0)
            return sPzn;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < countLeedingZerosToAdd; i++) {
            sb.append("0");
        }
        return sb.toString() + sPzn;
    }
}

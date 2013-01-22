package de.test;

public class Testooen {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception   {
        test();
    }
    
    private static void test() throws Exception{
        try {
            System.out.println("already tryin'");
            return;
        } catch (Exception e) {
            throw e;
        }finally{
            System.out.println("im finally");
        }
    }

}

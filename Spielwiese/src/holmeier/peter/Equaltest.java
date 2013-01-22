package holmeier.peter;

public class Equaltest {

    public static void main(String[] args) {
        String a = new String("wilde buchstabenkombination");
        String b = new String("wilde buchstabenkombination");
        Integer i = new Integer(1);
        System.out.println(a.toString());
        System.out.println(b.toString());
        if(a == b){
            System.out.println("==");
        }
        if(a.equals(b)){
            System.out.println("eqi");
        }
    }
}

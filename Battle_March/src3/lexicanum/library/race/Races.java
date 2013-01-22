package lexicanum.library.race;

import lexicanum.data.Race;

public class Races {

    public static final Race SM = new Race("Space Marines");
    public static final Race TYRAS = new Race("Tyraniden");
    public static final Race ORKS = new Race("Orks");
    public static final Race DE = new Race("Dark Eldar");
    public static final Race CSM = new Race("Chaos Space Marines");
    public static final Race IMP = new Race("Imperiale Armee");
    
    public static String[] getRaces(){
        return new String[]{null,
                            SM.getName(),
                            TYRAS.getName(),
                            ORKS.getName(),
                            DE.getName(),
                            CSM.getName(),
                            IMP.getName()};
    }
}

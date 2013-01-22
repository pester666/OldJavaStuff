package lib;


public class Henchmen extends Unit {

    public Henchmen(int movement,
        int weaponSkill,   
        int balisticSkill,
        int strength,
                    int toughness,
                    int wounds,
                    int initiative,
                    int attacks,
                    
                    int leadership,
                    
                    int cost,
                    
                    String name,
                    int exp,
                    Skill[] skills,
                    Equipment[] equipment,
                    int size,
                    int warband,
                    int armorsave,
                    String img,
                    String type) {
        super(false, strength, toughness, movement, initiative, weaponSkill, balisticSkill, leadership, attacks, cost, wounds, name, exp, skills, new Injury[1], equipment, State.CONCIOUS, strength+1, toughness+1, movement+1, initiative+1, weaponSkill+1, balisticSkill+1, leadership+1, attacks+1, wounds+1, false, size, warband, null, armorsave, img, type);
    }
}

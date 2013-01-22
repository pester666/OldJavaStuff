package lib;


public class Hero extends Unit {

    public Hero(int movement,
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
                int maxMovement,
                int maxWS,
                int maxBS,
                int maxStrength,
                int maxToughness,
                int maxWounds,
                int maxInitiative,
                int maxAttacks,
                int maxLeadership,
                boolean mayUseMagic,
                int size,
                int warband,
                Spell[] spells,
                int armorsave,
                String img,
                String type) {
        super(true, strength, toughness, movement, initiative, weaponSkill, balisticSkill, leadership, attacks, cost, wounds, name, exp, skills, new Injury[1], equipment, maxStrength, maxToughness, maxMovement, maxInitiative, maxWS, maxBS, maxLeadership, maxAttacks, maxWounds, maxWounds, mayUseMagic, size, warband, spells, armorsave, img, type);
    }
}

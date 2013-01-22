package lib.dice;

import java.util.Random;

import lib.Unit;

public class AdvanceRollHeroes {

    public static Unit makeAdvanceRoll(Unit hero, int modifier) {
        Random ran = new Random();
        int throwed = (ran.nextInt(6) + 1) + (ran.nextInt(6) + 1) + modifier;
        String s = "You throwed a " + throwed + ": ";
        if (throwed <= 5) {
            s = s + "New Skill\n";
            //TODO skill
            //            hero.addSkill(skill);
        } else if (throwed == 6) {
            //TODO blaaa
            if((hero.getMaxStrength() >= hero.getStrength() + 1) || (hero.getMaxAttacks() >= hero.getAttacks() + 1)){
                if ((ran.nextInt(3) + 1) <= 3 && (hero.getMaxStrength() >= hero.getStrength() + 1)) {
                    s = s + "Characteristic Increase\n" + "+1 Strength";
                    hero.setStrength(hero.getStrength() + 1);
                } else if(hero.getMaxAttacks() >= hero.getAttacks() + 1){
                    s = s + "Characteristic Increase\n" + "+1 Attack";
                    hero.setAttacks(hero.getAttacks() + 1);
                } else{
                    //roll again
                }
            }else{
                
            }
        } else if (throwed == 7) {
            if ((ran.nextInt(3) + 1) <= 3) {
                s = s + "Characteristic Increase\n" + "+1 Weaponskill";
                hero.setWeaponSkill(hero.getWeaponSkill() + 1);
            } else {
                s = s + "Characteristic Increase\n" + "+1 Balisticskill";
                hero.setBalisticSkill(hero.getBalisticSkill() + 1);
            }
        } else if (throwed == 8) {
            if ((ran.nextInt(3) + 1) <= 3) {
                s = s + "Characteristic Increase\n" + "+1 Initiative";
                hero.setInitiative(hero.getInitiative() + 1);
            } else {
                s = s + "Characteristic Increase\n" + "+1 Leadership";
                hero.setLeadership(hero.getLeadership() + 1);
            }
        } else if (throwed == 9) {
            if ((ran.nextInt(3) + 1) <= 3) {
                s = s + "Characteristic Increase\n" + "+1 Wound";
                hero.setWounds(hero.getWounds() + 1);
            } else {
                s = s + "Characteristic Increase\n" + "+1 Toughness";
                hero.setToughness(hero.getToughness() + 1);
            }
        } else if (throwed <= 12) {
            s = s + "New Skill\n";
            //TODO skill
            //            hero.addSkill(skill);
        }
        hero.addInfo(s);
        return hero;
    }
}

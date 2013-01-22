package lib.dice;

import java.util.Random;

import lib.Unit;

public class AdvanceRollHenchmen {

    public static Unit roll(Unit henchman, int modifier) {
        Random ran = new Random();
        int throwed = (ran.nextInt(6) + 1) + (ran.nextInt(6) + 1) + modifier;
        String s = throwed + ": ";
        if (throwed <= 4 && (henchman.getMaxInitiative()>= henchman.getInitiative() + 1)) {
            s = s + "Advance\n" + "+1 Initiative";
            henchman.setInitiative(henchman.getInitiative() + 1);
        } else if (throwed == 5 && (henchman.getMaxStrength()>= henchman.getStrength() + 1)) {
            s = s + "Advance\n" + "+1 Strength";
            henchman.setStrength(henchman.getStrength() + 1);
        } else if (throwed <= 7) {
            s = s + "Advance\n" + "Choose either +1 BS or +1 WS.";
            //TODO choose bs or ws
        } else if (throwed == 8 && (henchman.getMaxAttacks()>= henchman.getAttacks() + 1)) {
            s = s + "Advance\n" + "+1 Attack";
            henchman.setAttacks(henchman.getAttacks() + 1);
        } else if (throwed == 9 && (henchman.getMaxLeadership()>= henchman.getLeadership() + 1)) {
            s = s + "Advance\n" + "+1 Leadership";
            henchman.setLeadership(henchman.getLeadership() + 1);
        } else if (throwed <= 12) {
            s = s + "The lad's got talent\n" + "Promotion to Hero.";
            henchman.setHero(true);
        }
        henchman.addInfo(s);
        return henchman;
    }
}

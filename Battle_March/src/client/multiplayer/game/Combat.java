package client.multiplayer.game;

import java.util.Random;

import lib.Constants;
import lib.Unit;

public class Combat {
    
    private String txt;
    
    public Combat(){
        txt = "";
    }

    public boolean shoot(Unit attacker, Unit defender){
        Random ran = new Random();
        int dice = ran.nextInt(6) + 1;
        if (dice == 1) {
            return false;
        }
        if(attacker.unitType == Constants.UNIT_ANTITANK && defender.unitType != Constants.UNIT_VEHICLE){
            dice = dice -1;
        }
        if(attacker.alreadyMoved == true){
            txt = "You need a " + (8 - attacker.rangedSkill) + " to hit the target.\n";
            if (dice >= 8 - attacker.rangedSkill) {
                txt = txt + "You throwed a " + dice + ". The target was hit!\n";
                txt = txt + "\n";
                return true;
            }
        }else{
            txt = "You need a " + (7 - attacker.rangedSkill) + " to hit the target.\n";
            if (dice >= 7 - attacker.rangedSkill) {
                txt = txt + "You throwed a " + dice + ". The target was hit!\n";
                txt = txt + "\n";
                return true;
            }
        }
        txt = txt + "You throwed a " + dice + ". The target was missed!\n";
        txt = txt + "\n";
        return false;
    }
    
    public int getArtilleryTarget(Unit attacker){
        Random ran = new Random();
        int dice = ran.nextInt(6) + 1;
        int abberation;
        if(dice == 6){
            txt = "Your unit has directly hitted the target!\n";
            return abberation = 0;
        }
        abberation = dice-attacker.rangedSkill;
        if(abberation == 0){
            dice = ran.nextInt(2) + 1;
            if(dice == 1){
                abberation = 1;
            }else{
                abberation = -1;
            }
        }
        txt = txt + "Your unit has missed the target by " + abberation + " tiles.\n";
        return abberation;
    }
        
    public boolean wound(Unit attacker, Unit defender, boolean cc){
        Random ran = new Random();
        int dice = ran.nextInt(6) + 1;
        int needed = 0;
        if(cc){
            if(attacker.gear.isMeleeWeapon){
                needed = 4+(defender.toughness-attacker.gear.strength-attacker.strength);
                if(needed > 6){
                        if(needed > 8){
                            //impossible to wound
                            needed = 10;
                        }else{
                            needed = 6;
                        }   
                }
                if(needed <= 1){
                    needed = 2;
                }
            }else{
                needed = 4+(defender.toughness-attacker.strength);
                if(needed > 6){
                        if(needed > 8){
                            //impossible to wound
                            needed = 10;
                        }else{
                            needed = 6;
                        }   
                }
                if(needed <= 1){
                    needed = 2;
                }
            }
        }else{
            needed = 4+(defender.toughness-attacker.gear.strength);
            if(needed > 6){
                    if(needed > 8){
                        //impossible to wound
                        needed = 10;
                    }else{
                        needed = 6;
                    }   
            }
            if(needed <= 1){
                needed = 2;
            }
        }
        txt = "You need a " + needed + " to wound the target.\n";
        if(dice >= needed){
            txt = txt + "You throwed a " + dice + ". The target was wounded with your " + attacker.gear.name + "!\n";
            txt = txt + "\n";
            return true;
        }
        txt = txt + "You throwed a " + dice + ". The target resisted!\n";
        txt = txt + "\n";
        
        return false;
    }
    
    public boolean meleeAttack(Unit attacker, Unit defender) {
        Random ran = new Random();
        int dice = ran.nextInt(6) + 1;
        int needed = 0;
        if (attacker.melee == defender.melee || attacker.melee + 1 == defender.melee || attacker.melee + 2 == defender.melee) {
            needed = 4;
        } else if (attacker.melee > defender.melee) {
            needed = 3;
        } else if (attacker.melee < defender.melee) {
            needed = 5;
        }
        txt = "You need a " + needed + " to hit the target.\n";
        if(dice >= needed){
            txt = txt + "You throwed a " + dice + ". The target was hit!\n";
            txt = txt + "\n";
            
            return true;
        }
        txt = txt + "You throwed a " + dice + ". The target was missed!\n";
        txt = txt + "\n";
        
        return false;
    }
    
    public boolean coverSaveFails(Unit attacker, Unit defender, int cover, boolean cc){
        Random ran = new Random();
        int dice = ran.nextInt(6) + 1;
        int needed = 0;
        if(cover <= 0 && defender.armorsave > cover){//if there is no cover
            if(attacker.gear.armorPenetration <= defender.armorsave){
                txt = "The attack pierced through the armor!\n";
                return true;
            }
            needed = defender.armorsave;
            if(!cc){
                if(attacker.gear.strength >= 4){
                    needed = defender.armorsave+(attacker.gear.strength-3);
                }else
                if(needed == 1){
                    //impossible to save
                    needed = 10;
                }  
            }else{
                if(attacker.gear.isMeleeWeapon){
                    if(attacker.strength+attacker.gear.strength >= 4){
                        needed = defender.armorsave+(attacker.strength+attacker.gear.strength-3);
                    }else
                    if(needed == 1){
                        //impossible to save
                        needed = 10;
                    } 
                }else{
                    if(attacker.strength >= 4){
                        needed = defender.armorsave+(attacker.strength-3);
                    }else
                    if(needed == 1){
                        //impossible to save
                        needed = 10;
                    } 
                }
            }
        }else{//here u get cover!
            if(!cc){
                txt = "The defender uses his cover of " + cover + "+";
                needed = cover;
                if(needed == 1){
                    needed = 2;
                }
            }else{
                needed = defender.armorsave;
                if(attacker.gear.strength >= 4){
                    needed = defender.armorsave+(attacker.gear.strength-3);
                }else
                if(needed == 1){
                    //impossible to save
                    needed = 10;
                }   
            }
        }
        txt = txt + "The defender needs a " + needed + " to negate the attack.";
        if(dice < needed){
            txt = txt + "The defender throwed a " + dice + ". The defender was injured!\n";
            txt = txt + "\n";
            return true;
        }
        txt = txt + "The defender throwed a " + dice + ". The defender survived!\n";
        txt = txt + "\n";
        return false;
    }
    
    public String getLatestTxt(){
        return txt;
    }
}

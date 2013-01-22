package client.singleplayer;

import java.awt.Color;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import lib.Constants;
import lib.Unit;

public class Combat {
    
    private JTextArea txt;
    public JDialog dlg;
    
    public Combat(){
        dlg = new JDialog();
        txt = new JTextArea();
        txt.setBackground(Color.black);
        txt.setForeground(Color.white);
        JScrollPane scrPane = new JScrollPane(txt);
        dlg.setTitle("Battle-Log");
        dlg.setSize(600, 200);
        dlg.setAlwaysOnTop(false);
        dlg.add(scrPane);
        dlg.setLocation(600, 800);
        dlg.setVisible(true);
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
            txt.append("You need a " + (8 - attacker.rangedSkill) + " to hit the target.\n");
            if (dice >= 8 - attacker.rangedSkill) {
                txt.append("You throwed a " + dice + ". The target was hit!\n");
                txt.append("\n");
                return true;
            }
        }else{
            txt.append("You need a " + (7 - attacker.rangedSkill) + " to hit the target.\n");
            if (dice >= 7 - attacker.rangedSkill) {
                txt.append("You throwed a " + dice + ". The target was hit!\n");
                txt.append("\n");
                return true;
            }
        }
        txt.append("You throwed a " + dice + ". The target was missed!\n");
        txt.append("\n");
        return false;
    }
    
    public int getArtilleryTarget(Unit attacker){
        Random ran = new Random();
        int dice = ran.nextInt(6) + 1;
        int abberation;
        if(dice == 6){
            txt.append("Your unit has directly hitted the target!\n");
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
        txt.append("Your unit has missed the target by " + abberation + " tiles.\n");
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
        txt.append("You need a " + needed + " to wound the target.\n");
        if(dice >= needed){
            txt.append("You throwed a " + dice + ". The target was wounded with your " + attacker.gear.name + "!\n");
            txt.append("\n");
            return true;
        }
        txt.append("You throwed a " + dice + ". The target resisted!\n");
        txt.append("\n");
        
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
        txt.append("You need a " + needed + " to hit the target.\n");
        if(dice >= needed){
            txt.append("You throwed a " + dice + ". The target was hit!\n");
            txt.append("\n");
            
            return true;
        }
        txt.append("You throwed a " + dice + ". The target was missed!\n");
        txt.append("\n");
        
        return false;
    }
    
    public boolean coverSaveFails(Unit attacker, Unit defender, int cover, boolean cc){
        Random ran = new Random();
        int dice = ran.nextInt(6) + 1;
        int needed = 0;
        if(cover <= 0 && defender.armorsave > cover){//if there is no cover
            if(attacker.gear.armorPenetration <= defender.armorsave){
                txt.append("The attack pierced through the armor!\n");
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
                txt.append("The defender uses his cover of " + cover + "+");
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
        txt.append("The defender needs a " + needed + " to negate the attack.");
        if(dice < needed){
            txt.append("The defender throwed a " + dice + ". The defender was injured!\n");
            txt.append("\n");
            return true;
        }
        txt.append("The defender throwed a " + dice + ". The defender survived!\n");
        txt.append("\n");
        return false;
    }
}

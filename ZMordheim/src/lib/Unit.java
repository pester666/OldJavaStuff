package lib;

import java.io.Serializable;

public class Unit implements Serializable{

    protected boolean isHero;
    protected boolean mayUseMagic;
    protected int strength;
    protected int toughness;
    protected int movement;
    protected int initiative;
    protected int weaponSkill;
    protected int balisticSkill;
    protected int leadership;
    protected int attacks;
    protected int cost;
    protected int wounds;
    protected int armorsave;
    protected String name;
    protected int exp;
    protected Skill[] skills;
    protected Injury[] injuries;
    protected Equipment[] equipment;
    protected int state = State.CONCIOUS;
    protected String[] infos = new String[10];
    
    protected int size;
    protected int warband;
    protected Spell[] spells;

    protected int maxStrength;
    protected int maxToughness;
    protected int maxMovement;
    protected int maxInitiative;
    protected int maxWS;
    protected int maxBS;
    protected int maxLeadership;
    protected int maxAttacks;
    protected int maxWounds;
    
    protected String type;
    protected String imgLocation;
    
    /**
     * isHero 
     * strength 
     * toughness 
     * movement 
     * nitiative 
     * weaponSkill 
     * balisticSkill
     * leadership 
     * attacks 
     * cost 
     * wounds 
     * name 
     * exp 
     * skills 
     * injuries 
     * equipment 
     * state
     * maxStrength 
     * maxToughness 
     * maxMovement 
     * maxInitiative 
     * maxWS 
     * maxBS
     * maxLeadership 
     * maxAttacks 
     * maxWounds
     */
    public Unit(boolean isHero, int strength, int toughness, int movement, int initiative, int weaponSkill, int balisticSkill, int leadership, int attacks, int cost, int wounds, String name, int exp, Skill[] skills, Injury[] injuries,
        Equipment[] equipment, int state, int maxStrength, int maxToughness, int maxMovement, int maxInitiative, int maxWS, int maxBS, int maxLeadership, int maxAttacks, int maxWounds, boolean mayUseMagic, int size,
        int warband, Spell[] spells, int armorsave, String imgLocation, String type) {
        super();
        this.isHero = isHero;
        this.strength = strength;
        this.toughness = toughness;
        this.movement = movement;
        this.initiative = initiative;
        this.weaponSkill = weaponSkill;
        this.balisticSkill = balisticSkill;
        this.leadership = leadership;
        this.attacks = attacks;
        this.cost = cost;
        this.wounds = wounds;
        this.name = name;
        this.exp = exp;
        this.skills = skills;
        this.injuries = injuries;
        this.equipment = equipment;
        this.state = state;
        this.maxStrength = maxStrength;
        this.maxToughness = maxToughness;
        this.maxMovement = maxMovement;
        this.maxInitiative = maxInitiative;
        this.maxWS = maxWS;
        this.maxBS = maxBS;
        this.maxLeadership = maxLeadership;
        this.maxAttacks = maxAttacks;
        this.maxWounds = maxWounds;
        this.mayUseMagic = mayUseMagic;
        this.size = size;
        this.warband = warband;
        this.spells = spells;
        this.armorsave = armorsave;
        this.imgLocation = imgLocation;
        this.type = type;
    }
          
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return imgLocation;
    }

    public void setImg(String img) {
        this.imgLocation = img;
    }

    public int getArmorsave() {
        return armorsave;
    }

    public void setArmorsave(int armorsave) {
        this.armorsave = armorsave;
    }

    public Spell[] getSpells() {
        return spells;
    }

    public void setSpells(Spell[] spells) {
        this.spells = spells;
    }

    public int getWarband() {
        return warband;
    }

    public void setWarband(int warband) {
        this.warband = warband;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWounds() {
        return wounds;
    }

    public void setWounds(int wounds) {
        this.wounds = wounds;
    }

    public int getMaxStrength() {
        return maxStrength;
    }

    public int getMaxToughness() {
        return maxToughness;
    }

    public int getMaxMovement() {
        return maxMovement;
    }

    public int getMaxInitiative() {
        return maxInitiative;
    }

    public int getMaxWS() {
        return maxWS;
    }

    public int getMaxBS() {
        return maxBS;
    }

    public int getMaxLeadership() {
        return maxLeadership;
    }

    public int getMaxAttacks() {
        return maxAttacks;
    }

    public int getMaxWounds() {
        return maxWounds;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isHero() {
        return isHero;
    }

    public void setHero(boolean isHero) {
        this.isHero = isHero;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getWeaponSkill() {
        return weaponSkill;
    }

    public void setWeaponSkill(int weaponSkill) {
        this.weaponSkill = weaponSkill;
    }

    public int getBalisticSkill() {
        return balisticSkill;
    }

    public void setBalisticSkill(int balisticSkill) {
        this.balisticSkill = balisticSkill;
    }

    public int getLeadership() {
        return leadership;
    }

    public void setLeadership(int leadership) {
        this.leadership = leadership;
    }

    public int getAttacks() {
        return attacks;
    }

    public void setAttacks(int attacks) {
        this.attacks = attacks;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }

    public void addSkill(Skill skill) {
        Skill[] oldSkills = this.skills;
        this.skills = new Skill[this.skills.length + 1];
        for (int i = 0; i < oldSkills.length; i++) {
            this.skills[i] = oldSkills[i];
        }
        this.skills[this.skills.length - 1] = skill;
    }

    public void removeSkill(Skill skill) {
        for (int i = 0; i < this.skills.length; i++) {
            if (this.skills[i].getName().equals(skill.getName())) {
                this.skills[i] = null;
            }
        }
        Skill[] newSkills = new Skill[this.skills.length - 1];
        int counter = 0;
        for (int i = 0; i < this.skills.length; i++) {
            if (this.skills[i] != null) {
                newSkills[i] = this.skills[counter];
            }
            counter++;
        }
        this.skills = new Skill[newSkills.length];
        this.skills = newSkills;
    }

    public Injury[] getInjuries() {
        return injuries;
    }

    public void setInjuries(Injury[] injuries) {
        this.injuries = injuries;
    }

    public void addInjury(Injury injury) {
        Injury[] oldInjuries = this.injuries;
        this.injuries = new Injury[this.injuries.length + 1];
        for (int i = 0; i < oldInjuries.length; i++) {
            this.injuries[i] = oldInjuries[i];
        }
        this.injuries[this.injuries.length - 1] = injury;
    }

    public void removeInjury(Injury injury) {
        for (int i = 0; i < this.injuries.length; i++) {
            if (this.injuries[i].getName().equals(injury.getName())) {
                this.injuries[i] = null;
            }
        }
        Injury[] newInjuries = new Injury[this.injuries.length - 1];
        int counter = 0;
        for (int i = 0; i < this.injuries.length; i++) {
            if (this.injuries[i] != null) {
                newInjuries[i] = this.injuries[counter];
            }
            counter++;
        }
        this.injuries = new Injury[newInjuries.length];
        this.injuries = newInjuries;
    }

    public Equipment[] getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment[] equipment) {
        this.equipment = equipment;
    }

    public void addEquipment(Equipment equipment) {
        Equipment[] oldEquipment = this.equipment;
        this.equipment = new Equipment[this.equipment.length + 1];
        for (int i = 0; i < oldEquipment.length; i++) {
            this.equipment[i] = oldEquipment[i];
        }
        this.equipment[this.equipment.length - 1] = equipment;
    }

    public void removeEquipment(Equipment equipment) {
        for (int i = 0; i < this.injuries.length; i++) {
            if (this.equipment[i].getName().equals(equipment.getName())) {
                this.equipment[i] = null;
            }
        }
        Equipment[] newEquipment = new Equipment[this.equipment.length - 1];
        int counter = 0;
        for (int i = 0; i < this.equipment.length; i++) {
            if (this.equipment[i] != null) {
                newEquipment[i] = this.equipment[counter];
            }
            counter++;
        }
        this.equipment = new Equipment[newEquipment.length];
        this.equipment = newEquipment;
    }

    public String[] getInfos() {
        return infos;
    }

    public void setInfos(String[] infos) {
        this.infos = infos;
    }

    public void addInfo(String message) {
        String[] os = this.infos;
        int counter = 1;
        for (int i = 0; i < this.infos.length; i++) {
            if (counter < os.length) {
                this.infos[i] = os[counter];
            }
            counter++;
        }
        this.infos[this.infos.length - 1] = message;
    }

    public boolean isMayUseMagic() {
        return mayUseMagic;
    }

    public void setMayUseMagic(boolean mayUseMagic) {
        this.mayUseMagic = mayUseMagic;
    }
}

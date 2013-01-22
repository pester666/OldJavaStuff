package lib;

public class UnitTypes {
    
    public String getUnitType(int type){
        if(type == Constants.UNIT_INFANTRY){
            return "Infantry";
        }else if(type == Constants.UNIT_ARTILLERY){
            return "Artillery";
        }else if(type == Constants.UNIT_VEHICLE){
            return "Vehicle";
        }else if(type == Constants.UNIT_ANTITANK){
            return "Antitank";
        }else if(type == Constants.UNIT_MACHINEGUN){
            return "Machinegun";
        }else if(type == Constants.UNIT_STRUCTURE){
            return "Structure";
        }
        return "No Unit type";
    }
    
}

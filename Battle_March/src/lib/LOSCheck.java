package lib;

public class LOSCheck {
    
    private int clickedRowNum = 0;
    private int clickedColNum = 0;
    private int oldRowNum = 0;
    private int oldColNum = 0;
    private Environment[][] envMap = new Environment[32][24];
    private int unitFoundInRow = 0;
    private int unitFoundInCol = 0;
    private String direction = null;
    private int maxRows = 0;
    
    public LOSCheck(int clickedRowNum, int clickedColNum, int oldRowNum, int oldColNum, Environment[][] envMap){
        this.clickedRowNum = clickedRowNum;
        this.clickedColNum = clickedColNum;
        this.oldRowNum = oldRowNum;
        this.oldColNum = oldColNum;
        this.envMap = envMap;
    }
    
    public boolean hasUnitLOS(){
        
        if(clickedRowNum > oldRowNum){
            direction = "south";
        }else if(clickedRowNum < oldRowNum){
            direction = "north";
        }
        
        if(direction == "south"){
            if(checkDirectionSouth()){
                if(isLOSFreeSouth()){
                    return true;
                }else{
                    return false;
                }                
            }else{
                if(clickedColNum < oldColNum){
                    if(checkDirectionWest()){
                        if(isLOSFreeWest()){
                            return true;
                        }else{
                            return false;
                        } 
                    }                    
                }else{
                    if(checkDirectionEast()){
                        if(isLOSFreeEast()){
                            return true;
                        }else{
                            return false;
                        } 
                    }
                }
            }
        }else if(direction == "north"){
            if(checkDirectionNorth()){
                if(isLOSFreeNorth()){
                    return true;
                }else{
                    return false;
                } 
            }else{
                if(clickedColNum < oldColNum){
                    if(checkDirectionWest()){
                        if(isLOSFreeWest()){
                            return true;
                        }else{
                            return false;
                        } 
                    }                    
                }else{
                    if(checkDirectionEast()){
                        if(isLOSFreeEast()){
                            return true;
                        }else{
                            return false;
                        } 
                    }
                }
            }
        }
        return true;
    }
    
    private boolean checkDirectionSouth(){
        int count = 1;
        maxRows = clickedRowNum-oldRowNum;
        for (int i = 1; i <= maxRows; i++) {
            for (int j = 1; j <= count; j++) {
                if(clickedColNum == oldColNum && clickedRowNum-i == oldRowNum){
                    unitFoundInRow = i;
                    unitFoundInCol = 0;
                    return true;
                }else if(clickedColNum-j == oldColNum && clickedRowNum-i == oldRowNum){
                    unitFoundInRow = i;
                    unitFoundInCol = j;
                    return true;
                }else if(clickedColNum+j == oldColNum && clickedRowNum-i == oldRowNum){
                    unitFoundInRow = i;
                    unitFoundInCol = j;
                    return true;
                }   
            }
            count = 1 + i*2;
        }
        return false;
    }
    
    private boolean checkDirectionNorth(){
        int count = 1;
        maxRows = oldRowNum-clickedRowNum;
        for (int i = 1; i <= maxRows; i++) {
            for (int j = 1; j <= count; j++) {
                if(clickedColNum == oldColNum && clickedRowNum+i == oldRowNum){
                    unitFoundInRow = i;
                    unitFoundInCol = 0;
                    return true;
                }else if(clickedColNum-j == oldColNum && clickedRowNum+i == oldRowNum){
                    unitFoundInRow = i;
                    unitFoundInCol = j;
                    return true;
                }else if(clickedColNum+j == oldColNum && clickedRowNum+i == oldRowNum){
                    unitFoundInRow = i;
                    unitFoundInCol = j;
                    return true;
                }   
            }
            count = 1 + i*2;
        }
        return false;
    }
    
    private boolean checkDirectionWest(){
        int count = 1;
        maxRows = oldColNum-clickedColNum;
        for (int i = 1; i <= maxRows; i++) {
            for (int j = 1; j <= count; j++) {
                if(clickedColNum-i == oldColNum && clickedRowNum == oldRowNum){
                    unitFoundInRow = i;
                    unitFoundInCol = 0;
                    return true;
                }else if(clickedColNum-i == oldColNum && clickedRowNum+j == oldRowNum){
                    unitFoundInRow = i;
                    unitFoundInCol = j;
                    return true;
                }else if(clickedColNum-i == oldColNum && clickedRowNum-j == oldRowNum){
                    unitFoundInRow = i;
                    unitFoundInCol = j;
                    return true;
                }   
            }
            count = 1 + i*2;
        }
        return false;
    }
    
    private boolean checkDirectionEast(){
        int count = 1;
        maxRows = oldColNum-clickedColNum;
        for (int i = 1; i <= maxRows; i++) {
            for (int j = 1; j <= count; j++) {
                if(clickedColNum+i == oldColNum && clickedRowNum == oldRowNum){
                    unitFoundInRow = i;
                    unitFoundInCol = 0;
                    return true;
                }else if(clickedColNum+i == oldColNum && clickedRowNum+j == oldRowNum){
                    unitFoundInRow = i;
                    unitFoundInCol = j;
                    return true;
                }else if(clickedColNum+i == oldColNum && clickedRowNum-j == oldRowNum){
                    unitFoundInRow = i;
                    unitFoundInCol = j;
                    return true;
                }   
            }
            count = 1 + i*2;
        }
        return false;
    }

    private boolean isLOSFreeSouth(){
        if (unitFoundInRow == 2) { //Close to the enemy the target has more cover
            if (clickedColNum < oldColNum) {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum - i][clickedRowNum - unitFoundInRow].los) {
                        return false;
                    }
                }
            } else {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum + i][clickedRowNum - unitFoundInRow].los) {
                        return false;
                    }
                }
            }
        }
        if (unitFoundInCol <= 1) { //stands direct above the target and checks if there is some shit between
            for (int i = 0; i <= unitFoundInRow; i++) {
                if (!envMap[clickedColNum][clickedRowNum - i].los) {
                    return false;
                }
            }
        }
        if (unitFoundInCol > 1) {
            //TODO Check outer col
            if (clickedColNum < oldColNum) {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum + i][clickedRowNum - unitFoundInRow].los) {
                        return false;
                    }
                }
            } else {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum - i][clickedRowNum - unitFoundInRow].los) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private boolean isLOSFreeNorth(){
        if (unitFoundInRow == 2) { //Close to the enemy the target has more cover
            if (clickedColNum < oldColNum) {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum - i][clickedRowNum + unitFoundInRow].los) {
                        return false;
                    }
                }
            } else {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum + i][clickedRowNum + unitFoundInRow].los) {
                        return false;
                    }
                }
            }
        }
        if (unitFoundInCol <= 1) { //stands direct above the target and checks if there is some shit between
            for (int i = 0; i <= unitFoundInRow; i++) {
                if (!envMap[clickedColNum][clickedRowNum + i].los) {
                    return false;
                }
            }
        }
        if (unitFoundInCol > 1) {
            //TODO Check outer col
            if (clickedColNum < oldColNum) {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum + i][clickedRowNum + unitFoundInRow].los) {
                        return false;
                    }
                }
            } else {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum - i][clickedRowNum + unitFoundInRow].los) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private boolean isLOSFreeWest(){
        if (unitFoundInCol == 2) { //Close to the enemy the target has more cover
            if (clickedRowNum < oldRowNum) {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum - unitFoundInCol][clickedRowNum - i].los) {
                        return false;
                    }
                }
            } else {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum - unitFoundInCol][clickedRowNum + i].los) {
                        return false;
                    }
                }
            }
        }
        if (unitFoundInRow <= 1) { //stands direct next to the target and checks if there is some shit between
            for (int i = 0; i <= unitFoundInCol; i++) {
                if (!envMap[clickedColNum-i][clickedRowNum].los) {
                    return false;
                }
            }
        }
        if (unitFoundInRow > 1) {
            //Check outer col
            if (clickedRowNum < oldRowNum) {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum - unitFoundInCol][clickedRowNum - i].los) {
                        return false;
                    }
                }
            } else {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum - unitFoundInCol][clickedRowNum + i].los) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private boolean isLOSFreeEast(){
        if (unitFoundInCol == 2) { //Close to the enemy the target has more cover
            if (clickedRowNum < oldRowNum) {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum + unitFoundInCol][clickedRowNum - i].los) {
                        return false;
                    }
                }
            } else {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum + unitFoundInCol][clickedRowNum + i].los) {
                        return false;
                    }
                }
            }
        }
        if (unitFoundInRow <= 1) { //stands direct next to the target and checks if there is some shit between
            for (int i = 0; i <= unitFoundInCol; i++) {
                if (!envMap[clickedColNum+i][clickedRowNum].los) {
                    return false;
                }
            }
        }
        if (unitFoundInRow > 1) {
            //Check outer col
            if (clickedRowNum < oldRowNum) {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum + unitFoundInCol][clickedRowNum - i].los) {
                        return false;
                    }
                }
            } else {
                for (int i = 1; i <= 2; i++) {
                    if (!envMap[clickedColNum + unitFoundInCol][clickedRowNum + i].los) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
}

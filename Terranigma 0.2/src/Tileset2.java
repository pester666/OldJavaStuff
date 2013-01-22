

public class Tileset2{
    
    private static String name = "Tileset2";

    private static ActualTile[] tileArray = new ActualTile[]{
      new ActualTile(0, true),
      new ActualTile(1, true),
      new ActualTile(2, true),
      new ActualTile(3, true),
      new ActualTile(4, true),
      new ActualTile(5, true),
      new ActualTile(6, true),
      new ActualTile(7, false),
      new ActualTile(8, true),
      new ActualTile(9, true),
      new ActualTile(10, true),
      new ActualTile(11, false),
      new ActualTile(12, false),
      new ActualTile(13, false),
      new ActualTile(14, false),
    };
    
    public static ActualTile getTile(int i){
        for (int j = 0; j < tileArray.length; j++) {
            if(tileArray[j].getId() == i){
                return tileArray[j];
            }
        }
        return new ActualTile(-1, false);
    }
    
    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Tileset2.name = name;
    }
}

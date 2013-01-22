

public class Tileset1{
    
    private static String name = "Tileset";

    private static ActualTile[] tileArray = new ActualTile[]{
      new ActualTile(0, true),
      new ActualTile(1, false),
      new ActualTile(2, false),
      new ActualTile(3, false),
      new ActualTile(4, false),
      new ActualTile(5, true),
      new ActualTile(6, true),
      new ActualTile(7, false),
      new ActualTile(8, false),
      new ActualTile(9, false),
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
        Tileset1.name = name;
    }
}

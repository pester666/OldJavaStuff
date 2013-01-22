package client.gui.hire;

import java.awt.Image;

public class HireBook {

    protected Image header;
    protected Image[] heraldik;
    protected Image[] description;
    protected String[] heraldikLocation;
    
    public Image getHeader() {
        return header;
    }
    public void setHeader(Image header) {
        this.header = header;
    }
    public Image getHeraldik(int i) {
        return heraldik[i];
    }
    public void setHeraldik(Image[] heraldik) {
        this.heraldik = heraldik;
    }
    public Image getDescription(int i) {
        return description[i];
    }
    public void setDescription(Image[] description) {
        this.description = description;
    }
    public Image[] getHeraldik() {
        return heraldik;
    }
    public Image[] getDescription() {
        return description;
    }
    public String getHeraldikLocation(int heroCounter) {
        return heraldikLocation[heroCounter];
    }
    public void setHeraldikLocation(String[] heraldikLocation) {
        this.heraldikLocation = heraldikLocation;
    }
}

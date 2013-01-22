package client.gui;

import javax.swing.JPanel;

public abstract class MenuPanel extends JPanel {

    public abstract void setActive(boolean active);
    public abstract int getPanelToActive();
    public abstract int getPanelId();

}

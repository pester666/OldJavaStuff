
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Listener des Tool Panels
 *
 */
public class CheckBoxListener implements ActionListener {
    MapWindow window;

    public CheckBoxListener(MapWindow window) {
        this.window = window;

    }

    public void actionPerformed(ActionEvent e) {
        if (window.layerPanel.checkBoxStatusList.get(Integer.parseInt(e.getActionCommand())) == false) {
            window.layerPanel.checkBoxStatusList.set(Integer.parseInt(e.getActionCommand()), true);
            window.repaint();
        } else if (window.layerPanel.checkBoxStatusList.get(Integer.parseInt(e.getActionCommand())) == true) {
            window.layerPanel.checkBoxStatusList.set(Integer.parseInt(e.getActionCommand()), false);
            window.repaint();
        }
    }
}

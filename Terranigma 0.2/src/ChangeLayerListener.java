
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Listener des Tool Panels
 *
 */
public class ChangeLayerListener implements ActionListener {
    MapWindow window;

    public ChangeLayerListener(MapWindow window) {
        this.window = window;
    }

    public void actionPerformed(ActionEvent e) {
        window.layer.setLayer(Integer.parseInt(e.getActionCommand()));

        window.footer.updateLayersView(" Layer: " + e.getActionCommand());
    }
}

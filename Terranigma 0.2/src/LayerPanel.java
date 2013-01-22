import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class LayerPanel extends JPanel {
    MapWindow window;
    Layer layer;
    ArrayList<Boolean> checkBoxStatusList = new ArrayList<Boolean>();
    JScrollPane scrollPane;
    JLabel title;
    Dimension scrpDimension = new Dimension(220, 200);

    /**
     * Konstruktor des ToolPanels.
     * 
     * @param layer
     */
    public LayerPanel(MapWindow window) {
        this.window = window;
        this.layer = window.layer;

        this.title = new JLabel("Layers: ");
        setPreferredSize(new Dimension(200, 300));
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(0, 1));

        for (int i = 0; i < layer.getLayerCount(); i++) {
            checkBoxStatusList.add(new Boolean(true));
            JCheckBox checkLayer = new JCheckBox();
            JButton changeLayerButton = new JButton("Layer " + i);
            changeLayerButton.setActionCommand("" + i);
            checkLayer.setActionCommand("" + i);
            //auf string parsen
            checkLayer.setSelected(true);
            changeLayerButton.addActionListener(new ChangeLayerListener(window));
            checkLayer.addActionListener(new CheckBoxListener(window));
            checkBoxPanel.add(checkLayer);
            checkLayer.setSize(new Dimension(5, 100));
            checkBoxPanel.add(changeLayerButton);
        }
        scrollPane = new JScrollPane(checkBoxPanel);
        scrollPane.setPreferredSize(scrpDimension);
        add(title);
        add(scrollPane);
    }

    /**
     * Gibt von einem Speziellen layer den status ob checked oder unchecked
     * 
     * @param i
     * @return
     */
    public Boolean getStatusForOneLayer(int i) {
        if (checkBoxStatusList.size() <= 0) {
            return false;
        }
        return checkBoxStatusList.get(i).booleanValue();
    }

    /**
     * update anzahl der layer etc
     */
    public void updateLayerCount() {
        this.remove(scrollPane);
        this.remove(title);
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(0, 2));

        for (int i = 0; i < layer.getLayerCount(); i++) {
            checkBoxStatusList.add(new Boolean(true));
            JCheckBox checkLayer = new JCheckBox();
            JButton changeLayerButton = new JButton("Layer " + i);
            changeLayerButton.setActionCommand("" + i);
            checkLayer.setActionCommand("" + i);
            //auf string parsen
            checkLayer.setSelected(true);
            changeLayerButton.addActionListener(new ChangeLayerListener(this.window));
            checkLayer.addActionListener(new CheckBoxListener(this.window));
            checkBoxPanel.add(checkLayer);
            checkBoxPanel.add(changeLayerButton);
        }
        title = new JLabel("Layers: ");
        scrollPane = new JScrollPane(checkBoxPanel);
        scrollPane.setPreferredSize(scrpDimension);
        add(title);
        add(scrollPane);
    }
}

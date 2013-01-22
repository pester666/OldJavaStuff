import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class ToolPanel extends JPanel {
    MapWindow window;
    JToggleButton btnErase;
    JToggleButton btnEvent;
    JToggleButton btnBrush;
    JToggleButton btnSelector;

    /**
     * Konstruktor des ToolPanels.
     * 
     * @param layer
     */
    public ToolPanel(final MapWindow window) {
        this.window = window;
        this.setLayout(new GridLayout(2,2));
        btnErase = new JToggleButton("Eraser");
        btnEvent = new JToggleButton("Event");
        btnBrush = new JToggleButton("Brush");
        btnSelector = new JToggleButton("Selector");
        ButtonGroup bg = new ButtonGroup();
        bg.add(btnErase);
        bg.add(btnEvent);
        bg.add(btnBrush);
        bg.add(btnSelector);

        btnBrush.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                window.tool.setMouseStatus(0);
            }
        });
        
        btnErase.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                window.tool.setMouseStatus(1);
            }
        });
        
        btnEvent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                window.tool.setMouseStatus(2);
            }
        });
        
        btnSelector.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                window.tool.setMouseStatus(3);
            }
        });
        
        this.add(btnBrush);
        this.add(btnErase);
        this.add(btnEvent);
        this.add(btnSelector);
    }
}

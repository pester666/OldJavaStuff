import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MapWindow extends JFrame {

    Map actualMap;
    TilePalette palette;
    MapView mapView;
    MapMenu mapMenu;
    InfoFooter footer;
    Layer layer;
    LayerPanel layerPanel;
    TreePanel treePanel;
    Tool tool;
    ToolPanel toolPanel;

    /**
     * Konstruktor des MapWindow. 1. Initialisierung s‰mtlicher GUI-Elemente 2.
     * Erstellen des Default Map-Objekts 3. Setzten der Frame Properties.
     */
    public MapWindow() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.requestFocus();

        tool = new Tool(this);
        layer = new Layer(this);
        actualMap = new Map(null, null, null);
        palette = new TilePalette(this);
        layerPanel = new LayerPanel(this);
        toolPanel = new ToolPanel(this);
        footer = new InfoFooter();
        mapView = new MapView(this, footer, layer);
        mapMenu = new MapMenu(this);
        treePanel = new TreePanel(this);

        setLayout(new BorderLayout());

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        leftPanel.add(palette, BorderLayout.CENTER);
        leftPanel.add(toolPanel, BorderLayout.SOUTH);
        add(mapView.scrollPane, BorderLayout.CENTER);
        add(mapMenu, BorderLayout.NORTH);
        rightPanel.add(layerPanel, BorderLayout.NORTH);
        rightPanel.add(treePanel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(footer, BorderLayout.SOUTH);

        //groe√üe des Fensters setzen
        setSize(1500, 1000);
        //Setzen eines Fenstertitels
        setTitle("Terranigma 0.2 - Startscreen");
    }
}

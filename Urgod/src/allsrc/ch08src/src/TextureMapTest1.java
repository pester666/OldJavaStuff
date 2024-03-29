package allsrc.ch08src.src;


import allsrc.ch08src.src.com.brackeen.javagamebook.graphics3D.SimpleTexturedPolygonRenderer;
import allsrc.ch08src.src.com.brackeen.javagamebook.math3D.Polygon3D;
import allsrc.ch08src.src.com.brackeen.javagamebook.math3D.Transform3D;
import allsrc.ch08src.src.com.brackeen.javagamebook.math3D.Vector3D;
import allsrc.ch08src.src.com.brackeen.javagamebook.math3D.ViewWindow;
import allsrc.ch08src.src.com.brackeen.javagamebook.test.GameCore3D;

public class TextureMapTest1 extends GameCore3D {

    public static void main(String[] args) {
        new TextureMapTest1().run();
    }

    public void createPolygons() {
        Polygon3D poly;

        // one wall for now
        poly = new Polygon3D(
            new Vector3D(-128, 256, -1000),
            new Vector3D(-128, 0, -1000),
            new Vector3D(128, 0, -1000),
            new Vector3D(128, 256, -1000));
        polygons.add(poly);
    }

    public void createPolygonRenderer() {
        viewWindow = new ViewWindow(0, 0,
            screen.getWidth(), screen.getHeight(),
            (float)Math.toRadians(75));

        Transform3D camera = new Transform3D(0,100,0);
        polygonRenderer = new SimpleTexturedPolygonRenderer(
            camera, viewWindow, "../images/test_pattern.png");
    }

}
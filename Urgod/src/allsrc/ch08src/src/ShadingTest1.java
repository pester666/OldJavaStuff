package allsrc.ch08src.src;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import allsrc.ch08src.src.com.brackeen.javagamebook.graphics3D.ShadedTexturedPolygonRenderer;
import allsrc.ch08src.src.com.brackeen.javagamebook.graphics3D.texture.Texture;
import allsrc.ch08src.src.com.brackeen.javagamebook.input.GameAction;
import allsrc.ch08src.src.com.brackeen.javagamebook.math3D.PointLight3D;
import allsrc.ch08src.src.com.brackeen.javagamebook.math3D.Transform3D;
import allsrc.ch08src.src.com.brackeen.javagamebook.math3D.ViewWindow;

public class ShadingTest1 extends TextureMapTest2 {

    public static void main(String[] args) {
        new ShadingTest1().run();
    }

    private GameAction brighterLight = new GameAction("brighter");
    private GameAction dimmerLight = new GameAction("dimmer");

    private PointLight3D light;

    public void init() {
        init(LOW_RES_MODES);
        inputManager.mapToKey(brighterLight, KeyEvent.VK_PLUS);
        inputManager.mapToKey(brighterLight, KeyEvent.VK_ADD);
        inputManager.mapToKey(brighterLight, KeyEvent.VK_EQUALS);
        inputManager.mapToKey(dimmerLight, KeyEvent.VK_SUBTRACT);
        inputManager.mapToKey(dimmerLight, KeyEvent.VK_MINUS);
    }

    public Texture loadTexture(String imageName) {
        return Texture.createTexture(imageName, true);
    }

    public void createPolygonRenderer() {
        viewWindow = new ViewWindow(0, 0,
            screen.getWidth(), screen.getHeight(),
            (float)Math.toRadians(75));


        Transform3D camera = new Transform3D(0,100,0);
        ShadedTexturedPolygonRenderer polygonRenderer =
            new ShadedTexturedPolygonRenderer(camera, viewWindow);
        light = new PointLight3D(-500,500,0, 1f);
        light.setDistanceFalloff(2000);
        polygonRenderer.setLightSource(light);
        polygonRenderer.setAmbientLightIntensity(.05f);

        this.polygonRenderer = polygonRenderer;

    }

    public void draw(Graphics2D g) {
        super.draw(g);
        g.setColor(Color.WHITE);
        g.drawString("Press +/- to change the light intensity.",
            5, fontSize*2);
    }

    public void update(long elapsedTime) {
        super.update(elapsedTime);

        if (brighterLight.isPressed()) {
            light.setIntensity(
                Math.min(5,light.getIntensity()+.005f*elapsedTime));
        }
        if (dimmerLight.isPressed()) {
            light.setIntensity(
                Math.max(0,light.getIntensity()-.005f*elapsedTime));
        }
    }

}
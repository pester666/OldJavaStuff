package allsrc.ch16src.src.com.brackeen.javagamebook.shooter3D;

import java.awt.DisplayMode;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import allsrc.ch16src.src.com.brackeen.javagamebook.bsp2D.BSPRenderer;
import allsrc.ch16src.src.com.brackeen.javagamebook.game.GameObject;
import allsrc.ch16src.src.com.brackeen.javagamebook.game.GameObjectManager;
import allsrc.ch16src.src.com.brackeen.javagamebook.input.GameAction;
import allsrc.ch16src.src.com.brackeen.javagamebook.input.InputManager;
import allsrc.ch16src.src.com.brackeen.javagamebook.math3D.MovingTransform3D;
import allsrc.ch16src.src.com.brackeen.javagamebook.math3D.ObjectLoader;
import allsrc.ch16src.src.com.brackeen.javagamebook.math3D.PointLight3D;
import allsrc.ch16src.src.com.brackeen.javagamebook.math3D.PolygonGroup;
import allsrc.ch16src.src.com.brackeen.javagamebook.math3D.Transform3D;
import allsrc.ch16src.src.com.brackeen.javagamebook.math3D.Vector3D;
import allsrc.ch16src.src.com.brackeen.javagamebook.math3D.ViewWindow;
import allsrc.ch16src.src.com.brackeen.javagamebook.test.GameCore3D;

public abstract class ShooterCore extends GameCore3D {

    private static final float PLAYER_SPEED = .5f;
    private static final float PLAYER_TURN_SPEED = 0.04f;
    private static final float CAMERA_HEIGHT = 100;
    private static final float BULLET_HEIGHT = 75;

    protected GameAction fire = new GameAction("fire",
        GameAction.DETECT_INITAL_PRESS_ONLY);
    protected GameAction jump = new GameAction("jump",
        GameAction.DETECT_INITAL_PRESS_ONLY);

    protected GameObjectManager gameObjectManager;
    protected PolygonGroup blastModel;
    protected DisplayMode[] modes;

    public ShooterCore(String[] args) {
        modes = LOW_RES_MODES;
        for (int i=0; i<args.length; i++) {
            if (args[i].equals("-lowres")) {
                modes = VERY_LOW_RES_MODES;
                fontSize = 12;
            }
        }
    }

    public void init() {

        init(modes);

        inputManager.mapToKey(jump, KeyEvent.VK_SPACE);
        inputManager.mapToMouse(fire, InputManager.MOUSE_BUTTON_1);

        // set up the local lights for the model.
        float ambientLightIntensity = .8f;
        List lights = new LinkedList();
        lights.add(new PointLight3D(-100,100,100, .5f, -1));
        lights.add(new PointLight3D(100,100,0, .5f, -1));

        // load the object model
        ObjectLoader loader = new ObjectLoader();
        loader.setLights(lights, ambientLightIntensity);
        try {
            blastModel = loader.loadObject("../images/blast.obj");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void createPolygonRenderer() {
        // make the view window the entire screen
        viewWindow = new ViewWindow(0, 0,
            screen.getWidth(), screen.getHeight(),
            (float)Math.toRadians(75));

        Transform3D camera = new Transform3D();
        polygonRenderer = new BSPRenderer(camera, viewWindow);
    }


    public void updateWorld(long elapsedTime) {

        float angleVelocity;

        // cap elapsedTime
        elapsedTime = Math.min(elapsedTime, 100);

        GameObject player = gameObjectManager.getPlayer();
        MovingTransform3D playerTransform = player.getTransform();
        Vector3D velocity = playerTransform.getVelocity();

        //playerTransform.stop();
        velocity.x = 0;
        velocity.z = 0;
        float x = -playerTransform.getSinAngleY();
        float z = -playerTransform.getCosAngleY();
        if (goForward.isPressed()) {
            velocity.add(x * PLAYER_SPEED, 0, z * PLAYER_SPEED);
        }
        if (goBackward.isPressed()) {
            velocity.add(-x * PLAYER_SPEED, 0, -z * PLAYER_SPEED);
        }
        if (goLeft.isPressed()) {
            velocity.add(z * PLAYER_SPEED, 0, -x * PLAYER_SPEED);
        }
        if (goRight.isPressed()) {
            velocity.add(-z * PLAYER_SPEED, 0, x * PLAYER_SPEED);
        }
        if (jump.isPressed()) {
            player.setJumping(true);
        }
        if (fire.isPressed()) {
            float cosX = playerTransform.getCosAngleX();
            float sinX = playerTransform.getSinAngleX();
            Blast blast = new Blast(
                (PolygonGroup)blastModel.clone(),
                new Vector3D(cosX*x, sinX, cosX*z));
            float dist = player.getBounds().getRadius() +
                blast.getBounds().getRadius();
            // blast starting location needs work. looks like
            // the blast is coming out of your forehead when
            // you're shooting down.
            blast.getLocation().setTo(
                player.getX() + x*dist,
                player.getY() + BULLET_HEIGHT,
                player.getZ() + z*dist);
            gameObjectManager.add(blast);
        }


        playerTransform.setVelocity(velocity);

        // look up/down (rotate around x)
        angleVelocity = Math.min(tiltUp.getAmount(), 200);
        angleVelocity += Math.max(-tiltDown.getAmount(), -200);
        playerTransform.setAngleVelocityX(angleVelocity *
             PLAYER_TURN_SPEED / 200);

        // turn (rotate around y)
        angleVelocity = Math.min(turnLeft.getAmount(), 200);
        angleVelocity += Math.max(-turnRight.getAmount(), -200);
        playerTransform.setAngleVelocityY(angleVelocity *
             PLAYER_TURN_SPEED / 200);

        // update objects
        gameObjectManager.update(elapsedTime);

        // limit look up/down
        float angleX = playerTransform.getAngleX();
        float limit = (float)Math.PI / 2;
        if (angleX < -limit) {
            playerTransform.setAngleX(-limit);
        }
        else if (angleX > limit) {
            playerTransform.setAngleX(limit);
        }

        // set the camera to be 100 units above the player
        Transform3D camera = polygonRenderer.getCamera();
        camera.setTo(playerTransform);
        camera.getLocation().add(0,CAMERA_HEIGHT,0);

    }



}
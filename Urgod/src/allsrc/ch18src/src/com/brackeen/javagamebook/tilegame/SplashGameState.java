package allsrc.ch18src.src.com.brackeen.javagamebook.tilegame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import allsrc.ch18src.src.com.brackeen.javagamebook.input.GameAction;
import allsrc.ch18src.src.com.brackeen.javagamebook.input.InputManager;
import allsrc.ch18src.src.com.brackeen.javagamebook.state.GameState;
import allsrc.ch18src.src.com.brackeen.javagamebook.state.ResourceManager;


public class SplashGameState implements GameState {

    private String splashFilename;
    private Image splash;
    private GameAction exitSplash;
    private long totalElapsedTime;
    private boolean done;

    public SplashGameState(String splashFilename) {
        exitSplash = new GameAction("exitSplash",
             GameAction.DETECT_INITAL_PRESS_ONLY);
        this.splashFilename = splashFilename;
    }

    public String getName() {
        return "Splash";
    }

    public void loadResources(ResourceManager resourceManager) {
        splash = resourceManager.loadImage(splashFilename);
    }


    public String checkForStateChange() {
        return done?"Main":null;
    }


    public void start(InputManager inputManager) {
        inputManager.mapToKey(exitSplash, KeyEvent.VK_SPACE);
        inputManager.mapToMouse(exitSplash,
            InputManager.MOUSE_BUTTON_1);

        totalElapsedTime = 0;
        done = false;
    }

    public void stop() {
        // do nothing
    }

    public void update(long elapsedTime) {
        totalElapsedTime+=elapsedTime;
        if (totalElapsedTime > 3000 || exitSplash.isPressed()) {
            done = true;
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(splash, 0, 0, null);
    }
}
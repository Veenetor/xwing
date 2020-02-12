package org.academiadecodigo.xwing;

import org.academiadecodigo.xwing.gameobject.XWing;
import org.academiadecodigo.xwing.grid.Grid;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGrid;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Game {

    private Grid map;
    private XWing player;
    private KeyboardHandler handler;
    private Keyboard control;
    private int delay;

    // GFX PROPERTIES;

    private SimpleGfxGrid gfxMap;

    // CONSTRUCTOR

    public Game (int cols, int rows, int delay) {
        map = new Grid(cols, rows);
        gfxMap = new SimpleGfxGrid(cols, rows);

        player = new XWing(map);
        handler = player;

        control = new Keyboard(player);

        this.delay = delay;

        KeyboardEvent moveUp = new KeyboardEvent();
        KeyboardEvent moveDown = new KeyboardEvent();


        moveUp.setKey(KeyboardEvent.KEY_UP);
        moveDown.setKey(KeyboardEvent.KEY_DOWN);

        moveUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        moveDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        control.addEventListener(moveUp);
        control.addEventListener(moveDown);

    }


    public void start() throws InterruptedException {

        while (true) {

            // Pause for a while
            Thread.sleep(delay);

            // game delay ();

        }

    }



    // GET

    public Grid getMap () {
        return map;
    }

    public XWing getPlayer () {
        return player;
    }


    // CLASS END
}

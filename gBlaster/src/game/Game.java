package game;

import game.gameobject.XWing;
import game.grid.Grid;
import game.simplegfx.SimpleGfxGrid;
import game.simplegfx.SimpleGfxGridPosition;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Game {

    private Grid map;
    private XWing player;
    private KeyboardHandler handler;
    private Keyboard control;

    // GFX PROPERTIES;

    private SimpleGfxGrid gfxMap;

    // CONSTRUCTOR

    public Game (int cols, int rows) {
        map = new Grid(cols, rows);

        player = new XWing(map);
        handler = player;

        control = new Keyboard(player);

        gfxMap = new SimpleGfxGrid(cols, rows);

        KeyboardEvent moveUp = new KeyboardEvent();
        KeyboardEvent moveDown = new KeyboardEvent();


        moveUp.setKey(KeyboardEvent.KEY_UP);
        moveDown.setKey(KeyboardEvent.KEY_DOWN);

        moveUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        moveDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        control.addEventListener(moveUp);
        control.addEventListener(moveDown);

    }





    public Grid getMap () {
        return map;
    }

    public XWing getPlayer () {
        return player;
    }


    // CLASS END
}

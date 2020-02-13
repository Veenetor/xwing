package org.academiadecodigo.xwing.gameobject;

import org.academiadecodigo.xwing.grid.Grid;
import org.academiadecodigo.xwing.grid.GridPosition;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGridPosition;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class XWing implements KeyboardHandler {

    private GridPosition pos;
    private GridPosition extraPos;
    private Grid map;

    private SimpleGfxGridPosition gfxPos;

    private int health;

    //CONSTRUCTOR

    public XWing (Grid map) {
        this.map = map;
        pos = new GridPosition((map.getRows()/2), map);
        extraPos = new GridPosition((map.getRows()/2), map);
        extraPos.movePos(0, 1);

        gfxPos = new SimpleGfxGridPosition(pos.getCol(), pos.getRow());

        health = 0;
    }


    // METHODS

    public SimpleGfxGridPosition getGfxPos() {
        return gfxPos;
    }

    public void moveUp () {
        movePlayer(-1);
    }
    public void moveDown () {
        movePlayer(1);
    }

    public int getCol () {return pos.getCol();}
    public int getRow () {
        return pos.getRow();
    }
    public int getExRow () {
        return extraPos.getRow();
    }

    public void movePlayer (int row) {
        if ((pos.getRow() + row) < 0) {
            return;
        }

        if ((extraPos.getRow() + row) > map.getRows()-1) {
            return;
        }

            pos.movePos(0, row);
            extraPos.movePos(0, row);
            gfxPos.movePlayer(0, row);

        }

    public void hit () {
        if (health > 0) {
            health--;
        }

        else {
            destroyed();
        }
    }

    public void destroyed () {
        gfxPos.destroyed();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (KeyboardEvent.KEY_DOWN == keyboardEvent.getKey()) {
                movePlayer(1);
        }

        if (KeyboardEvent.KEY_UP == keyboardEvent.getKey()) {
            movePlayer(-1);
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    // CLASS END

}

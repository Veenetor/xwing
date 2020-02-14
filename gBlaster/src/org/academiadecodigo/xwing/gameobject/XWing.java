package org.academiadecodigo.xwing.gameobject;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.xwing.Game;
import org.academiadecodigo.xwing.grid.Grid;
import org.academiadecodigo.xwing.grid.GridPosition;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGrid;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGridPosition;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.awt.*;

public class XWing implements KeyboardHandler {

    private GridPosition pos;
    private GridPosition extraPos;
    private Grid map;

    private SimpleGfxGridPosition gfxPos;

    private Picture[] health;
    // private int health2;

    private boolean isDestroyed;

    //CONSTRUCTOR

    public XWing (Grid map) {
        this.map = map;
        pos = new GridPosition((map.getRows()/2), map);
        extraPos = new GridPosition((map.getRows()/2), map);
        extraPos.movePos(0, 1);

        gfxPos = new SimpleGfxGridPosition(pos.getCol(), pos.getRow());

        health = new Picture[5];

        for (int h = 0; h < 3; h++ ) {
            health[h] = new Picture(( map.getCols() - h) * SimpleGfxGrid.cellSize, (map.getRows()*SimpleGfxGrid.cellSize)+50, "images/r2d2-health.png");
            health[h].draw();
        }

    }


    // METHODS

    public SimpleGfxGridPosition getGfxPos() {
        return gfxPos;
    }

    public void moveUp () {
        movePlayer(0, -1);
    }
    public void moveDown () {
        movePlayer(0, 1);
    }

    public int getCol () {return pos.getCol();}
    public int getRow () {
        return pos.getRow();
    }
    public int getExRow () {
        return extraPos.getRow();
    }

    public void movePlayer (int col, int row) {
        if ((pos.getRow() + row) < 0) {
            return;
        }

        if ((extraPos.getRow() + row) > map.getRows()-1) {
            return;
        }

        if ((pos.getCol() + col) < 0) {
            return;
        }

        if ((pos.getCol() + col) > map.getCols()-1) {
            return;
        }

            pos.movePos(col, row);
            extraPos.movePos(col, row);
            gfxPos.movePlayer(col, row);

        }

    public void hit () {

        if (health[1] == null) {
            health[0].delete();
            isDestroyed = true;
        } else {

            for (int h = health.length - 1; h > 0; h--) {

                if (health[h] != null) {
                    health[h].delete();
                    health[h] = null;
                    return;
                }
            }
        }

        if (isDestroyed) {
            destroyed();
        }

    }

    public boolean isDestroyed () {
        return isDestroyed;
    }

    public void destroyed () {
        gfxPos.destroyed();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (KeyboardEvent.KEY_DOWN == keyboardEvent.getKey()) {
                movePlayer(0, 1);
                return;
        }

        if (KeyboardEvent.KEY_UP == keyboardEvent.getKey()) {
            movePlayer(0, -1);
            return;
        }

        if (KeyboardEvent.KEY_LEFT == keyboardEvent.getKey()) {
            movePlayer(-1, 0);
            return;
        }

        if (KeyboardEvent.KEY_RIGHT == keyboardEvent.getKey()) {
            movePlayer(1, 0);
            return;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    // CLASS END

}

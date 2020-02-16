package org.academiadecodigo.xwing.gameobject;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.xwing.Game;
import org.academiadecodigo.xwing.grid.Grid;
import org.academiadecodigo.xwing.grid.GridPosition;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGrid;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGridPosition;

public class TieFighter extends GameObject {

    private boolean hasExploded;
    private int explosionLag;
    private Picture explosion;
    private GridPosition extPos;
    private Grid map;
    private int tieHealth = 200;

    private int direction;  // 1. down, 2. up, 3. left, 4. right

    public TieFighter(ObjectType type, Grid map, int col) {
        super(type);

        this.map = map;
        pos = new GridPosition(type, map, col);
        extPos = new GridPosition(pos.getCol(), pos.getRow()+1, map);
        gfxPos = new SimpleGfxGridPosition(pos.getCol(), pos.getRow(), type);
        hasExploded = false;

        if (pos.getRow() > 0) {
            direction = 1;
        } else {
            direction = 2;
        }
    }

    @Override
    public void move() {

        if (extPos.getRow() == map.getRows())  {
            pos.movePos(0, -1);
            extPos.movePos( 0, -1);
            gfxPos.movePlayer(0, -1);
            direction = 2;
        }

        if (pos.getRow() == 0) {
            pos.movePos(0, 1);
            extPos.movePos(0, 1);
            gfxPos.movePlayer(0, 1);
            direction = 1;
        }

        int roll = (int) Math.ceil(Math.random()*20);

        if (roll <= 19) {

            if (direction == 1) {
                pos.movePos(0, 1);
                extPos.movePos(0, 1);
                gfxPos.movePlayer(0, 1);

            }

            if (direction == 2) {
                pos.movePos(0, -1);
                extPos.movePos( 0, -1);
                gfxPos.movePlayer(0, -1);

            }

        }

        else {

            if (direction == 1) {
                pos.movePos(0, -1);
                extPos.movePos( 0, -1);
                gfxPos.movePlayer(0, -1);
                direction = 2;

            }

            if (direction == 2) {
                pos.movePos(0, 1);
                extPos.movePos(0, 1);
                gfxPos.movePlayer(0, 1);
                direction = 1;
            }

        }

    }
}

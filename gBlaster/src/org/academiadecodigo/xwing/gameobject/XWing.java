package org.academiadecodigo.xwing.gameobject;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.xwing.Game;
import org.academiadecodigo.xwing.grid.Grid;
import org.academiadecodigo.xwing.grid.GridPosition;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGrid;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGridPosition;

import java.io.File;

public class XWing /*implements KeyboardHandler*/ {

    private GridPosition pos;
    private GridPosition extraPos;
    private GridPosition posBullet;
    private Grid map;
    private GameObject gameObject;
    private SimpleGfxGridPosition gfxPos;
    private Picture xwingShot;
    private File audioLaserShots;
    private Game game;

    private Picture[] health;
    // private int health2;

    private Picture shootUp;
    private Picture shootDown;
    private Picture xWingLaserShot;

    private boolean isDestroyed = false;
    private boolean shotFired;

    //CONSTRUCTOR

    public XWing (Grid map) {
        this.map = map;
        pos = new GridPosition((map.getRows()/2), map);
        extraPos = new GridPosition((map.getRows()/2), map);
        extraPos.movePos(0, 1);

        gfxPos = new SimpleGfxGridPosition(pos.getCol(), pos.getRow());

        health = new Picture[5];

        //xWingLaserShot = new Picture(this.getCol()*SimpleGfxGrid.cellSize, this.getRow()*SimpleGfxGrid.cellSize, "images/xwing-laserup.png");

        //shootUp = new Picture(this.getCol()*SimpleGfxGrid.cellSize, this.getRow()*SimpleGfxGrid.cellSize, "images/laserUp.png");
        //shootDown = new Picture(this.getCol()*SimpleGfxGrid.cellSize, (this.getRow()+1)*SimpleGfxGrid.cellSize, "images/laserDown.png");

        for (int h = 0; h < 3; h++ ) {
            health[h] = new Picture(( map.getCols() - h) * SimpleGfxGrid.cellSize - 150, (map.getRows()*SimpleGfxGrid.cellSize)+20, "gBlaster/resources/images/r2d2-health.png");
            health[h].draw();
        }

        posBullet = pos;
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

    public void setDestroyed(){
        isDestroyed = false;
    }

    public void destroyed () {
        gfxPos.destroyed();
    }

    public void moveBullet(int col, int row){

        for (int i = 0; i < map.getCols(); i++) {

            /*if ((posBullet.getCol() + col) > map.getCols()-1) {
                return;
            }*/

            //xWingLaserShot.draw();
            //game.audio(audioLaserShots);
            posBullet.movePos(col++, row);
            gfxPos.moveBulletUp();
        }

    }

    public void destroyEnemies(){

    }

    public void shoot() {





/*        if (shotFired) {
            return;
        }
        moveBullet(pos.getCol(), pos.getRow());

        shotFired = true;*/
    }


    public boolean hasShot() {
        return shotFired;
    }

    /*@Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }*/

    // CLASS END

}

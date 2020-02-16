package org.academiadecodigo.xwing.gameobject;


import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.xwing.Game;
import org.academiadecodigo.xwing.grid.Grid;
import org.academiadecodigo.xwing.grid.GridPosition;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGrid;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGridPosition;


public class Asteroid extends GameObject {

    private boolean hasExploded;
    private int explosionLag;
    private Picture explosion;

    public Asteroid(ObjectType type, Grid map) {
        super(type);

        pos = new GridPosition(type, map);
        gfxPos = new SimpleGfxGridPosition(pos.getCol(), pos.getRow(), type);
        hasExploded = false;

    }

    public int getLag () {
        return explosionLag;
    }
    public void remLag () {
        explosionLag--;
    }

    public boolean isExploded () {
        return hasExploded;
    }

    public void explode () throws InterruptedException {
        gfxPos.destroyed();
        hasExploded = true;
        explosionLag = 2;

        explosion();
    }

    public void explosion() throws InterruptedException {
        for (int i = 10; i < 17 ; i++) {
            this.explosion = new Picture(this.getCol()*SimpleGfxGrid.cellSize, this.getRow()*SimpleGfxGrid.cellSize, "images/explosion/"+i+".png");
            explosion.draw();
            Thread.sleep(15);
            explosion.delete();

        }
    }

    public void destoyed () {
            explosion.delete();

         // gfxPos.destroyed();
         // Game.incScore(100);
    }

    public void remove () {
        gfxPos.destroyed();
    }

    @Override
    public void move () {
        pos.movePos(-1, 0);
        gfxPos.moveAst();
    }

    public int getRow () {
        return pos.getRow();
    }

    public int getCol () {
        return pos.getCol();
    }

}

package org.academiadecodigo.xwing.gameobject;


import org.academiadecodigo.xwing.grid.Grid;
import org.academiadecodigo.xwing.grid.GridPosition;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGrid;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGridPosition;


public class Asteroid extends GameObject {



    public Asteroid(ObjectType type, Grid map) {
        super(type);


        pos = new GridPosition(map);
        gfxPos = new SimpleGfxGridPosition(pos.getCol(), pos.getRow(), type);

    }


    public void destoyed () {
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

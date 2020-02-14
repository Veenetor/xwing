package org.academiadecodigo.xwing.grid;

import org.academiadecodigo.xwing.gameobject.ObjectType;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGrid;

public class GridPosition {

    private Grid map;
    private int col;
    private int row;
    private GridColor color;

    // CONSTRUCTORS

    /* Player constructor */
    public GridPosition (int row, Grid map) {
        this.map = map;
        this.row = row;
        col = 2;

    }

    public GridPosition (int col, int row, Grid map) {
        // to be used for extPos!!
        this.map = map;
        this.col = col;
        this.row = row;
    }

    /* Asteroid constructor */
    public GridPosition (ObjectType type, Grid map) {
        this.map = map;

        if (type == ObjectType.ASTEROID) {
            col = map.getCols();
            row = (int) Math.ceil(Math.random() * (map.getRows() - 1));
        }

        if (type == ObjectType.TIE) {
            col = map.getCols() - 2;

            int roll = (int) Math.ceil(Math.random() * 6);

            if (roll > 3) {
                row = map.getRows() - 2;
            } else {
                row = 0;
            }
        }
    }


    public GridPosition (ObjectType type, Grid map, int col) {
        if (type == ObjectType.TIE) {
            this.col = map.getCols() - (2 + (col*2));

            int roll = (int) Math.ceil(Math.random() * 6);

            if (roll > 3) {
                row = map.getRows() - 2;
            } else {
                row = 0;
            }
        }
    }

    // METHODS

    public int getCol () {
        return col;
    }

    public int getRow () {
        return row;
    }

    private void moveCol (int col) {
        this.col += col;
    }

    private void moveRow (int row) {
        this.row += row;
    }


    public void movePos(int col, int row) {
      moveCol(col);
      moveRow(row);

    }

    public void setColor (GridColor color) {
        this.color = color;
    }

    // CLASS END

}

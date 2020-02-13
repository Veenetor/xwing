package org.academiadecodigo.xwing.grid;

import org.academiadecodigo.xwing.gameobject.ObjectType;

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

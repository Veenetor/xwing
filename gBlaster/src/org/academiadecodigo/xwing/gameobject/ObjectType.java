package org.academiadecodigo.xwing.gameobject;

import org.academiadecodigo.xwing.grid.GridColor;

public enum ObjectType {


    ASTEROID (GridColor.GREY),
    TIE (GridColor.BLACK);

    private GridColor color;

    ObjectType(GridColor color) {
        this.color = color;
    }

    public GridColor getColor () {
        return color;
    }
}

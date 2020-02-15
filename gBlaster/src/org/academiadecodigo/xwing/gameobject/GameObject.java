package org.academiadecodigo.xwing.gameobject;

import org.academiadecodigo.xwing.grid.Grid;
import org.academiadecodigo.xwing.grid.GridColor;
import org.academiadecodigo.xwing.grid.GridPosition;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGrid;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGridPosition;

public abstract class GameObject {


    protected GridPosition pos;
    protected SimpleGfxGridPosition gfxPos;
    private int speed;
    private ObjectType type;
    private boolean destroyed;

    // CONSTRUCTOR
    public GameObject (ObjectType type) {
        this.type = type;


    }



    // METHODS

    /*

    Fuck me all (collision) detection!!


    */


    public abstract void move ();


    /*

    Override to String();


    */

}

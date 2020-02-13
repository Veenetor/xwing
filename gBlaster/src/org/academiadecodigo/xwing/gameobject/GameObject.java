package game.gameobject;


abstract public class GameObject {

import org.academiadecodigo.xwing.grid.Grid;
import org.academiadecodigo.xwing.grid.GridColor;
import org.academiadecodigo.xwing.grid.GridPosition;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGrid;

public abstract class GameObject {


    private GridPosition pos;
    private SimpleGfxGrid gfxPos;
    private int speed;
    private ObjectType type;
    private boolean destroyed;

    // CONSTRUCTOR
    public GameObject (GridPosition pos, ObjectType type) {
        this.pos = pos;
        this.type = type;

        pos.setColor(type.getColor());

    }



    // METHODS

    /*

    Fuck me all (collision) detection!!


    */


    public void move () {

    }


    /*

    Override to String();


    */


}

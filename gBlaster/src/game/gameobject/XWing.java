package game.gameobject;

import game.grid.Grid;
import game.grid.GridPosition;
import game.simplegfx.SimpleGfxGridPosition;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class XWing implements KeyboardHandler {

    private GridPosition pos;
    private Grid map;

    private SimpleGfxGridPosition gfxPos;

    //CONSTRUCTOR

    public XWing (Grid map) {
        this.map = map;
        pos = new GridPosition((map.getRows()/2), map);

        gfxPos = new SimpleGfxGridPosition(pos.getCol(), pos.getRow());
    }


    // METHODS

    public SimpleGfxGridPosition getGfxPos() {
        return gfxPos;
    }

    public void moveUp () {
        movePlayer(-1);
    }
    public void moveDown () {
        movePlayer(1);
    }

    public int getRow () {
        return pos.getRow();
    }

    public void movePlayer (int row) {
        if ((pos.getRow() + row) < 0) {
            return;
        }

        if ((pos.getRow() + row) > map.getRows()-1) {
            return;
        }

            pos.movePos(0, row);
            gfxPos.movePlayer(0, row);
        }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (KeyboardEvent.KEY_DOWN == keyboardEvent.getKey()) {
                movePlayer(1);
        }

        if (KeyboardEvent.KEY_UP == keyboardEvent.getKey()) {
            movePlayer(-1);
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    // CLASS END

}

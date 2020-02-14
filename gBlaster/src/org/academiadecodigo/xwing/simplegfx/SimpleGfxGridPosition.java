package org.academiadecodigo.xwing.simplegfx;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.xwing.gameobject.ObjectType;

public class SimpleGfxGridPosition implements KeyboardHandler {

    private int col;
    private int row;
    private Rectangle xWing;
    private Picture picture;

    //CONSTRUCTOR

    /* player constructor */
    public SimpleGfxGridPosition (int col, int row) {
        this.col = col*SimpleGfxGrid.cellSize+SimpleGfxGrid.PADDING;
        this.row = row*SimpleGfxGrid.cellSize+SimpleGfxGrid.PADDING;

        //xWing = new Rectangle(this.col, this.row, SimpleGfxGrid.cellSize, SimpleGfxGrid.cellSize);
        //xWing.draw();

        picture = new Picture(this.col, this.row, "images/xwing.png");
        picture.draw();
    }

    /* obstacle constructor */
    public SimpleGfxGridPosition (int col, int row, ObjectType type) {

        if (type == ObjectType.ASTEROID) {

            this.col = col*SimpleGfxGrid.cellSize+SimpleGfxGrid.PADDING;
            this.row = row*SimpleGfxGrid.cellSize+SimpleGfxGrid.PADDING;

            picture = new Picture(this.col, this.row, "images/asteroid.png");
            picture.draw();
        }

            // TIE FIGHTER CONSTRUCTOR

        if (type == ObjectType.TIE ) {

            this.col = col*SimpleGfxGrid.cellSize+SimpleGfxGrid.PADDING;
            this.row = row*SimpleGfxGrid.cellSize+SimpleGfxGrid.PADDING;

            picture = new Picture(this.col, this.row, "images/tieF.png");
            picture.draw();
        }

    }


    public void destroyed () {
        picture.delete();
    }

    public void moveAst () {
        picture.translate(-1*SimpleGfxGrid.cellSize, 0);
    }

    public void movePlayer (int col, int row) {
        picture.translate(col*SimpleGfxGrid.cellSize, row*SimpleGfxGrid.cellSize);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}

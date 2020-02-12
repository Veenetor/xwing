package org.academiadecodigo.xwing.simplegfx;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SimpleGfxGridPosition implements KeyboardHandler {

    private int col;
    private int row;
    private Rectangle xWing;
    private Picture xPic;

    //CONSTRUCTOR

    public SimpleGfxGridPosition (int col, int row) {
        this.col = col*SimpleGfxGrid.cellSize+SimpleGfxGrid.PADDING;
        this.row = row*SimpleGfxGrid.cellSize+SimpleGfxGrid.PADDING;

        //xWing = new Rectangle(this.col, this.row, SimpleGfxGrid.cellSize, SimpleGfxGrid.cellSize);
        //xWing.draw();

        xPic = new Picture(this.col, this.row, "images/xwing.png");
        xPic.draw();
    }


    public void movePlayer (int col, int row) {
        xPic.translate(col*SimpleGfxGrid.cellSize, row*SimpleGfxGrid.cellSize);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}

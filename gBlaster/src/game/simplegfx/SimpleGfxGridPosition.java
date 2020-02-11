package game.simplegfx;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public class SimpleGfxGridPosition implements KeyboardHandler {

    private int col;
    private int row;
    private Rectangle xWing;
    private Picture xPic;

    //CONSTRUCTOR

    public SimpleGfxGridPosition (int col, int row) {
        this.col = col*SimpleGfxGrid.cellSize;
        this.row = row*SimpleGfxGrid.cellSize;

        //xWing = new Rectangle(this.col, this.row, SimpleGfxGrid.cellSize, SimpleGfxGrid.cellSize);
        //xWing.draw();

        xPic = new Picture(this.col, this.row, "xwing.png");
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

package game.simplegfx;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SimpleGfxGrid {

    public static final int PADDING = 10;
    public static final int cellSize = 70;
    private int cols;
    private int rows;
    private Rectangle canvas;

    // private Picture background;

    public SimpleGfxGrid(int cols, int rows){
        this.cols = cols;
        this.rows = rows;

        canvas = new Rectangle(PADDING, PADDING, cols*cellSize, rows*cellSize);
        canvas.draw();

    }


}

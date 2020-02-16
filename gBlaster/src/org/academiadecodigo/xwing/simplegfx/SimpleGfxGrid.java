package org.academiadecodigo.xwing.simplegfx;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.xwing.Game;

public class SimpleGfxGrid {

    public static final int PADDING = 10;
    public static final int cellSize = 40;
    private int cols;
    private int rows;
    private Rectangle canvas;
    private Picture background;
    private Game game;

    // private Picture background;

    public SimpleGfxGrid(int cols, int rows){
        this.cols = cols;
        this.rows = rows;

        background = new Picture(PADDING, PADDING, "images/background.jpg");
        background.draw();

        //canvas = new Rectangle(PADDING, PADDING, cols*cellSize, rows*cellSize);
        //canvas.draw();
    }

    public void deleteBackground(){
        background.delete();
    }
}

package org.academiadecodigo.xwing.simplegfx;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SimpleGfxGrid {

    public static final int PADDING = 10;
    public static final int cellSize = 40;
    private int cols;
    private int rows;
    private Rectangle canvas;
    private Picture background;

    // private Picture background;

    public SimpleGfxGrid(int cols, int rows){
        this.cols = cols;
        this.rows = rows;


        //canvas = new Rectangle(PADDING, PADDING, cols*cellSize, rows*cellSize);
        //canvas.draw();
    }

    public void deleteBackground(){
        background.delete();
    }

    public void getBackground() {
        background = new Picture(PADDING, PADDING, "gBlaster/resources/images/background.jpg");
        background.draw();
    }

}

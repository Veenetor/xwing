package org.academiadecodigo.xwing;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game(30, 17, 100);

        Picture startMenu = new Picture(10,10, "resources/images/init.jpg");
        startMenu.draw();

        Thread.sleep(5000);
        startMenu.delete();
        //game.init();
        game.start();
        // MAIN END
    }

}

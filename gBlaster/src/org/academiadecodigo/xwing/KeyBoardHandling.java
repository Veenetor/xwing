package org.academiadecodigo.xwing;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.xwing.gameobject.XWing;
import org.academiadecodigo.xwing.grid.Grid;

public class KeyBoardHandling implements KeyboardHandler {

    private Game game;

    public KeyBoardHandling(Game game){
        this.game = game;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {



        if (KeyboardEvent.KEY_DOWN == keyboardEvent.getKey()) {
            System.out.println("down");
            game.getPlayer().movePlayer(0, 1);
            return;
        }

        if (KeyboardEvent.KEY_UP == keyboardEvent.getKey()) {
            game.getPlayer().movePlayer(0, -1);
            return;
        }

        if (KeyboardEvent.KEY_LEFT == keyboardEvent.getKey()) {
            game.getPlayer().movePlayer(-1, 0);
            return;
        }

        if (KeyboardEvent.KEY_RIGHT == keyboardEvent.getKey()) {
            game.getPlayer().movePlayer(1, 0);
            return;
        }

        if (KeyboardEvent.KEY_SPACE == keyboardEvent.getKey()) {

            System.out.println("hfjhgfkhfkugilugi");
            //game.getPicture().delete();
            //game.start();
            if (!game.getGameStarted()){
                System.out.println("dassssseeeeee");
                game.startListner();
                System.out.println("depois da merda");
                game.setGameStarted();

            }

            System.out.println("SPACE KEY PRESSED");

        }



    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


}

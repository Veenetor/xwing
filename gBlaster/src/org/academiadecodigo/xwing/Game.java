package org.academiadecodigo.xwing;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.xwing.gameobject.*;
import org.academiadecodigo.xwing.grid.Grid;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGrid;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class Game  implements KeyboardHandler {

    private Grid map;
    private XWing player;

    private KeyboardHandler handler;
    private Keyboard keyboard;
    private int delay;
    private Asteroid[] asteroidField;
    private int astCooldown;
    private TieFighter[] tieFleet;
    private Picture picture;
    private boolean gameStarted = false;

    // static

    private static int score = 0000;
    public static void incScore (int points) {
        score += points;
    }

    // GFX PROPERTIES;

    private SimpleGfxGrid gfxMap;

    // CONSTRUCTOR

    public Game (int cols, int rows, int delay) throws InterruptedException {
        map = new Grid(cols, rows);
        gfxMap = new SimpleGfxGrid(cols, rows);
        asteroidField = new Asteroid[30];
        tieFleet = new TieFighter[3];

        this.delay = delay;

        player = new XWing(map);
        handler = new KeyBoardHandling(this);
        keyboard = new Keyboard(handler);
        System.out.println("cenas e situaçoes do caralho");
        initListner();


    }



    public void reduceDelay() {
        delay -= 50;
    }


        public void initListner(){
            KeyboardEvent startGame = new KeyboardEvent();
            startGame.setKey(KeyboardEvent.KEY_SPACE);
            startGame.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(startGame);
        }

        public void startListner(){
        // moveUp
            KeyboardEvent moveUp = new KeyboardEvent();
            moveUp.setKey(KeyboardEvent.KEY_UP);
            moveUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(moveUp);

        // moveDown
        KeyboardEvent moveDown = new KeyboardEvent();
        moveDown.setKey(KeyboardEvent.KEY_DOWN);
        moveDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveDown);

        // moveBack
        KeyboardEvent moveBack = new KeyboardEvent();
        moveBack.setKey(KeyboardEvent.KEY_LEFT);
        moveBack.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveBack);

        // moveFor
        KeyboardEvent moveFor = new KeyboardEvent();
        moveFor.setKey(KeyboardEvent.KEY_RIGHT);
        moveFor.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveFor);

        // fireX
            KeyboardEvent fireX = new KeyboardEvent();
            fireX.setKey(KeyboardEvent.KEY_F);
            fireX.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(fireX);

        }



        @Override
        public void keyPressed(KeyboardEvent keyboardEvent) {

        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {

        }


    public void start() throws InterruptedException {
        System.out.println("Game started");

        //while (gameStarted){

            // Pause for a while
            Thread.sleep(delay);

            genAst();
            if (score > 1500) {
                genTie();
            }
            moveAll();

            colCheck();
            expManager();
            astScore();

            //System.out.println(score);
            //System.out.println("Game started");

       // }

    }



    private void genAst () {

        if (astCooldown > 0) {
            reduceCooldown(1);

        } else {

        int astRoll = (int) Math.floor(Math.random()*map.getRows());
        int colAdd = 0;

        for (int i = 0; i < asteroidField.length; i++) {
            if (asteroidField[i] == null && astRoll > 0) {
                int genRoll = (int) Math.ceil(Math.random() * 10);

                if (genRoll > 6) {
                    asteroidField[i] = new Asteroid(ObjectType.ASTEROID, map);
                    colAdd++;
                }
                astRoll--;
            }
            }

        incAstCooldown(colAdd);

        }

    }

    private void genTie () {

        int tieRoll = (int) Math.floor(Math.random()*6);

        if (tieRoll > 1) {
            for (int t = 0; t < tieFleet.length; t++) {

                if (tieFleet[t] == null) {
                    tieFleet[t] = new TieFighter(ObjectType.TIE, map, t);
                    return;
                }

            }
        }
    }

    private void incAstCooldown (int number) {
        astCooldown = (int) (2 + Math.random()*number);
    }

    private void reduceCooldown (int number) {
        astCooldown -= number;
    }

    private void moveAll () {
        // asteroid.move();
        moveAllAst();
        moveAllTie();

    }


    private void moveAllAst () {
        for (Asteroid ast : asteroidField) {
            if (ast != null) {
                ast.move();
            }
        }
    }

    private void moveAllTie () {
        for (TieFighter tie : tieFleet) {
            if (tie != null) {
                tie.move();
            }
        }
    }


    private void expManager () {

        for (int i = 0; i < asteroidField.length; i++) {

            if (asteroidField[i] != null) {

                if (asteroidField[i].isExploded()) {
                    if (asteroidField[i].getLag() > 0) {
                        asteroidField[i].remLag();
                    } else {
                        asteroidField[i].destoyed();
                        asteroidField[i] = null;
                    }
                }
            }
        }
    }


    private void astScore () {

        boolean astDestroyed = false;

        for (int i = 0; i < asteroidField.length; i++) {

            if (asteroidField[i] != null) {


                if (asteroidField[i].getCol() < 0 && !asteroidField[i].isExploded()) {
                    asteroidField[i].remove();
                    asteroidField[i] = null;
                    astDestroyed = true;
                }

            }
        }

        if (astDestroyed && !player.isDestroyed()) {
            incScore(100);
        }

    }

    private void colCheck () {

        if (!player.isDestroyed()) {

            for (int i = 0; i < asteroidField.length; i++) {

                if (asteroidField[i] != null) {

                    if (asteroidField[i].getCol() == player.getCol() && asteroidField[i].getRow() == player.getRow() || asteroidField[i].getCol() == player.getCol() && asteroidField[i].getRow() == player.getExRow()) {
                        player.hit();
                        asteroidField[i].explode();
                    }
                }
            }
        }
    }


    // GET

    public Grid getMap () {
        return map;
    }

    public XWing getPlayer () {
        return player;
    }

    public Picture getPicture(){
        return picture;
    }

    public boolean getGameStarted(){
        return gameStarted;
    }

    public void setGameStarted (){
        gameStarted = true;
    }


    // CLASS END
}

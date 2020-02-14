package org.academiadecodigo.xwing;

import org.academiadecodigo.xwing.gameobject.Asteroid;
import org.academiadecodigo.xwing.gameobject.GameObject;
import org.academiadecodigo.xwing.gameobject.ObjectType;
import org.academiadecodigo.xwing.gameobject.XWing;
import org.academiadecodigo.xwing.grid.Grid;
import org.academiadecodigo.xwing.simplegfx.SimpleGfxGrid;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Game {

    private Grid map;
    private XWing player;
    private KeyboardHandler handler;
    private Keyboard control;
    private int delay;
    private Asteroid[] asteroidField;
    private int astCooldown;

    // static

    private static int score = 0000;
    public static void incScore (int points) {
        score += points;
    }

    // GFX PROPERTIES;

    private SimpleGfxGrid gfxMap;

    // CONSTRUCTOR

    public Game() {
    }

    public Game (int cols, int rows, int delay) {
        map = new Grid(cols, rows);
        gfxMap = new SimpleGfxGrid(cols, rows);
        asteroidField = new Asteroid[30];

        player = new XWing(map);
        handler = player;

        control = new Keyboard(player);

        this.delay = delay;

        KeyboardEvent moveUp = new KeyboardEvent();
        KeyboardEvent moveDown = new KeyboardEvent();
        KeyboardEvent moveBack = new KeyboardEvent();
        KeyboardEvent moveFor = new KeyboardEvent();

        moveUp.setKey(KeyboardEvent.KEY_UP);
        moveDown.setKey(KeyboardEvent.KEY_DOWN);
        moveBack.setKey(KeyboardEvent.KEY_LEFT);
        moveFor.setKey(KeyboardEvent.KEY_RIGHT);

        moveUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        moveDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        moveBack.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        moveFor.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        control.addEventListener(moveUp);
        control.addEventListener(moveDown);
        control.addEventListener(moveBack);
        control.addEventListener(moveFor);

    }


    public void reduceDelay() {
        delay -= 50;
    }

    public void start() throws InterruptedException {

        while (true) {

            // Pause for a while
            Thread.sleep(delay);

            genAst();
            moveAll();
            // game delay ();
            colCheck();
            expManager();
            astScore();


            System.out.println(score);


            // if (score >= 200) { reduceDelay(); }
        }

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

    private void incAstCooldown (int number) {
        astCooldown = (int) (2 + Math.random()*number);
    }

    private void reduceCooldown (int number) {
        astCooldown -= number;
    }

    private void moveAll () {
        // asteroid.move();
        moveAllAst();



    }


    public void moveAllAst () {
        for (Asteroid ast : asteroidField) {
            if (ast != null) {
                ast.move();
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


    // CLASS END
}

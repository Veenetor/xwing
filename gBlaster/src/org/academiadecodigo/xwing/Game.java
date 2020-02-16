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
import org.academiadecodigo.simplegraphics.graphics.Color;


import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;


public class Game  implements KeyboardHandler {

    private Grid map;
    private XWing player;
    private KeyboardHandler handler;
    private Keyboard control;
    private Keyboard endControl;
    KeyboardHandler endHandler;
    private int delay;
    private Asteroid[] asteroidField;
    private int astCooldown;
    private TieFighter[] tieFleet;
    private Text text;
    private Text endScore;
    private Text bestScore;
    private int level;
    private File audioGameOver;
    private boolean gameOverB;

    // static

    private int score = 00000;
    private int updatedScore;
    public int incScore (int points) {
        score += points;
        return updatedScore = score;
    }

    public int getScore(){
        return score;
    }

    // GFX PROPERTIES;

    private SimpleGfxGrid gfxMap;

    // CONSTRUCTOR

    public Game (int cols, int rows, int delay) {
        map = new Grid(cols, rows);
        gfxMap = new SimpleGfxGrid(cols, rows);
        asteroidField = new Asteroid[30];
        tieFleet = new TieFighter[3];

        player = new XWing(map);
        handler = player;

        control = new Keyboard(player);

        this.delay = delay;

        // MOVEMENT
        KeyboardEvent moveUp = new KeyboardEvent();
        KeyboardEvent moveDown = new KeyboardEvent();
        KeyboardEvent moveBack = new KeyboardEvent();
        KeyboardEvent moveFor = new KeyboardEvent();
        KeyboardEvent startGame = new KeyboardEvent();
        KeyboardEvent endGame = new KeyboardEvent();

        moveUp.setKey(KeyboardEvent.KEY_UP);
        moveDown.setKey(KeyboardEvent.KEY_DOWN);
        moveBack.setKey(KeyboardEvent.KEY_LEFT);
        moveFor.setKey(KeyboardEvent.KEY_RIGHT);
        startGame.setKey(KeyboardEvent.KEY_SPACE);
        endGame.setKey(KeyboardEvent.KEY_Q);
        endGame.setKey(KeyboardEvent.KEY_R);

        moveUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        moveDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        moveBack.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        moveFor.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        startGame.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        startGame.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        endGame.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        control.addEventListener(moveUp);
        control.addEventListener(moveDown);
        control.addEventListener(moveBack);
        control.addEventListener(moveFor);
        control.addEventListener(startGame);
        control.addEventListener(endGame);

        // SHOOT CONTROLS

        KeyboardEvent fireX = new KeyboardEvent();

        fireX.setKey(KeyboardEvent.KEY_F);

        fireX.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        // addEventListener();

        LoadGame loadGame = new LoadGame(); // instancia Game loader
        //loadGame.loadGame();


        //  PRINT SCORE BASIS!!!!

        text = new Text(1170, 730, "%05d" + "Score");
        text.grow(13,13);
        text.setColor(Color.WHITE);
        text.draw();
        //text.setText(String.format("Score: "+updatedScore));

        // Audio Resources

        audioGameOver = new File("/Users/codecadet/Documents/AndreGoncalves/dev/game-blasters/xwing/gBlaster/src/resources/audio/space-invaders.wav");

    }

    public void reduceDelay() {
        delay -= 50;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (KeyboardEvent.KEY_R == keyboardEvent.getKey()) {
            try {
                start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        if (KeyboardEvent.KEY_Q == keyboardEvent.getKey()) {
            System.exit(0);
            return;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    private class LoadGame implements KeyboardHandler{
        private Picture picture = new Picture(10, 10, "images/background.jpg");
        public void loadGame() {
            picture.draw();
        }
        @Override
        public void keyPressed(KeyboardEvent keyboardEvent) {
            if(KeyboardEvent.KEY_SPACE == keyboardEvent.getKey()){
                try {
                    start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {
            if(KeyboardEvent.KEY_SPACE == keyboardEvent.getKey()){
                picture.delete();
            }
        }
    }

    public void start() throws InterruptedException {

        while (true) {

            System.out.println(score);

            // Pause for a while
            Thread.sleep(delay);

            genAst();
            //audioDuringGame;
            if (player.isDestroyed() == true){
                gameOver();
            }

            if (score > 1500) {
                genTie();
            }
            moveAll();
            // game delay ();
            colCheck();
            expManager();
            astScore();

            text.setText(String.format("Score: "+updatedScore));

            // if (score >= 200) { reduceDelay(); }
        }

    }


    public void gameOver() throws InterruptedException {
                Thread.sleep(500);
                Picture gameOver = new Picture(10, 10, "images/gameover.png");
                gameOver.draw();
                audio(audioGameOver);

                endScore = new Text(1000, 700, "%05d" + "Score");
                endScore.grow(15,15);
                endScore.setColor(Color.WHITE);
                endScore.setText(String.format("Your final Score was: " + updatedScore));
                endScore.draw();

                endScore = new Text(1000, 730, "%05d" + "Score");
                endScore.grow(15,15);
                endScore.setColor(Color.WHITE);
                endScore.setText(String.format("Your best score is: " + bestScore));
                endScore.draw();

                //Thread.sleep(2000); // System.exit()
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

    private void colCheck () throws InterruptedException {

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

        public int mvUP = 0, mvDown = 0, mVleft = 0, mVright = 0;
        Clip audioClipIntro;
        Clip audioClipEndGame;
        Clip audioClipGameOver;

        public void audio(File audioFile){

            try{
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                audioClipGameOver.open(audioStream);
                audioClipGameOver.start();

            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }



    /*
    Implementar

    public difIncrease(){


    if (score % X == x a nossa escolha, entao level++)

    public extraMove()

    for (int i =0; i< level; i++){
    moveAllAst
    colCheck
    verificar genAst
    }


     */


    // CLASS END

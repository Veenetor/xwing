package org.academiadecodigo.xwing;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
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
import java.io.File;


public class Game  /*implements KeyboardHandler*/ {

    private Grid map;
    private int delay;
    private int astCooldown;
    private int level;
    private boolean gameOverB;
    private boolean gameStarted = false;
    private Picture startMenu = new Picture(10,10, "gBlaster/resources/images/init.jpg");
    private Grid grid;
    private int rows;
    private int cols;
    private Rectangle rect;

    // Game Object Properties

    private XWing player;
    private Asteroid[] asteroidField;
    private TieFighter[] tieFleet;

    //Text Properties

    private Text text;
    private Text endScore;

    // Score Properties

    private int score = 0;
    private int updatedScore;
    private int bestScore;

    // Keyboard Properties

    private KeyboardHandler handler;
    private Keyboard control;
    private Keyboard keyboard;
    private Keyboard endControl;
    private KeyboardHandler endHandler;

    // Audio Properties

    private File audioGameOver;
    private File audioStartMenu;
    private File audioLaserShots;
    private File audioLoser;
    private File audioDuringGame;
    private File audioButton;

    // GFX PROPERTIES;

    private SimpleGfxGrid gfxMap;

    // CONSTRUCTOR

    public Game(int cols, int rows, int delay) {

        this.cols = cols;
        this.rows = rows;
        this.delay = delay;
        //asteroidField = new Asteroid[30];
        //tieFleet = new TieFighter[3];

        // KeyBoard Stuff

        // player = new XWing(map);
        handler = new KeyBoardHandling(this);
        keyboard = new Keyboard(handler);
        initListner();
        startListner();

/*        //  Print Score

        text = new Text(1140, 715, "%05d" + score);
        text.grow(13, 13);
        text.setColor(Color.WHITE);
        text.draw();*/

        // Audio Resources
/*
        audioLaserShots = new File("Users/codecadet/Documents/AndreGoncalves/dev/game-blasters/xwing/gBlaster/src/resources/audio/shoot.wav");
        audioLoser = new File("Users/codecadet/Documents/AndreGoncalves/dev/game-blasters/xwing/gBlaster/src/resources/audio/loser.wav");
        audioButton = new File("Users/codecadet/Documents/AndreGoncalves/dev/game-blasters/xwing/gBlaster/src/resources/audio/button.wav");

        // Falta:
        audioGameOver = new File("/Users/codecadet/Documents/AndreGoncalves/dev/game-blasters/xwing/gBlaster/src/resources/audio/starwars.wav");
        audioDuringGame = new File("Users/codecadet/Documents/AndreGoncalves/dev/game-blasters/xwing/gBlaster/src/resources/audio/");
        audioStartMenu = new File("Users/codecadet/Documents/AndreGoncalves/dev/game-blasters/xwing/gBlaster/src/resources/audio/");
  */  }

    // Score Methods

    public int incScore(int points) {
        score += points;
        return updatedScore = score;
    }

    public int getScore() {
        return score;
    }

    public int setBestScore(){

        if (updatedScore <= bestScore){
            return bestScore = updatedScore;
        }

        return score;
    }

    public int getBestScore(){
        return bestScore;
    }

    // ------------------------- INIT ----------------------------------

    public void init() throws InterruptedException {

        //startMenu = new Picture(10,10,"images/init.jpg");
        startMenu.draw();
        //Thread.sleep(1000);
        //startMenu.delete();
        //start();


        /*Rectangle rect = new Rectangle(10,10,1260,760);
        rect.setColor(Color.BLACK);
        rect.fill();
        rect.draw();
        Thread.sleep(200);*/
    }

    // ----------------------------START---------------------------


    public void start() throws InterruptedException {

        System.out.println("suppppp");
        //gameStarted = true;

        map = new Grid(this.cols, this.rows);
        gfxMap = new SimpleGfxGrid(this.cols, this.rows);
        startMenu.delete();
        gfxMap.getBackground();

        player = new XWing(map);
        asteroidField = new Asteroid[30];
        tieFleet = new TieFighter[3];


        text = new Text(1140, 715, "%05d" + score);
        text.grow(13, 13);
        text.setColor(Color.WHITE);
        text.draw();


        while(true) {

            if (player.isDestroyed() == true) {
                gameOver();
                break;
            }
            // Pause for a while
            Thread.sleep(delay);

            genAst();

            //audioDuringGame;

            if (score > 1500) {
                genTie();
            }
            moveAll();
            // game delay ();
            colCheck();
            expManager();
            astScore();

            text.setText(String.format("Score: " + updatedScore));

        }
    }

    // -------------------------END START---------------------------

    public Picture getStartMenu(){
        startMenu.draw();
        return startMenu;
    }

    public void deleteStartMenu(){
        startMenu.delete();
    }



    public void gameOver() throws InterruptedException {
        Thread.sleep(500);
        Picture gameOver = new Picture(10, 10, "resources/images/gameover.png");
        gameOver.draw();
        //gfxMap.deleteBackground();
        setBestScore();

        System.out.println(bestScore);

        audio(audioGameOver);

        endScore = new Text(1000, 670, "%05d" + "Score");
        endScore.grow(15, 15);
        endScore.setColor(Color.WHITE);
        endScore.setText(String.format("Your final Score was: " + updatedScore));
        endScore.draw();

        // Meter a funcionar
        /*
        endScore = new Text(1000, 700, "%05d" + "Score");
        endScore.grow(15, 15);
        endScore.setColor(Color.WHITE);
        endScore.setText(String.format("Your best score is: " + getBestScore()));
        endScore.draw();
        gameOverB = true;
        */
    }

    public void reduceDelay() {
        delay -= 50;
    }

    private void genAst() {

        if (astCooldown > 0) {
            reduceCooldown(1);

        } else {

            int astRoll = (int) Math.floor(Math.random() * map.getRows());
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

    private void genTie() {

        int tieRoll = (int) Math.floor(Math.random() * 6);

        if (tieRoll > 1) {
            for (int t = 0; t < tieFleet.length; t++) {

                if (tieFleet[t] == null) {
                    tieFleet[t] = new TieFighter(ObjectType.TIE, map, t);
                    return;
                }

            }
        }
    }

    private void incAstCooldown(int number) {
        astCooldown = (int) (2 + Math.random() * number);
    }

    private void reduceCooldown(int number) {
        astCooldown -= number;
    }

    private void moveAll() {
        // asteroid.move();
        moveAllAst();
        moveAllTie();

    }


    private void moveAllAst() {
        for (Asteroid ast : asteroidField) {
            if (ast != null) {
                ast.move();
            }
        }
    }

    private void moveAllTie() {
        for (TieFighter tie : tieFleet) {
            if (tie != null) {
                tie.move();
            }
        }
    }


    private void expManager() {

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


    private void astScore() {

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

    private void setGameOverBoolean(boolean gameOverB) {
        this.gameOverB = gameOverB;
    }

    private boolean isGameOverB() {
        return gameOverB;
    }

    private void colCheck() throws InterruptedException {

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

    public Grid getMap() {
        return map;
    }

    public XWing getPlayer() {
        return player;
    }

    public int mvUP = 0, mvDown = 0, mVleft = 0, mVright = 0;
    Clip audioClipIntro;
    Clip audioClipEndGame;
    Clip audioClipGameOver;

    public void audio(File audioFile) {

        try {
            //InputStream audioscr = getClass().getResourceAsStream("/Users/codecadet/Documents/AndreGoncalves/dev/game-blasters/xwing/gBlaster/src/resources/audio/space-invaders.wav");
            //InputStream bufferedIn = new BufferedInputStream(audioscr);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClipGameOver = (Clip) AudioSystem.getLine(info);
            audioClipGameOver.open(audioStream);
            audioClipGameOver.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void initListner() {
        KeyboardEvent startGame = new KeyboardEvent();
        startGame.setKey(KeyboardEvent.KEY_SPACE);
        startGame.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(startGame);
    }

    public void startListner() {
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

        // play another game
        KeyboardEvent restart = new KeyboardEvent();
        restart.setKey(KeyboardEvent.KEY_R);
        restart.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(restart);

        // quit
        KeyboardEvent quit = new KeyboardEvent();
        quit.setKey(KeyboardEvent.KEY_Q);
        quit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(quit);

    }


   /* @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }*/

    public boolean getGameStarted(){
        return gameStarted;
    }

    public void setGameStarted (){
        gameStarted = true;
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

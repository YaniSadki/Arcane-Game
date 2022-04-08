package main;

import main.gamestates.Action;
import main.gamestates.GamesStates;
import main.gamestates.Player1;
import main.gamestates.Player2;

import java.awt.*;

public class Game implements Runnable{
    //Game stuff
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET=120;
    private final int UPS_SET=200;
    //Game states
    private Player1 player1=new Player1(this);
    private Player2 player2=new Player2(this);
    private Action action=new Action(this);

    public Game(){
        GamesStates.gameState=GamesStates.PLAYER1;
        getPlayer1().initButtons();
        getPlayer1().initClasses();
        getPlayer2().initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    //Starting the thread!
    private void startGameLoop(){
        gameThread=new Thread(this);
        gameThread.start();
    }

    //Update the entire logical / visual
    public void update(){
        if (GamesStates.gameState != GamesStates.ACTION){
            getPlayer1().update();
            getPlayer2().update();
        }
        else
            getAction().update();
    }
    public void render(Graphics g){
        switch (GamesStates.gameState){
            case PLAYER1:
                getPlayer1().draw(g);
                break;
            case PLAYER2:
                getPlayer2().draw(g);
                break;
            case ACTION:
            default:
                break;
        }
        getPlayer1().getPlayerVi().render(g);
        getPlayer1().getPlayerJayce().render(g);
        getPlayer1().getPlayerViktor().render(g);
        getPlayer1().getPlayerCaitlyn().render(g);
        getPlayer2().getPlayerSevika().render(g);
        getPlayer2().getPlayerSinged().render(g);
        getPlayer2().getPlayerSilco().render(g);
        getPlayer2().getPlayerJinx().render(g);
    }

    //FPS/UPS check + PANEL update
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime=System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime=System.nanoTime();

            deltaU+=(currentTime - previousTime)/timePerUpdate;
            deltaF+=(currentTime - previousTime)/timePerFrame;
            previousTime=currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }
            if (deltaF>=1){
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    //Getters
    public Player1 getPlayer1() {return player1;}
    public Player2 getPlayer2(){return player2;}
    public Action getAction() {return action;}
}

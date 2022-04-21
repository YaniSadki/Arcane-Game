package main;

import main.gamestates.*;
import style.ManaBar;
import style.Sound;

import java.awt.*;

public class Game implements Runnable{
    //Game stuff
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private ManaBar manaBar;
    private Sound sound;
    private Thread gameThread;
    private final int FPS_SET=120;
    private final int UPS_SET=200;
    //Game states
    private Player1 player1;
    private Player2 player2;
    private Action action;
    private End end;
    private Start start;

    public static int[][] effectRoundLeft;
    public static float[][] effectRatio;

    public static int manaPlayer1;
    public static int manaPlayer2;

    public static int ROUND=1;

    public Game(){
        player1=new Player1(this);
        player2=new Player2(this);
        action=new Action(this);
        end=new End(this);
        start = new Start(this);
        manaBar = new ManaBar(this);
        manaBar.loadImage();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        getPlayer1().initClasses();
        getPlayer2().initClasses();
        initEffectTables();
        getPlayer1().initButtons();

        GamesStates.gameState=GamesStates.START;

        gamePanel.requestFocus();
        start.update();
        startGameLoop();
    }

    //Starting the thread!
    private void startGameLoop(){
        gameThread=new Thread(this);
        gameThread.start();
    }

    //Update the entire logical / visual
    public void update(){
        if ((GamesStates.gameState != GamesStates.ACTION) && (GamesStates.gameState != GamesStates.START) && (GamesStates.gameState != GamesStates.END)){
            getPlayer1().update();
            getPlayer2().update();
        }
        else if (GamesStates.gameState == GamesStates.ACTION)
            getAction().update();
    }
    public void render(Graphics g){
        switch (GamesStates.gameState){
            case START:
                getStart().draw(g);
                break;
            case PLAYER1:
                getPlayer1().draw(g);
                break;
            case PLAYER2:
                getPlayer2().draw(g);
                break;
            case ACTION:
                break;
            case END:
            default:
                getEnd().draw(g);
                break;
        }
        if (GamesStates.gameState != GamesStates.START) {
            getPlayer1().getPlayerVi().render(g);
            getPlayer1().getPlayerJayce().render(g);
            getPlayer1().getPlayerViktor().render(g);
            getPlayer1().getPlayerCaitlyn().render(g);
            getPlayer2().getPlayerSevika().render(g);
            getPlayer2().getPlayerSinged().render(g);
            getPlayer2().getPlayerSilco().render(g);
            getPlayer2().getPlayerJinx().render(g);
        }
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

    public int winningTeam(){
        if (player1.getPlayerVi().health<=0 && player1.getPlayerJayce().health<=0 && player1.getPlayerViktor().health<=0 && player1.getPlayerCaitlyn().health<=0){
            return 2;
        } else {
            return 1;
        }
    }

    public void initEffectTables(){
        effectRatio=new float[8][4];
        effectRoundLeft=new int [8][4];
        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                effectRoundLeft[i][j]=0;
                if (j==0)
                    effectRatio[i][j]=0;
                else
                    effectRatio[i][j]=1;
            }
        }
    }

    //Getters
    public Player1 getPlayer1() {return player1;}
    public Player2 getPlayer2(){return player2;}
    public Action getAction() {return action;}
    public End getEnd() {return end;}
    public Start getStart(){return start;}

    public ManaBar getManaBar() {
        return manaBar;
    }
}

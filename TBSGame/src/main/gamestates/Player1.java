package main.gamestates;

import entities.PlayerCaitlyn;
import entities.PlayerJayce;
import entities.PlayerVi;
import entities.PlayerViktor;
import inputs.MyButton;
import inputs.MyButtonNext;
import inputs.MyButtonRect;
import main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

import static utilz.Constants.PlayerConstants.IDLE;
import static utilz.Constants.PlayerNumber.*;

public class Player1 extends State implements StateMethods {
    //Menu of each character
    private MyButton buttonVi,buttonVi2;
    private MyButtonRect buttonVi3,buttonVi4;
    private MyButton buttonJayce,buttonJayce2;
    private MyButtonRect buttonJayce3,buttonJayce4;
    private MyButton buttonViktor,buttonViktor2;
    private MyButtonRect buttonViktor3,buttonViktor4;
    private MyButton buttonCaitlyn,buttonCaitlyn2;
    private MyButtonRect buttonCaitlyn3,buttonCaitlyn4;
    private MyButtonNext buttonNext;
    //Characters
    private PlayerVi playerVi;
    private PlayerJayce playerJayce;
    private PlayerViktor playerViktor;
    private PlayerCaitlyn playerCaitlyn;
    //Actions of each character -> next state
    private int totalAction;
    public int actionVi=0, actionJayce=0, actionViktor=0, actionCaitlyn=0;
    private boolean nextReady=false;
    //Conditions
    private String attacker=new String("");
    private boolean moveAllowed=true;

    public Player1(Game game) {
        super(game);
    }

    //Initialize players and buttons
    public void initClasses() {
        Game.manaPlayer1=10;
        playerVi = new PlayerVi(VI,300,300, getGame(), "Vi",1, 100, 60, 80, 10);
        playerJayce = new PlayerJayce(JAYCE,200,300,getGame(), "Jayce", 1,100, 60, 60, 30);
        playerViktor = new PlayerViktor(VIKTOR,100,300,getGame(), "Viktor", 1,100, 60, 60, 50);
        playerCaitlyn = new PlayerCaitlyn(CAITLYN,0,300,getGame(), "Caitlyn", 1,100, 80, 40, 70);
    }
    public void initButtons() {
        int[] x=new int[3];
        int[] y=new int[3];
        x[0]=1120;
        x[1]=1160;
        x[2]=1120;
        y[0]=500;
        y[1]=520;
        y[2]=540;
        buttonNext=new MyButtonNext(x,y,3);

        buttonVi=new MyButton(1000,600,100,100,"Attack");
        buttonVi2=new MyButton(1100,600,100,100,"Guard");
        buttonVi3=new MyButtonRect(1000,700,200,50,"Special 1");
        buttonVi4=new MyButtonRect(1000,750,200,50,"Special 2");

        buttonJayce=new MyButton(700,600,100,100,"Attack");
        buttonJayce2=new MyButton(800,600,100,100,"Guard");
        buttonJayce3=new MyButtonRect(700,700,200,50,"Special 1");
        buttonJayce4=new MyButtonRect(700,750,200,50,"Special 2");

        buttonViktor=new MyButton(400,600,100,100,"Attack");
        buttonViktor2=new MyButton(500,600,100,100,"Guard");
        buttonViktor3=new MyButtonRect(400,700,200,50,"Special 1");
        buttonViktor4=new MyButtonRect(400,750,200,50,"Special 2");

        buttonCaitlyn=new MyButton(100,600,100,100,"Attack");
        buttonCaitlyn2=new MyButton(200,600,100,100,"Guard");
        buttonCaitlyn3=new MyButtonRect(100,700,200,50,"Special 1");
        buttonCaitlyn4=new MyButtonRect(100,750,200,50,"Special 2");
    }

    //Temporarily remove the buttons
    public void eraseButtons(){
        buttonVi=null;
        buttonVi2=null;
        buttonVi3=null;
        buttonVi4=null;

        buttonViktor=null;
        buttonViktor2=null;
        buttonViktor3=null;
        buttonViktor4=null;

        buttonJayce=null;
        buttonJayce2=null;
        buttonJayce3=null;
        buttonJayce4=null;

        buttonCaitlyn=null;
        buttonCaitlyn2=null;
        buttonCaitlyn3=null;
        buttonCaitlyn4=null;

    }

    //Inherited state methods
    @Override
    public void update() {
        playerVi.update();
        playerJayce.update();
        playerViktor.update();
        playerCaitlyn.update();

        totalAction=actionVi+actionJayce+actionCaitlyn+actionViktor;
    }

    @Override
    public void draw(Graphics g) {
        getGame().getManaBar().showManaBar(g);

        if (playerVi.health>0) {
            buttonVi.draw(g);       //Vi's buttons
            buttonVi2.draw(g);
            buttonVi3.draw(g);
            buttonVi4.draw(g);
            playerVi.showInfo(900,600,100,"VI",g);
        }
        if (playerJayce.health>0) {
            buttonJayce.draw(g);       //Viktor's buttons
            buttonJayce2.draw(g);
            buttonJayce3.draw(g);
            buttonJayce4.draw(g);
            playerJayce.showInfo(600, 600, 100, "JAYCE", g);
        }
        if (playerViktor.health>0) {
            buttonViktor.draw(g);       //Jayce's buttons
            buttonViktor2.draw(g);
            buttonViktor3.draw(g);
            buttonViktor4.draw(g);
            playerViktor.showInfo(300, 600, 100, "VIKTOR", g);
        }
        if (playerCaitlyn.health>0){
            buttonCaitlyn.draw(g);       //Caitlyn's buttons
            buttonCaitlyn2.draw(g);
            buttonCaitlyn3.draw(g);
            buttonCaitlyn4.draw(g);
            playerCaitlyn.showInfo(0,600,100,"CAITLYN",g);
        }

        buttonNext.draw(g);     //Next state button

        if (!moveAllowed){
            g.setFont(new Font("Times New Roman",Font.PLAIN,28));
            g.drawString("Select your target!",480,580);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        playerVi.mouseClicked(x, y);
        playerJayce.mouseClicked(x, y);
        playerViktor.mouseClicked(x, y);
        playerCaitlyn.mouseClicked(x, y);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        buttonNext.setMouseClicked(false);
        if (nextReady){
            getGame().getPlayer2().initButtons();
            eraseButtons();
            GamesStates.gameState = GamesStates.PLAYER2;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        boolean boolNext=getButtonNext().getBounds().contains(x,y);
        if (boolNext){
            getButtonNext().setMouseClicked(true);
            if (totalAction==4)
                nextReady=true;
        }
    }

    //Setters
    public void setActionVi(int actionVi) {this.actionVi = actionVi;}
    public void setActionJayce(int actionJayce) {this.actionJayce = actionJayce;}
    public void setActionViktor(int actionViktor) {this.actionViktor = actionViktor;}
    public void setActionCaitlyn(int actionCaitlyn) {this.actionCaitlyn = actionCaitlyn;}

    public void setMoveAllowed(boolean moveAllowed) {this.moveAllowed = moveAllowed;}
    public void setAttacker(String attacker) {this.attacker = attacker;}
    //Getters
    public boolean isMoveAllowed() {return moveAllowed;}

    public PlayerVi getPlayerVi(){return playerVi;}
    public PlayerJayce getPlayerJayce() {return playerJayce;}
    public PlayerViktor getPlayerViktor() {return playerViktor;}
    public PlayerCaitlyn getPlayerCaitlyn() {return playerCaitlyn;}

    public MyButtonNext getButtonNext(){return buttonNext;}
    public MyButton getButtonVi() {return buttonVi;}
    public MyButton getButtonVi2() {return buttonVi2;}
    public MyButtonRect getButtonVi3() {return buttonVi3;}
    public MyButtonRect getButtonVi4() {return buttonVi4;}
    public MyButton getButtonJayce() {return buttonJayce;}
    public MyButton getButtonJayce2() {return buttonJayce2;}
    public MyButtonRect getButtonJayce3() {return buttonJayce3;}
    public MyButtonRect getButtonJayce4() {return buttonJayce4;}
    public MyButton getButtonViktor() {return buttonViktor;}
    public MyButton getButtonViktor2() {return buttonViktor2;}
    public MyButtonRect getButtonViktor3() {return buttonViktor3;}
    public MyButtonRect getButtonViktor4() {return buttonViktor4;}
    public MyButton getButtonCaitlyn() {return buttonCaitlyn;}
    public MyButton getButtonCaitlyn2() {return buttonCaitlyn2;}
    public MyButtonRect getButtonCaitlyn3() {return buttonCaitlyn3;}
    public MyButtonRect getButtonCaitlyn4() {return buttonCaitlyn4;}

    public String getAttacker() {return attacker;}

    public int getTotalAction() {
        return totalAction;
    }
    public void setNextReady(boolean nextReady) {this.nextReady=nextReady;}
}

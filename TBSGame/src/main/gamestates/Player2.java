package main.gamestates;

import entities.*;
import inputs.MyButton;
import inputs.MyButtonNext;
import inputs.MyButtonRect;
import main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import static utilz.Constants.PlayerNumber.*;

public class Player2 extends State implements StateMethods {
    //Menu of each character
    private MyButton buttonSevika,buttonSevika2;
    private MyButtonRect buttonSevika3,buttonSevika4;
    private MyButton buttonSinged,buttonSinged2;
    private MyButtonRect buttonSinged3,buttonSinged4;
    private MyButton buttonSilco,buttonSilco2;
    private MyButtonRect buttonSilco3,buttonSilco4;
    private MyButton buttonJinx,buttonJinx2;
    private MyButtonRect buttonJinx3,buttonJinx4;
    private MyButtonNext buttonNext;
    //Characters
    private PlayerSevika playerSevika;
    private PlayerSinged playerSinged;
    private PlayerSilco playerSilco;
    private PlayerJinx playerJinx;
    //Actions of each character -> next state
    private int totalAction;
    public int actionSevika=0, actionSinged=0, actionSilco=0, actionJinx=0;
    private boolean nextReady=false;
    //Conditions
    private String attacker=new String("");
    private boolean moveAllowed=true;

    public Player2(Game game) {
        super(game);
    }

    //Initialize players and buttons
    public void initClasses() {
        Game.manaPlayer2=10;
        playerSevika = new PlayerSevika(SEVIKA,600,300, getGame(), "Sevika", 2,100,60,80,20);
        playerSinged = new PlayerSinged(SINGED,700,300,getGame(), "Singed", 2, 100, 60, 60, 40);
        playerSilco = new PlayerSilco(SILCO,800,300,getGame(), "Silco", 2,100,60,50,60);
        playerJinx = new PlayerJinx(JINX,900,300,getGame(), "Jinx", 2, 100, 80, 40, 80);
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
        buttonSevika=new MyButton(100,600,100,100,"Attack");
        buttonSevika2=new MyButton(200,600,100,100,"Guard");
        buttonSevika3=new MyButtonRect(100,700,200,50,"Special 1");
        buttonSevika4=new MyButtonRect(100,750,200,50,"Special 2");

        buttonSinged=new MyButton(400,600,100,100,"Attack");
        buttonSinged2=new MyButton(500,600,100,100,"Guard");
        buttonSinged3=new MyButtonRect(400,700,200,50,"Special 1");
        buttonSinged4=new MyButtonRect(400,750,200,50,"Special 2");

        buttonSilco=new MyButton(700,600,100,100,"Attack");
        buttonSilco2=new MyButton(800,600,100,100,"Guard");
        buttonSilco3=new MyButtonRect(700,700,200,50,"Special 1");
        buttonSilco4=new MyButtonRect(700,750,200,50,"Special 2");

        buttonJinx=new MyButton(1000,600,100,100,"Attack");
        buttonJinx2=new MyButton(1100,600,100,100,"Guard");
        buttonJinx3=new MyButtonRect(1000,700,200,50,"Special 1");
        buttonJinx4=new MyButtonRect(1000,750,200,50,"Special 2");
    }

    //Temporarily removes the buttons
    public void eraseButtons(){
        buttonSevika=null;
        buttonSevika2=null;
        buttonSevika3=null;
        buttonSevika4=null;

        buttonSinged=null;
        buttonSinged2=null;
        buttonSinged3=null;
        buttonSinged4=null;

        buttonSilco=null;
        buttonSilco2=null;
        buttonSilco3=null;
        buttonSilco4=null;

        buttonJinx=null;
        buttonJinx2=null;
        buttonJinx3=null;
        buttonJinx4=null;

    }

    //Inherited state methods
    @Override
    public void update() {
        playerSevika.update();
        playerSinged.update();
        playerSilco.update();
        playerJinx.update();

        totalAction=actionSevika+actionSinged+actionSilco+actionJinx;
    }

    @Override
    public void draw(Graphics g) {
        getGame().getManaBar().showManaBar(g);

        if (playerSevika.health>0) {
            buttonSevika.draw(g);       //Sevika's buttons
            buttonSevika2.draw(g);
            buttonSevika3.draw(g);
            buttonSevika4.draw(g);
            playerSevika.showInfo(0, 600, 100, "SEVIKA", g);
        }
        if (playerSinged.health>0) {
            buttonSinged.draw(g);       //Singed's buttons
            buttonSinged2.draw(g);
            buttonSinged3.draw(g);
            buttonSinged4.draw(g);
            playerSinged.showInfo(300, 600, 100, "SINGED", g);
        }
        if (playerSilco.health>0) {
            buttonSilco.draw(g);       //Silco's buttons
            buttonSilco2.draw(g);
            buttonSilco3.draw(g);
            buttonSilco4.draw(g);
            playerSilco.showInfo(600, 600, 100, "SILCO", g);
        }
        if (playerJinx.health>0) {
            buttonJinx.draw(g);       //Jinx's buttons
            buttonJinx2.draw(g);
            buttonJinx3.draw(g);
            buttonJinx4.draw(g);
            playerJinx.showInfo(900, 600, 100, "JINX", g);
        }

        buttonNext.draw(g);       //Next state button

        if (!moveAllowed){
            g.setFont(new Font("Times New Roman",Font.PLAIN,28));
            g.drawString("Select your target!",480,580);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        playerSevika.mouseClicked(x, y);
        playerSinged.mouseClicked(x, y);
        playerSilco.mouseClicked(x, y);
        playerJinx.mouseClicked(x, y);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        buttonNext.setMouseClicked(false);
        if (nextReady){
            eraseButtons();
            getGame().getAction().initClasses();
            GamesStates.gameState = GamesStates.ACTION;
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
    public void setActionSevika(int actionSevika) {this.actionSevika = actionSevika;}
    public void setActionSinged(int actionSinged) {this.actionSinged = actionSinged;}
    public void setActionSilco(int actionSilco) {this.actionSilco = actionSilco;}
    public void setActionJinx(int actionJinx) {this.actionJinx = actionJinx;}

    public void setMoveAllowed(boolean moveAllowed) {this.moveAllowed = moveAllowed;}
    public void setAttacker(String attacker) {this.attacker = attacker;}

    //Getters
    public boolean isMoveAllowed() {return moveAllowed;}

    public PlayerSevika getPlayerSevika(){return playerSevika;}
    public PlayerSinged getPlayerSinged() {return playerSinged;}
    public PlayerSilco getPlayerSilco() {return playerSilco;}
    public PlayerJinx getPlayerJinx() {return playerJinx;}

    public MyButtonNext getButtonNext(){return buttonNext;}

    public MyButton getButtonSevika() {
        return buttonSevika;
    }
    public MyButton getButtonSevika2() {
        return buttonSevika2;
    }
    public MyButtonRect getButtonSevika3() {
        return buttonSevika3;
    }
    public MyButtonRect getButtonSevika4() {
        return buttonSevika4;
    }

    public MyButton getButtonSinged() {
        return buttonSinged;
    }
    public MyButton getButtonSinged2() {
        return buttonSinged2;
    }
    public MyButtonRect getButtonSinged3() {
        return buttonSinged3;
    }
    public MyButtonRect getButtonSinged4() {
        return buttonSinged4;
    }

    public MyButton getButtonSilco() {
        return buttonSilco;
    }
    public MyButton getButtonSilco2() {
        return buttonSilco2;
    }
    public MyButtonRect getButtonSilco3() {
        return buttonSilco3;
    }
    public MyButtonRect getButtonSilco4() {
        return buttonSilco4;
    }

    public MyButton getButtonJinx() {
        return buttonJinx;
    }
    public MyButton getButtonJinx2() {
        return buttonJinx2;
    }
    public MyButtonRect getButtonJinx3() {
        return buttonJinx3;
    }
    public MyButtonRect getButtonJinx4() {
        return buttonJinx4;
    }

    public String getAttacker() {return attacker;}

    public int getTotalAction() {
        return totalAction;
    }

    public void setNextReady(boolean nextReady) {this.nextReady=nextReady;}
}

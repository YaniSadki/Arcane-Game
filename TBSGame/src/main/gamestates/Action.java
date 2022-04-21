package main.gamestates;

import entities.*;
import main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Timer;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.PlayerNumber.*;

public class Action extends State implements StateMethods{

    private PlayerVi playerVi;
    private PlayerJayce playerJayce;
    private PlayerViktor playerViktor;
    private PlayerCaitlyn playerCaitlyn;
    private PlayerSevika playerSevika;
    private PlayerSinged playerSinged;
    private PlayerSilco playerSilco;
    private PlayerJinx playerJinx;

    public int actionVi=0, actionJayce=0, actionViktor=0, actionCaitlyn=0, actionSevika=0, actionSinged=0, actionSilco=0, actionJinx=0;
    private int totalAction;

    private static Entity[] attackTarget=new Entity[8];
    private int[] speeds;

    public static boolean nextMove=true;

    public Action(Game game) {
        super(game);
    }
    //Init classes
    public void initClasses(){
        actionVi=0;
        actionJayce=0;
        actionViktor=0;
        actionCaitlyn=0;
        actionSevika=0;
        actionSinged=0;
        actionSilco=0;
        actionJinx=0;
        playerVi = getGame().getPlayer1().getPlayerVi();
        playerJayce = getGame().getPlayer1().getPlayerJayce();
        playerViktor = getGame().getPlayer1().getPlayerViktor();
        playerCaitlyn = getGame().getPlayer1().getPlayerCaitlyn();
        playerSevika = getGame().getPlayer2().getPlayerSevika();
        playerSinged = getGame().getPlayer2().getPlayerSinged();
        playerSilco = getGame().getPlayer2().getPlayerSilco();
        playerJinx = getGame().getPlayer2().getPlayerJinx();
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void update() {
        playerVi.update();
        playerJayce.update();
        playerViktor.update();
        playerCaitlyn.update();
        playerSevika.update();
        playerSinged.update();
        playerSilco.update();
        playerJinx.update();
        refreshSpeeds();
        setAction();
        totalAction=actionVi+actionJayce+actionViktor+actionCaitlyn+actionSevika+actionSinged+actionSilco+actionJinx;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    public void setAction() {
        int maxSpeed = getMax(speeds);
        if (nextMove){
            if (maxSpeed == speeds[VI] && actionVi == 0) {
                if (playerVi.getPlayerMemorized() != GROUND){
                    nextMove=false;
                    playerVi.setPlayerAction(playerVi.getPlayerMemorized());
                }
                this.speeds[VI] = 0;
                actionVi = 1;
            } else if (maxSpeed == speeds[JAYCE] && actionJayce == 0) {
                if (playerJayce.getPlayerMemorized() != GROUND){
                    nextMove=false;
                    playerJayce.setPlayerAction(playerJayce.getPlayerMemorized());
                }
                this.speeds[JAYCE] = 0;
                actionJayce = 1;
            } else if (maxSpeed == speeds[VIKTOR] && actionViktor == 0) {
                if (playerViktor.getPlayerMemorized() != GROUND){
                    nextMove=false;
                    playerViktor.setPlayerAction(playerViktor.getPlayerMemorized());
                }
                this.speeds[VIKTOR] = 0;
                actionViktor = 1;
            } else if (maxSpeed == speeds[CAITLYN] && actionCaitlyn == 0) {
                if (playerCaitlyn.getPlayerMemorized() != GROUND){
                    nextMove=false;
                    playerCaitlyn.setPlayerAction(playerCaitlyn.getPlayerMemorized());
                }
                this.speeds[CAITLYN] = 0;
                actionCaitlyn = 1;
            } else if (maxSpeed == speeds[SEVIKA] && actionSevika == 0) {
                if (playerSevika.getPlayerMemorized() != GROUND){
                    nextMove=false;
                    playerSevika.setPlayerAction(playerSevika.getPlayerMemorized());
                }
                this.speeds[SEVIKA] = 0;
                actionSevika = 1;
            } else if (maxSpeed == speeds[SINGED] && actionSinged == 0) {
                if (playerSinged.getPlayerMemorized() != GROUND){
                    nextMove=false;
                    playerSinged.setPlayerAction(playerSinged.getPlayerMemorized());
                }
                this.speeds[SINGED] = 0;
                actionSinged = 1;
            } else if (maxSpeed == speeds[SILCO] && actionSilco == 0) {
                if (playerSilco.getPlayerMemorized() != GROUND) {
                    nextMove = false;
                    playerSilco.setPlayerAction(playerSilco.getPlayerMemorized());
                }
                this.speeds[SILCO] = 0;
                actionSilco = 1;
            } else if (maxSpeed == speeds[JINX] && actionJinx == 0) {
                if (playerJinx.getPlayerMemorized() != GROUND){
                    nextMove=false;
                    playerJinx.setPlayerAction(playerJinx.getPlayerMemorized());
                }
                this.speeds[JINX] = 0;
                actionJinx = 1;
            } else if (totalAction == 8) {
                if ((playerVi.health<=0 && playerJayce.health<=0 && playerViktor.health<=0 && playerCaitlyn.health<=0) || (playerSevika.health<=0 && playerSinged.health<=0 && playerSilco.health<=0 && playerJinx.health<=0)) {
                    GamesStates.gameState = GamesStates.END;
                }else {
                    nextRound();
                    GamesStates.gameState = GamesStates.PLAYER1;
                }

            }
        }
    }

    public void refreshSpeeds(){
        speeds=new int[8];
        if (actionVi==0)
            speeds[VI]=playerVi.speed;
        if (actionJayce==0)
            speeds[JAYCE]=playerJayce.speed;
        if (actionViktor==0)
            speeds[VIKTOR]=playerViktor.speed;
        if (actionCaitlyn==0)
            speeds[CAITLYN]=playerCaitlyn.speed;
        if (actionSevika==0)
            speeds[SEVIKA]=playerSevika.speed;
        if (actionSinged==0)
            speeds[SINGED]=playerSinged.speed;
        if (actionSilco==0)
            speeds[SILCO]=playerSilco.speed;
        if (actionJinx==0)
            speeds[JINX]=playerJinx.speed;
    }

    public int getMax(int[] speeds){
        int max=0;
        for (int i=0;i<speeds.length;i++){
            if (max<speeds[i])
                max=speeds[i];
        }
        return max;
    }

    public void nextRound(){
        effectUpdate();
        statReset();
        actionReset();
        upMana();
        Game.ROUND++;
        getGame().getPlayer1().initButtons();
    }

    public void effectUpdate(){
        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                if (Game.effectRoundLeft[i][j]>0)
                    Game.effectRoundLeft[i][j]--;
                else {
                    if (j==0)
                        Game.effectRatio[i][j]=0;
                    else
                        Game.effectRatio[i][j]=1;
                }
            }
        }
    }

    public void statReset(){
        playerVi.statReset();
        playerJayce.statReset();
        playerViktor.statReset();
        playerCaitlyn.statReset();
        playerSevika.statReset();
        playerSinged.statReset();
        playerSilco.statReset();
        playerJinx.statReset();
    }

    public void actionReset(){
        getGame().getPlayer1().setActionVi(IDLE);
        getGame().getPlayer1().setActionJayce(IDLE);
        getGame().getPlayer1().setActionViktor(IDLE);
        getGame().getPlayer1().setActionCaitlyn(IDLE);
        getGame().getPlayer1().getPlayerVi().setPlayerMemorized(IDLE);
        getGame().getPlayer1().getPlayerJayce().setPlayerMemorized(IDLE);
        getGame().getPlayer1().getPlayerViktor().setPlayerMemorized(IDLE);
        getGame().getPlayer1().getPlayerCaitlyn().setPlayerMemorized(IDLE);

        getGame().getPlayer1().setActionVi(0);
        getGame().getPlayer1().setActionJayce(0);
        getGame().getPlayer1().setActionViktor(0);
        getGame().getPlayer1().setActionCaitlyn(0);
        getGame().getPlayer1().setNextReady(false);

        getGame().getPlayer2().setActionSevika(IDLE);
        getGame().getPlayer2().setActionSilco(IDLE);
        getGame().getPlayer2().setActionSinged(IDLE);
        getGame().getPlayer2().setActionJinx(IDLE);
        getGame().getPlayer2().getPlayerSevika().setPlayerMemorized(IDLE);
        getGame().getPlayer2().getPlayerSinged().setPlayerMemorized(IDLE);
        getGame().getPlayer2().getPlayerSilco().setPlayerMemorized(IDLE);
        getGame().getPlayer2().getPlayerJinx().setPlayerMemorized(IDLE);

        getGame().getPlayer2().setActionSevika(0);
        getGame().getPlayer2().setActionSinged(0);
        getGame().getPlayer2().setActionSilco(0);
        getGame().getPlayer2().setActionJinx(0);
        getGame().getPlayer2().setNextReady(false);
    }

    public void upMana(){
        int turn=Game.ROUND;
        if (turn <=3){
            if (Game.manaPlayer1+turn<=10)
                Game.manaPlayer1+=turn;
            else
                Game.manaPlayer1=10;
            if (Game.manaPlayer2+turn<=10)
                Game.manaPlayer2+=turn;
            else
                Game.manaPlayer2=10;
        }
        else {
            if (Game.manaPlayer1+3<=10)
                Game.manaPlayer1+=3;
            else
                Game.manaPlayer1=10;
            if (Game.manaPlayer2+3<=10)
                Game.manaPlayer2+=3;
            else
                Game.manaPlayer2=10;
        }
    }

    public void setAttackTarget(Entity target, int i) {attackTarget[i]=target;}

    public static Entity[] getAttackTarget() {return attackTarget;}
}

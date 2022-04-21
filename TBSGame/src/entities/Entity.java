package entities;

import main.Game;
import main.gamestates.GamesStates;

import java.awt.*;


import static utilz.Constants.PlayerConstants.IDLE;
import static utilz.Constants.PlayerNumber.*;

public class Entity {

    protected int id;
    protected float x,y;
    protected Rectangle hitbox;
    protected Game game;
    protected int team;
    public String name;
    protected int playerAction = IDLE;
    //Logical variables
    public int health,atk,def,speed;
    public final int MAXHEALTH,BASEATK,BASEDEF,BASESPEED;


    public Entity(int id, float x, float y, Game game, String name, int team, int health, int atk, int def, int speed){
        this.id=id;
        this.x=x;
        this.y=y;
        this.hitbox=new Rectangle((int) (x+80),(int) (y+15),96,116);
        this.game=game;
        this.name=name;
        this.team=team;
        this.health=health;
        this.atk=atk;
        this.def=def;
        this.speed=speed;
        this.MAXHEALTH=health;
        this.BASEATK=atk;
        this.BASEDEF=def;
        this.BASESPEED=speed;
    }

    //Logical stuff
    public void takeEffect(float dmg, float atkRatio, float defRatio, float speedRatio){
        if (health-dmg>MAXHEALTH)
            health=MAXHEALTH;
        else
            health-=dmg;
        atk*=atkRatio;
        def*=defRatio;
        speed*=speedRatio;
    }
    public void spriteClicked(int x, int y){
        if (health>0) {
            if (!game.getPlayer1().isMoveAllowed() || !game.getPlayer2().isMoveAllowed()) {
                if (hitbox.contains(x, y)) {
                    if (GamesStates.gameState == GamesStates.PLAYER1) {
                        switch (game.getPlayer1().getAttacker()) {
                            case "Vi":
                                game.getPlayer1().setActionVi(1);
                                game.getAction().setAttackTarget(this, VI);
                                game.getPlayer1().setMoveAllowed(true);
                                break;
                            case "Jayce":
                                game.getPlayer1().setActionJayce(1);
                                game.getAction().setAttackTarget(this, JAYCE);
                                game.getPlayer1().setMoveAllowed(true);
                                break;
                            case "Viktor":
                                game.getPlayer1().setActionViktor(1);
                                game.getAction().setAttackTarget(this, VIKTOR);
                                game.getPlayer1().setMoveAllowed(true);
                                break;
                            case "Caitlyn":
                            default:
                                game.getPlayer1().setActionCaitlyn(1);
                                game.getAction().setAttackTarget(this, CAITLYN);
                                game.getPlayer1().setMoveAllowed(true);
                                break;
                        }
                    }
                    if (GamesStates.gameState == GamesStates.PLAYER2) {
                        switch (game.getPlayer2().getAttacker()) {
                            case "Sevika":
                                game.getPlayer2().setActionSevika(1);
                                game.getAction().setAttackTarget(this, SEVIKA);
                                game.getPlayer2().setMoveAllowed(true);
                                break;
                            case "Singed":
                                game.getPlayer2().setActionSinged(1);
                                game.getAction().setAttackTarget(this, SINGED);
                                game.getPlayer2().setMoveAllowed(true);
                                break;
                            case "Silco":
                                game.getPlayer2().setActionSilco(1);
                                game.getAction().setAttackTarget(this, SILCO);
                                game.getPlayer2().setMoveAllowed(true);
                                break;
                            case "Jinx":
                            default:
                                game.getPlayer2().setActionJinx(1);
                                game.getAction().setAttackTarget(this, JINX);
                                game.getPlayer2().setMoveAllowed(true);
                                break;
                        }
                    }
                }
            }
        }
    }

    //Visual stuff
    public void showHealthBar(int x, int y, int MAXHEALTH, Graphics g){
        int percent = (health*60)/MAXHEALTH;
        if (health > 0){
            g.setColor(Color.black);
            g.setFont(new Font("Book Antiqua",Font.PLAIN,14));
            g.drawString(name,x+105,y-5);
            g.fillRect(x+98,y-1,62,10);
            if (team==1) {
                g.setColor(Color.CYAN);
            }
            else
                g.setColor(Color.RED);
            g.fillRect(x+99,y,percent,8);
            g.setColor(Color.black);
        }
    }

    public void showInfo(int x, int y, int MAXHEALTH, String name, Graphics g){
        g.setFont(new Font("Times New Roman",Font.BOLD,20));
        g.drawString(name,x+6,y+40);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("HP: "+health+"/"+MAXHEALTH,x+6,y+80);
        g.drawString("ATK: "+atk,x+6,y+110);
        g.drawString("DEF: "+def,x+6,y+140);
        g.drawString("SPE: "+speed,x+6,y+170);
    }

    public void statReset(){
        if (health-Game.effectRatio[id][0]>MAXHEALTH)
            health=MAXHEALTH;
        else
            health-=Game.effectRatio[id][0];

        atk=(int) (BASEATK*Game.effectRatio[id][1]);
        def=(int) (BASEDEF*Game.effectRatio[id][2]);
        speed=(int) (BASESPEED*Game.effectRatio[id][3]);
    }

    public int getPlayerAction() {
        return playerAction;
    }

    public void setPlayerAction(int playerAction) {
        this.playerAction = playerAction;
    }
}

package entities;

import inputs.MyButton;
import inputs.MyButtonRect;
import main.Game;
import main.gamestates.Action;
import main.gamestates.Player1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.PlayerNumber.*;

public class PlayerVi extends Entity {
    //Links
    private Player1 player1=game.getPlayer1();
    //Logical variables

    //Visual variables
    private BufferedImage[][] animations;
    private int aniTick,aniIndex,aniSpeed=15;
    private int playerMemorized = IDLE;
    private boolean attacking;
    //Logical

    public PlayerVi(int id, float x, float y, Game game, String name, int team, int health, int atk, int def, int speed) {
        super(id, x, y, game, name, team, health,atk,def,speed);
        loadAnimations();
    }

    //Loading the sprites
    private void loadAnimations() {
        InputStream is=getClass().getResourceAsStream("/sprites/player_sprites0.png");
        try {
            BufferedImage img= ImageIO.read(is);
            animations = new BufferedImage[9][6];
            for (int j=0; j<animations.length;j++){
                for (int i=0;i<animations[j].length;i++)
                    animations[j][i]=img.getSubimage(i*64,j*40,64,40);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Update the logical, Render the animations
    public void update(){
        if (health<=0){
            player1.setActionVi(1);
            game.getAction().actionVi=1;
        }
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex],(int)x,(int)y,256,160,null);
        g.drawRect(hitbox.x,hitbox.y,hitbox.width, hitbox.height);      //Remove later
        animate();
        showHealthBar((int) x,(int) y,100,g);
    }

    //Make the animation!
    private void animate() {
        boolean b= (playerAction != IDLE) && (playerAction != GROUND);
        if (b && !attacking){
            aniIndex = 0;
            if (playerAction != HIT)
                aniSpeed=30;
            attacking=true;
        }
        aniTick++;
        if (aniTick>=aniSpeed){
            aniTick=0;
            aniIndex++;
            if (aniIndex>=GetSpriteAmount(playerAction)){
                if (b) {
                    effectAttack();
                    if (playerAction==HIT)
                        Action.nextMove=true;
                    if (playerMemorized==GROUND)
                        playerAction = GROUND;
                    else
                        playerAction = IDLE;
                    aniSpeed=15;
                    attacking=false;
                }
                aniIndex=0;
            }
        }
    }

    //Setting actions using mouse inputs
    public void mouseClicked(int x, int y) {
        memorizeAction(x,y);

    }

    public void memorizeAction(int x, int y){
        if (health>0) {
            MyButton buttonVi = player1.getButtonVi(), buttonVi2 = player1.getButtonVi2();
            MyButtonRect buttonVi3 = player1.getButtonVi3(), buttonVi4 = player1.getButtonVi4();
            boolean bool1 = buttonVi.getBounds().contains(x, y), bool2 = buttonVi2.getBounds().contains(x, y), bool3 = buttonVi3.getBounds().contains(x, y), bool4 = buttonVi4.getBounds().contains(x, y);
            if (player1.isMoveAllowed()) {
                if ((bool1 || bool2 || bool3 || bool4) && player1.actionVi == 1) {  //Can't have 2 actions + reset button color
                    if (buttonVi.getMouseClicked())
                        buttonVi.setMouseClicked(false);
                    if (buttonVi2.getMouseClicked())
                        buttonVi2.setMouseClicked(false);
                    if (buttonVi3.getMouseClicked()){
                        buttonVi3.setMouseClicked(false);
                        Game.manaPlayer1+=4;
                    }
                    if (buttonVi4.getMouseClicked()){
                        buttonVi4.setMouseClicked(false);
                        Game.manaPlayer1+=2;
                    }
                    statReset();
                    player1.setActionVi(0);
                }
                if (bool1) {                                //Settings of the action "Attack"
                    buttonVi.setMouseClicked(true);
                    setPlayerAction(IDLE);
                    setPlayerMemorized(ATTACK_1);
                    player1.setMoveAllowed(false);
                    player1.setAttacker(name);
                }
                if (bool2) {                         //Settings of the action "Guard"
                    buttonVi2.setMouseClicked(true);
                    def *= 1.5;
                    setPlayerAction(GROUND);
                    setPlayerMemorized(GROUND);
                    player1.setActionVi(1);
                }
                if (bool3 && Game.manaPlayer1>=4) {           //Settings of the action "Special 1"
                    buttonVi3.setMouseClicked(true);
                    Game.manaPlayer1-=4;
                    speed *= 100;
                    setPlayerAction(IDLE);
                    setPlayerMemorized(ATTACK_JUMP_1);
                    player1.setActionVi(1);
                }
                if (bool4 && Game.manaPlayer1>=2) {                         //Settings of the action "Special 2"
                    buttonVi4.setMouseClicked(true);
                    Game.manaPlayer1-=2;
                    setPlayerAction(IDLE);
                    setPlayerMemorized(ATTACK_JUMP_2);
                    player1.setMoveAllowed(false);
                    player1.setAttacker(name);
                }
            }
        }
    }

    public void effectAttack(){
        Entity target = Action.getAttackTarget()[VI];
        switch (playerAction){
            case ATTACK_1:
                target.takeEffect((float) (atk*10)/target.def,1,1,1);
                target.setPlayerAction(HIT);
                break;
            case ATTACK_JUMP_1:
                Action.getAttackTarget()[SEVIKA]=this;
                Action.getAttackTarget()[SINGED]=this;
                Action.getAttackTarget()[SILCO]=this;
                Action.getAttackTarget()[JINX]=this;
                playerAction=HIT;
                def*=3;
                break;
            case ATTACK_JUMP_2:
                target.takeEffect((float) (atk*15)/ target.def,1,0.7f,1);
                Game.effectRoundLeft[target.id][2]=3;
                Game.effectRatio[target.id][2]=0.7f;
                target.setPlayerAction(HIT);
        }
    }


    //Setters
    public void setPlayerMemorized(int playerMemorized) {this.playerMemorized = playerMemorized;}

    //Getters
    public int getPlayerMemorized() {return playerMemorized;}
}

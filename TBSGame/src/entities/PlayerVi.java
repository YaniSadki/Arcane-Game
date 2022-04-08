package entities;

import inputs.MyButton;
import inputs.MyButtonRect;
import main.Game;
import main.gamestates.Player1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.*;

public class PlayerVi extends Entity {
    //Links
    private Player1 player1=game.getPlayer1();
    //Logical variables

    //Visual variables
    private BufferedImage[][] animations;
    private int aniTick,aniIndex,aniSpeed=20;
    private int playerAction = IDLE;
    private int playerMemorized = IDLE;
    //Logical

    public PlayerVi(float x, float y, Game game, int health, int atk, int def, int speed) {
        super(x, y, game,health,atk,def,speed);
        loadAnimations();
    }

    //Loading the sprites
    private void loadAnimations() {
        InputStream is=getClass().getResourceAsStream("/player_sprites0.png");
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
        updateAnimationTick();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex],(int)x,(int)y,256,160,null);
        g.drawRect(hitbox.x,hitbox.y,hitbox.width, hitbox.height);      //Remove later
    }

    //Make the animation!
    private void updateAnimationTick(){
        aniTick++;
        if (aniTick>=aniSpeed){
            aniTick=0;
            aniIndex++;
            if (aniIndex>=GetSpriteAmount(playerAction)){
                aniIndex=0;
            }
        }
    }

    //Setting actions using mouse inputs
    public void mouseClicked(int x, int y) {
        MyButton buttonVi=player1.getButtonVi(),buttonVi2=player1.getButtonVi2();
        MyButtonRect buttonVi3=player1.getButtonVi3(), buttonVi4=player1.getButtonVi4();
        boolean bool1=buttonVi.getBounds().contains(x,y), bool2=buttonVi2.getBounds().contains(x,y),bool3=buttonVi3.getBounds().contains(x,y),bool4=buttonVi4.getBounds().contains(x,y);

        if ((bool1 || bool2 || bool3 || bool4) && player1.actionVi == 1) {  //Can't have 2 actions + reset button color
            buttonVi.setMouseClicked(false);
            buttonVi2.setMouseClicked(false);
            buttonVi3.setMouseClicked(false);
            buttonVi4.setMouseClicked(false);
            player1.setActionVi(0);
        }
        if (bool1){                                //Settings of the action "Attack"
            buttonVi.setMouseClicked(true);
            setPlayerAction(IDLE);
            setPlayerMemorized(ATTACK_1);
            player1.setActionVi(1);
        }else if (bool2){                         //Settings of the action "Guard"
            buttonVi2.setMouseClicked(true);
            setPlayerAction(GROUND);
            setPlayerMemorized(GROUND);
            player1.setActionVi(1);
        }else if (bool3){                         //Settings of the action "Special 1"
            buttonVi3.setMouseClicked(true);
            setPlayerAction(IDLE);
            setPlayerMemorized(ATTACK_JUMP_1);
            player1.setActionVi(1);
        }else if (bool4){                         //Settings of the action "Special 2"
            buttonVi4.setMouseClicked(true);
            setPlayerAction(IDLE);
            setPlayerMemorized(ATTACK_JUMP_2);
            player1.setActionVi(1);
        }
    }

    //Setters
    public void setPlayerAction(int playerAction) {this.playerAction=playerAction;}
    public void setPlayerMemorized(int playerMemorized) {this.playerMemorized = playerMemorized;}

    //Getters
    public int getPlayerMemorized() {return playerMemorized;}
    public int getPlayerAction() {return playerAction;}
}

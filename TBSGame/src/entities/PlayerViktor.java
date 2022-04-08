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

public class PlayerViktor extends Entity {
    //Links
    private Player1 player1=game.getPlayer1();
    //Logical variables
    private int health=100,atk=50,def=80,speed=40;
    private static final int MAX_HEALTH=100;
    //Visual variables
    private BufferedImage[][] animations;
    private int aniTick,aniIndex,aniSpeed=20;
    private int playerAction = IDLE;
    private int playerMemorized = IDLE;


    public PlayerViktor(float x, float y, Game game, int health, int atk, int def, int speed) {
        super(x, y, game, health, atk, def, speed);
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
        MyButton buttonViktor=player1.getButtonViktor(),buttonViktor2=player1.getButtonViktor2();
        MyButtonRect buttonViktor3=player1.getButtonViktor3(), buttonViktor4=player1.getButtonViktor4();
        boolean bool1=buttonViktor.getBounds().contains(x,y), bool2=buttonViktor2.getBounds().contains(x,y),bool3=buttonViktor3.getBounds().contains(x,y),bool4=buttonViktor4.getBounds().contains(x,y);

        if (bool1 || bool2 || bool3 || bool4) {
            if (player1.actionViktor == 1) {            //Can't have 2 actions + reset button color
                buttonViktor.setMouseClicked(false);
                buttonViktor2.setMouseClicked(false);
                buttonViktor3.setMouseClicked(false);
                buttonViktor4.setMouseClicked(false);
                player1.setActionViktor(0);
            }
        }

        if (bool1){                                //Settings of the action "Attack"
            buttonViktor.setMouseClicked(true);
            setPlayerAction(IDLE);
            setPlayerMemorized(ATTACK_1);
            player1.setActionViktor(1);
        }else if (bool2){                         //Settings of the action "Guard"
            buttonViktor2.setMouseClicked(true);
            setPlayerAction(GROUND);
            setPlayerMemorized(GROUND);
            player1.setActionViktor(1);
        }else if (bool3){                         //Settings of the action "Special 1"
            buttonViktor3.setMouseClicked(true);
            setPlayerAction(IDLE);
            setPlayerMemorized(ATTACK_JUMP_1);
            player1.setActionViktor(1);
        }else if (bool4){                         //Settings of the action "Special 2"
            buttonViktor4.setMouseClicked(true);
            setPlayerAction(IDLE);
            setPlayerMemorized(ATTACK_JUMP_2);
            player1.setActionViktor(1);
        }

    }

    //Setters
    public void setPlayerAction(int playerAction) {this.playerAction=playerAction;}
    public void setPlayerMemorized(int playerMemorized) {
        this.playerMemorized = playerMemorized;
    }

    //Getters
    public int getPlayerMemorized() {
        return playerMemorized;
    }
    public int getPlayerAction() {
        return playerAction;
    }
}
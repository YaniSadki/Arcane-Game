package style;

import main.Game;
import main.gamestates.GamesStates;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ManaBar {

    private Game game;
    private BufferedImage[] mana;

    public ManaBar(Game game){
        this.game=game;
    }

    public void showManaBar(Graphics g){
        if (GamesStates.gameState == GamesStates.PLAYER1){
            switch (Game.manaPlayer1){

                case 0:
                    g.drawImage(mana[0],30,540,240,40,null);
                    break;
                case 1:
                    g.drawImage(mana[1],30,540,240,40,null);
                    break;
                case 2:
                    g.drawImage(mana[2],30,540,240,40,null);
                    break;
                case 3:
                    g.drawImage(mana[3],30,540,240,40,null);
                    break;
                case 4:
                    g.drawImage(mana[4],30,540,240,40,null);
                    break;
                case 5:
                    g.drawImage(mana[5],30,540,240,40,null);
                    break;
                case 6:
                    g.drawImage(mana[6],30,540,240,40,null);
                    break;
                case 7:
                    g.drawImage(mana[7],30,540,240,40,null);
                    break;
                case 8:
                    g.drawImage(mana[8],30,540,240,40,null);
                    break;
                case 9:
                    g.drawImage(mana[9],30,540,240,40,null);
                    break;
                case 10:
                default:
                    g.drawImage(mana[10],30,540,240,40,null);
                    break;
            }
        }
        if (GamesStates.gameState == GamesStates.PLAYER2){
            switch (Game.manaPlayer2){

                case 0:
                    g.drawImage(mana[0],30,540,240,40,null);
                    break;
                case 1:
                    g.drawImage(mana[1],30,540,240,40,null);
                    break;
                case 2:
                    g.drawImage(mana[2],30,540,240,40,null);
                    break;
                case 3:
                    g.drawImage(mana[3],30,540,240,40,null);
                    break;
                case 4:
                    g.drawImage(mana[4],30,540,240,40,null);
                    break;
                case 5:
                    g.drawImage(mana[5],30,540,240,40,null);
                    break;
                case 6:
                    g.drawImage(mana[6],30,540,240,40,null);
                    break;
                case 7:
                    g.drawImage(mana[7],30,540,240,40,null);
                    break;
                case 8:
                    g.drawImage(mana[8],30,540,240,40,null);
                    break;
                case 9:
                    g.drawImage(mana[9],30,540,240,40,null);
                    break;
                case 10:
                default:
                    g.drawImage(mana[10],30,540,240,40,null);
                    break;
            }
        }
    }

    public void loadImage() {
        InputStream is=getClass().getResourceAsStream("/sprites/manabar.png");
        try {
            BufferedImage img= ImageIO.read(is);
            mana = new BufferedImage[11];
            for (int i=0; i<mana.length;i++){
                mana[i]=img.getSubimage(0,i*254,1666 ,254);
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
}

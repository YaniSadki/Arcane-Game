package inputs;

import main.GamePanel;
import main.gamestates.Action;
import main.gamestates.GamesStates;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;


public class MouseInputs implements MouseListener {

    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GamesStates.gameState) {
            case PLAYER1:
                gamePanel.getGame().getPlayer1().mouseClicked(e);
                break;
            case PLAYER2:
                gamePanel.getGame().getPlayer2().mouseClicked(e);
                break;
            case ACTION:
            default:
                break;
        }
        broadcastClick(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            switch (GamesStates.gameState) {
                case START:
                    gamePanel.getGame().getStart().mousePressed(e);
                    break;
                case PLAYER1:
                    gamePanel.getGame().getPlayer1().mousePressed(e);
                    break;
                case PLAYER2:
                    gamePanel.getGame().getPlayer2().mousePressed(e);
                    break;
                case ACTION:
                default:
                    break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            switch (GamesStates.gameState) {
                case START:
                    gamePanel.getGame().getStart().mouseReleased(e);
                    break;
                case PLAYER1:
                    gamePanel.getGame().getPlayer1().mouseReleased(e);
                    break;
                case PLAYER2:
                    gamePanel.getGame().getPlayer2().mouseReleased(e);
                    break;
                case ACTION:
                default:
                    break;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void broadcastClick(MouseEvent e){
        int x=e.getX(), y=e.getY();
        if ((GamesStates.gameState != GamesStates.ACTION) && (GamesStates.gameState != GamesStates.START) && (GamesStates.gameState != GamesStates.END)){
            gamePanel.getGame().getPlayer1().getPlayerVi().spriteClicked(x,y);
            gamePanel.getGame().getPlayer1().getPlayerJayce().spriteClicked(x,y);
            gamePanel.getGame().getPlayer1().getPlayerViktor().spriteClicked(x,y);
            gamePanel.getGame().getPlayer1().getPlayerCaitlyn().spriteClicked(x,y);
            gamePanel.getGame().getPlayer2().getPlayerSevika().spriteClicked(x,y);
            gamePanel.getGame().getPlayer2().getPlayerSinged().spriteClicked(x,y);
            gamePanel.getGame().getPlayer2().getPlayerSilco().spriteClicked(x,y);
            gamePanel.getGame().getPlayer2().getPlayerJinx().spriteClicked(x,y);
        }
    }
}

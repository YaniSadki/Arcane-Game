package inputs;

import main.GamePanel;
import main.gamestates.GamesStates;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


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

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            switch (GamesStates.gameState) {
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
}

package main;

import inputs.MouseInputs;
import main.gamestates.GamesStates;

import style.Sound;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {

    private Game game;



    public GamePanel(Game game){
        MouseInputs mouseInputs=new MouseInputs(this);
        this.game=game;
        setPanelSize();
        addMouseListener(mouseInputs);
    }
    private void setPanelSize() {
        Dimension size=new Dimension(1200,800);
        setPreferredSize(size);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
        if ((GamesStates.gameState != GamesStates.START) && (GamesStates.gameState != GamesStates.END)) {
            drawRound(g);
        }
    }

    public void drawRound(Graphics g){
        g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        g.drawString("ROUND " + Game.ROUND, 50, 50);
        g.drawRect(30, 15, 170, 50);
    }



    public Game getGame(){
        return game;
    }

}

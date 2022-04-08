package main;

import inputs.MouseInputs;

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

    }
    public Game getGame(){
        return game;
    }


}

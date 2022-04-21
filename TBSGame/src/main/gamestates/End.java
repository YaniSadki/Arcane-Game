package main.gamestates;

import main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

import static utilz.Constants.PlayerConstants.JUMP;

public class End extends State implements StateMethods{

    public End(Game game){
        super(game);
    }
    @Override
    public void draw(Graphics g) {
        g.setFont(new Font("Book Antiqua",Font.BOLD,80));
        g.setColor(Color.black);
        g.drawString("WINNER",450,150);
        g.setFont(new Font("Book Antiqua",Font.BOLD,60));
        if (getGame().winningTeam()==1){
            g.setColor(Color.CYAN);
            g.drawString("PILTOVER",469,250);
        }
        else{
            g.setColor(Color.RED);
            g.drawString("ZAUN",525,250);
        };
    }

    @Override
    public void update() {

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


}

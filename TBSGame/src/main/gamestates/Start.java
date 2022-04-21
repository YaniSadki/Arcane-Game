package main.gamestates;

import inputs.MyButtonRect;
import main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Start extends State implements StateMethods {

    private MyButtonRect startButton = new MyButtonRect(500,350,200,100,"START");

    public Start(Game game){
        super(game);
    }

    @Override
    public void draw(Graphics g) {
        startButton.draw(g);
    }

    @Override
    public void update() {
        playMusic(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (startButton.getMouseClicked()) {
            startButton.setMouseClicked(false);
            stopMusic();
            GamesStates.gameState = GamesStates.PLAYER1;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x= e.getX(), y = e.getY();
        if (startButton.getBounds().contains(x,y)){
            startButton.setMouseClicked(true);
        }
    }
}

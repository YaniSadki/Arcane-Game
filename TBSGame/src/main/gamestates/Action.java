package main.gamestates;

import entities.*;
import main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Action extends State implements StateMethods{

    private PlayerVi playerVi;
    private PlayerJayce playerJayce;
    private PlayerViktor playerViktor;
    private PlayerCaitlyn playerCaitlyn;
    private PlayerSevika playerSevika;
    private PlayerSinged playerSinged;
    private PlayerSilco playerSilco;
    private PlayerJinx playerJinx;

    public Action(Game game) {
        super(game);
    }

    //Init classes
    public void initClasses(){
        playerVi = getGame().getPlayer1().getPlayerVi();
        playerJayce = getGame().getPlayer1().getPlayerJayce();
        playerViktor = getGame().getPlayer1().getPlayerViktor();
        playerCaitlyn = getGame().getPlayer1().getPlayerCaitlyn();
        playerSevika = getGame().getPlayer2().getPlayerSevika();
        playerSinged = getGame().getPlayer2().getPlayerSinged();
        playerSilco = getGame().getPlayer2().getPlayerSilco();
        playerJinx = getGame().getPlayer2().getPlayerJinx();

    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void update() {
        playerVi.setPlayerAction(playerVi.getPlayerMemorized());
        playerVi.update();
        playerJayce.setPlayerAction(playerJayce.getPlayerMemorized());
        playerJayce.update();
        playerViktor.setPlayerAction(playerViktor.getPlayerMemorized());
        playerViktor.update();
        playerCaitlyn.setPlayerAction(playerCaitlyn.getPlayerMemorized());
        playerCaitlyn.update();
        playerSevika.setPlayerAction(playerSevika.getPlayerMemorized());
        playerSevika.update();
        playerSinged.setPlayerAction(playerSinged.getPlayerMemorized());
        playerSinged.update();
        playerSilco.setPlayerAction(playerSilco.getPlayerMemorized());
        playerSilco.update();
        playerJinx.setPlayerAction(playerJinx.getPlayerMemorized());
        playerJinx.update();
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

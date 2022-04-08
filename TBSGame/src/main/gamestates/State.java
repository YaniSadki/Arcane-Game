package main.gamestates;

import main.Game;

public class State {

    private Game game;

    State(Game game){
        this.game=game;
    }

    public Game getGame(){
        return game;
    }
}

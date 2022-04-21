package main.gamestates;

import main.Game;
import style.Sound;

public class State {

    private Game game;
    Sound sound = new Sound();

    State(Game game){
        this.game=game;
    }

    public Game getGame(){
        return game;
    }

    public void playMusic(int i){
        sound.setFile(i);
        System.out.println("sound 0: "+sound.soundURL[0]);
        System.out.println("clip: "+sound.clip);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}

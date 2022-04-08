package entities;

import main.Game;

import java.awt.*;

public class Entity {

    protected float x,y;
    protected Rectangle hitbox;
    protected Game game;
    //Logical variables
    protected int health,atk,def,speed;

    public Entity(float x, float y, Game game, int health, int atk, int def, int speed){
        this.x=x;
        this.y=y;
        this.hitbox=new Rectangle((int) (x+80),(int) (y+15),96,116);
        this.game=game;
        this.health=health;
        this.atk=atk;
        this.def=def;
        this.speed=speed;
    }
}

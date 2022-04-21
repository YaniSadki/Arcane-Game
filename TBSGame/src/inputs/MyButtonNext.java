package inputs;

import main.gamestates.GamesStates;

import java.awt.*;

public class MyButtonNext {

    private int[] x,y;
    private int n;
    private Polygon bounds;
    private boolean mouseClicked;

    public MyButtonNext(int[] x, int[] y, int n){
        this.x=x;
        this.y=y;
        this.n=n;

        initBounds();
    }

    private void initBounds(){
        this.bounds=new Polygon(x,y,n);
    }

    public void draw(Graphics g){
        //body
        drawBody(g);
        //bounds
        drawBounds(g);
    }

    //Style methods
    private void drawBody(Graphics g) {
        if (mouseClicked){g.setColor(Color.lightGray);}
        else{
            if (GamesStates.gameState==GamesStates.PLAYER1)
                g.setColor(Color.CYAN);
            else
                g.setColor(Color.RED);
        }
        g.fillPolygon(x,y,n);
    }
    private void drawBounds(Graphics g){
        g.setColor(Color.black);
        g.drawPolygon(x,y,n);
    }

    public void setMouseClicked(boolean mouseClicked){
        this.mouseClicked=mouseClicked;
    }

    public Polygon getBounds(){
        return bounds;
    }

}

package inputs;

import java.awt.*;

public class MyButton {

    private int x,y,width,height;
    private String text;
    private Rectangle bounds;
    private boolean mouseClicked;

    public MyButton(int x, int y, int width, int height, String text){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.text=text;

        initBounds();
    }

    //Initialized the hitbox
    private void initBounds(){
        this.bounds=new Rectangle(x,y,width,height);
    }

    //Update the look of the buttons
    public void draw(Graphics g){
        //body
        drawBody(g);
        //bounds
        drawBounds(g);
        //text
        drawText(g);
    }

    //Style methods
    private void drawBounds(Graphics g){
        g.setColor(Color.black);
        g.drawRect(x,y,width,height);
    }
    private void drawBody(Graphics g) {
        if (mouseClicked){g.setColor(Color.lightGray);}
        else{g.setColor(Color.white);}
        g.fillRect(x,y,width,height);
    }

    private void drawText(Graphics g) {
        g.setFont(new Font("Book Antiqua",Font.BOLD,26));
        int w=g.getFontMetrics().stringWidth(text);
        g.drawString(text, x+w/8+2,y+height/2+5);
    }

    //Setters
    public void setMouseClicked(boolean mouseClicked){
        this.mouseClicked=mouseClicked;
    }

    //Getters
    public Rectangle getBounds(){
        return bounds;
    }
    public boolean getMouseClicked(){
        return mouseClicked;
    }

}

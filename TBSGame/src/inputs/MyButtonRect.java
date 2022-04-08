package inputs;

import java.awt.*;

public class MyButtonRect {

    private int x,y,width,height;
    private String text;
    private Rectangle bounds;
    private boolean mouseClicked;

    public MyButtonRect (int x, int y, int width, int height, String text){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.text=text;

        initBounds();
    }

    private void initBounds(){
        this.bounds=new Rectangle(x,y,width,height);
    }

    public void draw(Graphics g){
        //body
        drawBody(g);
        //bounds
        g.setColor(Color.black);
        g.drawRect(x,y,width,height);
        //text
        drawText(g);
    }

    private void drawBody(Graphics g) {
        if (mouseClicked){g.setColor(Color.lightGray);}
        else{g.setColor(Color.white);}
        g.fillRect(x,y,width,height);
    }


    private void drawText(Graphics g) {
        g.setFont(new Font("Book Antiqua",Font.BOLD,26));
        int w=g.getFontMetrics().stringWidth(text);
        g.drawString(text, x+w/2-5,y+height/2+5);
    }

    public void setMouseClicked(boolean mouseClicked){
        this.mouseClicked=mouseClicked;
    }

    public Rectangle getBounds(){
        return bounds;
    }

}

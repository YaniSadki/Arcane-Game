package main.gamestates;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface StateMethods {
    void draw(Graphics g);               //Update the render
    void update();                       //Update the logical
    void mouseClicked(MouseEvent e);     //Mouse click effect
    void mouseReleased(MouseEvent e);    //Mouse release effect
    void mousePressed(MouseEvent e);     //Mouse press effect
}

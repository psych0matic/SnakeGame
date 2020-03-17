package org.example;

import java.awt.*;

public class Snake{
    private int x, y;
    private int xSpeed, ySpeed;

    public Snake() {
        x = 0;
        y = 0;
        xSpeed = 1;
        ySpeed = 0;
    }

    public void update() {
        x += xSpeed;
        y += ySpeed;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fill(new Rectangle(x,y,10,10));
    }
}

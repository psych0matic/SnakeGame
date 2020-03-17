package org.example;

import java.awt.*;

public class Snake{
    public SnakeGame sg = new SnakeGame();
    public Point pos = new Point();
    public int xSpeed, ySpeed;
    public int scl;
    public int width, height;
    public Snake() {
        pos.x = 0;
        pos.y = 0;
        xSpeed = 1;
        ySpeed = 0;
        scl = sg.scl;
        width = sg.width;
        height = sg.height;
    }

    public void dir(int x, int y) {
        xSpeed = x;
        ySpeed = y;
    }

    public void update() {
        pos.x += xSpeed * scl;
        pos.y += ySpeed * scl;
        pos.x = sg.constrain(pos.x,0,width - scl);
        pos.y = sg.constrain(pos.y,0,height - scl);
    }

    public void eat(Point food) {

    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fill(new Rectangle(pos.x,pos.y,scl,scl));
    }


}

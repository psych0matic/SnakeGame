package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class Snake{
    public SnakeGame sg = new SnakeGame();
    public Point pos = new Point();
    public int xSpeed, ySpeed;
    public int scl;
    public int width, height;
    public int total = 0;
    public ArrayList<Point> tail = new ArrayList<>();
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

    public boolean eat(Point food) {
        double d = pos.distance(food);
        if (d < 1) {
            total++;
            return true;
        } else {
            return false;
        }
    }

    public void death() {
        for (int i = 0; i < tail.size(); i++) {
            Point p = tail.get(i);
            double d = pos.distance(p);
            if (d < 1) {
                System.out.println("Starting over");
                total = 0;
                tail.clear();
            }
        }
    }

    public void update() {
        if (total > 0) {
            if (total == tail.size() && !tail.isEmpty()) {
                tail.remove(0);
            }
            tail.add(new Point(pos.x,pos.y));
        }

        pos.x += xSpeed * scl;
        pos.y += ySpeed * scl;
        pos.x = sg.constrain(pos.x,0,width - scl);
        pos.y = sg.constrain(pos.y,0,height - scl);

    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        for (Point p : tail) {
            g2d.fill(new Rectangle(p.x,p.y,scl,scl));
        }
        g2d.fill(new Rectangle(pos.x,pos.y,scl,scl));
    }


}

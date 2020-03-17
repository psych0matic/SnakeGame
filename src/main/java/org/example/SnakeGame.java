package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Hello world!
 *
 */
public class SnakeGame extends JPanel implements Runnable,KeyListener
{
    private boolean isRunning = true;
    private JFrame frame;

    private Random rand = new Random();
    private Snake s;
    private Point food;
    public int scl = 20;
    public int width = 600, height = 600;
    public static void main( String[] args ) {
        SnakeGame s = new SnakeGame();
        s.run();
    }

    @Override
    public void run() {
        init();
        while(isRunning) {
            repaint();
        }
    }

    public void canvas(int width,int height) {
        setPreferredSize(new Dimension(width,height));
        setDoubleBuffered(true);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(this);
    }

    public void init() {
        canvas(width,height);
        s = new Snake();
        food = pickLocation();
    }

    private Point pickLocation() {
        int cols = (int)Math.floor(width/scl);
        int rows = (int)Math.floor(height/scl);
        return new Point((int)Math.floor(random(cols))*scl,(int)Math.floor(random(rows))*scl);
    }

    private void draw(Graphics2D g2d) {
        s.update();
        s.draw(g2d);
        s.eat(food);
        g2d.setColor(new Color(255,0,100));
        g2d.fillRect(food.x,food.y,scl,scl);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0,0,getWidth(),getHeight());
        draw(g2d);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q: isRunning = false; frame.dispose(); break;
            case KeyEvent.VK_W:  s.dir(0,-1);break;
            case KeyEvent.VK_S:  s.dir(0,1);break;
            case KeyEvent.VK_A:  s.dir(-1,0);break;
            case KeyEvent.VK_D:  s.dir(1,0);break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public int constrain(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    public int random(int value) {
        return ThreadLocalRandom.current().nextInt(0, value + 1);
    }
}

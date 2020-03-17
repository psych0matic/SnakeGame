package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class SnakeGame extends JPanel implements Runnable,KeyListener
{
    public boolean isRunning = true;
    private JFrame frame;

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
        pickLocation();
    }

    private void pickLocation() {
        int cols = width/scl;
        int rows = height/scl;
        int x = (int)Math.floor(random(cols))*scl;
        int y = (int)Math.floor(random(rows))*scl;
        if (x == 600)
            x -= scl;
        if (y == 600)
            y -= scl;
        food = new Point(x,y);
        System.out.println(x + " " + y);
    }

    private void draw(Graphics2D g2d) {
        if (s.eat(food)) {
            pickLocation();
        }
        s.death();
        s.update();
        s.draw(g2d);

        g2d.setColor(new Color(255,0,100));
        g2d.fillRect(food.x,food.y,scl,scl);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setBackground(Color.DARK_GRAY);
        g2d.clearRect(0,0,getWidth(),getHeight());
        draw(g2d);
        try {
            TimeUnit.MILLISECONDS.sleep(70);
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
            case KeyEvent.VK_T: s.total++;
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

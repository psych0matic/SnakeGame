package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class SnakeGame extends JPanel implements Runnable,KeyListener
{
    private boolean isRunning = true;
    private JFrame frame;

    Snake s = new Snake();

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
        setPreferredSize(new Dimension(600,600));
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
        canvas(600,600);

    }

    private void draw(Graphics2D g2d) {
        s.update();
        s.draw(g2d);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0,0,getWidth(),getHeight());
        draw(g2d);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'q': isRunning = false; frame.dispose(); break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}

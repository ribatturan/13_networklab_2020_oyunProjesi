package pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class tennis extends Applet implements Runnable, KeyListener {

    final int width = 700;
    final int height = 500;
    Thread thread;
    HumanPaddle p1;
    HumanPaddle p2;
    int point1 = 0;
    int point2 = 0;
    ball b1;
    boolean gameStarted;
    Graphics gfx;
    Image img;

    public void init() {
        this.resize(width, height);
        gameStarted = false;
        this.addKeyListener(this);
        p1 = new HumanPaddle(1);
        p2 = new HumanPaddle(2);
        b1 = new ball();
        thread = new Thread(this);
        img = createImage(width, height);
        gfx = img.getGraphics();
        thread.start();

    }

    public void paint(Graphics g) {

        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, width, height);
        if (b1.getX() < -10) {
            point2++;
            b1.x = 350;
            b1.y = 250;
          // gameStarted = false;
        } else if (b1.getX() > 720) {
            point1++;
            b1.x = 350;
            b1.y = 250;
            //gameStarted = false;
        } else {
            if (point2 >= 5) {
                gfx.setColor(Color.red);
                gfx.drawString("Player 1 Won", 350, 250);
            }else if(point1 >=5){
                gfx.drawString("Player 2 Won", 350, 250);
            }else{
            gfx.setColor(Color.white);
            gfx.drawString("P1: " + point1, 300, 25);
            gfx.drawString("P2: " + point2, 400, 25);
            p1.draw(gfx);
            p2.draw(gfx);
            b1.draw(gfx);}
        }
        if (!gameStarted) {
            gfx.setColor(Color.white);
            gfx.drawString("PONG", 340, 100);
            gfx.drawString("Press Enter to Begin", 310, 130);
        }
        g.drawImage(img, 0, 0, this);

    }

    public void update(Graphics g) {
        paint(g);

    }

    @Override
    public void run() {
        while (true) {
            if (gameStarted) {
                p1.move();
                p2.move();
                b1.move();
                b1.chechkPaddleCollision(p1, p2);
            }
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(tennis.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            p1.setUpAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            p1.setdownAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            p2.setUpAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            p2.setdownAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameStarted = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            p1.setUpAccel(false);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            p1.setdownAccel(false);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            p2.setUpAccel(false);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            p2.setdownAccel(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Uğurcan
 */
public class ball {

    double xVel, yVel, x, y;

    public ball() {
        x = 350;
        y = 250;
        xVel = getRandomSpeed() * getRandomDirection();
        yVel = getRandomSpeed() * getRandomDirection();
    }

    public double getRandomSpeed() {
        return (Math.random() * 3 + 2);
    }

    public int getRandomDirection() {
        int rand = (int) (Math.random() * 2);
        if (rand == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    public void move() {
        x += xVel;
        y += yVel;
        if (y < 10) {
            yVel = -yVel;
        }
        if (y > 490) {
            yVel = -yVel;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval((int) x - 10, (int) y - 10, 20, 20);
    }

    public void chechkPaddleCollision(Paddle p1, Paddle p2) {
        if (x <= 50) {
            if (y >= p1.getY() && y <= p1.getY() + 80) {
                xVel = -xVel;
            }
        } else if (x >= 650) {
            if (y >= p2.getY() && y <= p2.getY() + 80) {
                xVel = -xVel;
            }
        }
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }
}
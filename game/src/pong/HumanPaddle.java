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
public class HumanPaddle implements Paddle {

    double y, yVel;
    boolean upAccel, downAccel;
    int player, x;
    final double GRAVITY=0.94;

    public HumanPaddle(int player) {
        upAccel = false;
        downAccel = false;
        y = 210;
        yVel = 0;
        if (player == 1) {
            x = 20;
        } else {
            x = 660;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, (int)y, 20, 80);
    }

    @Override
    public void move() {
        if(upAccel){
            yVel-=2;
        }else if(downAccel){
            yVel+=2;
        }else if(!upAccel && !downAccel){
            yVel*=0;//GRAVITY; eğer yavaşlayarak durmasını istiyorsan gravitye eşitle
        }
       
        if(yVel>=5)yVel=5;
        else if(yVel<=-5) yVel=-5;
         y+=yVel;
         if(y<0)y=0;
         if(y>420)y=420;
    }
    public void setUpAccel(boolean input){
        upAccel=input;
    }
        public void setdownAccel(boolean input){
        downAccel=input;
    }
    @Override
    public int getY() {
        return (int)y;
    }

}

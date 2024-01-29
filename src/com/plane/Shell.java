package com.plane;

import java.awt.Color;
import java.awt.Graphics;


public class Shell extends GameObject{
    double degree;

    public Shell(){
        degree = Math.random()*Math.PI*2;
        x = 200;
        y = 200;
        width = 10;
        height = 10;
        speed = 3;
    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.yellow);

        g.fillOval((int)x, (int)y, width, height);

        //shells shoot all directions
        x += (int)(speed*Math.cos(degree));
        y += (int)(speed*Math.sin(degree));

        // bouncing if shells hit the boundaries
        if(y > Constant.GAME_HEIGHT - height || y < 30){
            degree =- degree;
        }

        if(x < 0 || x > Constant.GAME_WIDTH - width){
            degree = Math.PI - degree;
        }

        //return to outer and change the color back
        g.setColor(c);
    }
}

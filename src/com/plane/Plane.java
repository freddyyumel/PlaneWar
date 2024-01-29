package com.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{

    boolean left, up, right, down;
    boolean live = true;

    // change the direction
    public void addDirection(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = true;
                break;

            case KeyEvent.VK_UP:
                up = true;
                break;

            case KeyEvent.VK_RIGHT:
                right = true;
                break;

            case KeyEvent.VK_DOWN:
                down = true;
                break;

            default:
                break;
        }

    }

    // stop changing the direction, keep still
    public void minusDirection(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = false;
                break;

            case KeyEvent.VK_UP:
                up = false;
                break;

            case KeyEvent.VK_RIGHT:
                right = false;
                break;

            case KeyEvent.VK_DOWN:
                down = false;
                break;

            default:
                break;
        }

    }
    @Override
    public void drawMySelf(Graphics g){
        if(live){
            super.drawMySelf(g);
            // change the location of the plane based ont the coordinate
            if(left){
                x -= speed;
            }

            if(right){
                x += speed;
            }

            if(up){
                y -= speed;
            }

            if(down){
                y += speed;
            }
        }
    }

    public Plane(Image img, double x, double y, int speed){
        super(img, x, y);
        this.speed = speed;
    }
}

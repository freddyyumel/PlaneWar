package com.plane;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

public class MyGameFrame extends Frame{
    // define images as attributes
    Image bgImg = GameUtil.getImage("images/bg.jpg");
    Image planeImg = GameUtil.getImage("images/plane.png");

    Plane plane = new Plane(planeImg, 300, 300, 3);

    ArrayList<Shell> shellList = new ArrayList<Shell>();

    // define explode object
    Explode explode;

    // time calculating
    Date startTime = new Date();
    Date endTime;

    // double image buffer
    private Image offScreenImage = null;

    public void update(Graphics g){
        if(offScreenImage == null)
            offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override // will be invoked automatically
    public void paint(Graphics g){
        g.drawImage(bgImg,0,0,null);
        plane.drawMySelf(g); // draw the plane

        //draw all the shells
        for(int i = 0; i < shellList.size(); i++){
            Shell b = shellList.get(i);
            b.draw(g);

            //test all collisions
            boolean collision = b.getRect().intersects(plane.getRect());
            if(collision){
                plane.live = false; // plane is dead

                if(explode == null){
                    explode = new Explode(plane.x, plane.y);
                }
                explode.draw(g);
            }
        }

        if(!plane.live){
            if(endTime == null){
                endTime = new Date();
            }

            int period = (int)((endTime.getTime()-startTime.getTime())/1000);
            printInfo(g, "Time:" + period + "s", 50, 120,260, Color.white);
        }
    }

    /**
     * print info on screen
     * @param g
     * @param str
     * @param size
     */
    public void printInfo(Graphics g, String str, int size, int x, int y, Color color){
        Color c = g.getColor();
        g.setColor(color);
        Font f = new Font("New Roman", Font.BOLD, size);
        g.setFont(f);
        g.drawString(str, x, y);
        g.setColor(c);

    }

    public void launchFrame(){
        //print game window
        setTitle("Freddy's First Game");
        // make it visible
        setVisible(true);
        //size: 500*500
        setSize(500, 500);
        //set location: the left corner of the window
        setLocation(300,300);

        //add windows listener, so users can end the game when they click the close button
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        new PaintThreads().start();

        // turn KeyMonitor on
        addKeyListener(new KeyMonitor());

        for(int i = 0; i < 50; i++){
            Shell b = new Shell();
            shellList.add(b);
        }
    }

    public static void main(String[] arg){
        MyGameFrame f= new MyGameFrame();
        f.launchFrame();
    }
    /**
     * define a window-repainting thread, a inner class
     * @author Fan
     */
    // define PaintThreads as inner class
    class PaintThreads extends Thread {
        public void run(){
            while(true) {
                repaint();
                try{
                    Thread.sleep(40);//1s = 1000ms
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // define KeyMonitor class, inner class
    class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e){
            plane.minusDirection(e);
        }
    }
}

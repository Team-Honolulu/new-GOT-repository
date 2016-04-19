package org.softuni;


import java.awt.*;

public class Enemy {

    // fields
    private double x;
    private double y;
    private int r;

    private double dx;
    private double dy;
    private double rad;     // angle in radians
    private double speed;

    private int health;
    private int type;
    private int rank;

    private Color color1;

    private boolean ready;  // checks if the enemy is inside the game screen
    private boolean dead;   // checks is the enemy has been destroyed


    // constructor

    public Enemy(int type, int rank) {

        this.type = type;
        this.rank = rank;

        // default enemy
        if (type == 1) {
            color1 = Color.BLUE;
            if (rank == 1) {
                speed = 2;
                r = 5;
                health = 1;

            }

            x = Math.random() * GamePanel.WIDTH / 2 + GamePanel.WIDTH / 4;    // random
            y = -r;

            double angle = Math.random()* 140 + 20;
            rad = Math.toRadians(angle);

            dx = Math.cos(rad)* speed;
            dy = Math.sin(rad)*speed;

            ready = false;
            dead = false;
        }
    }
    // functions
    public double getx(){return x;}
    public double gety(){return y;}
    public double getr(){return r;}

    public boolean isDead(){return dead;}

    public void hit(){
        health--;
        if (health <= 0){
            dead= true;
        }
    }

    public void update(){

        x += dx;
        y += dy;

        if (!ready){
            if (x > r && x < GamePanel.WIDTH - r &&
                    y > r && y < GamePanel.HEIGHT -r){

                ready = true;
            }
        }

        if (x < r && dx < 0) dx = -dx;
        if (y < r && dy < 0) dy = -dy;
        if (x > GamePanel.WIDTH - r && dx > 0) dx = -dx;
        if (y > GamePanel.HEIGHT - r && dy > 0) dy = -dy;

    }

    public void draw(Graphics2D g){

        g.setColor(color1);
        g.fillOval((int)x-r,(int)y-r,2*r,2*r);

        g.setStroke(new BasicStroke(3)); // setting the line thickness (frame)
        g.setColor(color1.darker());
        g.drawOval((int)x-r,(int)y-r,2*r,2*r);
        g.setStroke(new BasicStroke(1));
    }

}

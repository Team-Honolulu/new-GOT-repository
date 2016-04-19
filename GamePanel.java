package org.softuni;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{

    // fields
    public static int WIDTH = 400;
    public static int HEIGHT = 400;

    private Thread thread;
    private boolean running;

    private BufferedImage image;   // canvas
    private  Graphics2D g;         // paintbrush

    private int FPS = 30;          // Setting frames per second
    private double averageFPS;

    // constructor
    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    // functions
    public void addNotify(){
        super.addNotify();
        if (thread == null){
            thread = new Thread(this);
            thread.start();
        }
    }

    public void run(){
        running = true;

        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        long startTime;
        long URDTimeMillis;
        long waitTime;
        long totalTime = 0;

        int frameCount = 0;
        int maxFrameCount = 30;

        long targetTime = 1000 / FPS;  // the amount of time that it takes for 1 loop to run in order to maintain 30fps
        // game loop
        while (running){

            startTime = System.nanoTime();      // Gets the current time in nanoseconds

            gameUpdate();
            gameRender();
            gameDraw();

            URDTimeMillis = (System.nanoTime() - startTime) / 1000000;   // dividing to convert the result from nanoseconds to milliseconds
            waitTime = targetTime - URDTimeMillis;                       // the amount of extra time we need to wait

            try{
                Thread.sleep(waitTime);
            } catch (Exception e) {
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if (frameCount == maxFrameCount){                            // to put a speed limit on the game loop
                averageFPS = 1000.0 / ((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime =0;
            }
        }
    }

    private void gameUpdate(){    // here goes all of the game logic
		
		//new wave
		if (waveStartTimer == 0 && enemies.size() == 0) {
			waveNumber++;
			waveStart = false;
			waveStartTimer = System.nanoTime()
		}
		else {
			waveStartTimerDiff = (System.nanoTime() - waveStartTimer) / 1000000;
			if (waveStartTimerDiff > waveDelay) {
				waveStart = true;
				waveStartTimer = 0;
				waveStartTimerDiff = 0;
			}
		}
		
		// create enemies
		if (waveStart && enemies.size() == 0) {
			createNewEnemies();
		}
		
		// player update
		player.update();
		
		// bullet update
		for(int i = 0; i < bullets.size(); i++) {
			boolean remove = bullets.get(i).update();
			if (remove) {
				bullets.remove(i);
				i--;
			}
		}
		
		// enemy update
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update;
		}
		
		// bullet-enemy collision
		for (int i = 0; < bull.size(); i++) {
			
			Bullet b = bullets.get(i);
			double bx = b.getx();
			double by = b.gety();
			double br = b.getr();
			
			for (int j = 0; j < enemies.size(); j++) {
				
				Enemy e = enemies.get(k);
				double ex = e.getx();
				double ey = e.gety();
				double er = e.getr();
				
				double dx = bx - ex;
				double dy = by - ey;
				double dist = Math.sqrt(dx * dx + dy * dy);
				
				if (dist < br + er) {
					e.hit();
					bullets.remove(i);
					i--;
					break;
				}
			}
			
			// player-enemy collision
		}
		
		// check dead enemies
		for (int i = 0; enemies.size(); i++) {
			if (enemies.get(i).isDead()) {
				enemies.remove(i);
				i--;
			}
		}
    }

    private  void gameRender(){   // offscreen image (double buffering)

        g.setColor(Color.gray);
        g.fillRect(0,0,WIDTH,HEIGHT);
        g.setColor(Color.black);
        g.drawString("FPS: " + averageFPS,10,10);
    }

    private void gameDraw(){      // drawing on the screen
        Graphics g2 = this.getGraphics();
        g2.drawImage(image,0,0, null);
        g2.dispose();
    }
}
package org.softuni;

import java.awt.*;

public class Player {
	
	// FIELDS
	private int x;
	private int y;
	private int r; // radius. The size of the dot.
	
	private int dx;
	private int dy;
	private int speed;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private boolean firing;
	private long firingTimer;
	private long firingDelay;
	
	private boolean recovering;
	private long recoveryTimer;

	private int lives;
	private Color color1;
	private Color color2;
	
	private int score;
	
	// CONSTRUCTOR
	public Player () {
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		r = 5;
		
		dx = 0 ;
		dy = 0;
		speed = 5;
		
		lives = 3;
		color1 = Color.WHITE;
		color2 = Color.RED;
		
		firing = false;
		firingTimer = System.nanoTime();
		firingDelay = 200;
		
		recovering = false;
		recoveryTimer = 0;
		
		score = 0;
	}
	
	// FUNCTIONS
	
	public int getx() {
		return x;
	}
	
	public int gety() {
		return y;
	}
	
	public int getr() {
		return r;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLives () {
		return lives;
	}
	
	public boolean isRecovering() {
		return recovering;
	}

	
	public void setLeft(boolean b) {
		left = b;
	}
	
	public void setRight(boolean b) {
		right = b;
	}
	
	public void setUp(boolean b) {
		up = b;
	}
	
	public void setDown(boolean b) {
		down = b;
	}
	
	public void setFiring(boolean b) {
		firing = b;
	}
	
	public void addScore(int i) {
		score += i;
	}
	
	public void loseLife() {
		lives--;
		recovering = true;
		recoveryTimer = System.nanoTime();
	}
	
	public void update () {
		if (left) {
			dx = -speed;
		}
		if (right) {
			dx = speed;
		}
		if (up) {
			dy = -speed;
		}
		if (down) {
			dy = speed;
		}
		x += dx;
		y += dy;
		if (x < r) {
			x = r;
		}
		if (y < r) {
			y = r;
		}
		if (x > GamePanel.WIDTH - r) {
			x = GamePanel.WIDTH - r;
		}
		if (y > GamePanel.HEIGHT - r) {
			y = GamePanel.HEIGHT - r;
		}
		dx = 0;
		dy = 0;
		
		if (firing) {
			long elapsed = (System.nanoTime() - firingTimer) / 1000000;
			if (elapsed > firingDelay) {
				GamePanel.bullets.add(new Bullet(270, x, y));
				firingTimer = System.nanoTime();
			}
		}
		
		long elapsed = (System.nanoTime() - recoveryTimer) / 1000000;
		if (elapsed > 2000) {
			recovering = false;
			recoveryTimer = 0;
		}
	}
	
	public void draw (Graphics2D g) {
		
		if(recovering) {
			g.setColor(color2);
			g.fillOval(x - r, y - r, 2 * r, 2 * r); // x and y coorinates of the center of the player
		
			g.setStroke(new BasicStroke(3));
			g.setColor(color2.darker());
			g.drawOval(x - r, y - r, 2 * r, 2 * r);
			g.setStroke(new BasicStroke(1));
		} 
		else {
			g.setColor(color1);
			g.fillOval(x - r, y - r, 2 * r, 2 * r); // x and y coorinates of the center of the player
		
			g.setStroke(new BasicStroke(3));
			g.setColor(color1.darker());
			g.drawOval(x - r, y - r, 2 * r, 2 * r);
			g.setStroke(new BasicStroke(1));
		}
	}
}

import java.awt.*;

poblic class Player {
	
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

	private int lives;
	private Color color1;
	private Color color2;
	
	// CONSTRUCTOR
	public Player () {
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		r = 5;
		
		dx 0 ;
		dy = 0;
		speef = 5;
		
		lives = 3;
		color1 = Color.WHITE;
		color2 = Color.RED;
	}
	
	// FUNCTIONS
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
	}
	
	public void draw (Graphics2D g) {
		g.setColor(color1);
		g.fillOval(x - r, y - r, 2 * r, 2 * r); // x and y coorinates of the center of the player
		
		g.setStroke(new BasicStroke(3));
		g.setColor(color1.darker());
		g.drawOval(x - r, y - r, 2 * r, 2 * r);
		g.setStroke(new BasicStroke(1));
		
		// We have to make changes in GamePanel.java:
		// under "private double averageFPS" we add
		// private Player player;
		//
		// ...and under "g = (Graphics2D) image.GetGraphics();" we add
		// player = new Player();
		//
		// ...and in method "gameUpdate()" we add
		// player.update();
		//
		// ...and under "g.drawString("FPS: " + averageFPS, 10, 10)" we add
		// player.draw(g);
		//
		// ...and in method "addNotify" after the scopes of "if" statement we add 
		// addKeyListener(this);
		//
		// ...and in "public class GamePanel extends JPanel implements Runnable" at the same line we add
		// , KeyListener
		//
		// ...and under method "private void gameDraw" we add another three methods
		//
		// public void keyTyped(KeyEvent key) {}
		// 	
		// public void keyPressed(KeyEvent key) {
		//		int keyCode = key.getKeyCode();
		//		if (keyCode == KeyEvent.VK_LEFT) {
		//			player.setLeft(true);
		//		}
		//		if (keyCode == KeyEvent.VK_RIGHT) {
		//			player.setRight(true);
		//		}
		//		if (keyCode == KeyEvent.VK_UP) {
		//			player.setUp(true);
		//		}
		//		if (keyCode == KeyEvent.VK_DOWN) {
		//			player.setDown(true);
		//		}
		// }
		// 
		// public void keyReleased(KeyEvent key) {
		//		int keyCode = key.getKeyCode();
		//		if (keyCode == KeyEvent.VK_LEFT) {
		//			player.setLeft(false);
		//		}
		//		if (keyCode == KeyEvent.VK_RIGHT) {
		//			player.setRight(false);
		//		}
		//		if (keyCode == KeyEvent.VK_UP) {
		//			player.setUp(false);
		//		}
		//		if (keyCode == KeyEvent.VK_DOWN) {
		//			player.setDown(false);
		//		}
		// }
		// 
		//
		// ...and we add 
		// import java.awt.event.*;
		//
		// ...and in method gameRender() we change g.setColor(Color.WHITE) to  
		// g.setColor(new Color(0, 100, 255))  //(this changes the background of the panel)
		// 
	}

}
















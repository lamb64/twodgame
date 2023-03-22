package twodgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;


public class GameFanel extends JPanel implements Runnable{
	//Screen Settings
	final int originalTileSize = 16; //16x16
	final int scale = 3; //Scale
	
	public final int tileSize = originalTileSize * scale; // 48x48 from a 16x16
	public final int maxScreenCol = 16; //Horizontal 16,53
	public final int maxScreenRow = 12; //vertical 12,29
	public final int screenWidth = tileSize * maxScreenCol; //Width 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //Height 576 pixels
	
	//World settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int maxWorldWidth = tileSize * maxScreenCol;
	public final int maxWorldHeight = tileSize * maxScreenRow;
	public String Map = "/maps/map02.txt";
	
	
	//Fps
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public TpChecker tpChecker = new TpChecker(this);
	public Player player = new Player(this,keyH);
	
	
	
	
	public GameFanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
				
		
		while(gameThread != null) {

			
			//1 Update info (Character petitions)
			update();
			
			
			
			//2 Draw info
			repaint();
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				
				remainingTime = remainingTime/1000000;
				
				
				if(remainingTime < 0) {
					remainingTime = 0;
				
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
		}
		
		
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	

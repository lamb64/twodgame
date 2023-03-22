package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import twodgame.GameFanel;
import twodgame.KeyHandler;

public class Player extends Entity{
	
	GameFanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	
	public Player(GameFanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}
	public void getPlayerImage() {
		try {
			//getting images
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/3.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/4.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/5.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/6.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/7.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/8.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";				
			}
			else if(keyH.downPressed == true) {
				direction = "down";				
			}
			else if(keyH.leftPressed == true) {
				direction = "left";				
			}
			else if(keyH.rightPressed == true) {
				direction = "right";				
			}
			//Check Tile Collision
			CollisionOn = false;
			gp.cChecker.checkTile(this);
			//Check Tile TP
			TpOn = false;
			gp.tpChecker.checkTile(this);
			
			
			//if collision is false, can move\
			if (CollisionOn == false )
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			
			if (TpOn == false )
				switch(direction) {
				case "up":
					gp.Map = "/maps/map03.txt";
					break;
				case "down":
					gp.Map = "/maps/map03.txt";
					break;
				case "left":
					gp.Map = "/maps/map03.txt";
					break;
				case "right":
					gp.Map = "/maps/map03.txt";
					break;
				}
			
			spriteCounter++;
			if(spriteCounter > 14) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if (spriteNum ==2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
				
			}
		}
		
		
		
	}
	public void draw(Graphics g2) {
		
		//g2.setColor(Color.white);		
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		
		//the game of the image
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			
			break;
		
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}

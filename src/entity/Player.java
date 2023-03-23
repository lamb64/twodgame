package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
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
	public int timed = 0;
	
	
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
		floorHeight = gp.tileSize * 21;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		jumpStrength = 50;
		direction = "down";
		weight = 3;
		
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
		
		while (GravityOn == true && timed == 0) {
			worldX = gp.tileSize * 23;
			worldY = gp.tileSize * 21;
			timed ++;
			
		}
			
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.spacePressed == true) {
			
			if(keyH.upPressed == true  && GravityOn == false) {
				direction = "up";				
			}
			else if(keyH.downPressed == true && GravityOn == false) {
				direction = "down";				
			}
			else if(keyH.leftPressed == true) {
				direction = "left";				
			}
			else if(keyH.rightPressed == true) {
				direction = "right";				
			}
			else if (keyH.spacePressed == true) {
				direction = "space";
			}
			else
				direction = "N/A";
				
				
				
			//Check Tile Collision
			CollisionOn = false;
			gp.cChecker.checkTile(this);
			//Check Tile TP
			TpOn = false;
			gp.tpChecker.checkTile(this);
			
			
			//if collision is false, can move
			if (CollisionOn == false )
				switch(direction) {
				case "up":
					if(GravityOn == false) {
						worldY -= speed;
					}
					else {
						
						//figuring out gravity
//						for(int x = 0; x > 5; x++)
//							worldY -= speed;
//						for(int x = 5; x > 0; x--)
//							worldY -= speed;
					}
						
					break;
				case "down":
					if(GravityOn == false) {
						worldY += speed;
					}
						
					break;
				case "left":
					if(keyH.spacePressed == true) {
						
					}
					else {
						worldX -= speed;
						break;
					}
					
					
				case "right":
					if(keyH.spacePressed == true) {
						
					}
					else {
						worldX += speed;
						break;
					}
					
					
				case "space":
					if (worldY <= floorHeight) {
						jumpStrength -= weight;
						worldY -= jumpStrength;
						System.out.println(keyH.spacePressed);
						if(jumpStrength < 0 && worldY >= gp.tileSize * 21 ) {
							keyH.spacePressed = false;
							jumpStrength = 50;
							worldY = gp.tileSize * 21;
							System.out.println(keyH.spacePressed);
						}
					}
					
					
										
						
					
				}
			if (worldY >= floorHeight)
						worldY = floorHeight;
			
			if (TpOn == false )
				switch(direction) {
				case "up":
					gp.Map = "/maps/map04.txt";
					break;
				case "down":
					gp.Map = "/maps/map04.txt";
					break;
				case "left":
					gp.Map = "/maps/map04.txt";
					break;
				case "right":
					gp.Map = "/maps/map04.txt";
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
		if (GravityOn == true) {
			image = down1;		
		}
		
		switch(direction) {
		case "up":
			if(GravityOn == false) {
				if(spriteNum == 1) {
					image = up1;
				}
				if(spriteNum == 2) {
					image = up2;
				}
			}
			break;
		case "down":
			if(GravityOn == false) {
				if(spriteNum == 1) {
					image = down1;
				}
				if(spriteNum == 2) {
					image = down2;
				}
				else {
					if(spriteNum == 1) {
						image = down1;
					}
				}
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

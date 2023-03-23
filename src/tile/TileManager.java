package tile;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import twodgame.GameFanel;

public class TileManager {
	
	GameFanel gp;
	public tile[] tile;
	public int mapTileNum[][];
	
	
	public TileManager(GameFanel gp) {
		this.gp = gp;
		
				
		tile = new tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap(gp.Map);
	}
	
	public void getTileImage() {
		try {
			tile[0] = new tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass2.png"));
			
			tile[1] = new tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;
			
			tile[2] = new tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].collision = true;
			
			
			tile[3] = new tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dirt1.png"));
			
			
			tile[4] = new tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			//tile[4].collision = true;
			tile[4].Tp = true;
			
			tile[5] = new tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
			tile[6] = new tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fence1.png"));
			tile[6].collision = true;

			tile[7] = new tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));
			
			tile[8] = new tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sky1.png"));
			
			tile[9] = new tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pGrass1.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath); 
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col <gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				while(col <gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		}catch(Exception e) {
			
		}
	}
	public void draw(Graphics g2) {
		int worldcol =0;
		int worldrow = 0;				
		boolean timer = true;
		int tt = 0;
		
		while(worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow) {
			tt++;
			if (tt%50 == 0) {
				timer = true;
				tt = 0;
			}
				
			if(gp.player.TpOn == true) {		
				while(timer == true) {
					timer = false;
					loadMap(gp.Map);
				}
					
				
			}
				
			
			int tileNum = mapTileNum[worldcol][worldrow];
			int worldX = worldcol * gp.tileSize;
			int worldY = worldrow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
					worldY + gp.tileSize > gp.player.worldY -gp.player.screenY && 
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {
				
			g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
			worldcol++;
		
			
			if(worldcol >= gp.maxWorldCol) {
				worldcol = 0;
				worldrow++;
				
			}
		}
	}
	
	
	
	
}

package com.timerunner;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;
 
public class Camera {
 
	private int transX, transY;
	private int mapWidth, mapHeight;
	private Rectangle viewPort; 
	/* We define a rectangle with the size of our screen, this represents our camera
	 * "range", so everything inside the viewport will be drawn on the screen, we will
	 * be able to move this rectangle across the map. */
 
	public Camera(TiledMap map, int mapWidth, int mapHeight) 
	{
		transX = 0;
		transY = 0;
		viewPort = new Rectangle(0, 0, Game.WIDTH, Game.HEIGHT);
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
	}
 
	public void translate (Graphics g, Player entity) 
	{
		if(entity.getX()-Game.WIDTH/2+16 < 0)
		{
			transX = 0; 
		}
		else if(entity.getX()+Game.WIDTH/2+16 > mapWidth)
		{
	    	transX = -mapWidth + Game.WIDTH;
		}
	    else
	    {
	    	transX = (int)-entity.getX() + Game.WIDTH/2 - 16;
	    }
	 
	    if( (entity.getY() - Game.HEIGHT/2 + 16) < 0)
	    {
	    	transY = 0;     	
	    }
	    else if( (entity.getY() + Game.HEIGHT/2 + 16) > mapHeight)
	    {
	    	transY = -mapHeight+Game.HEIGHT;
	    }
	    else
	    {
	    	transY = (int)-entity.getY()+Game.HEIGHT/2-16;
	    }
    	
		g.translate(transX, transY);
    	viewPort.setX(-transX);
    	viewPort.setY(-transY);
	}
	
	public int getTransX()
	{
		return this.transX;
	}
	
	public int getTransY()
	{
		return this.transY;
	}
}
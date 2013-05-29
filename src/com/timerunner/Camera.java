package com.timerunner;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;
 
/**
 * The Class Camera.
 * @author Shockper
 */
public class Camera 
{
	/** The trans y. */
	private int transX, transY;
	/** The map height. */
	private int mapWidth, mapHeight;
	
	/* We define a rectangle with the size of our screen, this represents our camera
	 * "range", so everything inside the viewport will be drawn on the screen, we will
	 * be able to move this rectangle across the map. */
	private Rectangle viewPort; 
 
	/**
	 * Instantiates a new camera.
	 *
	 * @param map the map
	 * @param mapWidth the map width
	 * @param mapHeight the map height
	 */
	public Camera(TiledMap map, int mapWidth, int mapHeight) 
	{
		transX = 0;
		transY = 0;
		viewPort = new Rectangle(0, 0, Game.WIDTH, Game.HEIGHT);
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
	}
 
	/**
	 * Translate.
	 *
	 * @param g the graphics
	 * @param entity the entity
	 */
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
	
	/**
	 * Gets the trans x.
	 *
	 * @return the trans x
	 */
	public int getTransX()
	{
		return this.transX;
	}
	
	/**
	 * Gets the trans y.
	 *
	 * @return the trans y
	 */
	public int getTransY()
	{
		return this.transY;
	}
}
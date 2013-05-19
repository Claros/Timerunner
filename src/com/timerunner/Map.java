package com.timerunner;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

public class Map extends TiledMap
{
	private boolean blocked[][]; //le tableau des tiles bloquantes = no go areas !!
	private ArrayList<Rectangle> blockedTile;
	//private GameState game;
	 
	public Map(String ref) throws SlickException 
	{
		super(ref);//envoie à TiledMap l'adresse du fichier .tmx
		//this.game = game;
		/*****On récupère les infos sur les no go areas ! *****/
		blocked = new boolean[width][height];
		blockedTile = new ArrayList<Rectangle>();
		for (int x = 0; x < width; x++)
		{
		      for (int y = 0; y < height; y++)
		      {
		            for (int l = 0; l < 2; l++)
		            {
		                int id = getTileId(x, y, l);
		                if (id != 0)
		                { 
		                	String b = getTileProperty(id, "walkable", "");
		                	if (b.equals("false")) 
		                	{
		                		blockedTile.add(new Rectangle(x*32, y*32, 32, 32));
		                		blocked[x][y] = true;
		                	} 
		                }
		            }
		       }
		} // fin du FOR*/
		blocked[0][0] = true;
		
	}
	
	public void drawRect(Graphics g)
	{
		for (Rectangle r : blockedTile)
		{
			g.draw(r);
		}
	}
	
	public boolean isTileBlocked(Rectangle rPlayer) 
	{ 
		for (Rectangle r : blockedTile)
		{
			if (r.intersects(rPlayer))
			{
				return true;
			}
		}
		return false;
	}
	
//	public boolean isPixelBlocked(final int x, final int y)
//	{
//		return this.isTileBlocked(Math.round(x/32), Math.round(y/32));
//	}
}
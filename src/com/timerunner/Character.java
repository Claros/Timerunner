package com.timerunner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Character extends Entity
{
	public long lastMove;

	public Character(float x, float y, int width, int height, String sprite, int spriteX, int spriteY, String pName) throws SlickException 
	{
		super(x, y, width, height, sprite, spriteX, spriteY, pName);
		lastMove = System.currentTimeMillis();
		super.statLife = 30;
		super.statStrength = 10;
		super.statDefense = 10;
	}

	@Override
	public void update(GameContainer gc, int mapWidth, int mapHeight, int delta, Map map) 
	{
		Vector2f trans = new Vector2f(0, 0);
		// Normal speed 0.10f
		float speed = 0.10f;
		
		if (System.currentTimeMillis() > (lastMove+2000) )
		{
			lastMove = System.currentTimeMillis();
			double random = Math.random() * 5;
			setRunning(false);
	     
	    	if(random >= 1 && random < 2)
	    	{//Delta is used to move things on a frame rate independent way
	    		trans.x= speed * delta;
	    		setRunning(true);
				setDirection("droite");
	    	}
	    	else if(random >= 2 && random < 3)
	    	{
	    		trans.x= -speed * delta;
	    		setRunning(true);
	    		setDirection("gauche");
	    	}
	    	else if(random >= 3 && random < 4)
	    	{
	    		trans.y = -speed * delta;
	    		setRunning(true);
	    		setDirection("haut");
	    	}
	    	else if(random >= 4 && random < 5)
	    	{
	    		trans.y = speed * delta;
	    		setRunning(true);
	    		setDirection("bas");
	    	}
		}
		else
		{
			if (isRunning())
			{
				switch (getDirection())
				{
					case "droite":
						trans.x= speed * delta;
						break;
					case "gauche":
						trans.x= -speed * delta;
						break;
					case "haut":
						trans.y = -speed * delta;
						break;
					case "bas":
						trans.y = speed * delta;
						break;
				}
			}
		}
    	
    	moveEntity(trans, mapWidth, mapHeight, map);
	}
}

package com.timerunner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 * The Class Player.
 */
public class Player extends Entity
{ 
	/**
	 * Instantiates a new player.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param sprite the sprite
	 * @throws SlickException the slick exception
	 */
	public Player(float x, float y, int width, int height, String sprite, int spriteX, int spriteY) throws SlickException 
	{
		super(x, y, width, height, sprite, spriteX, spriteY);
	}
 
	/**
	 * Update.
	 *
	 * @param gc the game container
	 * @param mapWidth the map width
	 * @param mapHeight the map height
	 * @param delta the delta
	 * @param map the map
	 */
	@Override
	public void update(GameContainer gc, int mapWidth, int mapHeight, int delta, Map map) 
	{
		Vector2f trans = new Vector2f(0, 0);
		Input input = gc.getInput();
		setRunning(false);
		float speed = 0.15f;
     
    	if(input.isKeyDown(Input.KEY_RIGHT))
    	{
    		trans.x= speed * delta;
    		setRunning(true);
			setDirection("droite");
    	}
     
    	if(input.isKeyDown(Input.KEY_LEFT))
    	{
    		trans.x= -speed * delta;
    		setRunning(true);
    		setDirection("gauche");
    	}
 
    	if(input.isKeyDown(Input.KEY_UP))
    	{//Delta is used to move things on a frame rate independent way
    		trans.y = -speed * delta;
    		setRunning(true);
    		setDirection("haut");
    	}
     
    	if(input.isKeyDown(Input.KEY_DOWN))
    	{
    		trans.y = speed * delta;
    		setRunning(true);
    		setDirection("bas");
    	}
    	
    	moveEntity(trans, mapWidth, mapHeight, map);
	}
}

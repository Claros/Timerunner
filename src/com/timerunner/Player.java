package com.timerunner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Player 
{
	protected Vector2f pos; // Vector contains a value with components x &amp; y
	protected Rectangle box;
	protected SpriteSheet sprite;
    private Animation[] courir;
    private Image[] repos;
    private String direction = "bas";
    private boolean isRunning = false;
 
	public Player(float x, float y, int width, int height, String sprite) throws SlickException 
	{
		this.pos = new Vector2f(x,y);
		this.box = new Rectangle(x, y+36, width, 12);
		this.sprite =  new SpriteSheet(sprite, width, height);
		this.repos = new Image[]{
			this.sprite.getSprite(7, 4),//bas
			this.sprite.getSprite(7, 7),//haut
			this.sprite.getSprite(7, 5),//gauche
			this.sprite.getSprite(7, 6),//droite
		};
		//Constructeur : Animation(SpriteSheet frames, int x1, int y1, int x2, int y2, boolean horizontalScan, int duration, boolean autoUpdate)
		this.courir = new Animation[] {
			new Animation(this.sprite, 6,4,8,4,true, 100, true),//bas
			new Animation(this.sprite, 6,7,8,7,true, 100, true),//haut
			new Animation(this.sprite, 6,5,8,5,true, 100, true),//gauche
			new Animation(this.sprite, 6,6,8,6,true, 100, true)//droite
		};
	}
 
	public void update(GameContainer gc, int mapWidth, int mapHeight, int delta, Map map) 
	{
		Vector2f trans = new Vector2f(0, 0);
		Input input = gc.getInput();
		isRunning = false;
		float speed = 0.15f;
     
    	if(input.isKeyDown(Input.KEY_RIGHT))
    	{
    		trans.x= speed * delta;
			isRunning = true;
			direction = "droite";
    	}
     
    	if(input.isKeyDown(Input.KEY_LEFT))
    	{
    		trans.x= -speed * delta;
			isRunning = true;
			direction = "gauche";
    	}
 
    	if(input.isKeyDown(Input.KEY_UP))
    	{//Delta is used to move things on a frame rate independent way
    		trans.y = -speed * delta;
			isRunning = true;
			direction = "haut";
    	}
     
    	if(input.isKeyDown(Input.KEY_DOWN))
    	{
    		trans.y = speed * delta;
			isRunning = true;
			direction = "bas";
    	}
 
		if (trans.x != 0 && trans.y != 0) 
		{ // If both components aren't null, we reduce them to have constant speed on all directions
			trans.set(trans.x / 1.5f, trans.y / 1.5f);
		}
		box.setLocation(pos.x+trans.x, pos.y+36+trans.y);
		
		if( (pos.x + trans.x) > 0 && (pos.x + trans.x) < (mapWidth-32) && !map.isTileBlocked(box))
		{// Is the player inside the map? (We add (subtract) because of the stone wall) 			
			pos.x += trans.x; 
		}
		else
		{
			box.setX(pos.x);
		}
		
		if( (pos.y+trans.y) > 0 && (pos.y+trans.y) < (mapHeight-48) && !map.isTileBlocked(box))
		{
			pos.y += trans.y;
		}
		else
		{
			box.setY(pos.y+36);
		}
	}
 
	public void render() 
	{
		if(isRunning)
		{
			switch(direction)
    		{
	   			case "bas":
	   				courir[0].draw(pos.x, pos.y);
	    	   	break;
    	   		case "haut":
    	   			courir[1].draw(pos.x, pos.y);
    	   		break;
    	   		case "gauche":
    	   			courir[2].draw(pos.x, pos.y);
    	   		break;
    	   		case "droite":
    	   			courir[3].draw(pos.x, pos.y);
    	   		break;    	
    	   }
       }
       else
       {
    	   switch(direction)
    	   {
	   			case "bas":
	   				repos[0].draw(pos.x, pos.y);
	    	   	break;
    	   		case "haut":
    	   			repos[1].draw(pos.x, pos.y);
    	   		break;
    	   		case "gauche":
    	   			repos[2].draw(pos.x, pos.y);
    	   		break;
    	   		case "droite":
    	   			repos[3].draw(pos.x, pos.y);
    	   		break;    	
    	   }
       }
	}
 
	// Getters and Setters
	public Vector2f getPos() 
	{
		return this.pos;
	}
 
	public float getX() 
	{
		return this.pos.x;
	}
 
	public float getY() {
		return this.pos.y;
	}
 
	public void setPos(Vector2f pos) 
	{
		this.pos = pos;
	}
 
	public Rectangle getBox() 
	{
		return this.box;
	}
 
	public void setBox(Rectangle box) 
	{
		this.box = box;
	}
 
	/*public Image getSprite() 
	{
		return sprite;
	}
 
	public void setSprite(Image sprite) 
	{
		this.sprite = sprite;
	}*/
}

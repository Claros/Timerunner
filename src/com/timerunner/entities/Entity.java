package com.timerunner.entities;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.timerunner.Map;
import com.timerunner.states.GameState;

/**
 * The Class Entity.
 * The Entity is a super class for the characters and Player
 */
public abstract class Entity implements Comparable<Entity>
{
	
	/** The position of the entity. */
	protected Vector2f pos; // Vector contains a value with components x &amp; y
	/** The box used for the collision with objects and others entities */
	protected Rectangle box;
	/** The box used to receive damage */
	protected Rectangle hitbox;
	/** The sprite sheet */
	protected SpriteSheet sprite;
	/** The animation sheet when the entity is moving */
    private Animation[] courir;
	/** The animation sheet when the entity is not moving */
    private Image[] repos;
	/** The direction where the entity go */
    private String direction = "bas";
    /** The running state */
    private boolean isRunning = false;
    /** The last entity touched */
    private Entity entityColliding;
    /** The last entity hited */
    private Entity entityHited;
    /** The dialogs */
	private HashMap<String,String[]> aDialogs;
	
	private int transY;
	
	protected int statStrength;
	protected int statLife;
	protected int statDefense;
    /** The name */
	private String name;
 
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
	public Entity(float x, float y, int width, int height, String sprite, int spriteX, int spriteY, String pName) throws SlickException 
	{
		this.pos = new Vector2f(x,y);
		this.transY = (int) Math.round(0.75*height);
		this.box = new Rectangle(x, y + transY, width-2, (0.25f*height));
		this.hitbox = new Rectangle(x, y, width-2, height);
		this.sprite =  new SpriteSheet(sprite, width, height);
		this.repos = new Image[]{
			this.sprite.getSprite(spriteX+1, spriteY),//bas
			this.sprite.getSprite(spriteX+1, spriteY+3),//haut
			this.sprite.getSprite(spriteX+1, spriteY+1),//gauche
			this.sprite.getSprite(spriteX+1, spriteY+2),//droite
		};
		//Constructeur : Animation(SpriteSheet frames, int x1, int y1, int x2, int y2, boolean horizontalScan, int duration, boolean autoUpdate)
		this.courir = new Animation[] {
			new Animation(this.sprite, spriteX, spriteY, spriteX+2, spriteY,true, 100, true),//bas
			new Animation(this.sprite, spriteX, spriteY+3, spriteX+2, spriteY+3,true, 100, true),//haut
			new Animation(this.sprite, spriteX, spriteY+1, spriteX+2, spriteY+1,true, 100, true),//gauche
			new Animation(this.sprite, spriteX, spriteY+2, spriteX+2, spriteY+2,true, 100, true)//droite
		};
		this.aDialogs = new HashMap<String,String[]>();
		
		this.name = pName;
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
	public abstract void update(GameContainer gc, int mapWidth, int mapHeight, int delta, Map map);
 
	/**
	 * Render.
	 */
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
	
	/**
	 * Move an entity and verify if their is collision.
	 *
	 * @param trans the translation vector
	 * @param mapWidth the map width
	 * @param mapHeight the map height
	 * @param map the map
	 * @return entity the entity colliding with the current entity
	 */
	public Entity moveEntity(Vector2f trans, int mapWidth, int mapHeight, Map map)
	{
		if (trans.x != 0 && trans.y != 0) 
		{ // If both components aren't null, we reduce them to have constant speed on all directions
			trans.set(trans.x / 1.5f, trans.y / 1.5f);
		}
		box.setLocation(pos.x+trans.x, pos.y+transY+trans.y);
		hitbox.setLocation(pos.x+trans.x, pos.y+trans.y);
		
		if( (pos.x + trans.x) > 0 && (pos.x + trans.x) < (mapWidth-32) && !map.isTileBlocked(box) && !isEntityHere(box))
		{// Is the player inside the map? (We add (subtract) because of the stone wall) 			
			pos.x += trans.x; 
		}
		else
		{
			// On revient en arrière
			box.setX(pos.x);
			hitbox.setX(pos.x);
			setRunning(false);
		}
		
		if( (pos.y+trans.y) > 0 && (pos.y+trans.y) < (mapHeight-48) && !map.isTileBlocked(box) && !isEntityHere(box))
		{
			pos.y += trans.y;
		}
		else
		{
			// On revient en arrière
			box.setY(pos.y+transY);
			hitbox.setY(pos.y);
			setRunning(false);
		}
		
		return entityColliding;
	}
	
	/**
	 * Checks if there is entity colliding with the current entity
	 *
	 * @return true, if there is an entity colliding with the current entity
	 */
	public boolean isEntityHere(final Shape pBox)
	{
		ArrayList<Entity> entities = new ArrayList<Entity> ( GameState.getCharacters() );
		// On retire l'entity courante
		entities.remove(this);
		for (Entity e : entities)
		{
			if (pBox.intersects(e.getBox()))
			{
				entityColliding = e;
				return true;
			}
		}
		entityColliding = null;
		return false;
	}
	
	public boolean isEntityHited(final Shape pBox)
	{
		ArrayList<Entity> entities = new ArrayList<Entity> ( GameState.getCharacters() );
		// On retire l'entity courante
		entities.remove(this);
		for (Entity e : entities)
		{
			if (pBox.intersects(e.getHitbox()))
			{
				entityHited = e;
				return true;
			}
		}
		entityHited = null;
		return false;
	}
	
	/**
	 * @return Entity the entityHited
	 */
	public Entity getEntityHited() 
	{
		return entityHited;
	}

	/**
	 * Adds a text dialog au character.
	 *
	 * @param pType the type of the dialog
	 * @param dialogs the dialogs
	 */
	public void addDialog(String pType, String[] dialogs)
	{
		this.aDialogs.put(pType, dialogs);
	}
	
	/**
	 * Gets the dialogs.
	 *
	 * @param pType the type of the dialog
	 * @return the dialogs
	 */
	public String[] getDialog(String pType)
	{
		return this.aDialogs.get(pType);
	}
 
	// Getters and Setters
	/**
	 * Gets the pos.
	 *
	 * @return the pos
	 */
	public Vector2f getPos() 
	{
		return this.pos;
	}
 
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public float getX() 
	{
		return this.pos.x;
	}
 
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public float getY() {
		return this.pos.y;
	}
 
	/**
	 * Sets the pos.
	 *
	 * @param pos the new pos
	 */
	public void setPos(Vector2f pos) 
	{
		this.pos = pos;
	}
	 
	/**
	 * Sets the X pos.
	 *
	 * @param pX the new x of the pos
	 */
	public void setPosX(float pX) 
	{
		this.pos.x = pX;
	}
	 
	/**
	 * Sets the Y pos.
	 *
	 * @param pY the new y of the pos
	 */
	public void setPosY(float pY) 
	{
		this.pos.y = pY;
	}
 
	/**
	 * Gets the box.
	 *
	 * @return the box
	 */
	public Rectangle getBox() 
	{
		return this.box;
	}
 
	/**
	 * Sets the box.
	 *
	 * @param box the new box
	 */
	public void setBox(Rectangle box) 
	{
		this.box = box;
	}

	/**
	 * Gets the hitbox.
	 *
	 * @return the hitbox
	 */
	public Rectangle getHitbox() 
	{
		return hitbox;
	}

	/**
	 * Sets the hitbox.
	 *
	 * @param hitbox the hitbox to set
	 */
	public void setHitbox(Rectangle hitbox) 
	{
		this.hitbox = hitbox;
	}

	/**
	 * Gets the sprite.
	 *
	 * @return the sprite
	 */
	public SpriteSheet getSprite() 
	{
		return sprite;
	}

	/**
	 * Sets the sprite.
	 *
	 * @param sprite the sprite to set
	 */
	public void setSprite(SpriteSheet sprite) 
	{
		this.sprite = sprite;
	}

	/**
	 * Gets the courir.
	 *
	 * @return the courir
	 */
	public Animation[] getCourir() 
	{
		return courir;
	}

	/**
	 * Sets the courir.
	 *
	 * @param courir the courir to set
	 */
	public void setCourir(Animation[] courir) 
	{
		this.courir = courir;
	}

	/**
	 * Gets the repos.
	 *
	 * @return the repos
	 */
	public Image[] getRepos() 
	{
		return repos;
	}

	/**
	 * Sets the repos.
	 *
	 * @param repos the repos to set
	 */
	public void setRepos(Image[] repos) 
	{
		this.repos = repos;
	}

	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public String getDirection() 
	{
		return direction;
	}

	/**
	 * Sets the direction.
	 *
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) 
	{
		this.direction = direction;
	}

	/**
	 * Checks if is running.
	 *
	 * @return the isRunning
	 */
	public boolean isRunning() 
	{
		return isRunning;
	}

	/**
	 * Sets the running.
	 *
	 * @param isRunning the isRunning to set
	 */
	public void setRunning(boolean isRunning) 
	{
		this.isRunning = isRunning;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the statStrength
	 */
	public int getStatStrength() {
		return statStrength;
	}

	/**
	 * @return the statDefense
	 */
	public int getStatDefense() {
		return statDefense;
	}

	/**
	 * @return the statLife
	 */
	public int getStatLife() {
		return statLife;
	}

	/**
	 * @param statLife the statLife to set
	 */
	public void setStatLife(final int statLife) {
		this.statLife = statLife;
	}
	
	public void subStatLife(final int statLife)
	{
		this.statLife -= statLife;
	}

	/** 
	 * On compare les entity en fonction de leur position y
	 * Cela permet de les afficher dans différents plans
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Entity pObj) 
	{
		double y1 = this.pos.y;
		double y2 = pObj.pos.y;
		
		if (y1 > y2)
		{
			return 1;
		}
		else if (y1 == y2)
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
}

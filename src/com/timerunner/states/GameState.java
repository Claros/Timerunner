package com.timerunner.states;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.timerunner.Camera;
import com.timerunner.Config;
import com.timerunner.Entity;
import com.timerunner.Game;
import com.timerunner.Map;
import com.timerunner.Player;
import com.timerunner.Character;

/**
 * The Class GameState.
 */
public class GameState extends GlobalState
{
	public static final int ID = 1;
    
    private int mapHeight, mapWidth;    
    private Player player;
    private Camera camera;
	private Music background;
    private static Map[] map;
    private Graphics renderGraphics;
    //private static ArrayList<Entity> characters;
    private String dialog;
    private static int currentMap;
    private Rectangle extVersInt;
    private Rectangle intVersExt;

	/* (non-Javadoc)
	 * @see com.timerunner.states.GlobalState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		super.init(container, game);
		this.background = new Music("res/military.xm");
		
		//dialog = new String();
		currentMap = Config.CURRENT_MAP.getValue();
		player = new Player(Config.POS_X.getValue(), Config.POS_Y.getValue(), 32, 48, "pics/vx_chara02_c.png", 6, 4, "");
		//characters = new ArrayList<Entity>();
		
		map = new Map[] {new Map("res/map_moyen_age_exterieur.tmx"), new Map("res/map_moyen_age_interieur.tmx")};
		extVersInt = new Rectangle(1375, 927, map[0].getTileWidth()*4, map[0].getTileHeight());
		intVersExt = new Rectangle(970, 2615, map[0].getTileWidth()*16, map[0].getTileHeight());
		// Map size = Tile Size * number of Tiles
		mapWidth = map[currentMap].getWidth() * map[currentMap].getTileWidth(); 
		mapHeight = map[currentMap].getHeight() * map[currentMap].getTileHeight();
		camera = new Camera(map[currentMap], mapWidth, mapHeight);
		
		map[0].addCharacter(new Character(1875, 1515, 32, 48, "pics/vx_chara01_a.png", 0, 0, "PNJ1"));
		map[0].addCharacter(new Character(1875, 1215, 32, 48, "pics/vx_chara01_a.png", 3, 0, "PNJ2"));
		map[0].addCharacter(new Character(1875, 1315, 32, 48, "pics/vx_chara01_a.png", 0, 4, "PNJ3"));
		map[0].addCharacter(new Character(1700, 1250, 32, 48, "pics/vx_chara01_b.png", 0, 4, "PNJ4"));
		map[0].addCharacter(new Character(1700, 1300, 32, 48, "pics/vx_chara01_b.png", 3, 0, "PNJ5"));
		map[0].addCharacter(new Character(1700, 1350, 32, 48, "pics/vx_chara01_b.png", 3, 4, "PNJ6"));
		map[0].addCharacter(new Character(1900, 1200, 32, 48, "pics/vx_chara02_a.png", 6, 4, "PNJ7"));
		map[0].addCharacter(new Character(1800, 1100, 32, 48, "pics/vx_chara02_b.png", 6, 4, "PNJ8"));
		map[0].addCharacter(new Character(1850, 1200, 32, 48, "pics/vx_chara02_d.png", 6, 4, "PNJ9"));
		map[0].addCharacter(new Character(1835, 1100, 32, 48, "pics/vx_chara03_a.png", 6, 4, "PNJ10"));
		map[0].addCharacter(new Character(1925, 1300, 32, 48, "pics/vx_chara03_b.png", 6, 4, "PNJ11"));
		map[0].addCharacter(new Character(1775, 1200, 32, 48, "pics/vx_chara03_c.png", 6, 4, "PNJ12"));
		map[0].addCharacter(new Character(1750, 1300, 32, 48, "pics/vx_chara03_d.png", 3, 0, "PNJ13"));
		map[0].addCharacter(new Character(1725, 1100, 32, 48, "pics/vx_chara03_e.png", 6, 4, "PNJ14"));
		map[0].addCharacter(new Character(1700, 1200, 32, 48, "pics/vx_chara03_f.png", 6, 4, "PNJ15"));
		map[0].addCharacter(new Character(1875, 1300, 32, 48, "pics/vx_chara03_g.png", 6, 4, "PNJ16"));
		map[currentMap].addCharacter(player);
		
		map[0].getCharacter(1).addDialog("collision", new String[] {"Attention !"});
		map[0].getCharacter(2).addDialog("collision", new String[] {"Attention à toi !"});
		map[0].getCharacter(3).addDialog("collision", new String[] {"Attention !"});
		map[0].getCharacter(4).addDialog("collision", new String[] {"Attention !"});
		map[0].getCharacter(5).addDialog("talk", new String[] {"Bla bla bla bla !"});
		map[0].getCharacter(6).addDialog("talk", new String[] {"Bla bla bla bla !"});
		map[0].getCharacter(7).addDialog("talk", new String[] {"Bla bla bla bla !"});
		
		
		// On trie la collection en fonction de la position y...
		// Pour la superposition des personnages
		Collections.sort(map[currentMap].getCharacters());
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException 
	{
		if (!this.background.playing())
		{
			this.background.loop();
		}
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException 
	{
		Config.POS_X.setValue(Math.round(player.getX()));
		Config.POS_Y.setValue(Math.round(player.getY()));
		Config.CURRENT_MAP.setValue(currentMap);
		Config.writeFile();
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		renderGraphics = g;
		g.setColor(Color.white);
		
		camera.translate(g, player);
		for (int i = 0; i < map[currentMap].getLayerCount()-1; i++)
		{
			map[currentMap].render(0, 0, i);
		}
		
		for (Entity chara : map[currentMap].getCharacters())
		{
			chara.render();
			g.drawString(chara.getName(), chara.getX(), chara.getY()-12);
		}
		
		map[currentMap].render(0, 0, map[currentMap].getLayerCount()-1);
    	
		/*
		 * Si on est en mode DEBUG, on affiche les rectangles pour les collisions,
		 * les FPS ainsi que les coordonnées du perso.
		 */
		if (Game.DEBUG)
		{
			g.setColor(Color.red);
			g.draw(extVersInt);
			g.draw(intVersExt);
			g.setColor(Color.white);
			map[currentMap].drawRect(g);
			g.drawString("Xp : " + player.getX() + ", Yp : " + player.getY(), 10 - camera.getTransX(), 25 - camera.getTransY());
			g.drawString("Xp : " + Math.round(player.getX()/32) + ", Yp : " + Math.round(player.getY()/32), 10 - camera.getTransX(), 40 -  camera.getTransY());
			for (Entity chara : map[currentMap].getCharacters())
			{
				g.setColor(Color.white);
				g.draw(chara.getBox());
				g.setColor(Color.black);
				g.draw(chara.getHitbox());
			}
		}
		
		/*
		 * S'il y a collision entre player et character
		 */
		if (!player.isRunning() && player.getContact() != null && player.getContact().getDialog("collision") != null)
		{
			drawDialog(g, player.getContact().getDialog("collision")[0]);
		}
		
		if (dialog != null)
		{
			drawDialog(g, dialog);
		}
	}
	
	public void drawDialog(Graphics g, String text)
	{
		g.setColor(Color.black);
		g.fillRoundRect(0 - camera.getTransX(), 480 - camera.getTransY(), 800, 120, 5);
		g.setColor(Color.white);
		g.fillRoundRect(10 - camera.getTransX(), 490 - camera.getTransY(), 780, 100, 5);
		g.setColor(Color.black);
		g.drawString(text, 15 - camera.getTransX(), 500 - camera.getTransY());
	}
	
	@Override
	public void keyPressed(int key, char c) 
	{
		super.keyPressed(key, c);
		if (key == Config.KEY_TALK.getValue())
		{
			if (dialog == null)
			{
				if (player.isEntityTalkable() != null)
				{
					if (player.isEntityTalkable().getDialog("talk") != null)
					{
						dialog = player.isEntityTalkable().getDialog("talk")[0];
					}
				}
			}
			else
			{
				dialog = null;
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		//ArrayList<Entity> chars = GameState.getCharacters();
		for (Entity chara : map[currentMap].getCharacters())
		{
			chara.update(container, mapWidth, mapHeight, delta, map[currentMap]);
		}
		
		// On trie la collection en fonction de la position y...
		// C'est pour la superposition des personnages
		Collections.sort(map[currentMap].getCharacters());
		
		// Si le joueur passe la porte pour changer de map...
		if (currentMap == 0 && player.getBox().intersects(extVersInt))
		{
			map[currentMap].removeCharacter(player);
			currentMap = 1;
			map[currentMap].addCharacter(player);
			camera = new Camera(map[currentMap], mapWidth, mapHeight);
			player.setPosX(1200);
			player.setPosY(2485);
		}
		else if (currentMap == 1 && player.getBox().intersects(intVersExt))
		{
			map[currentMap].removeCharacter(player);
			currentMap = 0;
			map[currentMap].addCharacter(player);
			camera = new Camera(map[currentMap], mapWidth, mapHeight);
			player.setPosX(1406);
			player.setPosY(968);
		}
	}

	/* (non-Javadoc)
	 * @see com.timerunner.states.GlobalState#getID()
	 */
	@Override
	public int getID() 
	{
		return GameState.ID;
	}
	
	/* (non-Javadoc)
	 * @see com.timerunner.states.GlobalState#getGraphics()
	 */
	@Override
	public Graphics getGraphics()
	{
		return this.renderGraphics;
	}

	/**
	 * @return the characters
	 */
	public static ArrayList<Entity> getCharacters() 
	{
		return map[currentMap].getCharacters();
	}
}

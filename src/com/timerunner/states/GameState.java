package com.timerunner.states;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.timerunner.Camera;
import com.timerunner.Config;
import com.timerunner.Game;
import com.timerunner.Map;
import com.timerunner.entities.Character;
import com.timerunner.entities.Entity;
import com.timerunner.entities.MovingCharacter;
import com.timerunner.entities.Player;

/**
 * The Class GameState.
 * The GameState manage the Game Engine.
 */
public class GameState extends GlobalState
{
	public static final int ID = 1;
    /** The map height. */ 
    private int mapHeight, mapWidth;   
    /** The player */ 
    private Player player;
    /** The camera */ 
    public static Camera camera;
    /** The sound of a shoot */ 
    private Sound shoot;
    /** The array of the maps */ 
    private static Map[] map;
    /** The graphics */ 
    private Graphics renderGraphics;
    /** The dialog to draw */
    private String dialog;
    /** The index of the current map */ 
    private static int currentMap;
    /** The box to go from the outside to the inside */
    private Rectangle extVersInt;
    /** The box to go from the inside to the outside */
    private Rectangle intVersExt;

	/* (non-Javadoc)
	 * @see com.timerunner.states.GlobalState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		super.init(container, game);
		this.shoot = new Sound("res/shoot.wav");
		
		currentMap = (Config.CURRENT_MAP.getValue() < 2 ? Config.CURRENT_MAP.getValue() : 0);
		player = new Player(Config.POS_X.getValue(), Config.POS_Y.getValue(), 32, 48, "pics/vx_chara02_c.png", 6, 4, "");
		
		map = new Map[] {new Map("res/map_moyen_age_exterieur.tmx"), new Map("res/map_moyen_age_interieur.tmx")};
		extVersInt = new Rectangle(1375, 927, map[0].getTileWidth()*4, map[0].getTileHeight());
		intVersExt = new Rectangle(970, 2510, map[0].getTileWidth()*16, map[0].getTileHeight());
		// Map size = Tile Size * number of Tiles
		mapWidth = map[currentMap].getWidth() * map[currentMap].getTileWidth(); 
		mapHeight = map[currentMap].getHeight() * map[currentMap].getTileHeight();
		camera = new Camera(mapWidth, mapHeight);
		
		// Initialisation des tous les PNJ
		map[0].addCharacter(new Character(325, 2850, 32, 48, "pics/vx_chara02_c.png", 9, 0, "  ?", 999, 999, 999));
		map[0].addCharacter(new Character(1792, 1725, 32, 48, "pics/vx_chara01_a.png", 0, 4, " Garde 1"));
		map[0].addCharacter(new Character(1870, 1725, 32, 48, "pics/vx_chara01_a.png", 0, 0, " Garde 2"));
		map[0].addCharacter(new Character(1948, 1725, 32, 48, "pics/vx_chara01_a.png", 0, 4, " Garde 3"));
		map[0].addCharacter(new Character(1375, 935, 32, 48, "pics/vx_chara01_a.png", 0, 4, " Garde 4"));
		map[0].addCharacter(new Character(1425, 935, 32, 48, "pics/vx_chara01_a.png", 0, 0, " Garde 5"));
		map[0].addCharacter(new Character(1475, 935, 32, 48, "pics/vx_chara01_a.png", 0, 4, " Garde 6"));
		map[0].addCharacter(new MovingCharacter(2165, 1031, 32, 48, "pics/vx_chara03_a.png", 0, 4, "Villageois 1"));
		map[0].addCharacter(new MovingCharacter(2363, 1028, 32, 48, "pics/vx_chara03_b.png", 0, 4, "Villageois 2"));
		map[0].addCharacter(new MovingCharacter(2333, 883, 32, 48, "pics/vx_chara03_b.png", 9, 0, "Villageois 3"));
		map[0].addCharacter(new MovingCharacter(1747, 1026, 32, 48, "pics/vx_chara03_a.png", 9, 0, "Villageois 4"));
		map[0].addCharacter(new MovingCharacter(1131, 1137, 32, 48, "pics/vx_chara03_a.png", 6, 0, "Villageois 5"));
		
		map[1].addCharacter(new Character(1183, 128, 32, 48, "pics/vx_chara02_c.png", 3, 0, " Roi"));
		map[1].addCharacter(new Character(1216, 128, 32, 48, "pics/vx_chara02_c.png", 6, 0, "  Reine"));
		map[1].addCharacter(new MovingCharacter(1769, 147, 32, 48, "pics/vx_chara02_a.png", 9, 0, "Cuisinier"));
		map[1].addCharacter(new MovingCharacter(2093, 175, 32, 48, "pics/vx_chara02_a.png", 9, 4, "Boucher"));
		map[1].addCharacter(new MovingCharacter(168, 1374, 32, 48, "pics/vx_chara01_a.png", 0, 4, " Garde 7"));
		map[1].addCharacter(new MovingCharacter(405, 1442, 32, 48, "pics/vx_chara01_a.png", 0, 0, " Garde 8"));
		map[1].addCharacter(new MovingCharacter(603, 1340, 32, 48, "pics/vx_chara01_a.png", 0, 4, " Garde 9"));
		map[1].addCharacter(new Character(2207, 1834, 32, 48, "pics/vx_chara02_b.png", 0, 0, "Alchimiste"));
		map[1].addCharacter(new Character(515, 137, 32, 48, "pics/vx_chara01_b.png", 0, 4, " Voleur"));
		map[1].addCharacter(new MovingCharacter(74, 581, 32, 48, "pics/vx_chara02_c.png", 9, 4, "Nourrice 1"));
		map[1].addCharacter(new MovingCharacter(493, 666, 32, 48, "pics/vx_chara02_c.png", 9, 4, "Nourrice 2"));
		
		map[currentMap].addCharacter(player);
		
		// On donne des dialogues aux PNJ
		map[0].getCharacter(0).addDialog("talk", new String[] {
			"Bonjour Time Runner.", 
			"Utilise les flèches pour te déplacer. :p", 
			"Pour parler appuies sur C. :p", 
			"Pour tirer appuies sur espace.",
			"Pour couper le son appuies sur F2.",
			"Pour mettre en pause le jeu appuies sur échap.",
			"Sinon tu es perdu au Moyen âge.",
			"Il y a un château au nord."
		});
		map[0].getCharacter(1).addDialog("talk", new String[] {"Bouge de là !"});
		map[0].getCharacter(2).addDialog("talk", new String[] {"T'es qui toi ?!"});
		map[0].getCharacter(3).addDialog("talk", new String[] {"Bouge de là !"});
		map[0].getCharacter(4).addDialog("talk", new String[] {"Bouge de là !"});
		map[0].getCharacter(5).addDialog("talk", new String[] {"T'es qui toi ?!"});
		map[0].getCharacter(6).addDialog("talk", new String[] {"Bouge de là !"});
		map[0].getCharacter(7).addDialog("collision", new String[] {"Attention à toi !"});
		
		map[1].getCharacter(0).addDialog("talk", new String[] {
				"Comment osez-vous déranger sa majesté ?!", 
				"Que voulez-vous ?",
				"Très bien.",
				"Qu'il en soit ainsi."
			});
		map[1].getCharacter(1).addDialog("talk", new String[] { "Parlez à mon époux." });
		map[1].getCharacter(7).addDialog("talk", new String[] { "Boum !" });
		map[1].getCharacter(8).addDialog("talk", new String[] { "Je ne trouve pas le trésor..." });
		
		
		// On trie la collection en fonction de la position y...
		// Pour la superposition des personnages
		Collections.sort(map[currentMap].getCharacters());
	}
	
	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#enter(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException 
	{
		if (!super.background.playing() && PLAYMUSIQUE)
		{
			this.background.loop();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#leave(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
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
			g.drawString(chara.getName(), chara.getX() - ((chara.getName().length()*6)/2), chara.getY()-15);
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
		
		if (player.isShooting())
		{
			player.getShoot().render(g);
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
			mapWidth = map[currentMap].getWidth() * map[currentMap].getTileWidth(); 
			mapHeight = map[currentMap].getHeight() * map[currentMap].getTileHeight();
			camera = new Camera(mapWidth, mapHeight);
			player.setPosX(1200);
			player.setPosY(2405);
		}
		else if (currentMap == 1 && player.getBox().intersects(intVersExt))
		{
			map[currentMap].removeCharacter(player);
			currentMap = 0;
			map[currentMap].addCharacter(player);
			mapWidth = map[currentMap].getWidth() * map[currentMap].getTileWidth(); 
			mapHeight = map[currentMap].getHeight() * map[currentMap].getTileHeight();
			camera = new Camera(mapWidth, mapHeight);
			player.setPosX(1426);
			player.setPosY(968);
		}
		
		// On met en pause le jeu si la fenêtre n'a pas le focus
		if (!container.hasFocus())
		{
			super.startPause();
		}
		
		if (player.isShooting())
		{
			if (player.getShoot().update(delta, player, mapWidth, mapHeight, map[currentMap]))
			{
				if (player.getEntityHited() != null)
				{
					player.getEntityHited().subStatLife(1);
					if (player.getEntityHited().getStatLife() == 0)
					{
						System.out.println(map[currentMap].getCharacters().remove(player.getEntityHited()));
					}
				}
				player.finishShoot();
			}
		}
	}
	
	/**
	 * Draw a text dialog.
	 *
	 * @param g the graphics
	 * @param text the text to draw
	 */
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
			if (player.isEntityTalkable() != null && player.isEntityTalkable().getDialog("talk") != null)
			{
				boolean vNext = false;
				String[] vDialogs = player.isEntityTalkable().getDialog("talk");
				if (dialog == null)
				{
					dialog = player.isEntityTalkable().getName() + " : " + vDialogs[0];
					return;
				}
				for (String vDialog : vDialogs)
				{
					if (vNext)
					{
						dialog = player.isEntityTalkable().getName() + " : " + vDialog;
						return;
					}
					if ((player.isEntityTalkable().getName() + " : " + vDialog).equals(dialog))
					{
						vNext = true;
					}
				}
				if (vNext && !dialog.equals( player.isEntityTalkable().getName() + " : " + vDialogs[vDialogs.length - 1] ) )
				{
					dialog = player.isEntityTalkable().getName() + " : " + vDialogs[vDialogs.length - 1];
				}
				else
				{
					dialog = null;
				}
			}
			else if (player.isEntityTalkable() == null && dialog != null)
			{
				dialog = null;
			}
		} else if (key == Config.KEY_SHOOT.getValue() && dialog == null)
		{
			if (!player.isShooting())
			{
				if (PLAYMUSIQUE)
				{
					shoot.play();
				}
				player.shoot();
			}
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
	 * @return the characters list
	 */
	public static ArrayList<Entity> getCharacters() 
	{
		return map[currentMap].getCharacters();
	}
}

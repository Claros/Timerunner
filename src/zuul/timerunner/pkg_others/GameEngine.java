package zuul.timerunner.pkg_others;

import java.util.HashMap;
import zuul.timerunner.pkg_commands.*;
import zuul.timerunner.pkg_rooms.*;
import zuul.timerunner.pkg_items.*;

/**
 *  GameEngine class. Manage all the logical of the game.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  ASTIER Naji & ROBIN Yohann
 */
public class GameEngine
{
    
    /** The aParser. */
    private static Parser aParser;    
    /** The aGui. */
    private static UserInterface aGui;    
    /** The nombre dappels. */
    //private int nombreDappels;    
    /** The player. */
    private static Player player;    
    /** The Room list. */
    private static HashMap <String, Room> aListRoom; 
    
    /**
     * Constructor for objects of class GameEngine.
     */
    public GameEngine()
    {
        GameEngine.aParser = new Parser();
        GameEngine.player = new Player ("Isaac Clark", 50); 
        createRooms();
        //this.nombreDappels = 0;
    }

    /**
     * Sets the this.aGui.
     *
     * @param userInterface 
     */
    public void setGUI(final UserInterface userInterface)
    {
        aGui = userInterface;
        printWelcome();
    }
    
    public static UserInterface getGUI()
    {
        return GameEngine.aGui;
    }
    
    public static Parser getParser ()
    {
        return aParser;
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        GameEngine.aGui.print("\n");
        GameEngine.aGui.println("Welcome to Zuul Time Runner!");
        GameEngine.aGui.println("Zuul Time Runner is a new, incredibly boring adventure game.");
        GameEngine.aGui.println("Type '" + CommandWord.HELP + "' if you need help.");
        GameEngine.aGui.print("\n");
        GameEngine.aGui.println(GameEngine.player.getCurrentRoom().getLongDescription());
        //GameEngine.aGui.println(GameEngine.player.getDescription());
        GameEngine.aGui.showImage(GameEngine.player.getCurrentRoom().getImageName());
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        GameEngine.aListRoom = new HashMap <String, Room> (); 
        Room cour, tourDeGuet, tourDeGarde, chateau, salleAManger, trone, salleDuTresor; 
        
      
        // create the rooms
        cour = new Room("in the castle courtyard", "cour.png");
        tourDeGuet = new Room("near the watchtower", "tour2.png");
        tourDeGarde = new Room("near the guard tower", "tour1.png");
        chateau = new Room("inside the castle", "chateau.png");
        salleAManger = new Room("in the dining room", "dinning-room.png");
        trone = new Room("in the throne room of the King", "throne-room.png");
        salleDuTresor = new Room("in the treasure room", "treasure.png");
        
        // initialise room exits
        cour.setExit("east",tourDeGarde );
        cour.setExit("west", tourDeGuet);
        cour.setExit("north", chateau);
        
        tourDeGuet.setExit("east", cour);
        
        tourDeGarde.setExit("west", cour);
        
        chateau.setExit("south", cour);
        chateau.setExit("east", salleAManger);
        chateau.setExit("west", trone);
        
        salleAManger.setExit("west", chateau);
        
        trone.setExit("east", chateau);
        trone.setExit("west", salleDuTresor);
        
        salleDuTresor.setExit("east", trone);
        
        GameEngine.player.setCurrentRoom(cour);
        
        cour.addCharacter(new Character("John", "Hello, I help you only if you give me your helmet.", "helmet", cour));
        tourDeGarde.addCharacter(new Character("guard1", "Stop ! That's an order !", "", tourDeGarde));
        tourDeGuet.addCharacter(new Character("archer1", "One shot. One death. Don't move !", "", tourDeGuet));
        trone.addCharacter(new Character("King", "I'm the king of this land, who are you stranger?", "", trone));
        trone.addCharacter(new Character("guard2", "How dare you disturb the King ?", "", trone));
        trone.addCharacter(new Character("guard3", "Yeah ! How dare you ?", "", trone));
        
        tourDeGarde.getItemList().addItem("sword", "a sword", 2, false); 
        tourDeGarde.getItemList().addItem("helmet", "a helmet", 1, false);
        cour.getItemList().addItem("boulder", "a gigantic boulder", 9999, false);
        salleDuTresor.getItemList().addItem("beamer", new Beamer ( 2, "The beamer", false));
        salleAManger.getItemList().addItem("chicken", "A roast chicken", 0.5, true);
        salleAManger.getItemList().addItem("apples", "A lot of apples", 1, true);
        salleAManger.getItemList().addItem("bread", "A great bread maked by the baker", 0.2, true);
        
        GameEngine.aListRoom.put ("cour", cour);
        GameEngine.aListRoom.put ("tourDeGuet", tourDeGuet);
        GameEngine.aListRoom.put ("tourDeGarde", tourDeGarde);
        GameEngine.aListRoom.put ("chateau", chateau);
        GameEngine.aListRoom.put ("salleAManger", salleAManger);
        GameEngine.aListRoom.put ("trone", trone);
        GameEngine.aListRoom.put ("salleDuTresor", salleDuTresor);
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     *
     * @param commandLine the command line
     */
    public static void interpretCommand(final String commandLine) 
    {
        GameEngine.getGUI().println("\n" + commandLine);
        Command vCommand = aParser.getCommand(commandLine);                                                                                                                                                                        
        if (vCommand == null)
        {
            GameEngine.getGUI().println("I don't know what you mean...");
        }
        else
        {
            vCommand.execute(player);
        }
    }
    
    /**
     * Gets the list room.
     *
     * @return the list room
     */
    public static HashMap <String,Room> getListRoom ()
    {
        return GameEngine.aListRoom; 
    }
}

package zuul.timerunner.pkg_others;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.net.URL;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import zuul.timerunner.pkg_rooms.Room;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @author  ASTIER Naji & ROBIN Yohann
 * @version 18/02/2013
 */
public class UserInterface implements ActionListener, KeyEventDispatcher
{
    private JFrame myFrame;
    private JTextField entryField;
    private JTextArea log;
    private JLabel image;
    private JButton buttonLook, buttonEast, buttonWest, buttonNorth, buttonSouth; 
    private HashMap<String, JButton> directionButton;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     */
    public UserInterface()
    {
        directionButton = new HashMap<String, JButton>();
        createGUI();
    }

    /**
     * Print out some text into the text area.
     */
    public void print(final String text)
    {
        log.append(text);
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println(final String text)
    {
        log.append(text + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Show an image file in the interface.
     */
    public void showImage(final String imageName)
    {
        //URL imageURL = this.getClass().getResource(imageName);
        ImageIcon icon = new ImageIcon(imageName);
        //ImageIcon icon = new ImageIcon(imageURL);
        image.setIcon(icon);
        myFrame.pack();
    }

    /**
     * Enable or disable input in the input field.
     */
    public void enable(final boolean on)
    {
        entryField.setEditable(on);
        buttonLook.setEnabled(on);
        for (String vS : directionButton.keySet())
        {
            directionButton.get(vS).setEnabled(on);
        }
        if(!on)
            entryField.getCaret().setBlinkRate(0);
    }
    
    /**
     * Enable the buttonn where an exit exist
     * @param pExits the exits list
     */
    public void enableButton(final HashMap<String,Room> pExits)
    {
        // We unactivate all the buttons
        for (String vS : directionButton.keySet())
        {
            directionButton.get(vS).setEnabled(false);
        }
        
        for (String vS : pExits.keySet())
        {
            directionButton.get(vS).setEnabled(true);
        }
    }

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        myFrame = new JFrame("Time Runner");
        entryField = new JTextField(34);

        log = new JTextArea();
        log.setEditable(false);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        
        JScrollPane listScroller = new JScrollPane(log);
        listScroller.setPreferredSize(new Dimension(400, 600));
        listScroller.setMinimumSize(new Dimension(400, 500));

        JPanel panel = new JPanel(),
               panelButton = new JPanel(),
               panelImage = new JPanel(),
               panelLine1 = new JPanel(),
               panelLine2 = new JPanel(),
               panelLine3 = new JPanel(),
               panelLine4 = new JPanel();
        
        image = new JLabel();
        
        buttonLook = new JButton("Regarder");
        buttonNorth = new JButton("↑");
        buttonSouth = new JButton("↓");
        buttonEast = new JButton("→");
        buttonWest = new JButton("←");
        
        // Centering the image
        panelImage.setLayout(new GridBagLayout());
        panelImage.add(image);
        panelImage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Adding the buttons 
        panelLine1.setLayout(new BoxLayout(panelLine1, BoxLayout.LINE_AXIS));
        panelLine2.setLayout(new BoxLayout(panelLine2, BoxLayout.LINE_AXIS));
        panelLine3.setLayout(new BoxLayout(panelLine3, BoxLayout.LINE_AXIS));
        panelLine4.setLayout(new BoxLayout(panelLine4, BoxLayout.LINE_AXIS));
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.PAGE_AXIS));
        
        panelLine1.add(buttonNorth);
        panelLine2.add(buttonWest);
        panelLine2.add(buttonEast);
        panelLine3.add(buttonSouth); 
        panelLine4.add(buttonLook);
        
        panelButton.add(panelLine1);
        panelButton.add(panelLine2);
        panelButton.add(panelLine3);
        panelButton.add(panelLine4);

        // Creating the global panel
        panel.setLayout(new BorderLayout());
        panel.add(panelImage, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(panelButton, BorderLayout.WEST);
        panel.add(entryField, BorderLayout.SOUTH);

        myFrame.getContentPane().add(panel, BorderLayout.CENTER);

        // add some event listeners to some components
        myFrame.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing( WindowEvent e ) 
            {
                killFrame();
            }
        });
        
        //myFrame.addKeyListener(this);
        KeyboardFocusManager manager =  KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher( this );

        entryField.addActionListener(this);
        buttonLook.addActionListener(this);
        buttonNorth.addActionListener(this);
        buttonSouth.addActionListener(this);
        buttonEast.addActionListener(this);
        buttonWest.addActionListener(this);

        myFrame.pack();
        myFrame.setVisible(true);
        entryField.requestFocus();
        
        directionButton.put("east", buttonEast);
        directionButton.put("west", buttonWest);
        directionButton.put("north", buttonNorth);
        directionButton.put("south", buttonSouth);
    }

    /**
     * Actionlistener interface for entry textfield and jButton
     */
    @Override
    public void actionPerformed(final ActionEvent e) 
    {
        if (e.getSource() == entryField)
        {
            processCommand();
        } 
        else if (e.getSource() == buttonLook)
        {
            GameEngine.interpretCommand("look");
        }
        else if (e.getSource() == buttonNorth)
        {
            GameEngine.interpretCommand("go north");
        }
        else if (e.getSource() == buttonSouth)
        {
            GameEngine.interpretCommand("go south");
        }
        else if (e.getSource() == buttonEast)
        {
            GameEngine.interpretCommand("go east");
        }
        else if (e.getSource() == buttonWest)
        {
            GameEngine.interpretCommand("go west");
        }
    }

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        //boolean finished = false;
        String input = entryField.getText();
        entryField.setText("");

        GameEngine.interpretCommand(input);
    }

    /* (non-Javadoc)
     * @see java.awt.KeyEventDispatcher#dispatchKeyEvent(java.awt.event.KeyEvent)
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) 
    {
        if(event.getID() == KeyEvent.KEY_RELEASED && !entryField.isFocusOwner())
        {
            if (event.getKeyCode() == KeyEvent.VK_DOWN) 
            {
                GameEngine.interpretCommand("go south");
            } 
            else if (event.getKeyCode() == KeyEvent.VK_UP)
            {
                GameEngine.interpretCommand("go north");
            } 
            else if (event.getKeyCode() == KeyEvent.VK_LEFT)
            {
                GameEngine.interpretCommand("go west");
            } 
            else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                GameEngine.interpretCommand("go east");
            }
        }
        return false;
    }
    
    /**
     * Kill the frame to close the windows.
     */
    public void killFrame()
    {
         myFrame.setVisible( false );
         myFrame.dispose();
    }
}
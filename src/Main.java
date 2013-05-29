import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class Main implements ActionListener
{
	private JFrame aFrame;
	private JButton aZuul, aSlick;
	public static final int VERSION = 1;
	
	public Main()
	{
		this.aFrame = new JFrame("Time Runner");
		
		JPanel vPanel = new JPanel();
		this.aZuul = new JButton("Tester la version zuul");
		this.aSlick = new JButton("Tester la version slick2D");
		
		vPanel.add(this.aZuul);
		vPanel.add(this.aSlick);
		
		this.aZuul.addActionListener(this);
		this.aSlick.addActionListener(this);
		
		this.aFrame.getContentPane().add(vPanel, BorderLayout.CENTER);
		this.aFrame.pack();
		this.aFrame.setVisible(true);
		
		try
		{
			URL url = new URL("http://www.esiee.fr/~robiny/version.txt");
			InputStream vFichier = url.openConnection().getInputStream();
			
		    BufferedReader vReader = new BufferedReader( new InputStreamReader( vFichier )  );

		    int version = Integer.parseInt(vReader.readLine());
		    if (version > Main.VERSION)
		    {
		    	int vRep = JOptionPane.showOptionDialog(this.aFrame, 
		    			"Voulez-vous télécharger la nouvelle version ?", 
		    			"Une mise est est disponible", 
		    			JOptionPane.YES_NO_CANCEL_OPTION,
		    		    JOptionPane.QUESTION_MESSAGE,
		    		    null, 
		    		    new String[]{"Oui","Non"}, 
		    		    "Oui");
		    	
		    	if (vRep == 0)
		    	{
		    	    URL jar = new URL(vReader.readLine());
		            URLConnection connection = jar.openConnection();
		            int fileLength = connection.getContentLength();

		            if (fileLength == -1)
		            {
		                // URL invalide
		                return;
		            }

		            InputStream input = connection.getInputStream();
		            //String fileName = jar.getFile().substring(jar.getFile().lastIndexOf('/') + 1);
		            String fileName = "timerunner.jar";
		            FileOutputStream writeFile = new FileOutputStream(fileName);
		            byte[] buffer = new byte[1024];
		            int read, avancement = 0;
		    		
		            JProgressBar vPB = new JProgressBar(0, fileLength);
		            vPB.setPreferredSize(new Dimension(175, 20));
		    		JLabel vLabel = new JLabel("Téléchargement en cours de " + fileName);
		    		
		    		JPanel panelDialog = new JPanel();
		    		panelDialog.add(vLabel);
		    		panelDialog.add(vPB);
		            
		            JDialog dialog = new JDialog(this.aFrame, "Téléchargement en cours...");
		            dialog.getContentPane().add(panelDialog, BorderLayout.CENTER);
		            dialog.pack();
		            dialog.setVisible(true);
		            
		            while ((read = input.read(buffer)) > 0)
		            {
		                writeFile.write(buffer, 0, read);
		                avancement += read;
		                vPB.setValue(avancement);
		            }
		            
		            writeFile.flush();
		            writeFile.close();
		            input.close();
		            dialog.dispose();
		            
		    	    JOptionPane.showMessageDialog(this.aFrame, "Téléchargement terminé");
		    	    this.aFrame.dispatchEvent(new WindowEvent(this.aFrame, WindowEvent.WINDOW_CLOSING));
		    	}
		    }
		    else
		    {
		    	System.out.println("Le jeu est à jour");
		    }
		    vReader.close();
		}
		catch (IOException e)
		{
			
		}
	}
	
	public static void main(final String[] pArgs)
	{
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.aZuul)
		{
			this.aFrame.dispatchEvent(new WindowEvent(this.aFrame, WindowEvent.WINDOW_CLOSING));
			zuul.timerunner.Game.main(null);
		} else if (e.getSource() == this.aSlick)
		{
			this.aFrame.dispatchEvent(new WindowEvent(this.aFrame, WindowEvent.WINDOW_CLOSING));
			com.timerunner.Game.main(null);
		}
	}
}

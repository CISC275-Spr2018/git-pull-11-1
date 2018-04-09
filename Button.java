 
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;
 
/* 
 * ButtonDemo.java requires the following files:
 *   images/right.gif
 *   images/middle.gif
 *   images/left.gif
 */
public class Button extends JPanel
                        implements ActionListener {
    protected JButton b1, b2, b3;
    
    int counter = 0;
    public Button() {

 
        b1 = new JButton("Start/stop");
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        b1.setMnemonic(KeyEvent.VK_D);
        b1.setActionCommand("stop");
 
        b2 = new JButton("turn");
        b2.setVerticalTextPosition(AbstractButton.CENTER);
        b2.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        b2.setMnemonic(KeyEvent.VK_D);
        b2.setActionCommand("right");
        
        //Listen for actions on buttons 1
        b1.addActionListener(this);
        b2.addActionListener(this);

 
        b1.setToolTipText("Click this button to start or stop");

        b2.setToolTipText("Click this button to make turn");
        //Add Components to this container, using the default FlowLayout.
        add(b1);
        add(b2);

    }
 
    public void actionPerformed(ActionEvent e) {
        if ("stop".equals(e.getActionCommand())) {
        	//write code makes the orc run
    		EventQueue.invokeLater(new Runnable(){
    			public void run(){

    				Controller.t.start();
    			}
    			});	
    			
        	b1.setActionCommand("start");

        } if ("start".equals(e.getActionCommand())) {
        	//write code makes the orc stop
        	EventQueue.invokeLater(new Runnable(){
    			public void run(){

    				Controller.t.stop();
    			}
    			});	
        	b1.setActionCommand("stop");
 
        }if ("right".equals(e.getActionCommand())){
        	//counter = counter + 1;
        	//counter = (counter % 4) +1;
        	if(Model.getDirect() == Direction.NORTHEAST){
        		Model.setDirect(Direction.SOUTHEAST);
        	}
        	if(Model.getDirect()==Direction.NORTHWEST){
        		Model.setDirect(Direction.NORTHEAST);
        	}
           	if(Model.getDirect()==Direction.SOUTHWEST){
           		Model.setDirect(Direction.NORTHWEST);
        	}
           	if(Model.getDirect()==Direction.SOUTHEAST){
           		Model.setDirect(Direction.SOUTHWEST);
        	}

			//Model.ModelReDir(counter);
        }
    }
 
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Button.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
 
    /**
     * Create the GUI and show it.  For thread safety, 
     * this method should be invoked from the 
     * event-dispatching thread.
     */
    static void createAndShowGUI() {
 
        //Create and set up the window.
        JFrame frame = new JFrame("ButtonDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        Button newContentPane = new Button();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
       
    }
 

}

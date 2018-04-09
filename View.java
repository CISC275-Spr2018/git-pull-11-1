/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/


import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.EventQueue; 
import java.awt.Graphics; 
import java.awt.event.ActionEvent; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import javax.imageio.ImageIO; 
import javax.swing.AbstractAction; 
import javax.swing.Action; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.Timer;

public class View extends JFrame{

    	final static int frameWidth = 800;//500
    	final static int frameHeight = 800;//300
    	final static int imgWidth = 165;//165
	final static int imgHeight = 165;
	final int frameCount = 10;
	private int picNum = 0;
	private BufferedImage[][] pics;
	private Direction dir = Direction.SOUTHEAST;
	private int xloc;
	private int yloc;
    	private DrawPanel drawPanel= new DrawPanel();
	final int frameStartSize = 800;

	View () {
        add(drawPanel);           
		// load any of the images that you would want into the picNames, in case you want to do anything
    		// other than just move the orc
    		String[] picNames = {"images/orc/orc_forward_southeast.png", 
    			"images/orc/orc_forward_southwest.png", "images/orc/orc_forward_west.png",
    			"images/orc/orc_forward_south.png", "images/orc/orc_forward_northwest.png",
    			"images/orc/orc_forward_northeast.png", "images/orc/orc_forward_north.png", 
    			"images/orc/orc_forward_east.png"};
    		// Initialize pics with a give 2D array
    		pics = new BufferedImage[picNames.length][frameCount];
    		for(int j = 0; j < picNames.length; j++) {
    			// load the image
    			BufferedImage img = createImage(picNames[j]);
    			// create the frameCount of the orc from that certain png to make it walk(change)
	    		pics[j] = new BufferedImage[frameCount];
	    		for(int i = 0; i < frameCount; i++) {
	    			// all of the different movements of that image
	    			pics[j][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
	    		}
    		}
        setBackground(Color.gray);                 
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                 
	setSize(getWidth(), getHeight());
	setVisible(true);
        pack();	
	}
	// Override the paint method in JPanel, and use getPicDir to get the right image
	// corresponding to the correct direction
	public void paint(Graphics g) {
		g.drawImage(pics[getPicDir(dir)][picNum], xloc, yloc, Color.gray, this);
	}

	public void drawPanel() {
		drawPanel.repaint();
	}

	private BufferedImage createImage(String fname){
    		
		BufferedImage bufferedImage;
    		
    		try {
    			// Method now takes a String (fname) in order to get the 
			// right image to load for all the images passed through
    			bufferedImage = ImageIO.read(new File(fname));
    			return bufferedImage;
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		return null;
	}

	public int getWidth() {
		return frameWidth;
	}
	public int getHeight() {
		return frameHeight;
	}
	public int getImageWidth() {
		return imgWidth;
	}
	public int getImageHeight() {
		return imgHeight;
	}
	// I wanted this to be a switch statement, so that would be the only thing to change this into
	// but switch-case statements needed ints and was having trouble with getting the enums to work
	// in my favor with converting to ints, but I found a way to compare the strings, which works
	// so it is just an alternative to what I originally wanted
	private int getPicDir(Direction d) {
		if (d.getName().equals(Direction.SOUTHEAST.getName())) { return 0;}
		else if (d.getName().equals(Direction.SOUTHWEST.getName())) { return 1;}
		else if (d.getName().equals(Direction.WEST.getName())) { return 2;}
		else if (d.getName().equals(Direction.SOUTH.getName())) { return 3;}
		else if (d.getName().equals(Direction.NORTHWEST.getName())) { return 4;}
		else if (d.getName().equals(Direction.NORTHEAST.getName())) { return 5;}
		else if (d.getName().equals(Direction.NORTH.getName())) { return 6;}
		else if (d.getName().equals(Direction.EAST.getName())) { return 7;}
		else { return 99999;}// Never going to happen, just a default for compiling
	}

    	@SuppressWarnings("serial")
	private class DrawPanel extends JPanel {

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
	    	picNum = (picNum + 1) % frameCount;
	    	g.drawImage(pics[getPicDir(dir)][picNum], xloc, yloc, Color.gray, this);
		}

		public Dimension getPreferredSize() {
			return new Dimension(frameStartSize, frameStartSize);
		}
	}

	// update location, continue through the subimages(picNum),, and then repaint the image
	public void update(int xloc, int yloc, Direction d) {//, JFrame frame) {
		this.xloc = xloc;
		this.yloc = yloc;
		dir = d;
	}
}

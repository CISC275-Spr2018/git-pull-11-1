
import java.awt.EventQueue; 
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.Timer;



public class Controller{

	private static Model model;
	private static View view;
	public static Timer t;
	private static Timer tJumpFire;
    	final int drawDelay = 75; //msec
	final int drawDelayJumpFire = 25;
	private Action drawAction;
	private Action drawActionJumpFire;
	private int actionCtr = 0;
	
	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		drawAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				model.setDirect(view.getDir());
				if (view.getActionJumpFire()) {
					t.stop();
					tJumpFire.start();
				} else {
					model.updateLocationAndDirection();
					view.update(model.getX(), model.getY(), model.getDirect());
					view.drawPanel();
				}
			}
		};
		drawActionJumpFire = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (actionCtr++ == 7) {
					t.start();
					tJumpFire.stop();
					model.setDirect(view.getDirFromJumpFire(view.getDir()));
					model.updateLocationAndDirection();
					view.update(model.getX(), model.getY(), model.getDirect());
					view.drawPanel();
					view.setActionJumpFire(false);
					actionCtr = 0;
				}
				if (view.getActionJumpFire()) {
					model.setDirect(view.getDir());
					model.updateLocationAndDirection();
					view.update(model.getX(), model.getY(), model.getDirect());
					view.drawPanel();
				} 
			}
		};
	}

	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            	public void run() {
                	Controller ctrllr = new Controller();
			t = new Timer(ctrllr.drawDelay, ctrllr.drawAction);
			tJumpFire = new Timer(ctrllr.drawDelayJumpFire, ctrllr.drawActionJumpFire);
                	t.start();
            	}
        });
	}
}

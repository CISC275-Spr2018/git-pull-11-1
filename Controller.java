
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
    	final int drawDelay = 50; //msec
	private Action drawAction;
	
	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		drawAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
                 	model.setDirect(view.getDir());
				model.updateLocationAndDirection();
				view.update(model.getX(), model.getY(), model.getDirect());
				view.drawPanel();
			}
		};
		

	}

	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Controller ctrllr = new Controller();
		        t = new Timer(ctrllr.drawDelay, ctrllr.drawAction);	
                t.start();
            }
        });
	}
}

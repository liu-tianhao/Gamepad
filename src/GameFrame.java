/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameFrame {
	/*public JLabel jl4 = new JLabel("0");
	public JLabel jl5 = new JLabel("0");
	public JLabel jl6 = new JLabel("0");*/
	

    public GameFrame() {
        Frame app = new Frame("GameFrame");

        app.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        app.setLocation(100, 100);
        GamePanel drawB = new GamePanel();
        app.add(drawB, BorderLayout.CENTER);
      
        app.pack();
        app.setVisible(true);
        drawB.stateJfram();
        drawB.initGame();
        
        drawB.gameStart();
        
        
    }

   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	new GameFrame();
    	
    // TODO code application logic here
    }
}

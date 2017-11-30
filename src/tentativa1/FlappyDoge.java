/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentativa1;


import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlappyDoge extends JFrame {
	
    JPanel menu;
    
    public FlappyDoge() {
        menu = new StartMenu(this);
    	initUI();
    }
    
    private void initUI() {
        add(menu);
        
        setResizable(false);
        pack();        
        setTitle("Flappy Doge");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void switchPanel(){
    	remove(menu);
        menu = new Board();
        add(menu);
        menu.requestFocusInWindow();
        pack();
    }
}

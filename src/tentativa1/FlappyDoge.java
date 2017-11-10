/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentativa1;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlappyDoge extends JFrame {
	
	JPanel menu;
	URL urlColisao = Board.class.getResource("SomColisao.wav");
    URL urlPulo = Board.class.getResource("SomPulo.wav");
    
    public FlappyDoge() {
        menu = new StartMenu(this);
    	initUI();
    }
    
    public FlappyDoge(int i) {
        menu = new Board(urlColisao,urlPulo);
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
    	this.dispose();
    	FlappyDoge ex = new FlappyDoge(1);
        ex.setVisible(true);
    }
}
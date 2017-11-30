/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentativa1;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author labccet
 */
public abstract class GameOver {
    
       public static void drawGameOver(Graphics g, Board board) throws InterruptedException {        
        board.setOpaque(true);
        g.drawImage(board.getBackgroundBoard(), 0, 0, null);
        board.getGameOverLabel().setBounds((800 - board.getGameOverIcon().getIconWidth())/2, 100, board.getGameOverIcon().getIconWidth(), board.getGameOverIcon().getIconHeight());
        board.add(board.getGameOverLabel());
        ImageIcon boxIcon = new ImageIcon("src/resources/CaixaPontos.png");
        Image box = boxIcon.getImage();
        g.drawImage(box, (800-boxIcon.getIconWidth())/2, 200, null);
        String recordPontos = Float.toString(board.getPoints().getRecord()[0].getPontos());
        String recordJogador = board.getPoints().getRecord()[0].getJogador();
        String recordPontos2 = Float.toString(board.getPoints().getRecord()[1].getPontos());
        String recordJogador2 = board.getPoints().getRecord()[1].getJogador();
        String recordPontos3 = Float.toString(board.getPoints().getRecord()[2].getPontos());
        String recordJogador3 = board.getPoints().getRecord()[2].getJogador();

        g.setFont(new Font("Century Gothic", Font.PLAIN, 45));
        g.drawString(Float.toString(board.getPoints().getPoints()), 440, 256);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 35));
        g.drawString(recordPontos, 370, 460);
        g.drawString(recordPontos2, 260, 460);
        g.drawString(recordPontos3, 490, 460);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 23));
        g.drawString(recordJogador, 373, 350);        
        g.drawString(recordJogador2, 260, 350);        
        g.drawString(recordJogador3, 480, 350);
        board.getPoints().setPoints(0);       
        board.add(board.jButton1);
        board.jButton1.grabFocus();
        
    }
}

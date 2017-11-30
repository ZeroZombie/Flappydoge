/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentativa1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;


public class Board extends JPanel implements ActionListener {
    
    javax.swing.JButton jButton1;
    private Bonus bonus;
    private Image background;
    private Timer timer;
    private Doge doge;
    private ArrayList<Wall> walls;
    private ArrayList<Integer> deleteWalls;
    private boolean ingame;
    private float intervalo;
    private int velocidade;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 600;
    private final int DELAY = 10;
    private Score points;
    private float generator;
    private AudioClip somColisao = Applet.newAudioClip(Board.class.getResource("SomColisao.wav"));
    private ImageIcon gameOverIcon = new ImageIcon(getClass().getResource("/resources/GameOverPNG.png")); //Imagem da label de Game Over
    private JLabel gameOverLabel = new JLabel(gameOverIcon); //Label de Game Over
		

    private final int[][] pos = {{0, 470}};

    public Board() {
        setLayout(null);        
        deleteWalls = new ArrayList<>();
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        loadImage();
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        this.setEnabled(true);
        doge = new Doge(ICRAFT_X, ICRAFT_Y);
        walls = new ArrayList<>();
        timer = new Timer(DELAY, this);
        points = new Score();
        
        jButton1 = new javax.swing.JButton();
        ImageIcon buttonIcon = new ImageIcon(getClass().getResource("/resources/BotaoProntoPNG.png"));
        jButton1.setIcon(buttonIcon);
        jButton1.setBorder(BorderFactory.createEmptyBorder());
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
         
        jButton1.setBounds(348, 500, buttonIcon.getIconWidth(), buttonIcon.getIconHeight());
        initBoard();
    }

    private void initBoard() {
        velocidade = 1;
        intervalo = 300;
        generator = 0;
        points.setPoints(0);
        ingame = true;
        bonus = new Bonus(-50,-50,1);
        doge.initDoge();
        Gerador.gerarParedes(walls,500);
        Gerador.gerarParedes(walls,800);
        

        timer.start();
    }
    
    private void loadImage() {

            ImageIcon ii = new ImageIcon("src/resources/background.png");
            background = ii.getImage();        
        }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {
        	g.drawImage(background, 0, 0, null);
            drawObjects(g);

        } else {

            try {
                GameOver.drawGameOver(g, this);
            } catch (InterruptedException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

        g.drawImage(doge.getImage(), doge.getX(), doge.getY(), this);
        
        for (Wall a : walls) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
        }
        
        g.drawImage(bonus.getImage(), bonus.getX(), bonus.getY(), this);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        g.drawString("Pontos: " + points.getPoints(), 5, 25);
    }

    
     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {    
        remove(jButton1);
        remove(gameOverLabel);
        walls.clear();
        deleteWalls.clear();
        doge.y = ICRAFT_Y;
        initBoard();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (ingame){
            updateCraft();
            updateWalls();
            updateBonus();
            

        checkCollisions();
        
        generator +=1;
        if(generator >= intervalo){
            generator=0;
            Gerador.gerarParedes(walls,800);
            if (points.getPoints()%5==0)
                bonus = Gerador.gerarBonus(1000,doge.getImgAtual());
        }

        }
        
        repaint();
    }
    
    private void pontuar(){
        points.addPoints();
        if (points.getPoints()%5 == 0 && points.getPoints() <= 40){
            velocidade += 1;
            intervalo -= 50;
        }
    }


    private void updateCraft() {

            doge.move();
    }
    
    private void updateBonus() {

            bonus.move(velocidade);
    }

    private void updateWalls() {

        for (int i = 0; i < walls.size(); i++) {

                Wall a = walls.get(i);
                if (a.move(velocidade)){
                    pontuar();
                }
                
                if(!(a.getVisible())){
                    deleteWalls.add(i);
                } 
        }
        
        for (int i = (deleteWalls.size()-1) ; i>=0 ; i--){
            walls.remove(i);
        }
        deleteWalls.clear();
    }

    public void checkCollisions() {
    	if (!ingame){
    		return;
    	}
        Rectangle colisaoJogador = doge.getBounds();
        doge.gotBonus(bonus);
        if(Wall.colide(walls,colisaoJogador) || doge.getForaDaTela()){
            ingame = false;
            doge.setForaDaTela(false);
            somColisao.play();
            points.setRecord();
            timer.stop();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_SPACE && ingame){
        	doge.jump();
            }
        }
    }
    
    public Score getPoints() {
        return points;
    }
    
    public Image getBackgroundBoard() {
        return background;
    }
    
    public ImageIcon getGameOverIcon() {
        return gameOverIcon;
    }

    public JLabel getGameOverLabel() {
        return gameOverLabel;
    }
}

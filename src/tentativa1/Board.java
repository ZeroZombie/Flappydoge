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
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
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
    private Image background;
    private Timer timer;
    private Bird bird;
    private ArrayList<Wall> walls;
    private ArrayList<Integer> deleteWalls;
    private ArrayList<Rectangle> pointMark;
    private boolean ingame;
    private float intervalo;
    private int velocidade;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 600;
    private final int DELAY = 10;
    private long startTime;
    private long endTime; 
    private float points;
    private float record;
    private float generator;
    private URL urlColisao;
    private URL urlPulo;
    private AudioClip somColisao;
    private static AudioClip somPulo;
    private ImageIcon gameOverIcon = new ImageIcon(getClass().getResource("/resources/GameOverPNG.png")); //Imagem da label de Game Over
    private JLabel gameOverLabel = new JLabel(gameOverIcon); //Label de Game Over
		

    private final int[][] pos = {{0, 470}};

    public Board(URL urlColisao, URL urlPulo) {
        setLayout(null);
        this.urlColisao = urlColisao;
        this.urlPulo = urlPulo;
        this.somPulo = Applet.newAudioClip(urlPulo);
        this.somColisao = Applet.newAudioClip(urlColisao);
        deleteWalls = new ArrayList<>();
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        loadImage();
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        bird = new Bird(ICRAFT_X, ICRAFT_Y);
        walls = new ArrayList<>();
        timer = new Timer(DELAY, this);
        record = 0;
        
        jButton1 = new javax.swing.JButton();
        //jButton1.setText("Reiniciar");
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
         
        jButton1.setBounds(348, 320, buttonIcon.getIconWidth(), buttonIcon.getIconHeight());
        initBoard();
    }

    private void initBoard() {
        velocidade = 1;
        intervalo = 300;
        generator = 0;
        points = 0;
        ingame = true;
        startTime = System.currentTimeMillis();
        gerar(500);
        gerar(800);
        
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
                drawGameOver(g);
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

        g.drawImage(bird.getImage(), bird.getX(), bird.getY(), this);
        
        for (Wall a : walls) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
        }

        g.setColor(Color.WHITE);
        g.drawString("Time: " + (int)points, 5, 15);
        g.drawString("Record: " + record, 5, 30);
    }

    private void drawGameOver(Graphics g) throws InterruptedException {        
        setOpaque(true);
        g.drawImage(background, 0, 0, null);
        gameOverLabel.setBounds((800-gameOverIcon.getIconWidth())/2, 100, gameOverIcon.getIconWidth(), gameOverIcon.getIconHeight());
        add(gameOverLabel);
        
        if (points>record)
            record=points;
        
        points = 0;       
        this.add(jButton1);
        
    }
     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {    
        remove(jButton1);
        remove(gameOverLabel);
        walls.clear();
        deleteWalls.clear();
        bird.y = ICRAFT_Y;
        initBoard();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();
        if (ingame){
        updateCraft();
        updateWalls();

        checkCollisions();
        
        generator +=1;
        if(generator >= intervalo){
        	generator=0;
        	gerar(800);
        }
        }
        
        repaint();
    }
    
    private void pontuar(){
        points += 0.5;
        if (points%5==0 && points<=20){
            velocidade+=1;
            intervalo -= 50;
        }
    }
    
    private void gerar(int x){
            Random numero = new Random();
            int baixo = 90+numero.nextInt(500);
            walls.add(new Wall(x, baixo,2));
            walls.add(new Wall(x, baixo-750,1));
    }
    
    private void inGame() {
        
        if (!ingame) {
            timer.stop();
        }
    }

    private void updateCraft() {

            bird.move();
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
        Rectangle r3 = bird.getBounds();

        for (Wall wall : walls) {
            Rectangle r2 = wall.getBounds();
            if (r3.intersects(r2)) {
                ingame = false;
                somColisao.play();
            }
        }
        

    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (ingame){
        	bird.keyPressed(e);
            }
        }
    }
    
    public static AudioClip getSomPulo(){
    	return somPulo;
    }
}

package tentativa1;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class StartMenu extends JPanel implements ActionListener{
	private Image background;
	TAdapter listener;
	ImageIcon labelImage = new ImageIcon(getClass().getResource("/resources/PressSpaceToPlayPNG.png"));
	Image image = labelImage.getImage();
	FlappyDoge frame;
        AlphaComposite composite;
	float alpha=1;
	float alphaAnterior=1;
	Timer meuTimer = new Timer(100, this);
	double buffer=0;

        
	
	StartMenu(FlappyDoge frame){
            this.frame = frame;
            loadImage();
            setLayout(null);
            setFocusable(true);
            setPreferredSize(new Dimension(800, 600));
            listener = new TAdapter();
            addKeyListener(listener);
            meuTimer.start();
	}
	
	private void loadImage(){
		ImageIcon ii = new ImageIcon("src/resources/backgroundStart.jpeg");
        background = ii.getImage();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		Graphics2D g2d = (Graphics2D) g;
		composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		g2d.setComposite(composite);
		g.drawImage(image, (800-labelImage.getIconWidth())/2, 330, null);
		buffer++;
		alpha=(float) ((float) (Math.sin(buffer)/2)+0.5);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
	
	private class TAdapter extends KeyAdapter {	
        @Override
        public void keyReleased(KeyEvent e) {
        	keyPressede(e);
        }
    }
	
	public void keyPressede(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE ) {
        	meuTimer.stop();
        	this.setVisible(false);
        	this.removeKeyListener(listener);
        	frame.switchPanel();
        }


	}
}

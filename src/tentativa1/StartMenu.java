package tentativa1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class StartMenu extends JPanel implements ActionListener{
	private Image background;
	TAdapter listener;
	ImageIcon labelImage = new ImageIcon(getClass().getResource("/resources/PressSpaceToPlayPNG.png"));
	JLabel Label = new JLabel(labelImage);
	CopyBird frame;
	
	StartMenu(CopyBird frame){
		this.frame = frame;
		loadImage();
		setLayout(null);
        setFocusable(true);
        setPreferredSize(new Dimension(800, 600));
        Label.setBounds((800-labelImage.getIconWidth())/2, 330, labelImage.getIconWidth(), labelImage.getIconHeight());
        add(Label);
        listener = new TAdapter();
        addKeyListener(listener);
	}
	
	private void loadImage(){
		ImageIcon ii = new ImageIcon("src/resources/backgroundStart.jpeg");
        background = ii.getImage();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
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
        	System.out.print("teste");
        	this.setVisible(false);
        	this.removeKeyListener(listener);
        	frame.switchPanel();
        }

	}
}

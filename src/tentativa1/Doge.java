/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentativa1;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
public class Doge extends Sprite {

    private double dy;
    private boolean subindo;
    private int tempoSubindo;
    private int imgAtual;
    private AudioClip SomPulo = Applet.newAudioClip(Board.class.getResource("SomPulo.wav"));
    private AudioClip SomBonus = Applet.newAudioClip(Board.class.getResource("SomBonus.wav"));
    private boolean foraDaTela;
    public Doge(int x, int y) {
        super(x, y);

        initDoge();
    }

    public void initDoge() {
        dy = 3;
        loadImage("src/resources/copybird01.png");
        getImageDimensions();
        subindo = false;
        tempoSubindo = 0;
        imgAtual = 1;
    }

    public void move() {

        y += dy;
        if(!subindo){
            if (dy<3)
                dy+=2;

            if (y < 1) {
                y = 1;
            }
        }
        tempoSubindo+=1;
        if (tempoSubindo == 15){
            subindo = false;
            tempoSubindo = 0;
        }
        if (y >= 565 || y<=-1) {
            foraDaTela=true;
        }
    }
    
    public Boolean getForaDaTela(){
        return foraDaTela;
    }
    public void setForaDaTela(boolean x){
        foraDaTela=x;
    }

    public void jump() {
            SomPulo.play();
            dy = -3;
            subindo = true;
            tempoSubindo = 0;

    }
    public void gotBonus(Bonus bonus){
        if (this.getBounds().intersects(bonus.getBounds())){
            SomBonus.play();
            setImage(bonus.getImage());
            bonus.pego();
            imgAtual = bonus.getNumImg();
        }
    }
    
    private void setImage(Image image){
        this.image = image;
    }
    
    public int getImgAtual(){
        return imgAtual;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentativa1;
import java.awt.Image;
/**
 *
 * @author pepej
 */
public class Wall extends Sprite {
    private int originalX;
    private boolean visible;
    private boolean passada;
    
    public Wall(int x, int y, int lado) {
        super (x,y);
        this.originalX = x;
        initWall(lado);
        visible = true;
        passada = false;
    }
    private void initWall(int lado) {
        if (lado%2==0)
            loadImage("src/resources/pipe0.png");
        else
            loadImage("src/resources/pipe1.png");
        getImageDimensions();
    }
    
    public boolean move(float velocidade){
        if (x < -60){
            visible = false;
            return false;
        }
        x -= velocidade;
        if (x<= 40-width && passada == false){
            passada = true;
            return true;
        }
        return false;
            
    }
    
    public boolean getVisible (){
        return visible;
    }
    
}

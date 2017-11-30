/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentativa1;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Wall extends Sprite {
    private boolean visible;
    private boolean passada;
    
    public Wall(int x, int y, int lado, boolean passada) {
        super (x,y);
        initWall(lado);
        visible = true;
        this.passada = passada;
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
    
    public static boolean colide(ArrayList<Wall> walls, Rectangle jogador){
        for (Wall wall : walls) {
            Rectangle parede = wall.getBounds();
            if (parede.intersects(jogador)) {
                return true;
            }
        }
        return false;
    }
    
}

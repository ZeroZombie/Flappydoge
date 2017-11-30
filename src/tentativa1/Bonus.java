/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentativa1;

/**
 *
 * @author labccet
 */
public class Bonus extends Sprite{
    private int numImg;
    Bonus(int x, int y, int atual){
        super(x,y);
        numImg = Gerador.imageBonus(atual);
        loadImage("src/resources/copybird0"+numImg+".png");
        getImageDimensions();
    }
    public void move(float velocidade){
        x -= velocidade;
    }
    
    public void pego(){
        x = -60;
    }
    
    public int getNumImg(){
        return numImg;
    }
}

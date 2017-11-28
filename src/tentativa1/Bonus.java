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
    Bonus(int x, int y){
        super(x,y);
        loadImage(Gerador.imageBonus());
        getImageDimensions();
    }
    public void move(float velocidade){
        x -= velocidade;
    }
}

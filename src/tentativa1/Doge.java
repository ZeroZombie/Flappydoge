/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentativa1;

import java.awt.event.KeyEvent;

public class Doge extends Sprite {

    private double dy;
    private boolean subindo;
    private int tempoSubindo;

    public Doge(int x, int y) {
        super(x, y);

        initCraft();
    }

    private void initCraft() {
        dy = 3;
        loadImage("src/resources/copybird01.png");
        getImageDimensions();
        subindo = false;
        tempoSubindo = 0;
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
        if (y > 550) {
            y = 550;
        }
    }

    public void jump() {
            Board.getSomPulo().play();
            dy = -3;
            subindo = true;
            tempoSubindo = 0;

    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentativa1;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author pepej
 */
public class Gerador {
    public static void gerarParedes(ArrayList<Wall> walls,int x){
        Random numero = new Random();
        int baixo = 90+numero.nextInt(500);
        walls.add(new Wall(x, baixo,2,false));
        walls.add(new Wall(x, baixo-750,1,true));
    }
}

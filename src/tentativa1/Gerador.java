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
public abstract class Gerador {
    public static void gerarParedes(ArrayList<Wall> walls,int x){
        Random numero = new Random();
        int baixo = 90+numero.nextInt(500);
        walls.add(new Wall(x, baixo,2,false));
        walls.add(new Wall(x, baixo-750,1,true));
    }
    public static Bonus gerarBonus(int x,int atual){
        Random numero = new Random();
        int y = 100+numero.nextInt(500);
        return new Bonus(x,y,atual);
    }
    public static int imageBonus(int atual){
        Random numero = new Random();  
        int valor;
        do{
            valor = numero.nextInt(5);
        }while(valor==atual);
        return valor;
    }
}

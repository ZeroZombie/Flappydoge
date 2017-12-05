/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentativa1;


import javax.swing.JOptionPane;


/**
 *
 * @author Beatriz
 */
public class Score {
    private Points[] records;
    private int point;
 
    
    
    Score() {
        records = new Points[3];
        point = 0;
        Points recorde0 = new Points("",0);
        Points recorde1 = new Points("",0);
        Points recorde2 = new Points("",0);
        records[0]=recorde0;
        records[1]=recorde1;
        records[2]=recorde2;
    }

    public Points[] getRecord() {
        return records;
    }
    
    public int getPoints(){
        return point;
    }
    
    public void addPoints(){
        point ++;
    }
    
    public void setPoints(int point) {
        this.point = point;
    }
    
    public void setRecord() {
       for(int cont=0; cont<3; cont++) {
          if(records[cont]==null || records[cont].getPontos()<point){
            for (int subs=2; subs> cont; subs--){
                records[subs] = records[subs-1];
            }
            Points novo = new Points(inputNomeDoJogador(), point);
              
            records[cont] = novo;
            return;
          } 
       }
        
    }
    
    private String inputNomeDoJogador() {
        String nomeJogador = 
        JOptionPane.showInputDialog(null,
                "Insira seu nome (mínimo de 3 caracteres):");
        if (nomeJogador==null) return "";
        while (nomeJogador.length() < 3) {
            nomeJogador =
            JOptionPane.showInputDialog(null,
                    "Insira seu nome (Eu disse TRÊS CARACTERES!):");
        }
        
        return nomeJogador.substring(0, 3);
    }



    
}

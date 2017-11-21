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
public class Points {
    private String jogador;
    private int pontos;
    
    Points(String jogador, int pontos) {
        this.jogador = jogador;
        this.pontos = pontos;
    }

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
    
}

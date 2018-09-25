/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iftm.ec.lfa;

/**
 *
 * @author marco
 */
public class Transicao {
    private int estadoInicial;
    private char caracter;
    private int estadoFinal;
    
    public Transicao(){}
    
    public Transicao(int estadoInicial, char caracter, int estadoFinal){
        this.estadoInicial = estadoInicial;
        this.caracter = caracter;
        this.estadoFinal = estadoFinal;
    }

    public int getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(int estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public int getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(int estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    
    
    
}

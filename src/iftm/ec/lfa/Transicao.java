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
    private int e_inicial;
    private char caracter;
    private int e_final;
    
    public Transicao(int e_inicial, char caracter, int e_final){
        this.e_inicial = e_inicial;
        this.caracter = caracter;
        this.e_final = e_final;
    }

    public int getE_inicial() {
        return e_inicial;
    }

    public void setE_inicial(int e_inicial) {
        this.e_inicial = e_inicial;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public int getE_final() {
        return e_final;
    }

    public void setE_final(int e_final) {
        this.e_final = e_final;
    }
    
    
}

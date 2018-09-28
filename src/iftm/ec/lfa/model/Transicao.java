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
    private String estadoInicial;
    private String simbolo;
    private String estadoFinal;
    
    public Transicao(){}
    
    public Transicao(String estadoInicial, String simbolo, String estadoFinal){
        this.estadoInicial = estadoInicial;
        this.simbolo = simbolo;
        this.estadoFinal = estadoFinal;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(String estadoFinal) {
        this.estadoFinal = estadoFinal;
    } 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iftm.ec.lfa;

import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class Automato {
    ArrayList<Transicao> transicoes;
    ArrayList<Integer> estadosfinais;
    int estadoInicial;
    
    public Automato() {
        this.transicoes = new ArrayList<Transicao>();
        this.estadosfinais = new ArrayList<Integer>();
    }

    public ArrayList<Transicao> getTransicoes() {
        return transicoes;
    }

    public ArrayList<Integer> getEstadosfinais() {
        return estadosfinais;
    }

    public int getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(int estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public void addTransicao(Transicao transicao){
        this.transicoes.add(transicao);
    }
    
    public void addEstadoFinal(int estado){
        this.estadosfinais.add(estado);
    }
}

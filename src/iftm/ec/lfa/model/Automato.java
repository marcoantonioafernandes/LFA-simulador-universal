/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iftm.ec.lfa.model;

import iftm.ec.lfa.model.Transicao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marco
 */
public class Automato {
    private List<Transicao> transicoes;
    private List<String> estadosFinais;
    private List<String> alfabeto;
    private List<String> estados;
    private String estadoInicial;
    private String sentencasPassoAPasso;
    
    public Automato() {
        this.transicoes = new ArrayList<Transicao>();
        this.estadosFinais = new ArrayList<String>();
        this.alfabeto = new ArrayList<String>();
        this.estados = new ArrayList<String>();
    }

    public List<Transicao> getTransicoes() {
        return transicoes;
    }

    public List<String> getEstadosfinais() {
        return estadosFinais;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public void addTransicao(Transicao transicao){
        this.transicoes.add(transicao);
    }
    
    public void addEstadoFinal(String estado){
        this.estadosFinais.add(estado);
    }
    
    public void addSimboloAlfabeto(String simbolo){
        //Antes de adicionar um simbolo no alfabeto verifica se o mesmo já foi adicionado
        if(!this.alfabeto.contains(simbolo))
            this.alfabeto.add(simbolo);
    }
    
    public void addEstado(String estado){
        if(!this.estados.contains(estado))
            this.estados.add(estado);
        
    }

    public List<String> getAlfabeto() {
        return alfabeto;
    }

    public List<String> getEstados() {
        return estados;
    }
    
    public boolean validaAFD(){
        /*
            Cria uma matriz nXm onde n = estados e n = simbolos do alfabeto
            E preenche essa lista com as relações em que o estado possui uma transição 
            com o símbolo
        */
        int n = this.estados.size();
        int m = this.alfabeto.size();
        int matrizValidacao[][] = new int[n][m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                matrizValidacao[i][j] = 0;
            }
        }
        
        for(int i=0;i<this.transicoes.size();i++){
            String simbolo = this.transicoes.get(i).getSimbolo();
            String estadoInicial = this.transicoes.get(i).getEstadoInicial();
            int indiceSimbolo = this.alfabeto.indexOf(simbolo);
            int indiceEstado = this.estados.indexOf(estadoInicial);
            if(matrizValidacao[indiceEstado][indiceSimbolo] == 0){
                matrizValidacao[indiceEstado][indiceSimbolo] = 1;
            } else {
                //caso exista mais de transicão do estado estados[indiceEstado] 
                //com o simbolo alfabeto[indiceAlfabeto] 
                return false;
            }
        }
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrizValidacao[i][j] == 0){
                    //Caso não exista transição do estado i com o caracter j
                    return false;
                }
            }
        }
        return true;
    }

    public String getSentencasPassoAPasso() {
        return sentencasPassoAPasso;
    }

    public void setSentencasPassoAPasso(String sentencasPassoAPasso) {
        this.sentencasPassoAPasso = sentencasPassoAPasso;
    }
       
}

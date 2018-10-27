/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iftm.ec.lfa.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marco
 */
public class LinguagemRegular {
    private List<String> vn;
    private List<String> vt;
    private List<String> proposicoes;
    private String s;
    
    public LinguagemRegular(){
        this.vn = new ArrayList<String>();
        this.vt = new ArrayList<String>();
        this.proposicoes = new ArrayList<String>();
    }

    public List<String> getVn() {
        return vn;
    }

    public void setVn(List<String> vn) {
        this.vn = vn;
    }

    public List<String> getVt() {
        return vt;
    }

    public void setVt(List<String> vt) {
        this.vt = vt;
    }

    public List<String> getProposicoes() {
        return proposicoes;
    }

    public void setProposicoes(List<String> proposicoes) {
        this.proposicoes = proposicoes;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
    
    public void addVt(String s){
        this.vn.add(s);
    }
    
    public void addProposicao(String s){
        this.proposicoes.add(s);
    }
    
    
    
    //Estados na linguagem serão representados por A seguido do numero do estado
    public LinguagemRegular converteAFDparaLinguagemRegular(Automato m){
        LinguagemRegular lr = new LinguagemRegular();
        for(int i=0;i<m.getAlfabeto().size();i++){
            //Adicionando simbolos do vocabulário terminal
            lr.addVt(m.getAlfabeto().get(i));
        }
        
        Map<String, String> mapaVN = new HashMap<>();
         
        String estadoInicial = m.getEstadoInicial();
        
        for(int i=0;i<m.getEstados().size();i++){
            String estado = m.getEstados().get(i);
            char simboloTransicao = 'A';
            if(estado.equals(estadoInicial)){
                mapaVN.put(estado, "S");
            } else {
                mapaVN.put(estado, String.valueOf(simboloTransicao++));
            }
        }
        
        for(int i=0;i<m.getEstados().size();i++){
            String estado = m.getEstados().get(i);
            String simboloTransicao = mapaVN.get(estado);
            String proposicao = "";
            
            
            
            for(int j=0;j<m.getTransicoes().size();j++){
                Transicao transicao = m.getTransicoes().get(j);
                if(estado.equals(transicao.getEstadoInicial())){
                    if(proposicao.equals("")){
                        proposicao = simboloTransicao + " -> " + transicao.getSimbolo() 
                                + transicao.getEstadoFinal();
                    }
                    proposicao+= " | " + transicao.getSimbolo() + transicao.getEstadoFinal();
                }
            }
            if(m.getEstadosfinais().contains(estado)){
                proposicao += "e";
            }
            lr.addProposicao(s);
        }
        
        
        
        return lr;
    }
    
}

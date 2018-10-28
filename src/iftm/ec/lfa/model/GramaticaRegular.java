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
public class GramaticaRegular {
    private List<String> vn;
    private List<String> vt;
    private List<String> proposicoes;
    private String s;
    private String grHtml;
    
    public GramaticaRegular(){
        this.vn = new ArrayList<String>();
        this.vt = new ArrayList<String>();
        this.proposicoes = new ArrayList<String>();
        this.grHtml = "";
    }

    public List<String> getVn() {
        return vn;
    }

    public void setVn(List<String> vn) {
        this.vn = vn;
    }
    
    public void addVn(String estado){
        this.vn.add(estado);
    }

    public List<String> getVt() {
        return vt;
    }

    public void setVt(List<String> vt) {
        this.vt = vt;
    }
    
    public void addVt(String estado){
        this.vt.add(estado);
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
    
    public void addProposicao(String s){
        this.proposicoes.add(s);
    }
    
    public String getGrHtml(){
        return this.grHtml;
    }
    
    public void buildGrHtml(){
        this.grHtml += "<html> VT = {  ";
        
        for(int i=0;i<this.vt.size();i++){
            if(i != this.vt.size()-1){
                this.grHtml += this.vt.get(i) + ", ";
            }else {
                this.grHtml += this.vt.get(i) + "} <br>";
            }
        }
        
        this.grHtml += "VN = {  ";
        
        for(int i=0;i<this.vn.size();i++){
            if(i != this.vn.size()-1){
                this.grHtml += this.vn.get(i) + ", ";
            }else {
                this.grHtml += this.vn.get(i) + "} <br>";
            }
        }
        
        this.grHtml += "S = { " + this.s + "} <br>";
        
        this.grHtml += "P = { <br>";
        for(int i=0;i<this.proposicoes.size();i++){
            if(i != this.proposicoes.size()-1){
                this.grHtml += "&emsp;" + this.proposicoes.get(i) + "<br> ";
            }else {
                this.grHtml += "&emsp;" + this.proposicoes.get(i) + " <br> }";
            }
        }
        
        this.grHtml += "</html>";
    }
    
    
    //Estados na linguagem serão representados por A seguido do numero do estado
    public void converteAFDparaLinguagemRegular(Automato m){
        for(int i=0;i<m.getAlfabeto().size();i++){
            //Adicionando simbolos do vocabulário terminal
            this.vt.add(m.getAlfabeto().get(i));
        }
        
        Map<String, String> mapaVN = new HashMap<>();
         
        String estadoInicial = m.getEstadoInicial();
        char simboloTransicao = 'A';
        for(int i=0;i<m.getEstados().size();i++){
            String estado = m.getEstados().get(i);
            
            if(estado.equals(estadoInicial)){
                mapaVN.put(estado, "S");
                this.vn.add("S");
            } else {
                mapaVN.put(estado, String.valueOf(simboloTransicao));
                this.vn.add(String.valueOf(simboloTransicao++));
            }
        }
        
        for(int i=0;i<m.getEstados().size();i++){
            String estado = m.getEstados().get(i);
            String simboloTransicao2 = mapaVN.get(estado);
            String proposicao = "";
            
            
            
            for(int j=0;j<m.getTransicoes().size();j++){
                Transicao transicao = m.getTransicoes().get(j);
                if(estado.equals(transicao.getEstadoInicial())){
                    if(proposicao.equals("")){
                        proposicao = simboloTransicao2 + " -> " + transicao.getSimbolo() 
                                + mapaVN.get(transicao.getEstadoFinal());
                    }else {
                        proposicao+= " | " + transicao.getSimbolo() + mapaVN.get(transicao.getEstadoFinal());
                    }
                }
            }
            
            if(m.getEstadosfinais().contains(estado)){
                proposicao += " | e";
            }
            
            if(estado.equals(m.getEstadoInicial())){
                this.s = proposicao;
            }
            
            
            this.addProposicao(proposicao);
        }
    }
    
}

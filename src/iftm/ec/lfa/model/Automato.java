/*
 * MIT License - Copyright (c) 2018 Francielle da Silva Nunes, Marco Antônio de Almeida Fernandes
 * Criada em 25 nov 2018
 */
package iftm.ec.lfa.model;

import iftm.ec.lfa.model.Transicao;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Francielle da Silva Nunes
 * @author Marco Antônio de Almeida Fernandes
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

    public void addTransicao(Transicao transicao) {
        this.transicoes.add(transicao);
    }

    public void addEstadoFinal(String estado) {
        this.estadosFinais.add(estado);
    }

    public void addSimboloAlfabeto(String simbolo) {
        // Antes de adicionar um simbolo no alfabeto verifica se o mesmo já foi adicionado
        if (!this.alfabeto.contains(simbolo)) {
            this.alfabeto.add(simbolo);
        }
    }

    public void addEstado(String estado) {
        if (!this.estados.contains(estado)) {
            this.estados.add(estado);
        }
    }

    public List<String> getAlfabeto() {
        return alfabeto;
    }

    public List<String> getEstados() {
        return estados;
    }

    public String getSentencasPassoAPasso() {
        return sentencasPassoAPasso;
    }

    public void setSentencasPassoAPasso(String sentencasPassoAPasso) {
        this.sentencasPassoAPasso = sentencasPassoAPasso;
    }

}

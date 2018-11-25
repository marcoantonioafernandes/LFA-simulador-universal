/*
 * MIT License - Copyright (c) 2018 Francielle da Silva Nunes, Marco Antônio de Almeida Fernandes
 * Criada em 25 nov 2018
 */
package iftm.ec.lfa.model;

/** 
 * @author Francielle da Silva Nunes
 * @author Marco Antônio de Almeida Fernandes
 */
public class Transicao {

    private String estadoInicial;
    private String simboloLeituraFita;
    private String simboloLeituraPilha;
    private String simboloEscritaPilha;
    private String estadoFinal;

    public Transicao() { }

    public Transicao(String estadoInicial, String simboloLeituraFita, String simboloLeituraPilha, String simboloEscritaPilha, String estadoFinal) {
        this.estadoInicial = estadoInicial;
        this.simboloLeituraFita = simboloLeituraFita;
        this.simboloLeituraPilha = simboloLeituraPilha;
        this.simboloEscritaPilha = simboloEscritaPilha;
        this.estadoFinal = estadoFinal;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }
    
    public String getSimboloLeituraFita() {
        return simboloLeituraFita;
    }

    public void setSimboloLeituraFita(String simboloLeituraFita) {
        this.simboloLeituraFita = simboloLeituraFita;
    }

    public String getSimboloLeituraPilha() {
        return simboloLeituraPilha;
    }

    public void setSimboloLeituraPilha(String simboloLeituraPilha) {
        this.simboloLeituraPilha = simboloLeituraPilha;
    }

    public String getSimboloEscritaPilha() {
        return simboloEscritaPilha;
    }

    public void setSimboloEscritaPilha(String simboloEscritaPilha) {
        this.simboloEscritaPilha = simboloEscritaPilha;
    }

    public String getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(String estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

}

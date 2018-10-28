/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iftm.ec.lfa.controller;

import iftm.ec.lfa.model.GramaticaRegular;
import iftm.ec.lfa.model.Automato;
import iftm.ec.lfa.model.Transicao;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author marco
 */
public class GramaticaRegularController {

    GramaticaRegular gr;
    Automato automato;
    
    String epsilon = "\u03B5";

    public GramaticaRegular getLinguagemRegular(Automato m) {
        GramaticaRegular gr = new GramaticaRegular();
        gr.converteAFDparaLinguagemRegular(m);
        gr.buildGrHtml();
        return gr;
    }

    public GramaticaRegular lerArquivo(String caminho) {
        try {
            Scanner s = new Scanner(new File(caminho));
            gr = new GramaticaRegular();
            while (s.hasNextLine()) {
                String linha = s.nextLine();
                if (linha.startsWith("VT")) {                    
                    linha = linha.replace("VT=", "");
                    linha = linha.replace("{", "");
                    linha = linha.replace("}", "");
                    String aux[] = linha.split(",");
                    List<String> estadosTerminais = Arrays.asList(aux);
                    for (int j = 0; j < estadosTerminais.size(); j++) {
                        String estado = estadosTerminais.get(j);
                        gr.addVt(estado);
                    }
                } else if (linha.startsWith("VN")) {                    
                    linha = linha.replace("VN=", "");
                    linha = linha.replace("{", "");
                    linha = linha.replace("}", "");
                    String aux[] = linha.split(",");
                    List<String> estadosNaoTerminais = Arrays.asList(aux);
                    for (int j = 0; j < estadosNaoTerminais.size(); j++) {
                        String estado = estadosNaoTerminais.get(j);
                        gr.addVn(estado);
                    }
                } else if (linha.startsWith("I")) {
                    linha = linha.replace("I=", "");
                    linha = linha.replace("{", "");
                    linha = linha.replace("}", "");
                    gr.setS(linha);
                } else {
                    if (!linha.startsWith("P") && !linha.startsWith("}")) {
                        // Pegando o estado antes da seta ->
                        int posA = linha.indexOf("-");
                        String estado = linha.substring(0, posA);

                        // Pegando as proposicoes depois da seta
                        int posD = linha.indexOf(">");
                        String proposicoes = linha.substring((posD + 1), linha.length());

                        // Dividindo as proposicoes e concatenando com o estado inicial da linha
                        String[] arrayString = proposicoes.split(Pattern.quote("|"));
                        for (String prop : arrayString) {
                            String proposicao = estado.concat(prop);
                            gr.addProposicao(proposicao);
                        }
                    }
                }
            }
            return gr;
        } catch (FileNotFoundException ex) {
            return null;
        }
    }

    public Automato converterGLUDparaAFN(GramaticaRegular gr) {

        automato = new Automato();

        // Adequando as proposicoes para que sejam transições validas        
        for (String prop : gr.getProposicoes()) {
            char[] arr = prop.toCharArray();
            if (arr.length < 3) {                
                String estado = String.valueOf(arr[0]);
                String simbolo = String.valueOf(arr[1]);
                if (gr.getVn().contains(estado)) {
                    // Valida casos como Ae e Aa
                    if (simbolo.equals("e")) {
                        Transicao transicao = new Transicao();
                        transicao.setEstadoInicial(estado);
                        transicao.setSimbolo("&#949");
                        transicao.setEstadoFinal("QF");
                        automato.addTransicao(transicao);
                    } else if (gr.getVt().contains(simbolo)) {
                        Transicao transicao = new Transicao();
                        transicao.setEstadoInicial(estado);
                        transicao.setSimbolo(simbolo);
                        transicao.setEstadoFinal("QF");
                        automato.addTransicao(transicao);
                    } else if (gr.getVn().contains(simbolo)) {
                        // Valida casos como AB
                        Transicao transicao = new Transicao();
                        transicao.setEstadoInicial(estado);
                        transicao.setSimbolo("&#949");
                        transicao.setEstadoFinal(simbolo);
                        automato.addTransicao(transicao);
                    }
                }
            } else if (arr.length == 3) {
                // Valida casos como AaB
                String estadoInicial = String.valueOf(arr[0]);
                String simbolo = "";
                if (arr[1] == 'e') {
                    simbolo = "&#949";
                } else {
                    simbolo = String.valueOf(arr[1]);
                }                
                String estadoFinal = String.valueOf(arr[2]);
                Transicao transicao = new Transicao();
                transicao.setEstadoInicial(estadoInicial);
                transicao.setSimbolo(simbolo);
                transicao.setEstadoFinal(estadoFinal);
                automato.addTransicao(transicao);
            }
        }

        // Adicionando os estados iniciais e finais
        automato.setEstadoInicial(gr.getS());
        automato.addEstadoFinal("QF");
        
        return automato;
    }
}

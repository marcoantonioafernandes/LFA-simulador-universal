/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iftm.ec.lfa.controller;

import iftm.ec.lfa.model.Automato;
import iftm.ec.lfa.model.Transicao;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author marco
 */
public class AutomatoController {

    private String sentencaAtual;

    Automato automato;

    public AutomatoController() {

    }

    //Estado de erro = 14;
    public Automato lerArquivo(String caminho) {
        try {
            Scanner s = new Scanner(new File(caminho));
            automato = new Automato();
            boolean erroArquivo = false;
            boolean existeEstadoInicial = false;
            while (s.hasNextLine()) {
                String linha = s.nextLine();
                //Verifica se a ultima linha está correta
                if (linha.charAt(0) == 'I') {
                    if (linha.length() < 3) {
                        erroArquivo = true;
                        break;
                    }
                    //Retira o I= da ultima linha para restar só o valor do estado inicial na linha
                    linha = linha.replace("I=", "");
                    automato.setEstadoInicial(linha);
                    existeEstadoInicial = true;
                } else if (linha.charAt(0) == 'F') {
                    if (linha.length() < 3) {
                        erroArquivo = true;
                        break;
                    }
                    //Retira o prefixo F= da string para separar os estados pelas ,
                    linha = linha.replace("F=", "");
                    String aux[] = linha.split(",");
                    List<String> estadosFinais = Arrays.asList(aux);
                    for (int j = 0; j < estadosFinais.size(); j++) {
                        String estado = estadosFinais.get(j);
                        automato.addEstadoFinal(estado);
                    }
                } else {
                    //Verifica se a linha tem o mínimo de 5 caracteres (que é o minímo para estar correto)
                    if (linha.length() < 5) {
                        erroArquivo = true;
                        break;
                    }
                    //Separa a linha por , criando um array e depois converte para list
                    //Onde cada elemento da list é um elemento da transição
                    String aux[] = linha.split(",");
                    System.out.println("aux " + aux[1]);
                    List<String> transicao = Arrays.asList(aux);
                    if (transicao.size() != 3) {
                        erroArquivo = true;
                        break;
                    }
                    Transicao novaTransicao = new Transicao();
                    String estadoInicial = transicao.get(0);
                    String simbolo = transicao.get(1);
                    String estadoFinal = transicao.get(2);
                    novaTransicao.setEstadoInicial(estadoInicial);
                    novaTransicao.setSimbolo(simbolo);
                    novaTransicao.setEstadoFinal(estadoFinal);
                    automato.addTransicao(novaTransicao);
                    automato.addSimboloAlfabeto(simbolo);
                    automato.addEstado(estadoInicial);
                    automato.addEstado(estadoFinal);
                }
            }
            //Verifica se existe algum erro no arquivo
            if (erroArquivo || !existeEstadoInicial || automato.getEstadosfinais().isEmpty()) {
                return null;
            }
            return automato;
        } catch (FileNotFoundException ex) {
            return null;
        }
    }

    /**
     * Função que recebe uma sentença e valida ela conforme o arquivo de
     * especificação
     *
     * @param sentenca array de caracteres que compoem a sentença
     * @return retorna um boolean indicando de a sentenca é valida ou não
     */
    public boolean validarSentenca(String sentenca) {

        sentencaAtual = new String();
        sentencaAtual += "<html>" + sentenca + "<br>";

        String estadoAtual = automato.getEstadoInicial(); // Inicializa o estado atual com o estado inicial do automato     
        String simboloAtual = "";

        // Percorrendo a sentença reconhecida
        for (int i = 0; i < sentenca.length(); i++) {
            simboloAtual = sentenca.charAt(i) + "";

            //Percorrendo a lista de transações
            for (Transicao transicao : automato.getTransicoes()) {
                // Conferindo se o simbolo reconhecido está no alfabeto
                if (automato.getAlfabeto().contains(simboloAtual)) {
                    if (estadoAtual.equals(transicao.getEstadoInicial()) && simboloAtual.equals(transicao.getSimbolo())) {

                        // Adiciona todos os caracteres anteriores
                        sentencaAtual += sentenca.substring(0, i);

                        // Adiciona o estado atual            
                        sentencaAtual += "<font color='blue'>q" + estadoAtual + "</font>";

                        // Adiciona o caracter atual
                        sentencaAtual += "<font color='red'>" + simboloAtual + "</font>";

                        // Adiciona todos os caracteres posteriores
                        sentencaAtual += sentenca.substring(i + 1, sentenca.length());

                        // Finaliza formatação
                        sentencaAtual += "<br>";

                        estadoAtual = transicao.getEstadoFinal();

                        break;
                    }
                } else {
                    return false;
                }
            }
        }

        sentencaAtual += "</html>";

        automato.setSentencasPassoAPasso(sentencaAtual);

        // Ao finalizar a leitura do automato, verificamos se o estado atual é um estado de aceitação
        return (automato.getEstadosfinais().contains(estadoAtual));
    }
    
    public String imprimeAutomato(Automato automato) {

        String strAutomato = new String();
        strAutomato += "<html>";

        for (Transicao transicoes : automato.getTransicoes()) {
            strAutomato += transicoes.getEstadoInicial() + "," + transicoes.getSimbolo() + "," + transicoes.getEstadoFinal() + "<br>";
        } 
        strAutomato += "I=" + automato.getEstadoInicial() + "<br>";
        strAutomato += "F=" + String.join("", automato.getEstadosfinais()) + "<br>";
        strAutomato += "</html>";
        
        return strAutomato;
    }
    
    
}

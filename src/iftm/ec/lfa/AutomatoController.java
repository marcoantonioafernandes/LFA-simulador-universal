/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iftm.ec.lfa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author marco
 */
public class AutomatoController {
    //Estado de erro = 14;
    public static Automato lerArquivo(String caminho){
        try {
            Scanner s = new Scanner(new File(caminho));
            Automato automato = new Automato();
            boolean erroArquivo = false;
            boolean existeEstadoInicial = false;
            while(s.hasNextLine()){
                String linha = s.nextLine();
                //Verifica se a ultima linha está correta
                if(linha.charAt(0) == 'I'){
                    if(linha.length() < 3){
                        erroArquivo = true;
                        break;
                    }
                    //Retira o I= da ultima linha para restar só o valor do estado inicial na linha
                    linha = linha.replace("I=", "");
                    automato.setEstadoInicial(linha);
                    existeEstadoInicial = true;
                } else if(linha.charAt(0)  == 'F'){
                    //Retira o prefixo F= da string para separar os estados pelas ,
                    linha = linha.replace("F=", "");
                    String aux[] = linha.split(",");
                    List<String> estadosFinais = Arrays.asList(aux);
                    for(int j=0;j<estadosFinais.size();j++){
                        String estado = estadosFinais.get(j);
                        automato.addEstadoFinal(estado);
                    }
                }else{
                    //Verifica se a linha tem o mínimo de 5 caracteres (que é o minímo para estar correto)
                    if(linha.length() < 5) {
                        erroArquivo = true;
                        break;
                    }
                    //Separa a linha por , criando um array e depois converte para list
                    //Onde cada elemento da list é um elemento da transição
                    String aux[] = linha.split(",");
                    List<String> transicao = Arrays.asList(aux);
                    if(transicao.size() != 3){
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
            if(erroArquivo || !existeEstadoInicial || automato.getEstadosfinais().isEmpty()){
                return null;
            }
            return automato;
        } catch (FileNotFoundException ex) {
            return null;
        }
    }
    
    
}

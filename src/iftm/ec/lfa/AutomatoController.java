/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iftm.ec.lfa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
                Transicao transicao = new Transicao();
                String linha = s.nextLine();
                
                if(linha.charAt(0) == 'I'){
                    if(linha.length() != 3){
                        erroArquivo = true;
                        break;
                    }
                    automato.estadoInicial = (int)linha.charAt(2) - 48;
                    existeEstadoInicial = true;
                } else if(linha.charAt(0)  == 'F'){
                    for(int j=2;j<linha.length();j++){
                        if(Character.isDigit(linha.charAt(j))){
                            int estado = (int)linha.charAt(j) - 48;
                            automato.addEstadoFinal(estado);
                        }
                    }
                }else{
                    if(linha.length() != 5) {
                        erroArquivo = true;
                        break;
                    }
                    if(Character.isDigit(linha.charAt(0))  || 
                            Character.isDigit(linha.charAt(2)) || 
                            Character.isDigit(linha.charAt(4))){
                        transicao.setEstadoInicial((int)linha.charAt(0) - 48);
                        transicao.setCaracter(linha.charAt(2));
                        transicao.setEstadoFinal((int)linha.charAt(4) - 48);
                        automato.addTransicao(transicao);
                    }else{
                        erroArquivo = true;
                        break;
                    }
                }
            }
            
            if(erroArquivo || !existeEstadoInicial || automato.getEstadosfinais().isEmpty()){
                return null;
            }
            return automato;
        } catch (FileNotFoundException ex) {
            return null;
        }
    }
    
    
}

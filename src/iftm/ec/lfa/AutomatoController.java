/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iftm.ec.lfa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author marco
 */
public class AutomatoController {
    //Estado de erro = 14;
    public static void lerArquivo(String caminho){
        try {
            Scanner s = new Scanner(new File(caminho));
            char c;
            int e = 0;
            boolean erro = false;
            String valor = "";
            Automato automato = new Automato();
            while(s.hasNextLine()){
                Transicao transicao = new Transicao();
                String linha = s.nextLine();
                for(int i = 0;i < linha.length();i++){
                    c = linha.charAt(i);
                    switch(e){
                        case 0:
                            if(Character.isDigit(c)){
                                e = 1;
                                valor += c;
                            }
                            else if(c == 'F') e = 6;
                            else if(c == 'I') e = 9;
                            else e = 14;
                            break;
                        case 1:
                            if(Character.isDigit(c)) valor += c;
                            else if(c == ',') e = 2;
                            else e = 14;
                            break;
                        case 2:
                            transicao.setEstadoInicial(Integer.parseInt(valor));
                            valor = "";
                            //proximas transições
                            break;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AutomatoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

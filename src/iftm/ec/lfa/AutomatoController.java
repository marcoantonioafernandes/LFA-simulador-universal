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
    public static void lerArquivo(String caminho){
        try {
            Scanner s = new Scanner(new File(caminho));
            char c;
            int e = 0;
            boolean erro = false;
            while(s.hasNextLine()){
                String linha = s.nextLine();
                for(int i = 0;i < linha.length();i++){
                    c = linha.charAt(i);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AutomatoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

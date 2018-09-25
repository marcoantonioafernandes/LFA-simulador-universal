/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iftm.ec.lfa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class Teste {
    public static void main(String args[]){
        Path caminho = Paths.get("/home/marco/Documentos/iftm/LFA/Projetos/teste.txt");
        try {
            byte[] texto = Files.readAllBytes(caminho);
            String leitura = new String(texto);
            System.out.println(leitura);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

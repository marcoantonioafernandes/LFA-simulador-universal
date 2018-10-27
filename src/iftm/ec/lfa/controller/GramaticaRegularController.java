/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iftm.ec.lfa.controller;
import iftm.ec.lfa.model.GramaticaRegular;
import iftm.ec.lfa.model.Automato;

/**
 *
 * @author marco
 */
public class GramaticaRegularController {
    GramaticaRegular gr;
    
    public GramaticaRegular getLinguagemRegular(Automato m){
        GramaticaRegular gr = new GramaticaRegular();
        gr.converteAFDparaLinguagemRegular(m);
        gr.buildGrHtml();
        return gr;
    }
}

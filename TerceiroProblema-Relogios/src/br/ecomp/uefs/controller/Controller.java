/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ecomp.uefs.controller;

import br.ecomp.uefs.util.Tempo;
import javax.swing.JLabel;

/**
 *
 * @author marcos ramos
 */
public class Controller {
    	private Tempo rel;
	

	public Controller(JLabel label) {
		rel = new Tempo(label);
	}

	public void conectar() throws Exception {
		
		System.out.println("Quem ta ai?");
		
		wait(1000);

	}


	public void start() {
		rel.start();
	}

	public void setDrift(long aux) {
		rel.setMille(aux);
	}

	public void stop() {
		System.out.println(" teste");
	}

	public void setSeg(int aux) {
		rel.setSeg(aux);
		rel.update();
	}

	public void setMin(int aux) {
		rel.setMin(aux);
		rel.update();
	}

	public void setHr(int aux) {
		rel.setHora(aux);
		rel.update();
	}
	
	public void Erro(){
            
            System.out.println(" erro");
		
	}
    
}

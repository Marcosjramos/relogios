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
 * @version 1.0
 *
 */
  /** a seguir  classe  que corresponde  ao controller  responsavel por receber um ação da interface e realizar um procedimento no sistema. <br/> */
public class Controller {
    	private Tempo rel;    /**  criação da variavel da classe tempo , responsavel  por algumas ações no sistema. <br/>*/
	
          /**  a seguir o construtor  da classe, que receber o seguinte parametro:
           *   @param JLabel  com  o tempo  que foi inserido. <br/>
           */
	public Controller(JLabel label) {
		rel = new Tempo(label); /** instancio o objeto rel para usar algumas ações  da classe tempo para o sistema. <br/> */
	}
          /**  a seguir  a  função  que vai ser chamda quando usuario  clicar em conectar. <br/>*/
        
	public void conectar() throws Exception {
		
		System.out.println("Quem ta ai?");
		
		wait(1000);

	}

        /**  a seguir  a  função  que vai ser chamda quando usuario  clicar em start, ocorrendo assim inicio da contagem do tempo. <br/>*/
	public void start() {
		rel.start();
	}
        /**  a seguir  a  função  que vai ser chamda quando usuario  clicar em alterar drifit ocorrendo a alteração do drift. <br/>*/
	public void setDrift(long aux) {
		rel.setMille(aux);
	}
        
          /**  a seguir  a  função  que vai ser chamda quando usuario  clicar em alterar o segundos, ocorrendo a alteração do segundo da contagem do tempo. <br/>*/
	public void setSeg(int aux) {
		rel.setSeg(aux);
		rel.update();
	}
            /**  a seguir  a  função  que vai ser chamda quando usuario  clicar em alterar o minutos, ocorrendo a alteração do segundo da contagem do tempo. <br/>*/
	public void setMin(int aux) {
		rel.setMin(aux);
		rel.update();
	}
         /**  a seguir  a  função  que vai ser chamda quando usuario  clicar em alterar na hora , ocorrendo a alteração do segundo da contagem do tempo. <br/>*/
	public void setHr(int aux) {
		rel.setHora(aux);
		rel.update();
	}  
}

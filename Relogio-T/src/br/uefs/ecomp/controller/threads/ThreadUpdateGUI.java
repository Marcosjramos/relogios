/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.controller.threads;

import br.uefs.ecomp.controller.ControllerRelogio;
import javax.swing.JLabel;

/**
 *
 * @author marcos
 */
public class ThreadUpdateGUI extends Thread  {
    
        private ControllerRelogio controller = ControllerRelogio.getInstance();
	private JLabel lblH;
	private JLabel lblM;
	private JLabel lblS;

    public ThreadUpdateGUI(JLabel lblH, JLabel lblM, JLabel lblS) {
        this.lblH = lblH;
        this.lblM = lblM;
        this.lblS = lblS;
    }
        
      @Override
	public void run(){
		while(true){
			//atualiza hora/minuto/segundo
			int horas = controller.getHoras();
			int minutos = controller.getMinutos();
			int segundos = controller.getSegundos();
			String sHoras;
			if(horas<10){
				sHoras = "0" + horas;
			}else{
				sHoras = "" + horas;
			}
			String sMinutos;
			if(minutos<10){
				sMinutos = "0" + minutos;
			}else{
				sMinutos = "" + minutos;
			}
			String sSegundos;
			if(segundos<10){
				sSegundos = "0" + segundos;
			}else{
				sSegundos = "" + segundos;
			}
				
			lblH.setText(sHoras);
			lblM.setText(sMinutos);
			lblS.setText(sSegundos);
			
			
			try {
				Thread.sleep(200);//demora meio minuto para atualizar novamente
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    
}

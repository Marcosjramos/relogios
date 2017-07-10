/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ecomp.uefs.util;

import javax.swing.JLabel;

/**
 *
 * @author marcos ramos
 * @version 1.0
 */
public class Tempo extends Thread{ 
    private int hora,min,seg; /** variaveis para representra horas, minutos e segundo. <br/> */
    private long  mille;      /** variavel para corresponder milizimos de segundos que tem as atelações de tempo. <br/> */
    private String relo;      /** para representar o horario.  <br/> */
    private JLabel tela;      /** variavel que vai receber o horario completo.  <br/> */

    /** Em seguinda, tem o  o construtor 
     * que vai inacializar a variaveis desta classe. <br/>*/
    
    public Tempo(JLabel tela) { 
        min  = 00;
        seg  = 00;
        hora =  00;
        mille = 1000;
        this.tela= tela;
        relo = hora+":"+min+":"+seg;
        tela.setText(relo);       
    }
    
    /**  A seguir  a  função  para  atelar  a hora  que  vai  receber como parametro <br/>
     *   @param int que corresponde ao hora. <br/> 
     */
    public void setHora(int hora) {
        this.hora = hora;
    }
    
     /**  A seguir  a  função  para  atelar  o minuto  que  vai  receber como parametro <br/>
     *   @param int que corresponde ao minuto.  <br/> 
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**  A seguir  a  função  para  atelar  o segundos   que  vai  receber como parametro <br/>
     *   @param int que corresponde ao segundos. <br/> 
     */
    public void setSeg(int seg) {
        this.seg = seg;
    }

    /** A seguir  a função  que  vai alterar o milezimo s que  o sistema usar para alterar o tmpo do relogio,<br/>
     * e vai receber com  parametro  o seguinte <br/>
     * @param long que vai  corresponder ao  milezimos de segundos. <br/>*/
    public void setMille(long mille) {
        this.mille = mille;
    }
    
    /** A seguir tem a função  que vai corresponder a atualização do campos  que foram alterados na função anterior. <br/> */
    public void update(){
        relo = hora+":"+min+":"+seg;
        tela.setText(relo);
   }
  
    /** A função  a seguir é a sobreescrita da  função run  que implementamos <br/>
     * após ocorrer a heranção desta  classe da classe  thread.  <br/>*/
    public void run(){

    while (true) {
        try {
                seg += 1;
                Thread.sleep(mille);
                relo = hora+":"+min+":"+seg;
                tela.setText(relo);
                System.out.println("Drift na contagem - "+ mille);
                if (seg > 60) {
                    min += 1;
                    seg = 0;
                    relo = hora+":"+min+":"+seg;
                    tela.setText(relo);
                }else if (min > 60) {
                    hora += 1;
                    min = 0;
                    relo = hora+":"+min+":"+seg;
                    tela.setText(relo);
                } else if (hora > 23) {
                    hora = 0;
                    relo = hora+":"+min+":"+seg;
                    tela.setText(relo);
                        }
                    } catch (InterruptedException e) {
                            System.out.println("Erro \n");
                }
            }


    }
    
}

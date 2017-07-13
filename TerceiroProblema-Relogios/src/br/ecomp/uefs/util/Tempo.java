/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ecomp.uefs.util;

import br.ecomp.uefs.view.Start;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author marcos ramos
 * @version 1.0
 */
public class Tempo extends Thread{ 
    private int hora,min,seg; /** variaveis para representra horas, minutos e segundo. <br/> */
    private long  mille;      /** variavel para corresponder milizimos de segundos que tem as alterações de tempo. <br/> */
    private String relo;      /** para representar o horario.  <br/> */
    private JLabel tela;      /** variavel que vai receber o horario completo.  <br/> */
    
    public static Tempo tempo;
    
    public static Tempo getInstace() { 
        if (tempo == null) {
            tempo = new Tempo();
            return tempo;
        } else {
            return tempo;
        }
    }

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

    private Tempo() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       min  = 00;
        seg  = 00;
        hora =  00;
        mille = 1000;
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
                JSONObject j = new JSONObject();
                j.put("op", 2);
                Start.s.operacao(j.toString(), 1234, hora, min, seg, mille);
                if( hora == 23 && min == 59 && seg == 59 ){
                    hora = 00;
                    min = 00;
                    seg = 00;
                     relo = hora+":"+min+":"+seg;
                }
                if (hora > 23) {
                    hora = 0;
                    relo = hora+":"+min+":"+seg;
                    tela.setText(relo);
                        }
                else if (min > 59) {
                    hora += 1;
                    min = 0;
                    relo = hora+":"+min+":"+seg;
                    tela.setText(relo);
                }
               else if (seg > 59 ) {
                   if (min == 59 ){
                    min = 00;
                    hora += 1;
                   } else {
                       min += 1;
                   }
                    seg = 0;
                    relo = hora+":"+min+":"+seg;
                    tela.setText(relo);
                } 
                    } catch (InterruptedException e) {
                            System.out.println("Erro \n");
                } catch (JSONException ex) {
            Logger.getLogger(Tempo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tempo.class.getName()).log(Level.SEVERE, null, ex);
        }
            }


    }

    public int getHora() {
        return hora;
    }

    public int getMin() {
        return min;
    }

    public int getSeg() {
        return seg;
    }

    public long getMille() {
        return mille;
    }
    
    
    
    
    
}

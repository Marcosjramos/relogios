/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ecomp.uefs.util;

import br.com.uefs.service.Cliente;
import com.sun.security.ntlm.Client;

/**
 *
 * @author cassio
 */
public class Contador extends Thread {

    public static long mille;
    private String ip;
    private int porta;
    /**
     * variavel para corresponder milizimos de segundos que tem as alterações de
     * tempo. <br/>
     */

    /**
     * Em seguinda, tem o o construtor que vai inacializar a variaveis desta
     * classe. <br/>
     */

    public Contador(String ip, int porta) {
        this.ip = ip;
        this.porta = porta;
        mille = 0;
    }

    public void run() {
         mille = System.currentTimeMillis();
        while (true) {
           mille++;
           if (mille > 3000 && mille < 3500){
               Cliente c = new Cliente(ip, porta);
               c.start();
           }
        }
    }

    public long getMille() {
        return mille;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ecomp.uefs.view;

import br.com.uefs.service.Servidor;
import java.awt.EventQueue;
import java.net.SocketException;

/**
 *
 * @author marcos
 */
public class Start {
    
    public static Servidor s;
    
    public static void main(String[] args) throws SocketException {
        s =  Servidor.getInstance(); // iniciando o cervidor
        s.start();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Gui window = new Gui();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

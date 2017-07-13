/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.view;

import br.uefs.ecomp.service.Cliente;
import br.uefs.ecomp.service.Servidor;
import java.awt.EventQueue;
import java.net.SocketException;
import org.json.JSONException;

/**
 *
 * @author marcos
 */
public class relogio {

    public static void main(String[] args) throws JSONException, SocketException {

        Servidor server = new Servidor(1234, 0);
        server.start();
        //Cliente c = new Cliente("40.0.0.106", 1234);
        //c.start();
       // c.conexao();
        //server.conexao();
        // Cliente client = new Cliente("40.0.0.106", 1234);
        //client.conexao();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    relogioGui window = new relogioGui();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

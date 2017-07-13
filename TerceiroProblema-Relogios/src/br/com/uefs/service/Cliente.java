/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import sun.security.x509.IPAddressName;

/**
 *
 * @author cassio
 */
public class Cliente extends Thread{
    
    private String ip;
    private int porta;

    public Cliente(String ip, int porta) {
        this.ip = ip;
        this.porta = porta;
    }

    @Override
    public void run() {
        try {
            conexao();
        } catch (JSONException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void conexao() throws JSONException{
       // BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(ip);
            
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            
           // System.out.println("Digite o texto a ser enviado ao servidor ");
            JSONObject j = new JSONObject();
            j.put("op", 1);
           String teste = j.toString();
           
           //String teste = "Texto para teste";

           sendData = teste.getBytes();
           DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
            System.out.println("Enviando pacote UDP para "+ip+" : "+porta);
           DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
           clientSocket.send(sendPacket);
           clientSocket.receive(receivePacket);
            System.out.println("Cliente Pacote UDP recebido...");
            
            String dadosRecebidos = new String (receivePacket.getData());
            System.out.println("Texto recebido do servidor :"+dadosRecebidos);
            
            clientSocket.close();
            System.out.println("Socket cliente fechado");
        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void operacao(String dados) throws JSONException{
        JSONObject j = new JSONObject(dados);
        int op = j.getInt("op");
        switch (op){
            case 1:
                
                break;
        }
    }
}

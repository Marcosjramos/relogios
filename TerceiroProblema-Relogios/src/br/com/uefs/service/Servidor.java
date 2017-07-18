/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.service;

import br.com.uefs.model.Usuario;
import br.ecomp.uefs.controller.Controller;
import br.ecomp.uefs.util.Tempo;
import br.ecomp.uefs.view.Gui;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe responsavel por destribuir o horário entre os 
 * @author cassio e marcos
 * @version 1.0
 */
public class Servidor extends Thread {

    private int porta; 
    private int numCont;
    public static List<Usuario> usuarios;
    private static InetAddress ipUsuario;
    private DatagramSocket serverSocket;

    private byte[] receiveData;
    private byte[] sendData;
    DatagramPacket receivePacket;
    public static Servidor s;
    
    /**
     * Metodo para retornar uma unica instância do servidor 
     * @return instância de servidor
     * @throws SocketException 
     */
    public static Servidor getInstance() throws SocketException {
        if (s == null) {
            s = new Servidor(1234, 0);
            return s;
        } else {
            return s;
        }
    }

    /**
     * Construtor da classe Servidor
     * @param porta porta
     * @param numCont contador de cliente 
     * @throws SocketException 
     */
    public Servidor(int porta, int numCont) throws SocketException {
        this.porta = porta;
        this.numCont = numCont;
        usuarios = new ArrayList<>();
        receiveData = new byte[1024];
        sendData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket = new DatagramSocket(porta);
    }

    /**
     * Método que ficará execuntando em paralelo com outros processos 
     */
    @Override
    public void run() {
        //    super.run(); //To change body of generated methods, choose Tools | Templates.
        try {

            while (true) {

                System.out.println("Esperando datagram Udp na porta " + porta);
                serverSocket.receive(receivePacket);
                System.out.println("Datagram UDP [" + numCont + "] recebido");

                String sentence = new String(receivePacket.getData());

                //  System.out.println(sentence);
                ipUsuario = receivePacket.getAddress();
                InetAddress IpAddress = receivePacket.getAddress();

                System.out.println("IP do usuario: " + IpAddress.toString());
                System.out.println("IP do usuario 2: " + ipUsuario.toString());
                // ipUsuario = IpAddress;
                int port = receivePacket.getPort();
                char v = sentence.charAt(0);
                String verificar = Character.toString(v);
                // System.out.println(verificar);
                if (!sentence.isEmpty() && verificar.equals("{")) {
                    System.out.println("sentence :" + sentence);
//                    Tempo t = Controller.getInstace().getRel();
                    JSONObject j = new JSONObject(sentence);
                    
                    operacao(j, port);
                }
                //comunicacao(IpAddress, port, capitalizedSentence, serverSocket);
            }
        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsavel pela comunicação com os clientes 
     * @param IpAddress endereço ip do destinatário 
     * @param port porta 
     * @param capitalizedSentence dados para o envio
     * @throws IOException 
     */
    public void comunicacao(InetAddress IpAddress, int port, String capitalizedSentence) throws IOException {
        // serverSocket = new DatagramSocket(port);
        // String capitalizedSentence2 = "Enviando dados do servidor";
        sendData = capitalizedSentence.getBytes();
        System.out.println(IpAddress.toString() + " " + port);
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IpAddress, port);
        System.out.println("Enviando " + capitalizedSentence + "...");
        serverSocket.send(sendPacket);
        System.out.println("OK\n");
    }

    /**
     * Método responsavel por dividir as operções
     * @param j Jsonobject contendo todos os paremtros necessario
     * @param porta porta de entrada
     * @throws JSONException
     * @throws IOException 
     */
    public void operacao(JSONObject j, int porta) throws JSONException, IOException {
        //JSONObject j = new JSONObject(s);
        int regra = j.getInt("op");
        //System.out.println(regra);
        switch (regra) {
            case 1: // Cadastrar usuário

                Usuario u = new Usuario();

                u.setIp(ipUsuario);
                             u.setStatus(0);
                //  u.setTempo(t);
                //JSONArray ja = new JSONArray();
                if (usuarios != null) {
                    boolean status = false;
                    for (Usuario user : usuarios) {
                        if (user.getIp().equals(ipUsuario)) {
                            status = true;
                            break;
                        }
                    }
                    if (!status) {
                        if (usuarios.size() == 0) {
                            u.setStatus(1);
                        } else {
                            u.setOrdem(usuarios.size());
                        }
                        usuarios.add(u);

                        for (Usuario usuario : usuarios) {
                            j = new JSONObject();
                            j.put("op", 1);
                            j.put("ip", ipUsuario.getHostName());
                            j.put("status", u.getHora());
                            j.put("ordem", u.getOrdem());
                            //ja.put(j);
                            System.out.println("Enviado usuarios  " + usuario.toString());
                            comunicacao(usuario.getIp(), porta, j.toString());
                        }
                    }
                }
                break;
            case 2:
                for (Usuario usuario : usuarios) {

                    JSONObject j2 = new JSONObject();
                    j2.put("h", Tempo.hora);
                    j2.put("m", Tempo.min);
                    j2.put("s", Tempo.seg);
                    j2.put("mille", Tempo.mille);
                    j2.put("op", 2);
                    j2.put("id", s.getId());
                    System.out.println("Teste 01: " + j2.toString());
                    System.out.println("Usuário :" + usuario.getIp().toString());
                    comunicacao(usuario.getIp(), porta, j2.toString());
                }
                break;
        }
    }
}

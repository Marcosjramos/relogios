/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.service;


import br.com.uefs.model.Usuario;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cassio
 */
public class Servidor extends Thread {

    private int porta;
    private int numCont;
    private List<Usuario> usuarios;
    private static InetAddress ipUsuario;
    private DatagramSocket serverSocket;
    private byte[] receiveData;
    private byte[] sendData;
     DatagramPacket receivePacket;

    public Servidor(int porta, int numCont) throws SocketException {
        this.porta = porta;
        this.numCont = numCont;
        usuarios = new ArrayList<>();
        receiveData = new byte[1024];
        sendData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket = new DatagramSocket(porta);
    }

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
                    System.out.println(verificar);
                    if (!sentence.isEmpty() && verificar.equals("{")){
                    System.out.println(sentence);
                    operacao(sentence, port);
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

    public void comunicacao(InetAddress IpAddress, int port, String capitalizedSentence) throws IOException {
       // serverSocket = new DatagramSocket(port);
      // String capitalizedSentence2 = "Enviando dados do servidor";
        sendData = capitalizedSentence.getBytes();
        System.out.println(IpAddress.toString()+" "+port);
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IpAddress, port);
        System.out.println("Enviando " + capitalizedSentence + "...");
        serverSocket.send(sendPacket);
        System.out.println("OK\n");
    }

    public void operacao(String s, int porta) throws JSONException, IOException {
        JSONObject j = new JSONObject(s);
        int regra = j.getInt("op");
        System.out.println(regra);
        switch (regra) {
            case 1:
                Usuario u = new Usuario();
                u.setIp(ipUsuario);
                u.setStatus(0);
                u.setHora(0);
                u.setMin(0);
                u.setSeg(0);
                if (usuarios != null) {
                    boolean status = false;
                    for (Usuario user : usuarios) {
                        if (user.getIp().equals(ipUsuario)) {
                            status = true;
                            break;
                        }
                    }
                    if (!status) {
                        usuarios.add(u);
                        for (Usuario usuario : usuarios) {
                            j = new JSONObject();
                            j.put("op", 1);
                            j.put("h", 10);
                            j.put("m", 30);
                            j.put("s", 0);
                            System.out.println("IP do usuario: 3  " + ipUsuario.toString());
                            comunicacao(usuario.getIp(), porta, j.toString());
                        }
                    }
                }
                break;
            case 2:
                for (Usuario usuario : usuarios) {
                    j = new JSONObject();
                    j.put("op", 1);
                    j.put("h", 10);
                    j.put("m", 30);
                    j.put("s", 0);
                    comunicacao(usuario.getIp(), porta, j.toString());
                }
                break;
        }
    }

    public void conexao() {
        /*try {
            DatagramSocket serverSocket = new DatagramSocket(porta);
            byte[] receiveData  = new byte[1024];
            byte[] sendData = new byte[1024];
            
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                System.out.println("Esperando datagram Udp na porta "+ porta);
                serverSocket.receive(receivePacket);
                System.out.println("Datagram UDP ["+ numCont+"] recebido");
                
                String sentence = new String(receivePacket.getData());
                System.out.println(sentence);
                
                InetAddress IpAddress = receivePacket.getAddress();
                
                int port  = receivePacket.getPort();
                
                String capitalizedSentence = sentence.toUpperCase();
                
                sendData = capitalizedSentence.getBytes();
                
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IpAddress, port);
                System.out.println("Enviando "+ capitalizedSentence+"...");
                serverSocket.send(sendPacket);
                System.out.println("OK\n");
            }
        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    }
}

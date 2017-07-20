/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.service;

import br.com.uefs.model.Usuario;
import br.ecomp.uefs.util.Contador;
import br.ecomp.uefs.util.Tempo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe resonsavel pela comunicação com o servidor
 *
 * @author cassio e marcos
 * @version 1.0
 */
/**
 * A função a seguir é um thread que corresponderá relogio/usuario do sistema .
 * <br/>
 */
public class Cliente extends Thread {

    private String ip;
    private int porta;

    public static Cliente conexao;

    /**
     * Método responsavel por retornar o estado da conexão
     *
     * @return conexao
     */
    public static Cliente getInstance() {
        return conexao;
    }

    //private String ip; /** variavel para  corresponder ao endereço para que ocorrá  a comunicação  . <br/>*/
    //private int porta; /** variavel para  corresponder aporta para que ocorrá  a comunicação  . <br/>*/
    /**
     * A seguir o construtor da classe que será usada no sistema para isso é
     * passado alguns parametros que são :
     *
     * @param String com endereço ip e <br/>
     * @param int com numero da porta <br/>
     */
    public Cliente(String ip, int porta) {
        this.ip = ip;
        this.porta = porta;
    }

    /**
     * A seguir uma função é sobre escrita para que possa ocorrer comunicação
     * que é função run .<br/>
     */
    @Override
    public void run() {
        try {
            conexao();
        } catch (JSONException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsavel por manter a conexão com o servidor ativa
     *
     * @throws JSONException
     */
    public void conexao() throws JSONException {
        // BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(ip);
            while (true) {
                //new Thread(){
                String dadosRecebidos = comunicacao(IPAddress, clientSocket);
               
                    operacao(dadosRecebidos);
                
            }

            //clientSocket.close();
            //System.out.println("Socket cliente fechado");
        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsavel por enviar e receber dados da rede
     *
     * @param IPAddress Endereço ip do destinatário
     * @param clientSocket conexão local
     * @return String com a resposta do servidor remoto
     * @throws JSONException
     * @throws IOException
     */
    public String comunicacao(InetAddress IPAddress, DatagramSocket clientSocket) throws JSONException, SocketException {
       
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        JSONObject j = new JSONObject();

        if (Servidor.usuarios.isEmpty()) {
            j.put("op", 1);
            j.put("id", Servidor.getInstance().getId());
        } else {
            j.put("op", 2);
        }
        String teste = j.toString();

        sendData = teste.getBytes();
        
        System.out.println("Enviando pacote UDP para " + ip + " : " + porta);
       
        String dadosRecebidos = null;
        Contador cont = new Contador(ip, porta);
        cont.start();
        int mCont = 0;
        boolean send = true;
        while (mCont < 3 && send) {
            try {
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
                clientSocket.send(sendPacket);
                send = false;
            } catch (IOException ex) {
                System.out.println("Erro ao enviar o pacote");
                mCont = mCont + 1;
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
             DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            dadosRecebidos = new String(receivePacket.getData());
            cont.stop();
        } catch (IOException ex) {
            // Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Cliente Pacote UDP recebido...");

        System.out.println("Texto recebido do servidor :" + dadosRecebidos);
        return dadosRecebidos;
    }

    /**
     * Método responsavel por organizar a saida e a entrada de dados de acordo
     * com o protocolo
     *
     * @param dados protocolo de comunicação
     * @throws JSONException
     * @throws UnknownHostException
     * @throws SocketException
     */
    public void operacao(String dados) throws JSONException, UnknownHostException, SocketException {
        JSONObject j = new JSONObject(dados);
        System.out.println("recebido: " + j.toString());
        int op = j.getInt("op");
        switch (op) {
            case 1:

                System.out.println((String) j.get("ip"));
                Usuario user = new Usuario();
                InetAddress ip = InetAddress.getByName(j.getString("ip"));
                user.setIp(ip);
                user.setOrdem(j.getInt("ordem"));
                user.setStatus(j.getInt("status"));
                adcionarRelogio(user);

                break;

            case 2:
                //Controller con = Controller.getInstace();
                int hora = j.getInt("h");
                int min = j.getInt("m");
                int s = j.getInt("s");
                long mille = j.getLong("mille");
                long id = j.getLong("id");

                //long milli = j.optLong("milli");
                //System.out.println(Servidor.getInstance().getId());
                if (id != Servidor.getInstance().getId()) {
                    System.out.println("Tempo: " + "  " + hora + " " + min + " " + " " + s + " " + mille);
                    Tempo.hora = hora;
                    Tempo.min = min;
                    Tempo.seg = s;
                    Tempo.mille = mille;
                }

                //comunicacao(ip, )
                break;
            case 3:
                 if(Servidor.usuarios.size() < 2) {
                     Usuario usuario = null;
                 
                for (Usuario u: Servidor.usuarios){
                    if (u.getStatus() == 1){
                        usuario = u;
                        break;
                    }
                }
                if (usuario != null) {
                    Servidor.usuarios.remove(usuario);
                    usuario.setStatus(0);
                    Servidor.usuarios.add(usuario);
                    Usuario novo = null;
                    for (Usuario u2: Servidor.usuarios) {
                        if (!u2.getIp().equals(usuario.getIp())){
                            novo = u2;
                            break;
                        }
                    }
                    if (novo != null) {
                        Servidor.usuarios.remove(novo);
                         usuario.setStatus(1);
                        Servidor.usuarios.add(novo);
                    }
                }
                 }
                break;
        }
    }

    /**
     * Método responsavel por adcionar um cliente remoto
     *
     * @param user Usuário
     *
     */
    public void adcionarRelogio(Usuario user) {
        if (Servidor.usuarios != null) {
            boolean v = false;
            for (Usuario u : Servidor.usuarios) {
                if (u.getIp().equals(user.getIp())) {
                    v = true;
                    break;
                }
            }
            if (!v) {
                Servidor.usuarios.add(user);
            }
        }
    }
}

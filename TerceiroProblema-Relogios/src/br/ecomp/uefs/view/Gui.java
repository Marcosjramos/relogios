/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ecomp.uefs.view;

import br.com.uefs.service.Cliente;
import br.com.uefs.service.Servidor;
import br.ecomp.uefs.controller.Controller;
import br.ecomp.uefs.util.Tempo;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Principal;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author marcos
 * @version 1.0
 */
public class Gui {

    private JTextField hora, min, seg, drift;
    /**
     * variaveis para representar o hoario e drift.  <br/>
     */
    JFrame frame;
    /**
     * variavel para tela.   <br/>
     */
    private Tempo relogio;
    /**
     * para ter a contagem de tempo.  <br/>
     */
    private JButton btnIniciarConectar;
    /**
     * Variavel para representar o botão Iniciar conectar.  <br/>
     */
    private JButton btnAlterar;
    /**
     * A variavel para represtra o botão Alterar.  <br/>
     */
    private JLabel clock;
    /**
     * variavel para tela . <br/>
     */
    private JTextField textField;  /**  varaivel para  corresponder  as tela que vai aparecer.  <br/>*/
    private Controller control;   /**  variavel  para  intacia o controller  do sistema. <br/> */
    
    /** A seguir a função do construtor que chamar a interface do sistema. <br/>*/
    public Gui() {
        Incializar();   /** chama função da interface  do sistema.  <br/> */
    }

    public void Incializar() {
        
         /** a seguir o if que vai set icones e cores na interface do sistema. <br/>  */
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        frame = new JFrame(); /**  instancia e set os parametros  corresponde a interface do0 sistema. <br/> */
        frame.setBounds(280, 290, 520, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("Relogios");/**set o nome  da  tela . <br/> */

        ButtonHandller btmH = new ButtonHandller(); /** criar  o objeto que vai  ser usado para verificara qual ação  o botão vai ser executada . <br/> */

        JLabel relogio = new JLabel(" ");/** para instancia o relogio  e  colocara seus parametros. <br/> */
        relogio.setHorizontalAlignment(SwingConstants.CENTER);
        relogio.setFont(new Font("Tahoma", Font.PLAIN, 62));
        relogio.setBounds(90, 27, 360, 75);
        frame.getContentPane().add(relogio);/**  adicionar na  tela . <br/> */
        control = new Controller(relogio); /** criar o objeto do  crontroller. <br/> */

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP); /**  instancia pane  que  vai abrigar as configurações no caso as ações que usuario pode fazer no sistema . <br/> */
        tabbedPane.setBounds(20, 80, 450, 350);
        frame.getContentPane().add(tabbedPane);/** adicinara na tela . <br/> */

        JPanel panel = new JPanel();
        tabbedPane.addTab("Configurações", null, panel, null);/* set  o nome  do pane. <br/> */
        panel.setLayout(null);

        hora = new JTextField();/**  instancia a tela para altera a a hora  e  seta seus parametros.  <br/> */
        hora.setBounds(12, 60, 89, 25);
        frame.getContentPane().add(hora); /**  adicionar na tela .<br/>*/
        hora.setColumns(10);
        panel.add(hora); /**  adicionar no pane.<br/>*/

        min = new JTextField();/**  instancia a tela para altera os  minutos  e  seta seus parametros.  <br/> */
        min.setBounds(170, 60, 89, 25);
        frame.getContentPane().add(min); /**  adicionar na tela .<br/>*/
        min.setColumns(10);
        panel.add(min);  /**  adicionar no pane.<br/>*/

        seg = new JTextField();/**  instancia a tela para altera os  segndos   e  seta seus parametros.  <br/> */
        seg.setBounds(320, 60, 89, 25);
        frame.getContentPane().add(seg); /**  adicionar na tela .<br/>*/
        seg.setColumns(10);
        panel.add(seg);   /**  adicionar no pane.<br/>*/

        JButton bHora = new JButton("Alterar Hora"); /**  intanciar o botão  e set seu parametros e adicionara na no panel e o panel na tela.<br/>*/
        bHora.addActionListener(btmH);
        bHora.setBounds(11, 85, 150, 30);
        frame.getContentPane().add(bHora);
        panel.add(bHora);

        JButton btnSetMinuto = new JButton("Alterar Minutos");  /**  intanciar o botão  e set seu parametros e adicionara na no panel e o panel na tela.<br/>*/
        btnSetMinuto.addActionListener(btmH);
        btnSetMinuto.setBounds(135, 85, 155, 30);
        frame.getContentPane().add(btnSetMinuto);
        panel.add(btnSetMinuto);

        JButton bSegundo = new JButton("Alterar Segundos"); /**  intanciar o botão  e set seu parametros e adicionara na no panel e o panel na tela.<br/>*/
        bSegundo.addActionListener(btmH);
        bSegundo.setBounds(265, 85, 155, 30);
        frame.getContentPane().add(bSegundo);
        panel.add(bSegundo);

        JButton bStart = new JButton("Start");  /**  intanciar o botão  e set seu parametros e adicionara na no panel e o panel na tela.<br/>*/
        bStart.addActionListener(btmH);
        bStart.setBounds(11, 140, 89, 23);
        frame.getContentPane().add(bStart);
        panel.add(bStart);

        JButton bConectar = new JButton("Conectar"); /**  intanciar o botão  e set seu parametros e adicionara na no panel e o panel na tela.<br/>*/
        bConectar.addActionListener(btmH);
        bConectar.setBounds(11, 165, 89, 23);
        frame.getContentPane().add(bConectar);
        panel.add(bConectar);

        JButton bStop = new JButton("Sair"); /**  intanciar o botão  e set seu parametros e adicionara na no panel e o panel na tela.<br/>*/
        bStop.addActionListener(btmH);
        bStop.setBounds(11, 190, 89, 23);
        frame.getContentPane().add(bStop);
        panel.add(bStop);

        drift = new JTextField(); /** cira a caixa de texto para  receber o novo drift. <br/> */
        drift.setBounds(300, 140, 40, 23);
        frame.getContentPane().add(drift);
        drift.setColumns(10);
        panel.add(drift);

        JButton bDrift = new JButton("Alterar Drift"); /**  intanciar o botão  e set seu parametros e adicionara na no panel e o panel na tela.<br/>*/
        bDrift.addActionListener(btmH);
        bDrift.setBounds(290, 165, 89, 23);
        frame.getContentPane().add(bDrift);
        panel.add(bDrift);
    }
     /**  a seguir  a função que vai verificar   qual o botão usuario clicou  e  relizar ação escolhida.<br/>*/
    private class ButtonHandller implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Start".equals(e.getActionCommand())) {

                control.start();

            } else if ("Sair".equals(e.getActionCommand())) {
    
                frame.dispose();

            } else if ("Alterar Drift".equals(e.getActionCommand())) {
                if (" ".equals(drift.getText())) {
                    JOptionPane.showMessageDialog(null, "Insira um valor de Drift!!");
                } else {
                    long aux = Long.parseLong(drift.getText());
                    System.out.println("TESTE: "+aux);
                    control.setDrift(aux);
                }
            } else if ("Alterar Hora".equals(e.getActionCommand())) {
                if ("".equals(hora.getText())) {
                    JOptionPane.showMessageDialog(null, "Insira um valor da Hora!!");
                } else {
                    int aux = Integer.parseInt(hora.getText());

                    //control.setHr(aux);
                    control.setHora(aux);

                }
            } else if ("Alterar Minutos".equals(e.getActionCommand())) {
                if ("".equals(min.getText())) {
                    JOptionPane.showMessageDialog(null, "Insira um valor de Minutos!!");
                } else {
                    int aux = Integer.parseInt(min.getText());
                    control.setMin(aux);
                }

            } else if ("Alterar Segundos".equals(e.getActionCommand())) {
                if ("".equals(seg.getText())) {
                    JOptionPane.showMessageDialog(null, "Insira um valor de Segundos!!");
                } else {
                    int aux = Integer.parseInt(seg.getText());
                    control.setSeg(aux);
                }
            } else if ("Conectar".equals(e.getActionCommand())) {
                String ip = JOptionPane.showInputDialog("Informe o IP do grupo", "");
                System.out.println("IP do grupo - " + ip);
                //Servidor s = new Servidor(ip, 1234);
                
                Cliente c = new Cliente(ip, 1234);
                c.start();
                
                try {
                     control.conectar();
                } catch (Exception e1) {

                    System.out.println("Erro no envio");
                }
            }

        }

    }

}

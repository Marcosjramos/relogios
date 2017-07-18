/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.view;

import br.uefs.ecomp.controller.ControllerRelogio;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.uefs.ecomp.controller.threads.ThreadUpdateGUI;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.security.Principal;

/**
 *
 * @author marcos
 */
public class relogioGui {

    JFrame frame;
    private JTextField textField;
    private JTextField textFieldSA;
    private JTextField textFieldMA;
    private JTextField textFieldHA;
    private JTextField textField_4;
    private JTextField textField_5;
    private JButton btnIniciarConectar;
    private JButton btnAlterar;
    private JLabel lblH;
    private JLabel lblM;
    private JLabel lblS;
    private ControllerRelogio controller = ControllerRelogio.getInstance();

    /**
     * Launch the application.
     */
    //public static void main(String[] args) {
    //	EventQueue.invokeLater(new Runnable() {
    //		public void run() {
    //			try {
    //				relogioGui window = new relogioGui();
    //				window.frame.setVisible(true);
    //			} catch (Exception e) {
    //				e.printStackTrace();
    //			}
    //		}
    //	});
    //}
    /**
     * Create the application.
     */
    public relogioGui() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
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

        String nome = System.getProperty("os.name");//recupera o nome do SO
        //if(nome.substring(0, 7).equals("Windows")){
        //	try { 
        //		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //	} catch (UnsupportedLookAndFeelException ex) {
        //		ex.printStackTrace();
        //	} catch (IllegalAccessException ex) {
        //		ex.printStackTrace();
        //	} catch (InstantiationException ex) {
        //		ex.printStackTrace();
        //	} catch (ClassNotFoundException ex) {
        //		ex.printStackTrace();
        //	}
        //}

        frame = new JFrame();
        frame.setBounds(310, 310, 460, 460);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(20, 16, 420, 320);
        frame.getContentPane().add(tabbedPane);

        JPanel panel = new JPanel();
        tabbedPane.addTab("Relogio", null, panel, null);
        panel.setLayout(null);

        JButton button = new JButton("Alterar");
        button.setEnabled(false);
        button.setBounds(310, 122, 89, 23);
        panel.add(button);

        textField = new JTextField();
        textField.setEnabled(false);
        textField.setColumns(10);
        textField.setBounds(310, 78, 28, 22);
        panel.add(textField);

        JLabel label = new JLabel("1.0");
        label.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label.setBounds(310, 36, 28, 22);
        panel.add(label);

        JLabel label_1 = new JLabel("Drift:");
        label_1.setBounds(310, 11, 46, 14);
        panel.add(label_1);

        textFieldSA = new JTextField();
        textFieldSA.setEnabled(false);
        textFieldSA.setColumns(10);
        textFieldSA.setBounds(121, 79, 28, 20);
        panel.add(textFieldSA);

        JLabel label_2 = new JLabel(":");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_2.setBounds(104, 75, 18, 22);
        panel.add(label_2);

        textFieldMA = new JTextField();
        textFieldMA.setEnabled(false);
        textFieldMA.setColumns(10);
        textFieldMA.setBounds(66, 79, 28, 20);
        panel.add(textFieldMA);

        JLabel label_3 = new JLabel(":");
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_3.setBounds(49, 75, 18, 22);
        panel.add(label_3);

        textFieldHA = new JTextField();
        textFieldHA.setEnabled(false);
        textFieldHA.setColumns(10);
        textFieldHA.setBounds(11, 79, 28, 20);
        panel.add(textFieldHA);

        btnAlterar = new JButton("Alterar");
        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alterar();
            }
        });
        btnAlterar.setEnabled(false);
        btnAlterar.setBounds(11, 122, 89, 23);
        panel.add(btnAlterar);

        btnIniciarConectar = new JButton("Iniciar/Conectar");
        btnIniciarConectar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                inicia();
            }
        });
        btnIniciarConectar.setBounds(20, 180, 180, 55);
        panel.add(btnIniciarConectar);

        lblS = new JLabel("00");
        lblS.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblS.setBounds(87, 27, 28, 22);
        panel.add(lblS);

        JLabel label_5 = new JLabel(":");
        label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_5.setBounds(76, 27, 18, 22);
        panel.add(label_5);

        lblM = new JLabel("00");
        lblM.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblM.setBounds(49, 27, 28, 22);
        panel.add(lblM);

        JLabel label_7 = new JLabel(":");
        label_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_7.setBounds(38, 27, 18, 22);
        panel.add(label_7);

        lblH = new JLabel("00");
        lblH.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblH.setBounds(11, 27, 28, 22);
        panel.add(lblH);

        JLabel label_9 = new JLabel("Tempo:");
        label_9.setBounds(10, 11, 46, 14);
        panel.add(label_9);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Configurações", null, panel_1, null);
        panel_1.setLayout(null);

        JLabel lblIp = new JLabel("IP:");
        lblIp.setBounds(10, 11, 23, 14);
        panel_1.add(lblIp);

        textField_4 = new JTextField();
        textField_4.setBounds(31, 8, 86, 20);
        panel_1.add(textField_4);
        textField_4.setColumns(10);

        JLabel lblPorta = new JLabel("Porta:");
        lblPorta.setBounds(127, 11, 40, 14);
        panel_1.add(lblPorta);

        textField_5 = new JTextField();
        textField_5.setBounds(166, 8, 86, 20);
        panel_1.add(textField_5);
        textField_5.setColumns(10);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(10, 46, 89, 23);
        panel_1.add(btnAtualizar);

        //ThreadAtualizaGUI threadAtualiza = new ThreadAtualizaGUI(lblH,lblM,lblS);
        //threadAtualiza.start();
    }

    public void inicia() {

        //controller.iniciaContador();
        btnIniciarConectar.setEnabled(false);
        textFieldHA.setEnabled(true);
        textFieldMA.setEnabled(true);
        textFieldSA.setEnabled(true);
        btnAlterar.setEnabled(true);

    }

    public void alterar() { // está com problemas  com eu java havia ti falado cassio 
        String hora = textFieldHA.getText();
        String minuto = textFieldMA.getText();
        String segundo = textFieldSA.getText();
        if (!hora.equals("")) {
            try {
                int h = Integer.parseInt(hora);
                controller.setHoras(h);
                lblH.setText(h + "");
//                                Timer timer = new Timer();
//                                TimerTask task = new TimerTask() {
//                                    public void run() {
//                                        lblH.setText(i+"");
//                                    }
//                                };
//                                for(int o=0; o<100; o++)
//                                timer.schedule(task, 1000);
                for (int i = h; i < 24; i++) {// essa funcao deve ser feita por uma thread                                    
                    try {
                        Thread.sleep(1000);
                        lblH.setText(i + "");
                    } catch (InterruptedException ex) {

                    }
                    if (i == 23) {
                        i = 0;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!minuto.equals("")) {
            try {
                int m = Integer.parseInt(minuto);
                controller.setMinutos(m);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!segundo.equals("")) {
            try {
                int s = Integer.parseInt(segundo);
                controller.setSegundos(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

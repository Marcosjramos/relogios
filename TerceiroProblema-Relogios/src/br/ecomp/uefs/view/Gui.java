/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ecomp.uefs.view;

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
    
    private JTextField hora, min, seg, drift; /** variaveis para representar o hoario  e drift.  <br/>*/
    JFrame frame; /**  variavel para tela.   <br/>*/
    private Tempo relogio; /**   para ter a  contagem de tempo.  <br/>*/
    private JButton btnIniciarConectar;/**   Variavel para representar o botão Iniciar  conectar.  <br/>*/
    private JButton btnAlterar; /**   A variavel  para represtra  o botão Alterar.  <br/>*/
    private JLabel clock;/**variavel  para tela . <br/> */
    private JTextField textField;
    private Controller control;
    
    public Gui(){
        Incializar();
    }
    
    public  void  Incializar(){
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
        
      	        frame = new JFrame();
		frame.setBounds(280, 290, 520, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
                frame.setTitle("Relogios");
                
                 ButtonHandller btmH = new ButtonHandller();
                 
		JLabel relogio = new JLabel(" ");
		relogio.setHorizontalAlignment(SwingConstants.CENTER);
		relogio.setFont(new Font("Tahoma", Font.PLAIN, 62));
		relogio.setBounds(90, 27, 360, 75);
		frame.getContentPane().add(relogio);
                control = new Controller(relogio);
                
                JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 80, 450, 350);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Configurações", null, panel, null);
		panel.setLayout(null);

                hora = new JTextField();
		hora.setBounds(12, 60, 89, 25);
		frame.getContentPane().add(hora);
		hora.setColumns(10);
                panel.add(hora);
                
		min = new JTextField();
		min.setBounds(170, 60, 89, 25);
		frame.getContentPane().add(min);
		min.setColumns(10);
                panel.add(min);
                
                seg = new JTextField();
		seg.setBounds(320, 60, 89, 25);
		frame.getContentPane().add(seg);
		seg.setColumns(10);
                panel.add(seg);

	       JButton btnSetHora = new JButton("Alterar Hora");
		btnSetHora.addActionListener(btmH);
		btnSetHora.setBounds(11, 85, 150, 30);
		frame.getContentPane().add(btnSetHora);
                panel.add(btnSetHora);

		JButton btnSetMinuto = new JButton("Alterar Minutos");
		btnSetMinuto.addActionListener(btmH);
		btnSetMinuto.setBounds(135, 85, 155, 30);
		frame.getContentPane().add(btnSetMinuto);
                panel.add(btnSetMinuto);

		JButton btnSetSegundo = new JButton("Alterar Segundos");
		btnSetSegundo.addActionListener(btmH);
		btnSetSegundo.setBounds(265, 85, 155, 30);
		frame.getContentPane().add(btnSetSegundo);
                 panel.add(btnSetSegundo);

                JButton btnStart = new JButton("Start");
		btnStart.addActionListener(btmH);
		btnStart.setBounds(11, 140, 89, 23);
		frame.getContentPane().add(btnStart);
                panel.add(btnStart);
                
                JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(btmH);
		btnConectar.setBounds(11, 165, 89, 23);
		frame.getContentPane().add(btnConectar);
                panel.add(btnConectar);

		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(btmH);
		btnStop.setBounds(11, 190, 89, 23);
		frame.getContentPane().add(btnStop);
                panel.add(btnStop);
                
                drift = new JTextField();
		drift.setBounds(300, 140, 40, 23);
		frame.getContentPane().add(drift);
		drift.setColumns(10);
                panel.add(drift);
                
                JButton btnSetDrift = new JButton("Alterar Drift");
		btnSetDrift.addActionListener(btmH);
		btnSetDrift.setBounds(290, 165, 89, 23);
		frame.getContentPane().add(btnSetDrift);
                panel.add(btnSetDrift);
    }

	private class ButtonHandller implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if ("Start".equals(e.getActionCommand())) {
                            
                            control.start();
				
			} else if ("Stop".equals(e.getActionCommand())) {
				control.stop();
                                
			} else if ("Alterar Drift".equals(e.getActionCommand())) {
				if (" ".equals(drift.getText())) {
					JOptionPane.showMessageDialog(null, "Insira um valor de Drift!!");
				} else {
					long aux = Long.parseLong(drift.getText());
					control.setDrift(aux);					
				}
			} else if ("Alterar Hora".equals(e.getActionCommand())) {
				if ("".equals(hora.getText())){
					JOptionPane.showMessageDialog(null, "Insira um valor da Hora!!");
				} else {
					int aux = Integer.parseInt(hora.getText());
                                        control.setHr(aux);
					
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
			}else if ("Conectar".equals(e.getActionCommand())) {
				String ip = JOptionPane.showInputDialog("Informe o IP do grupo", "");
				System.out.println("IP do grupo - "+ ip);
				try {
					
				} catch (Exception e1) {
					
					System.out.println("Erro no envio");
				}
			}

		}

	
    }
    
}


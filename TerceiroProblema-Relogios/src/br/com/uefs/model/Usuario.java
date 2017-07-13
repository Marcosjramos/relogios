/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.model;

import java.net.InetAddress;

/**
 *
 * @author cassio e marcos 
 * @version 1.0
 */

/** a  classe a seguir vai ser usada para representar o usuario e todas as suaações que no caso são os relogios  . <br/>*/
public class Usuario {
    
    private InetAddress ip;  /** variavel para endereço ip do relogio/usuario. <br/>*/
    private int status;   /** variavel para  verificar o status do relogio/usuario se está funcionado ou não . <br/>*/
    private int hora;    /** variavel para corresponder a hora do relogio/usuario. <br/>*/
    private int min;     /** variavel para corresponder aos minutos do relogio/usuario. <br/>*/
    private int seg;     /** variavel para corresponder aos segundos do relogio/usuario. <br/>*/

    
      /** a função  a seguir  pega o endereço ip do relogio/usuario.  <br/>*/
    public InetAddress getIp() {
        return ip;
    }
     /** a função  a seguir  aletra o endereço ip do relogio/usuario.  <br/>*/
    public void setIp(InetAddress ip) {
        this.ip = ip;
    }
   /** a função  a seguir  pega o status do relogio/usuario.  <br/>*/
    public int getStatus() {
        return status;
    }
    /** a função  a seguir altera o status  do relogio/usuario.  <br/>*/
    public void setStatus(int status) {
        this.status = status;
    }
    /** a função  a seguir  pega a hora  do relogio/usuario.  <br/>*/
    public int getHora() {
        return hora;
    }
    /** a função  a seguir altera a hora   do relogio/usuario.  <br/>*/
    public void setHora(int hora) {
        this.hora = hora;
    }
   /** a função  a seguir  pega os minutos  do relogio/usuario.  <br/>*/
    public int getMin() {
        return min;
    }
     /** a função  a seguir altera os minutos  do relogio/usuario.  <br/>*/
    public void setMin(int min) {
        this.min = min;
    }
       /** a função  a seguir  pega os segundos  do relogio/usuario.  <br/>*/
    public int getSeg() {
        return seg;
    }
    /** a função  a seguir altera os segundos do relogio/usuario.  <br/>*/
    public void setSeg(int seg) {
        this.seg = seg;
    }
    
    
}

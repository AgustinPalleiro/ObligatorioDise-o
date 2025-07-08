/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;

/**
 *
 * @author Agus
 */
public class Apuesta {
    
    private double valor;
    private Jugador creadorApuesta;
    private ArrayList<Jugador> apostaron;
    
    public Apuesta(double valor, Jugador j){
        this.valor = valor;
        this.creadorApuesta=j;
        
        this.apostaron= new  ArrayList<Jugador>();
    }

    Apuesta() {
        this.valor=0;
         this.creadorApuesta=null;
         this.apostaron= new  ArrayList<Jugador>();
    }

    
    
    
    
   

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Jugador getCreadorApuesta() {
        return creadorApuesta;
    }

    public ArrayList<Jugador> getApostaron() {
        return apostaron;
    }    
    
    public void agregarAListaApostaron(Jugador j){
        apostaron.add(j);
    }
    
}

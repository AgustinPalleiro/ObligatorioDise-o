/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import panelCartasPoker.CartaPoker;

/**
 *
 * @author emili
 */
public abstract class Figura {
    
    private int valor;
    private String nombre;
    
    
  public Figura(String nombre, int valor){
        this.nombre = nombre;
        this.valor = valor;
    }
    
    public abstract String getNombre();
    
    
    public abstract int definirFigura(ArrayList<CartaPoker> cartas);

    public abstract int getValor();

    @Override
    public String toString() {
        return  nombre;
    }

    boolean esFigura(ArrayList<CartaPoker> cartas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public abstract int cartaAlta(ArrayList<CartaPoker> cartas);
        
    

}

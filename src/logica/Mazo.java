/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import excepciones.CartaException;
import java.util.ArrayList;
import java.util.Random;
import panelCartasPoker.CartaPoker;

/**
 *
 * @author Agus
 */
public class Mazo {
    
    private ArrayList<Carta> cartas = new ArrayList();
  
    public Mazo() throws CartaException {
        cargarMazo();
    }
    
    
    public void cargarMazo() throws CartaException {
       
        int[] valores = {
            Carta.AS, Carta.DOS, Carta.TRES, Carta.CUATRO, Carta.CINCO,
            Carta.SEIS, Carta.SIETE, Carta.OCHO, Carta.NUEVE, Carta.DIEZ,
            Carta.J, Carta.Q, Carta.K
        };

        String[] palos = {Carta.CORAZON, Carta.DIAMANTE, Carta.TREBOL, Carta.PIQUE};

        for (String palo : palos) {
            for (int valor : valores) {
                cartas.add(new Carta(valor, palo));
            }
        }
    }
    
    public ArrayList<CartaPoker> repartir5Cartas() {
    ArrayList<CartaPoker> lasCartas = new ArrayList<>();
 
        for (int i = 0; i < 5; i++) {  
            int posicion = numeroRandom(0, cartas.size() - 1);  
            Carta laCarta = cartas.get(posicion);
            laCarta.setVisible(true);            
            lasCartas.add(laCarta);
            cartas.remove(posicion);  
        }
        return lasCartas;  
    }  

    private int numeroRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
    
    
    /*public void cargarMazo() {
        int[] valores = {
            Carta.AS, Carta.DOS, Carta.TRES, Carta.CUATRO, Carta.CINCO,
            Carta.SEIS, Carta.SIETE, Carta.OCHO, Carta.NUEVE, Carta.DIEZ,
            Carta.J, Carta.Q, Carta.K
        };
 
        String[] palos = {Carta.CORAZON, Carta.DIAMANTE, Carta.TREBOL, Carta.PIQUE};
 
        for (String palo : palos) {
            for (int valor : valores) {
                int valorAsignado;
 
            // Asigna valores específicos a las figuras
                if (valor == Carta.AS) {
                    valorAsignado = 11;
                } else if (valor == Carta.J || valor == Carta.Q || valor == Carta.K) {
                    valorAsignado = 10;
                } else {
                    valorAsignado = valor;
                }
                cartas.add(new Carta(valorAsignado, palo));
            }
        }
    }*/

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import excepciones.CartaException;
import excepciones.MesaException;
import java.util.ArrayList;
import observador.Observable;

/**
 *
 * @author Agus
 */
public class SistemaMesa extends Observable{

    private ArrayList<Mesa> mesas = new ArrayList();

    public ArrayList<Mesa> getMesa() {
        return mesas;
    }
    
    public ArrayList<Mesa> getListaMesasAbiertas() {
       
 ArrayList<Mesa> mesasAbiertas = new ArrayList<Mesa>();
        for(Mesa m: mesas){
            if (m.getEstado().getDescripcion().equals("Abierta"))
                mesasAbiertas.add(m);
        }
        return mesasAbiertas;
    }

    public void crearMesa(int cantJugadores, double apuestaMinima, int comision) throws MesaException, CartaException{
        Mesa m = new Mesa(cantJugadores, apuestaMinima, comision);
        m.validar();
        mesas.add(m);
        Fachada.getInstancia().avisar(Eventos.eventos.cambioListaMesas);
   
        
    }
    
    public double getMontoTotalMesas(){
        double montoTotal = 0;
        for(Mesa m: mesas){
            montoTotal += m.montoTotalManos();
        }
        return montoTotal;
    }

    

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import excepciones.SaldoException;
import java.util.ArrayList;
import panelCartasPoker.CartaPoker;

/**
 *
 * @author emili
 */

public class Jugador extends Usuario{
    
    private double saldo;
    private ArrayList<CartaPoker> cartas;
    private Mano manoActual;
    private SituacionJugador situacion;
    private Figura laFigura;

    public void borrarCartas() {
        this.cartas.clear();
    }

   
    
    public enum Eventos{cambioSaldo};

    public Jugador(double saldo, String nombreCompleto, String password, String cedula) {
        super(nombreCompleto, password, cedula);
        this.saldo = saldo;
    }

    public Jugador(String nombreCompleto, String password, String cedula) {
        super(nombreCompleto, password, cedula);
    }
    
    public enum SituacionJugador{AccionPendiente, ApuestaIniciada, ApuestaPagada, NoPaga} 

    public double getSaldo() {
        return saldo;
    }                 

    public ArrayList<CartaPoker> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<CartaPoker> cartas) {
        this.cartas = cartas;
    }

    public Mano getManoActual() {
        return manoActual;
    }

    public void setManoActual(Mano manoActual) {
         this.manoActual = manoActual;
    }
    
    public SituacionJugador getSituacion() {
        return situacion;
    }

    public void setSituacion(SituacionJugador situacion) {
        this.situacion = situacion;
    }
    
    public void cambiarSituacion(){
        if(manoActual.getEstado().getDescripcion().equals("ApuestaIniciada") || manoActual.getEstado().getDescripcion().equals("EsperandoApuesta")){
            this.setSituacion(SituacionJugador.AccionPendiente);
        }
    }
    
  public void bajarSaldo(double monto) throws SaldoException {     
    if (monto > 0 && monto <= saldo) {        
        saldo -= monto;   
        avisar(Eventos.cambioSaldo);
    } else {        
        
        throw new SaldoException("Monto inválido o saldo insuficiente.");
    }
}
    
     public void incrementarSaldo(double monto) {     
        if (monto > 0 && monto <= saldo) {        
            saldo += monto;   
            avisar(Eventos.cambioSaldo);
        } 
    }
    
   
    private void identificarFigura() {
        if (cartas == null || cartas.size() != 5) {
            this.laFigura = null;
            return;
        }
        Figura[] figuras = { new Poker(), new Escalera(), new Pierna(), new Par(), new SinFigura()};
        for (Figura figura : figuras) {
            if (figura.definirFigura(cartas)==1) {
                this.laFigura = figura;
                return;
            }
        }
    }

    public Figura getLaFigura() {
        return laFigura;
    }
    
    public void agarrarCartas(ArrayList<CartaPoker> cartasMano) {
        if (cartas == null) {
            cartas = new ArrayList<>();
        }
        cartas.addAll(cartasMano); 
        identificarFigura();
    }

    public int cartaAlta() {
        
        return laFigura.cartaAlta(cartas);
        
    }
    
    
    
    
    
    
}

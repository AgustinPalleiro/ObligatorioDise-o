/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author Agus
 */
public class Pozo {
    
    private double monto;
    
    public Pozo(/*double monto*/){
        this.monto = 0;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    public void incrementarPozo(double importe) {
        this.monto += importe; 
    }

    void incremetarPozo(double monto) {
       this.monto+=monto;
    }
    
    void bajarPozo(double monto) {
       this.monto-=monto;
    }
}

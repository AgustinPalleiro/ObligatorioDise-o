/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import excepciones.CartaException;
import panelCartasPoker.CartaPoker;

/**
 *
 * @author emili
 */
public class Carta implements CartaPoker {
    private int valor;
    private String palo;
    private boolean visible;

    public Carta(int valor, String palo) throws CartaException {
        if (valor < AS || valor > K) {
            throw new CartaException("Valor de carta inválido.");
        }
        if (!palo.equals(CORAZON) && !palo.equals(DIAMANTE) && !palo.equals(TREBOL) && !palo.equals(PIQUE)) {
            throw new CartaException("Palo de carta inválido.");
        }
        this.valor = valor;
        this.palo = palo;
        this.visible = false; // Por defecto, la carta está oculta
    }

    @Override
    public int getValorCarta() {
        return valor;
    }

    @Override
    public String getPaloCarta() {
        return palo;
    }

    @Override
    public boolean estaVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return (visible ? valor + palo : "Carta oculta");
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author emili
 */


import java.util.ArrayList;
import panelCartasPoker.CartaPoker;

public class SinFigura extends Figura {

    private CartaPoker cartaAlta; // La carta más alta entre las dadas

    public SinFigura() {
        super("Sin Figura", 0); // Valor jerárquico más bajo
    }

    @Override
    public boolean esFigura(ArrayList<CartaPoker> cartas) {
        if (cartas == null || cartas.isEmpty()) {
            return false; // No se puede determinar sin cartas
        }

        // Determinar la carta más alta
      /*  cartaAlta = cartas.stream().max((c1, c2) -> Integer.compare(c1.getValor(), c2.getValor())).orElse(null);*/
        return true; // Siempre es válido como última opción
    }

    public CartaPoker getCartaAlta() {
        return cartaAlta;
    }

    @Override
    public String toString() {
        return "Sin Figura";
    }

    @Override
    public String getNombre() {
         return "Sin Figura";
    }

    @Override
    public int definirFigura(ArrayList<CartaPoker> cartas) {
        return 1;
    }

    @Override
    public int getValor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int cartaAlta(ArrayList<CartaPoker> cartas) {
        if (cartas == null || cartas.isEmpty()) {
            throw new IllegalArgumentException("La lista de cartas no puede estar vacía");
        }

        int valorMasAlto = 0;
        for (CartaPoker carta : cartas) {
            if (carta.getValorCarta() > valorMasAlto) {
                valorMasAlto = carta.getValorCarta();
            }
        }
        return valorMasAlto; // Retorna el valor de la carta más alta
    }

}


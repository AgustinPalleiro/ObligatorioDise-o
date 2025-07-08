/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import panelCartasPoker.CartaPoker;

/**
 *
 * @author Agus
 */
public class Poker extends Figura{
    
    private String nombre;
    private int valor;
    
    public Poker() {
        super("Poker", 4);
    }


    @Override
    public String getNombre() {
        return "Poker";
    }

    @Override
    public int getValor() {
        return 4;
    }     

   @Override
   public int definirFigura(ArrayList<CartaPoker> cartas) {
    // Usamos un Map para contar cuántas veces aparece cada valor
    Map<Integer, Integer> contadorValores = new HashMap<>();
    for (CartaPoker carta : cartas) {
        int valor = carta.getValorCarta();
        contadorValores.put(valor, contadorValores.getOrDefault(valor, 0) + 1);
    }

    // Verificar si alguna carta aparece exactamente cuatro veces
    for (int count : contadorValores.values()) {
        if (count == 4) {
            return 1; // Es un póker
        }
    }
    return 0; // No es un póker
}

   
   @Override
    public int cartaAlta(ArrayList<CartaPoker> cartas) {
        Map<Integer, Integer> contadorValores = new HashMap<>();
    for (CartaPoker carta : cartas) {
        int valor = carta.getValorCarta();
        contadorValores.put(valor, contadorValores.getOrDefault(valor, 0) + 1);
    }

    // Identificar la carta que forma el póker
    for (Map.Entry<Integer, Integer> entry : contadorValores.entrySet()) {
        if (entry.getValue() == 4) {
            return entry.getKey(); // Retornar el valor de la carta que forma el póker
        }
    }

    return 0; // No debería llegar aquí si ya se ha validado que es un póker
    }
}

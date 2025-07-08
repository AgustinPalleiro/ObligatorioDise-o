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
public class Par extends Figura{
    
    private String nombre;
    private int valor;
    
    public Par() {
        super("Par", 1);
    }


    
    @Override
    public String getNombre() {
        return "Par";
    }
    
    @Override
    public int getValor() {
        return 1;
    }

   @Override
    public int definirFigura(ArrayList<CartaPoker> cartas) {
    // Contar cuántas veces aparece cada valor en las cartas
    Map<Integer, Integer> contadorValores = new HashMap<>();
    for (CartaPoker carta : cartas) {
        int valor = carta.getValorCarta();
        contadorValores.put(valor, contadorValores.getOrDefault(valor, 0) + 1);
    }

    // Verificar si hay exactamente un par
    for (int count : contadorValores.values()) {
        if (count == 2) {
            return 1; // Es un par
        }
    }
    return 0; // No es un par
}

    @Override
    public int cartaAlta(ArrayList<CartaPoker> cartas) {
    // Este método se debe llamar únicamente si definirFigura confirmó un Par
    // Por seguridad, se valida una vez más
    if (definirFigura(cartas) != 1) {
        return 0;
    }

    // Contar cuántas veces aparece cada valor en las cartas
    Map<Integer, Integer> contadorValores = new HashMap<>();
    for (CartaPoker carta : cartas) {
        int valor = carta.getValorCarta();
        contadorValores.put(valor, contadorValores.getOrDefault(valor, 0) + 1);
    }

    // Buscar el valor de la carta que forma parte del Par
    for (Map.Entry<Integer, Integer> entry : contadorValores.entrySet()) {
        if (entry.getValue() == 2) { // Si es un Par
            return entry.getKey(); // Devolver el valor de la carta del Par
        }
    }
        return 0;
   
  }

 
}

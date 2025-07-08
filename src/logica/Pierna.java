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
public class Pierna extends Figura{
    
    private String nombre;
    private int valor;
    
    public Pierna() {
        super("Pierna", 2);
    }

    
    
    @Override
    public String getNombre() {
        return "Pierna";
    }
    
    @Override
    public int getValor() {
        return valor;
    }

     @Override
    public int definirFigura(ArrayList<CartaPoker> cartas) {
        // Usamos un Map para contar cuántas veces aparece cada valor
        Map<Integer, Integer> contadorValores = new HashMap<>();
        for (CartaPoker carta : cartas) {
            int valor = carta.getValorCarta();
            contadorValores.put(valor, contadorValores.getOrDefault(valor, 0) + 1);
        }

        // Verificar si hay exactamente tres cartas con el mismo valor
        for (int count : contadorValores.values()) {
            if (count == 3) {
                return 1; // Es una Pierna
            }
        }
        return 0; // No es una Pierna
    }

   @Override
public int cartaAlta(ArrayList<CartaPoker> cartas) {
    if (cartas == null || cartas.isEmpty()) {
        throw new IllegalArgumentException("La lista de cartas no puede estar vacía");
    }

    // Validar si las cartas forman una Pierna
    if (definirFigura(cartas) != 1) { // `definirFigura` debería devolver 1 si es una Pierna
       return 0;
    }

    // Contar cuántas veces aparece cada valor
    Map<Integer, Integer> contadorValores = new HashMap<>();
    for (CartaPoker carta : cartas) {
        int valor = carta.getValorCarta();
        contadorValores.put(valor, contadorValores.getOrDefault(valor, 0) + 1);
    }

    // Buscar el valor que forma la Pierna
    for (Map.Entry<Integer, Integer> entry : contadorValores.entrySet()) {
        if (entry.getValue() == 3) {
            return entry.getKey(); // Devolver el valor de la carta que forma la Pierna
        }
    }

    // No debería llegar aquí si definirFigura asegura que hay una Pierna
    return 0;
}

    
}

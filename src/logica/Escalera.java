/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import panelCartasPoker.CartaPoker;

/**
 *
 * @author Agus
 */
public class Escalera extends Figura{
    
    private String nombre;
    private int valor;
    
    public Escalera() {
        super("Escalera", 3);
    }
    
    @Override
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public int getValor() {
        return valor;
    }
@Override
    public int definirFigura(ArrayList<CartaPoker> cartas) {
    // Obtener los valores de las cartas y ordenarlos
    List<Integer> valores = cartas.stream()
                                   .map(CartaPoker::getValorCarta)
                                   .sorted()
                                   .toList();

    // Verificar si los valores son consecutivos
    for (int i = 0; i < valores.size() - 1; i++) {
        if (valores.get(i) + 1 != valores.get(i + 1)) {
            return 0; // No es una escalera
        }
    }
    return 1; // Es una escalera
}

@Override
public int cartaAlta(ArrayList<CartaPoker> cartas) {
    if (cartas == null || cartas.isEmpty()) {
        throw new IllegalArgumentException("La lista de cartas no puede estar vacía");
    }

    // Encontrar y devolver el valor más alto de las cartas
    return cartas.stream()
                 .mapToInt(CartaPoker::getValorCarta)
                 .max()
                 .orElseThrow(() -> new IllegalArgumentException("Error al calcular la carta más alta"));
}


}
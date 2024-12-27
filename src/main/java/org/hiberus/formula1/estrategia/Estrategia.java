package org.hiberus.formula1.estrategia;

import org.hiberus.formula1.exception.CombustibleAgotadoException;
import org.hiberus.formula1.exception.NeumaticosAgotadosException;
import org.hiberus.formula1.model.combustible.Combustible;
import org.hiberus.formula1.model.neumaticos.Neumaticos;

public class Estrategia {
    private final Combustible combustible;
    private final Neumaticos neumaticos;
    private final int kmRecorrer;

    public Estrategia(Combustible combustible, Neumaticos neumaticos, int kmRecorrer) {
        this.combustible = combustible;
        this.neumaticos = neumaticos;
        this.kmRecorrer = kmRecorrer;
    }

    public boolean esViable() {
        if (kmRecorrer <= 0) {throw new IllegalArgumentException("La distancia a recorrer debe ser mayor a 0.");}
        if (!combustible.puedeCompletarRecorrido(kmRecorrer)) {throw new CombustibleAgotadoException("Gasolina insuficiente para completar el recorrido.");}
        if (!neumaticos.puedeCompletarRecorrido(kmRecorrer)) {throw new NeumaticosAgotadosException("Los neumáticos no pueden completar el recorrido.");}
        return true;
    }
}
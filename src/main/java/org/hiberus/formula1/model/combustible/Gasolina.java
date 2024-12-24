package org.hiberus.formula1.model.combustible;

import org.hiberus.formula1.model.combustible.Combustible;
import org.hiberus.formula1.exception.CombustibleAgotadoException;

public class Gasolina implements Combustible {
    private double litrosDisponibles;
    private double consumoPorKm;

    public Gasolina(double litrosDisponibles, double consumoPorKm) {
        this.litrosDisponibles = litrosDisponibles;
        this.consumoPorKm = consumoPorKm;
    }

    @Override
    public boolean puedeCompletarRecorrido(int km) {
        if (litrosDisponibles < consumoPorKm * km) {
            throw new CombustibleAgotadoException("Gasolina insuficiente para completar el recorrido.");
        }
        return true;
    }
}
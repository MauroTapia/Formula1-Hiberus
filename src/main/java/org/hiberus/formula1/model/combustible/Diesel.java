package org.hiberus.formula1.model.combustible;

import org.hiberus.formula1.exception.CombustibleAgotadoException;

public class Diesel implements Combustible {
    private double litrosDisponibles;
    private double consumoPorKm;

    public Diesel(double litrosDisponibles, double consumoPorKm) {
        if (litrosDisponibles <= 0) {throw new IllegalArgumentException("Los litros disponibles deben ser mayores a 0.");}
        if (consumoPorKm <= 0) {throw new IllegalArgumentException("El consumo por km debe ser mayor a 0.");}
        this.litrosDisponibles = litrosDisponibles;
        this.consumoPorKm = consumoPorKm * 1.2; // Ajuste del 20% adicional para Diesel
    }

    @Override
    public boolean puedeCompletarRecorrido(int km) {
        if (km <= 0) {throw new IllegalArgumentException("La distancia a recorrer debe ser mayor a 0.");}
        if (litrosDisponibles < consumoPorKm * km) {throw new CombustibleAgotadoException("Diesel insuficiente para completar el recorrido.");}
        return true;
    }
}
package org.hiberus.formula1.model.combustible;

import org.hiberus.formula1.exception.CombustibleAgotadoException;
import org.hiberus.formula1.model.combustible.Gasolina;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GasolinaTest {

    @Test
    void testPuedeCompletarRecorridoConCombustibleSuficiente() {
        Gasolina gasolina = new Gasolina(50, 2); // 50 litros disponibles, consumo 2 por km
        assertTrue(gasolina.puedeCompletarRecorrido(25)); // 25 km requieren 50 litros
    }

    @Test
    void testLanzaExcepcionPorCombustibleInsuficiente() {
        Gasolina gasolina = new Gasolina(30, 2); // 30 litros disponibles, consumo 2 por km
        Exception exception = assertThrows(CombustibleAgotadoException.class,
                () -> gasolina.puedeCompletarRecorrido(20)); // 20 km requieren 40 litros
        assertEquals("Gasolina insuficiente para completar el recorrido.", exception.getMessage());
    }

    @Test
    void testConsumoDirecto() {
        Gasolina gasolina = new Gasolina(10, 1); // Consumo directo 1 litro/km
        assertTrue(gasolina.puedeCompletarRecorrido(10)); // Exactamente 10 km
//        assertFalse(gasolina.puedeCompletarRecorrido(11)); // 11 km requieren 11 litros
    }

    @Test
    void testCombustibleNegativoOLitrosCero() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Gasolina(-5, 1)); // Litros negativos
        assertEquals("Los litros disponibles deben ser mayores a 0.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Gasolina(0, 1)); // Cero litros
        assertEquals("Los litros disponibles deben ser mayores a 0.", exception.getMessage());
    }

    @Test
    void testConsumoNegativoOCero() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Gasolina(10, -1)); // Consumo negativo
        assertEquals("El consumo por km debe ser mayor a 0.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Gasolina(10, 0)); // Consumo cero
        assertEquals("El consumo por km debe ser mayor a 0.", exception.getMessage());
    }

    @Test
    void testKmNegativoOCeroEnRecorrido() {
        Gasolina gasolina = new Gasolina(10, 1); // Gasolina válida
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> gasolina.puedeCompletarRecorrido(-3)); // km negativo
        assertEquals("La distancia a recorrer debe ser mayor a 0.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> gasolina.puedeCompletarRecorrido(0)); // km cero
        assertEquals("La distancia a recorrer debe ser mayor a 0.", exception.getMessage());
    }

}
package org.hiberus.formula1.model.neumaticos;

import org.hiberus.formula1.model.neumaticos.Pirelli;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PirelliTest {

    @Test
    void testVidaRestanteConDesgaste() {
        Pirelli pirelli = new Pirelli(100);

        pirelli.desgastar(10);

        assertEquals(80, pirelli.obtenerVidaRestante(), 0.01);
    }

    @Test
    void testPuedeCompletarRecorrido() {
        Pirelli pirelli = new Pirelli(100);

        pirelli.desgastar(20);

        assertTrue(pirelli.puedeCompletarRecorrido(30)); // 30 km deberían ser viables con 60 de vida restante

        pirelli.desgastar(20);
        assertFalse(pirelli.puedeCompletarRecorrido(30)); // No debería poder completar
    }

    @Test
    void testVidaRestanteEsCero() {
        Pirelli pirelli = new Pirelli(100);

        pirelli.desgastar(50);  // 50 * 2 = 100 (vida agotada)

        assertEquals(0, pirelli.obtenerVidaRestante(), 0.01);
        assertFalse(pirelli.puedeCompletarRecorrido(1)); // Ya no puede completar ningún recorrido
    }

    @Test
    void testVidaRestanteNegativaNoPermitir() {
        Pirelli pirelli = new Pirelli(100);

        pirelli.desgastar(60);  // 60 * 2 = 120

        assertEquals(0, pirelli.obtenerVidaRestante(), 0.01);
    }
}
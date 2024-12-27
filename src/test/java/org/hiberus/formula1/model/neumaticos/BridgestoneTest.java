package org.hiberus.formula1.model.neumaticos;

import org.hiberus.formula1.model.neumaticos.Bridgestone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BridgestoneTest {

    @Test
    void testVidaRestanteConDesgaste() {
        Bridgestone bridgestone = new Bridgestone(100);

        bridgestone.desgastar(10);

        double vidaRestante = bridgestone.obtenerVidaRestante();
        System.out.println("Vida restante después de desgastar 10 km: " + vidaRestante);
        assertEquals(65, vidaRestante, 0.1);
    }

    @Test
    void testPuedeCompletarRecorrido() {
        Bridgestone bridgestone = new Bridgestone(100);

        bridgestone.desgastar(20);

        double vidaRestante = bridgestone.obtenerVidaRestante();
        System.out.println("Vida restante después de desgastar 20 km: " + vidaRestante);
        assertEquals(30, vidaRestante, 0.01);

        boolean puedeCompletar = bridgestone.puedeCompletarRecorrido(30);
        System.out.println("Puede completar el recorrido de 30 km: " + puedeCompletar);
        assertTrue(puedeCompletar);

        bridgestone.desgastar(20);

        vidaRestante = bridgestone.obtenerVidaRestante();
        System.out.println("Vida restante después de desgastar otros 20 km: " + vidaRestante);
        assertEquals(0, vidaRestante, 0.01);

        puedeCompletar = bridgestone.puedeCompletarRecorrido(30);
        System.out.println("Puede completar el recorrido de 30 km con vida restante 0: " + puedeCompletar);
        assertFalse(puedeCompletar);
    }

    @Test
    void testVidaRestanteEsCero() {
        Bridgestone bridgestone = new Bridgestone(100);

        bridgestone.desgastar(30);

        assertEquals(0, bridgestone.obtenerVidaRestante(), 0.01);
        assertFalse(bridgestone.puedeCompletarRecorrido(1)); // Ya no puede completar ningún recorrido
    }

    @Test
    void testVidaRestanteNegativaNoPermitir() {
        Bridgestone bridgestone = new Bridgestone(100);

        bridgestone.desgastar(35);  // 35 * 3.5 = 122.5

        assertEquals(0, bridgestone.obtenerVidaRestante(), 0.01);
    }
}
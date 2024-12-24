package formula1.model.combustible;

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


}
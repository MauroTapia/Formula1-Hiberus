package formula1.model.neumaticos;

import org.hiberus.formula1.exception.CombustibleAgotadoException;
import org.hiberus.formula1.model.combustible.Diesel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DieselTest {

    @Test
    void testPuedeCompletarRecorridoConCombustibleSuficiente() {
        Diesel diesel = new Diesel(50, 2); // 50 litros disponibles, consumo base 2 (ajustado a 2.4)
        assertTrue(diesel.puedeCompletarRecorrido(20)); // 20 km requieren 48 litros
    }

    @Test
    void testLanzaExcepcionPorCombustibleInsuficiente() {
        Diesel diesel = new Diesel(47, 2); // 30 litros disponibles, consumo base 2 (ajustado a 2.4)
        Exception exception = assertThrows(CombustibleAgotadoException.class,
                () -> diesel.puedeCompletarRecorrido(20)); // 20 km requieren 48 litros
        assertEquals("Diesel insuficiente para completar el recorrido.", exception.getMessage());
    }

    @Test
    void testConsumoAjustadoCorrectamente() {
        Diesel diesel = new Diesel(50, 2); // Consumo ajustado a 2.4
        assertTrue(diesel.puedeCompletarRecorrido(20)); // 20 km * 2.4 = 48 litros
//        assertFalse(diesel.puedeCompletarRecorrido(25)); // 25 km * 2.4 = 60 litros (insuficiente)
    }

    @Test
    void testCombustibleNegativoOLitrosCero() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Diesel(-10, 2)); // Litros negativos
        assertEquals("Los litros disponibles deben ser mayores a 0.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Diesel(0, 2)); // Cero litros
        assertEquals("Los litros disponibles deben ser mayores a 0.", exception.getMessage());
    }

    @Test
    void testConsumoNegativoOCero() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Diesel(10, -2)); // Consumo negativo
        assertEquals("El consumo por km debe ser mayor a 0.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Diesel(10, 0)); // Consumo cero
        assertEquals("El consumo por km debe ser mayor a 0.", exception.getMessage());
    }

    @Test
    void testKmNegativoOCeroEnRecorrido() {
        Diesel diesel = new Diesel(10, 1.2); // Diesel válido
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> diesel.puedeCompletarRecorrido(-5)); // km negativo
        assertEquals("La distancia a recorrer debe ser mayor a 0.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> diesel.puedeCompletarRecorrido(0)); // km cero
        assertEquals("La distancia a recorrer debe ser mayor a 0.", exception.getMessage());
    }
}
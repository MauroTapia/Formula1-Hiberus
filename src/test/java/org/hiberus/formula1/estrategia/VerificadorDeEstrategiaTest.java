package org.hiberus.formula1.estrategia;

import org.hiberus.formula1.exception.CombustibleAgotadoException;
import org.hiberus.formula1.model.combustible.Combustible;
import org.hiberus.formula1.model.combustible.Gasolina;
import org.hiberus.formula1.model.neumaticos.Bridgestone;
import org.hiberus.formula1.model.neumaticos.Neumaticos;
import org.hiberus.formula1.model.neumaticos.Pirelli;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VerificadorDeEstrategiaTest {

    private Estrategia crearEstrategia(double combustibleInicial, double consumoPorKm, double vidaInicialNeumaticos, String tipoNeumatico, int kmRecorrer) {
        Neumaticos neumaticos = "Bridgestone".equalsIgnoreCase(tipoNeumatico)
                ? new Bridgestone(vidaInicialNeumaticos)
                : new Pirelli(vidaInicialNeumaticos);
        Combustible gasolina = new Gasolina(combustibleInicial, consumoPorKm);
        return new Estrategia(gasolina, neumaticos, kmRecorrer);
    }


    @ParameterizedTest
    @CsvSource({
            "50.0, 5.0, 50.0, Pirelli, 10",   // Estrategia viable
            "30.0, 3.0, 80.0, Pirelli, 8",    // Estrategia no viable por neumáticos
            "100.0, 2.0, 100.0, Pirelli, 0"   // Estrategia no viable por distancia inválida
    })
    void testVerificadorDeEstrategia(double combustibleInicial, double consumoPorKm,
                                     double vidaInicialNeumaticos, String tipoNeumatico, int kmRecorrer) {
        Estrategia estrategia = crearEstrategia(combustibleInicial, consumoPorKm, vidaInicialNeumaticos, tipoNeumatico, kmRecorrer);
        VerificadorDeEstrategia.verificarEstrategia(estrategia, "Estrategia de prueba");

        if (kmRecorrer <= 0) {
            testDistancia(estrategia); // Validación de la distancia cuando es <= 0
        } else {
            testViabilidad(estrategia); // Si la distancia es válida, comprobamos la viabilidad
        }
    }

    private void testDistancia(Estrategia estrategia) {
        assertThrows(IllegalArgumentException.class, estrategia::esViable,
                "Se esperaba una excepción por distancia no válida.");
    }

    private void testViabilidad(Estrategia estrategia) {
        if (!estrategia.esViable()) {
            assertThrows(CombustibleAgotadoException.class, estrategia::esViable,
                    "Se esperaba que se lanzara una excepción por falta de combustible.");
        } else {
            assertTrue(estrategia.esViable(), "La estrategia debería ser viable.");
        }
    }
}

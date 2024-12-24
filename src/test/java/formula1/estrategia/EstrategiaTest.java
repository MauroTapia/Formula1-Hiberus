package formula1.estrategia;

import org.hiberus.formula1.estrategia.Estrategia;
import org.hiberus.formula1.exception.CombustibleAgotadoException;
import org.hiberus.formula1.exception.NeumaticosAgotadosException;
import org.hiberus.formula1.model.combustible.Combustible;
import org.hiberus.formula1.model.combustible.Gasolina;
import org.hiberus.formula1.model.neumaticos.Bridgestone;
import org.hiberus.formula1.model.neumaticos.Neumaticos;
import org.hiberus.formula1.model.neumaticos.Pirelli;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EstrategiaTest {

    private Estrategia crearEstrategia(double combustibleInicial, double consumoPorKm, double vidaInicialNeumaticos, String tipoNeumatico, int kmRecorrer) {
        Neumaticos neumaticos = "Bridgestone".equalsIgnoreCase(tipoNeumatico)
                ? new Bridgestone(vidaInicialNeumaticos)
                : new Pirelli(vidaInicialNeumaticos);
        Combustible gasolina = new Gasolina(combustibleInicial, consumoPorKm);
        return new Estrategia(gasolina, neumaticos, kmRecorrer);
    }

    @ParameterizedTest
    @CsvSource({
            "50.0, 5.0, 50.0, Pirelli, 10",
            "30.0, 3.0, 80.0, Pirelli, 8",
            "100.0, 5.0, 100.0, Bridgestone, 14",
            "100.0, 6.0, 100.0, Bridgestone, 12"
    })
    void testEstrategiaViable(double combustibleInicial, double consumoPorKm, double vidaInicialNeumaticos,
                              String tipoNeumatico, int kmRecorrer) {
        Estrategia estrategia = crearEstrategia(combustibleInicial, consumoPorKm, vidaInicialNeumaticos, tipoNeumatico, kmRecorrer);

        assertTrue(estrategia.esViable(), "La estrategia debería ser viable.");
    }

    @ParameterizedTest
    @CsvSource({
            "100.0, 5.0, 5.0, 5",
            "100.0, 5.0, 10.0, 20"
    })
    void testEstrategiaNoViablePorNeumaticos(double combustibleInicial, double consumoPorKm,
                                             double vidaInicialNeumaticos, int kmRecorrer) {

        Estrategia estrategia = crearEstrategia(combustibleInicial, consumoPorKm, vidaInicialNeumaticos, "Pirelli", kmRecorrer);

        assertThrows(NeumaticosAgotadosException.class, estrategia::esViable,
                "Se esperaba que se lanzara una excepción por desgaste excesivo de neumáticos.");
    }

    @ParameterizedTest
    @CsvSource({
            "10.0, 5.0, 100.0, 10",
            "15.0, 3.0, 100.0, 6"
    })
    void testEstrategiaNoViablePorFaltaDeCombustible(double combustibleInicial, double consumoPorKm,
                                                     double vidaInicialNeumaticos, int kmRecorrer) {

        Estrategia estrategia = crearEstrategia(combustibleInicial, consumoPorKm, vidaInicialNeumaticos, "Pirelli", kmRecorrer);

        assertThrows(CombustibleAgotadoException.class, estrategia::esViable,
                "Se esperaba que se lanzara una excepción por falta de combustible.");
    }

    @ParameterizedTest
    @CsvSource({
            "50.0, 5.0, 100.0, 0",
            "50.0, 5.0, 100.0, -1"
    })
    void testEstrategiaConDistanciaInvalida(double combustibleInicial, double consumoPorKm,
                                            double vidaInicialNeumaticos, int kmRecorrer) {

        Estrategia estrategia = crearEstrategia(combustibleInicial, consumoPorKm, vidaInicialNeumaticos, "Pirelli", kmRecorrer);

        assertThrows(IllegalArgumentException.class, estrategia::esViable,
                "Se esperaba una excepción por distancia no válida.");
    }

    // Test para verificar que la estrategia sea viable cuando los recursos son justos
    @ParameterizedTest
    @CsvSource({
            "10.0, 10.0, 10.0, 1",
            "20.0, 10.0, 10.0, 2"
    })
    void testEstrategiaLimite(double combustibleInicial, double consumoPorKm,
                              double vidaInicialNeumaticos, int kmRecorrer) {

        Estrategia estrategia = crearEstrategia(combustibleInicial, consumoPorKm, vidaInicialNeumaticos, "Pirelli", kmRecorrer);

        assertTrue(estrategia.esViable(), "La estrategia debería ser viable cuando los recursos son justos.");
    }
}
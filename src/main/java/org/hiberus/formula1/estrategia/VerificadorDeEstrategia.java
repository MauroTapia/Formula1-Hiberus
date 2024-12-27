package org.hiberus.formula1.estrategia;

import org.hiberus.formula1.exception.CombustibleAgotadoException;
import org.hiberus.formula1.exception.NeumaticosAgotadosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerificadorDeEstrategia {
    private static final Logger logger = LoggerFactory.getLogger(VerificadorDeEstrategia.class);

    public static void verificarEstrategia(Estrategia estrategia, String nombreEstrategia) {
        try {
            logger.info("{} es viable: {}", nombreEstrategia, estrategia.esViable());
        } catch (CombustibleAgotadoException | NeumaticosAgotadosException e) {
            logger.error("Error en {}: {}", nombreEstrategia, e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Error en {}: {}", nombreEstrategia, e.getMessage());
        }
    }
}
package org.hiberus.formula1.estrategia;

import org.hiberus.formula1.exception.CombustibleAgotadoException;
import org.hiberus.formula1.exception.NeumaticosAgotadosException;

public class VerificadorDeEstrategia {
    public static void verificarEstrategia(Estrategia estrategia, String nombreEstrategia) {
        try {
            System.out.println(nombreEstrategia + " es viable: " + estrategia.esViable());
        } catch (CombustibleAgotadoException | NeumaticosAgotadosException e) {
            System.out.println("Error en " + nombreEstrategia + ": " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en " + nombreEstrategia + ": " + e.getMessage());
        }
    }
}
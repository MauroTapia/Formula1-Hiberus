package org.hiberus;


import org.hiberus.formula1.estrategia.Estrategia;
import org.hiberus.formula1.estrategia.VerificadorDeEstrategia;
import org.hiberus.formula1.model.combustible.Combustible;
import org.hiberus.formula1.model.neumaticos.Bridgestone;
import org.hiberus.formula1.model.combustible.Diesel;
import org.hiberus.formula1.model.combustible.Gasolina;
import org.hiberus.formula1.model.neumaticos.Pirelli;
import org.hiberus.formula1.model.neumaticos.Neumaticos;

public class Formula1Main {
    public static void main(String[] args) {
        Combustible gasolina = new Gasolina(50.0, 5.0);
        Combustible diesel = new Diesel(60.0, 5.0);
        Neumaticos pirelli = new Pirelli(100.0);
        Neumaticos bridgestone = new Bridgestone(80.0);

        Estrategia estrategia1 = new Estrategia(gasolina, pirelli, 10);
        Estrategia estrategia2 = new Estrategia(diesel, bridgestone, 10);

        VerificadorDeEstrategia.verificarEstrategia(estrategia1, "Estrategia 1");
        System.out.println("Vida restante de los neumáticos en Estrategia 1: "
                + pirelli.obtenerVidaRestante() + " unidades.");


        VerificadorDeEstrategia.verificarEstrategia(estrategia2, "Estrategia 2");

        System.out.println("Vida restante de los neumáticos en Estrategia 2 (Bridgestone): "
                + bridgestone.obtenerVidaRestante() + " unidades.");

    }
}
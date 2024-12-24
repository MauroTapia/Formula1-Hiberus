package org.hiberus.formula1.model.neumaticos;

public class Pirelli extends NeumaticosBase {

    private static final double DESGASTE_POR_KM = 2.0;
    public Pirelli(double vidaInicial) {
        super(vidaInicial);
    }

    @Override
    protected double getDesgastePorKm() {
        return DESGASTE_POR_KM;
    }
}
package org.hiberus.formula1.model.neumaticos;

public class Bridgestone extends NeumaticosBase {
    private static final double DESGASTE_POR_KM = 3.5;

    public Bridgestone(double vidaInicial) {
        super(vidaInicial);
    }

    @Override
    protected double getDesgastePorKm() {
        return DESGASTE_POR_KM;
    }
}
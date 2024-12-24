package org.hiberus.formula1.model.neumaticos;

public abstract class NeumaticosBase implements Neumaticos {
    protected double vidaInicial;
    protected double kmRecorridos;

    public NeumaticosBase(double vidaInicial) {
        this.vidaInicial = vidaInicial;
        this.kmRecorridos = 0;
    }

    protected abstract double getDesgastePorKm();

    @Override
    public void desgastar(int km) {
        this.kmRecorridos += km;
    }

    @Override
    public double obtenerVidaRestante() {
        return Math.max(vidaInicial - kmRecorridos * getDesgastePorKm(), 0);
    }

    @Override
    public boolean puedeCompletarRecorrido(int km) {
        return obtenerVidaRestante() > km * getDesgastePorKm();
    }
}
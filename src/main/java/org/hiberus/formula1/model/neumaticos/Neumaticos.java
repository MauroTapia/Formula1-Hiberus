package org.hiberus.formula1.model.neumaticos;

public interface Neumaticos {

    boolean puedeCompletarRecorrido(int km);
    void desgastar(int km);
    double obtenerVidaRestante();

}
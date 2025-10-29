package com.ordenariscapital.riskengine.enums;

public enum NivelRiesgo {
    BAJO(1),
    MEDIO(2),
    ALTO(3),
    RECHAZADO(4);

    private final int valor;

    NivelRiesgo(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    /**
     * Aumenta el nivel de riesgo en un punto
     * @return siguiente nivel de riesgo, o el mismo si ya es RECHAZADO
     */
    public NivelRiesgo aumentar() {
        return switch (this) {
            case BAJO -> MEDIO;
            case MEDIO -> ALTO;
            case ALTO, RECHAZADO -> RECHAZADO;
        };
    }
    
    /**
    * Disminuye el nivel de riesgo en un punto
    * @return nivel anterior de riesgo, o el mismo si ya es BAJO
    */
   public NivelRiesgo disminuir() {
       return switch (this) {
           case RECHAZADO -> ALTO;
           case ALTO -> MEDIO;
           case MEDIO -> BAJO;
           case BAJO -> BAJO;
       };
   }
   
   /**
    * Obtiene el nivel de riesgo más alto entre dos
    */
   public static NivelRiesgo max(NivelRiesgo r1, NivelRiesgo r2) {
       return r1.valor > r2.valor ? r1 : r2;
   }
}

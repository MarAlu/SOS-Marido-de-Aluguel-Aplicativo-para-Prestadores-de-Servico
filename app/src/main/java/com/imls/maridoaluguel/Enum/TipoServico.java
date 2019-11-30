package com.imls.maridoaluguel.Enum;

public enum TipoServico {
    DIRECIONADO(1), LIVRE(2);

    private final int cdTipo;

    TipoServico(int cdTipo) {
        this.cdTipo = cdTipo;
    }

    public int getCdTipo() {
        return cdTipo;
    }
}

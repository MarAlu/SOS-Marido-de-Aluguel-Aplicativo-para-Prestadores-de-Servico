package com.imls.maridoaluguel.Enum;

public enum StatusUsuario {
    ATIVO(1), INATIVO(2);

    private final int cdStatus;

    StatusUsuario(int cdStatus) {
        this.cdStatus = cdStatus;
    }

    public int getCdStatus() {
        return cdStatus;
    }

}

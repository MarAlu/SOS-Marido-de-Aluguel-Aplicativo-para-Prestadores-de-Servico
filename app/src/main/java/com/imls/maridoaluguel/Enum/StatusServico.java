package com.imls.maridoaluguel.Enum;

public enum StatusServico {
    ABERTO(1), ACEITO(2), CONCLUIDO(3), CANCELADO(4), RECUSADO(5);

    private final int cdStatus;

    StatusServico(int cdStatus) {
        this.cdStatus = cdStatus;
    }

    public int getCdStatus() {
        return cdStatus;
    }
}

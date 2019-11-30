package com.imls.maridoaluguel.Enum;

public enum TipoUsuario {
    DOMESTICO(1), MARIDO_ALUGUEL(2), DOMESTICO_E_MARIDO(3);

    private final int idTipo;

    TipoUsuario(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }
}
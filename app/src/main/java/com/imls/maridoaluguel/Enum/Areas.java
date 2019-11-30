package com.imls.maridoaluguel.Enum;

public enum Areas    {
    ELETRICA(1), ENCANAMENTO(2), ALVENARIA(3), PINTURA(4), MARCENARIA(5), OUTROS(6), A(7);

    private final int id;

    Areas(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}

package com.imls.maridoaluguel.Form;

public class UsuarioDomestico {

    private int idDomestico;
    private int idUsuario;
    private int servicosFin;
    private float avaliacao;

    public UsuarioDomestico() {

    }

    public int getIdDomestico() {
        return idDomestico;
    }

    public void setIdDomestico(int idDomestico) {
        this.idDomestico = idDomestico;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(float avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getServicosFin() {
        return servicosFin;
    }

    public void setServicosFin(int servicosFin) {
        this.servicosFin = servicosFin;
    }
}

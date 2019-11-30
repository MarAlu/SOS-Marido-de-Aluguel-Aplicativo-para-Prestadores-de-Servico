package com.imls.maridoaluguel.Form;

import com.imls.maridoaluguel.Enum.Areas;

import java.util.ArrayList;

public class UsuarioMarido {

    private int idMarido;
    private int idUsuario;
    private Areas areaEletrica;
    private Areas areaAlvenaria;
    private Areas areaPintura;
    private Areas areaOutros;
    private Areas areaMarcenaria;
    private Areas areaEncanamento;
    private String descHabilidade;
    private int servicosRealizados;
    private float avaliacao;

    public UsuarioMarido() {

    }

    public int getIdMarido() {
        return idMarido;
    }

    public void setIdMarido(int idMarido) {
        this.idMarido = idMarido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Areas getAreaEletrica() {
        return areaEletrica;
    }

    public void setAreaEletrica(Areas areaEletrica) {
        this.areaEletrica = areaEletrica;
    }

    public Areas getAreaAlvenaria() {
        return areaAlvenaria;
    }

    public void setAreaAlvenaria(Areas areaAlvenaria) {
        this.areaAlvenaria = areaAlvenaria;
    }

    public Areas getAreaPintura() {
        return areaPintura;
    }

    public void setAreaPintura(Areas areaPintura) {
        this.areaPintura = areaPintura;
    }

    public Areas getAreaOutros() {
        return areaOutros;
    }

    public void setAreaOutros(Areas areaOutros) {
        this.areaOutros = areaOutros;
    }

    public Areas getAreaMarcenaria() {
        return areaMarcenaria;
    }

    public void setAreaMarcenaria(Areas areaMarcenaria) {
        this.areaMarcenaria = areaMarcenaria;
    }

    public Areas getAreaEncanamento() {
        return areaEncanamento;
    }

    public void setAreaEncanamento(Areas areaEncanamento) {
        this.areaEncanamento = areaEncanamento;
    }

    public String getDescHabilidade() {
        return descHabilidade;
    }

    public void setDescHabilidade(String descHabilidade) {
        this.descHabilidade = descHabilidade;
    }

    public float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(float avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getServicosRealizados() {
        return servicosRealizados;
    }

    public void setServicosRealizados(int servicosRealizados) {
        this.servicosRealizados = servicosRealizados;
    }
}

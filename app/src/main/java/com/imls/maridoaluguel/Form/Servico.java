package com.imls.maridoaluguel.Form;

import com.imls.maridoaluguel.Enum.Areas;
import com.imls.maridoaluguel.Enum.StatusServico;
import com.imls.maridoaluguel.Enum.TipoServico;

public class Servico {

    private int idServico;
    private int idDomestico;
    private int idMarido;
    private String descServico;
    private StatusServico statusServico;
    private Areas areaServico;
    private int notaParaMarido;
    private int notaParaDomestico;
    private int foneDomestico;
    private TipoServico tipoServico;

    public Servico() {

    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public int getIdDomestico() {
        return idDomestico;
    }

    public void setIdDomestico(int idDomestico) {
        this.idDomestico = idDomestico;
    }

    public int getIdMarido() {
        return idMarido;
    }

    public void setIdMarido(int idMarido) {
        this.idMarido = idMarido;
    }

    public String getDescServico() {
        return descServico;
    }

    public void setDescServico(String descServico) {
        this.descServico = descServico;
    }

    public StatusServico getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(StatusServico statusServico) {
        this.statusServico = statusServico;
    }

    public Areas getAreaServico() {
        return areaServico;
    }

    public void setAreaServico(Areas areaServico) {
        this.areaServico = areaServico;
    }

    public int getNotaParaMarido() {
        return notaParaMarido;
    }

    public void setNotaParaMarido(int notaParaMarido) {
        this.notaParaMarido = notaParaMarido;
    }

    public int getNotaParaDomestico() {
        return notaParaDomestico;
    }

    public void setNotaParaDomestico(int notaParaDomestico) {
        this.notaParaDomestico = notaParaDomestico;
    }

    public int getFoneDomestico() {
        return foneDomestico;
    }

    public void setFoneDomestico(int foneDomestico) {
        this.foneDomestico = foneDomestico;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }
}

package com.imls.maridoaluguel.Util;

import com.imls.maridoaluguel.Form.Usuario;

public class GerenciaInstanciaLogin {

    private static GerenciaInstanciaLogin instance = null;
    private static Usuario usuario = null;

    public static GerenciaInstanciaLogin getInstance() {
        if (instance == null) {
            usuario = new Usuario();
            return instance = new GerenciaInstanciaLogin();
        } else {
            return instance;
        }
    }

    public void setUsuario(Usuario usuario) {
        GerenciaInstanciaLogin.usuario = usuario;
    }

    public Usuario getUsuario() {
        return GerenciaInstanciaLogin.usuario;
    }
}

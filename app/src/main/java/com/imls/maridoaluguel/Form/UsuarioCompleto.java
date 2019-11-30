package com.imls.maridoaluguel.Form;

import com.imls.maridoaluguel.Enum.StatusUsuario;
import com.imls.maridoaluguel.Enum.TipoUsuario;

public class UsuarioCompleto {
    private Usuario user;
    private UsuarioMarido userMarido;
    private UsuarioDomestico userDomestico;

    public UsuarioCompleto() {

    }

    public UsuarioMarido getUserMarido() {
        return userMarido;
    }

    public void setUserMarido(UsuarioMarido userMarido) {
        this.userMarido = userMarido;
    }

    public UsuarioDomestico getUserDomestico() {
        return userDomestico;
    }

    public void setUserDomestico(UsuarioDomestico userDomestico) {
        this.userDomestico = userDomestico;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}

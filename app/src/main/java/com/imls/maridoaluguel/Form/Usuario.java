package com.imls.maridoaluguel.Form;

import com.imls.maridoaluguel.Enum.StatusUsuario;
import com.imls.maridoaluguel.Enum.TipoUsuario;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String dataNasc;
    private String fone;
    private TipoUsuario tipoUser;
    private StatusUsuario ativo;
    private String cidade;
    private String estado;

    public Usuario() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public TipoUsuario getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(TipoUsuario tipoUser) {
        this.tipoUser = tipoUser;
    }

    public StatusUsuario getAtivo() {
        return ativo;
    }

    public void setAtivo(StatusUsuario ativo) {
        this.ativo = ativo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    Usuario(String nome, String email, String cidade, String estado, String dataNasc, String senha, TipoUsuario tipoUser, String fone) {
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.estado = estado;
        this.dataNasc = dataNasc;
        this.senha = senha;
        this.tipoUser = tipoUser;
        this.fone = fone;
    }

    public  Usuario(String nome, String email, String cidade, String estado, String fone, String dataNasc) {
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.estado = estado;
        this.fone = fone;
        this.dataNasc = dataNasc;
    }
}

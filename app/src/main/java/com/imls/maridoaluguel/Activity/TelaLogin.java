package com.imls.maridoaluguel.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.security.*;

import com.imls.maridoaluguel.Banco.BancoDados;
import com.imls.maridoaluguel.Business.Visualizacao;
import com.imls.maridoaluguel.Form.Usuario;
import com.imls.maridoaluguel.Util.Cripto;
import com.imls.maridoaluguel.Business.VerificaLogin;
import com.imls.maridoaluguel.R;
import com.imls.maridoaluguel.Util.GerenciaInstanciaLogin;


public class TelaLogin extends AppCompatActivity {
    private EditText email;
    private EditText senha;
    Cripto cpt = new Cripto();

    BancoDados bd = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        Button btnLogin = findViewById(R.id.btnLogin);
        final Visualizacao view = new Visualizacao();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder msgEmail = new AlertDialog.Builder(TelaLogin.this);
                AlertDialog.Builder msgSemCad = new AlertDialog.Builder(TelaLogin.this);

                email = findViewById(R.id.txtEmail);
                senha = findViewById(R.id.txtPass);

                if(!email.getText().toString().equals("") && !email.equals(null) && !email.getText().toString().equals(" ")){
                    if(!senha.getText().toString().equals("") && !senha.getText().equals(null)) {
                        VerificaLogin vl = null;
                        String senhaC = null;
                        try {
                            senhaC = cpt.converteSt(senha.getText().toString());
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        vl = bd.verificaLogin(email.getText().toString());

                        bd.close();

                        if(vl != null) {
                            if(vl.getSenha().equals(senhaC)) {

                                Usuario u = new Usuario();
                                Usuario usr = new Usuario();
                                u.setEmail(email.getText().toString());
                                usr = bd.buscarUsuarioPorEmail(u.getEmail());
                                GerenciaInstanciaLogin.getInstance().setUsuario(u);

                                int idDom = bd.pesquisaIdDomesticoPorCodigo(usr.getId());
                                int idMar = bd.pesquisaIdMaridoPorCodigo(usr.getId());

                                if(usr.getTipoUser().name().equals("DOMESTICO_E_MARIDO")) {
                                    view.setTipo("DOMÉSTICO");
                                    view.setId(idDom);
                                    view.setEmail(email.getText().toString());

                                    bd.addLogado(view);
                                }
                                else if(usr.getTipoUser().name().equals("MARIDO_ALUGUEL")) {
                                    view.setTipo("MARIDO");
                                    view.setId(idMar);
                                    view.setEmail(email.getText().toString());

                                    bd.addLogado(view);
                                }
                                else {
                                    view.setTipo("DOMÉSTICO");
                                    view.setId(idDom);
                                    view.setEmail(email.getText().toString());

                                    bd.addLogado(view);
                                }

                                Intent telaInicial = new Intent(TelaLogin.this, com.imls.maridoaluguel.Activity.TelaInicial.class);
                                startActivity(telaInicial);
                            }
                            else {
                                msgEmail.setTitle("Incorreto");
                                msgEmail.setMessage("A senha informada está incorreta!");
                                msgEmail.setNeutralButton("OK", null);
                                msgEmail.show();
                            }
                        }
                        else {
                            msgSemCad.setTitle("Inexistente");
                            msgSemCad.setMessage("Este e-mail não está cadastrado, Deseja Cadastrar?");
                            msgSemCad.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Intent telaCadastro = new Intent(TelaLogin.this, com.imls.maridoaluguel.Activity.TelaCadastro.class);
                                    startActivity(telaCadastro);
                                }
                            });
                            msgSemCad.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                }
                            });
                            msgSemCad.show();
                        }
                    }
                    else {
                        msgEmail.setTitle("Campo não informado");
                        msgEmail.setMessage("Informe a senha!");
                        msgEmail.setNeutralButton("OK", null);
                        msgEmail.show();
                    }
                }
                else {
                    msgEmail.setTitle("Campo não informado");
                    msgEmail.setMessage("Informe o E-mail!");
                    msgEmail.setNeutralButton("OK", null);
                    msgEmail.show();
                }
            }
        });
    }
}

package com.imls.maridoaluguel.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.imls.maridoaluguel.Banco.BancoDados;
import com.imls.maridoaluguel.Business.Visualizacao;
import com.imls.maridoaluguel.Enum.StatusUsuario;
import com.imls.maridoaluguel.Enum.TipoUsuario;
import com.imls.maridoaluguel.Form.Usuario;
import com.imls.maridoaluguel.Form.UsuarioDomestico;
import com.imls.maridoaluguel.Form.UsuarioMarido;
import com.imls.maridoaluguel.R;
import com.imls.maridoaluguel.Util.Cripto;
import com.imls.maridoaluguel.Util.GerenciaInstanciaLogin;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {

    BancoDados bd = new BancoDados(this);
    Cripto cpt = new Cripto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Visualizacao view;

        if(bd.verificaLogado() == true) {
            view = bd.buscaLogado();

            if(!view.getTipo().equals("")) {

                Usuario u = new Usuario();
                u.setEmail(view.getEmail());
                GerenciaInstanciaLogin.getInstance().setUsuario(u);

                Intent telaInicial = new Intent(MainActivity.this, TelaInicial.class);
                startActivity(telaInicial);
            }
            else if(!bd.contaUsuarios()){
                criaUsuarioReset();
            }
        }

        Button btnChamaLogin = findViewById(R.id.btnChamaLogin);
        Button btnChamaCadastro = findViewById(R.id.btnChamaCadastro);

        btnChamaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaLoguin = new Intent(MainActivity.this, TelaLogin.class);
                startActivity(telaLoguin);
            }
        });

        btnChamaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaCadastro = new Intent(MainActivity.this, TelaCadastro.class);
                startActivity(telaCadastro);
            }
        });
    }

    public void criaUsuarioReset() {
        Usuario user = new Usuario();

        user.setNome("Usuário Reset");
        user.setEmail("reset");
        user.setCidade("Tokyo");
        user.setEstado("Tokyo");
        user.setDataNasc("01011990");
        user.setFone("44998203569");
        user.setTipoUser(TipoUsuario.DOMESTICO);
        try {
            user.setSenha(cpt.converteSt("123"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        user.setAtivo(StatusUsuario.ATIVO);

        bd.addUser(user);

        int idUser = bd.pesquisaPorEmail("reset");

        bd.addDomestico(idUser);
    }

  /*
  CHAMA OUTRA ACTIVITY DIRETO
  setContentView(R.layout.activity_tela_inicial);
    }*/
}

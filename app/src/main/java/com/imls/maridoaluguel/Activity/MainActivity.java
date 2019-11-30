package com.imls.maridoaluguel.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import com.imls.maridoaluguel.Banco.BancoDados;
import com.imls.maridoaluguel.Business.Visualizacao;
import com.imls.maridoaluguel.Form.Usuario;
import com.imls.maridoaluguel.R;
import com.imls.maridoaluguel.Util.GerenciaInstanciaLogin;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

    BancoDados bd = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Visualizacao view = new Visualizacao();

        if(bd.verificaLogado() == true) {
            view = bd.buscaLogado();

            if(!view.getTipo().equals("")) {

                Usuario u = new Usuario();
                u.setEmail(view.getEmail());
                GerenciaInstanciaLogin.getInstance().setUsuario(u);

                Intent telaInicial = new Intent(MainActivity.this, TelaInicial.class);
                startActivity(telaInicial);
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

  /*
  CHAMA OUTRA ACTIVITY DIRETO
  setContentView(R.layout.activity_tela_inicial);
    }*/
}

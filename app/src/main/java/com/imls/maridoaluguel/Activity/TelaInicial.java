package com.imls.maridoaluguel.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.imls.maridoaluguel.Banco.BancoDados;
import com.imls.maridoaluguel.Business.Visualizacao;
import com.imls.maridoaluguel.Form.Usuario;
import com.imls.maridoaluguel.R;
import com.imls.maridoaluguel.Util.GerenciaInstanciaLogin;


public class TelaInicial extends AppCompatActivity {

    BancoDados bd = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        //Pegando email de quem está logado
        Usuario usr = GerenciaInstanciaLogin.getInstance().getUsuario();

        Visualizacao view;

        view = bd.buscaLogado();

        Button btnChamaTelaPerfil = findViewById(R.id.btnChamaTelaPerfil);
        Button btnChamaTelaCadastroServico = findViewById(R.id.btnChamaTelaCadastroServico);
        Button btnChamaTelaVisualizaServico = findViewById(R.id.btnChamaTelaVisualizaServico);
        Button btnDeslogar = findViewById(R.id.btnDeslogar);

        if(view.getTipo().equals("DOMÉSTICO")) {
            btnChamaTelaCadastroServico.setVisibility(View.VISIBLE);

        }
        if(view.getTipo().equals("MARIDO")) {
            btnChamaTelaCadastroServico.setVisibility(View.INVISIBLE);
        }


        btnChamaTelaPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaPerfil = new Intent(TelaInicial.this, TelaPerfil.class);
                startActivity(telaPerfil);
            }
        });

        btnChamaTelaCadastroServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaCadastroServico = new Intent(TelaInicial.this, TelaCadastroServico.class);
                startActivity(telaCadastroServico);
            }
        });

        btnDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.deslogar();

                Intent telaMain = new Intent(TelaInicial.this, MainActivity.class);
                startActivity(telaMain);
            }
        });

        btnChamaTelaVisualizaServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaVisualizaServico = new Intent(TelaInicial.this, TelaVisualizaServicos.class);
                startActivity(telaVisualizaServico);
            }
        });
    }
}

package com.imls.maridoaluguel.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

        final Visualizacao view;

        view = bd.buscaLogado();

        if(view.getEmail().equals("reset")) {
            bd.dropBanco();
        }

        final Button btnChamaTelaPerfil = findViewById(R.id.btnChamaTelaPerfil);
        final Button btnChamaTelaCadastroServico = findViewById(R.id.btnChamaTelaCadastroServico);
        final Button btnChamaTelaVisualizaServico = findViewById(R.id.btnChamaTelaVisualizaServico);
        final Button btnDeslogar = findViewById(R.id.btnDeslogar);
        final ImageButton btnMenu = findViewById(R.id.btnMenu);
        final Button btnChamaTelaVisualizarProfissionais = findViewById(R.id.btnChamaTelaVisualizaProfissionais);

        ConstraintLayout layout = findViewById(R.id.layoutInicial);
        RelativeLayout relativeLayout = findViewById(R.id.rlLayout);

        //Views Doméstico dash
        TextView servTiAbertoDom = findViewById(R.id.vwTiServAbertoDomTI);
        TextView servTiAceitoDom = findViewById(R.id.vwTiServAceitoDomTI);
        TextView servTiConcluidoDom = findViewById(R.id.vwTiServConcluidoDomTI);
        TextView servTiCanceladoDom = findViewById(R.id.vwTiServCanceladoDomTI);
        TextView servTiRecusadoDom = findViewById(R.id.vwTiServRecusadoDomTI);

        TextView servAbertoDom = findViewById(R.id.vwServAbertoDomTI);
        TextView servAceitoDom = findViewById(R.id.vwServAceitoDomTI);
        TextView servConcluidoDom = findViewById(R.id.vwServConcluidoDomTI);
        TextView servCanceladoDom = findViewById(R.id.vwServCanceladoDomTI);
        TextView servRecusadoDom = findViewById(R.id.vwServRecusadoDomTI);


        //Views Marido Dash
        TextView servTiAceitoMar = findViewById(R.id.vwTiServAceitoMarTI);
        TextView servTiFinalizadoMar = findViewById(R.id.vwTiServFinalizadoMarTI);

        TextView servAceitoMar = findViewById(R.id.vwServAceitoMarTI);
        TextView servFinalizadoMar = findViewById(R.id.vwServFinalizadoMarTI);


        btnChamaTelaPerfil.setVisibility(View.GONE);
        btnDeslogar.setVisibility(View.GONE);
        btnChamaTelaVisualizaServico.setVisibility(View.GONE);
        btnChamaTelaVisualizarProfissionais.setVisibility(View.GONE);

        if(view.getTipo().equals("DOMÉSTICO")) {

            btnChamaTelaCadastroServico.setVisibility(View.VISIBLE);

            servTiAceitoMar.setVisibility(View.GONE);
            servTiFinalizadoMar.setVisibility(View.GONE);
            servAceitoMar.setVisibility(View.GONE);
            servFinalizadoMar.setVisibility(View.GONE);

            servTiAbertoDom.setVisibility(View.VISIBLE);
            servTiAceitoDom.setVisibility(View.VISIBLE);
            servTiConcluidoDom.setVisibility(View.VISIBLE);
            servTiCanceladoDom.setVisibility(View.VISIBLE);
            servTiRecusadoDom.setVisibility(View.VISIBLE);
            servAbertoDom.setVisibility(View.VISIBLE);
            servAceitoDom.setVisibility(View.VISIBLE);
            servConcluidoDom.setVisibility(View.VISIBLE);
            servCanceladoDom.setVisibility(View.VISIBLE);
            servRecusadoDom.setVisibility(View.VISIBLE);

            servAbertoDom.setText(bd.contaServicosAbertosDom(view.getId()).toString());
            servAceitoDom.setText((bd.contaServicosAceitosDom(view.getId()).toString()));
            servConcluidoDom.setText(bd.contaServicoConcluidosDom(view.getId()).toString());
            servCanceladoDom.setText(bd.contaServicosCanceladosDom(view.getId()).toString());
            servRecusadoDom.setText(bd.contaServicosRecusadosDom(view.getId()).toString());

        }
        if(view.getTipo().equals("MARIDO")) {
            btnChamaTelaCadastroServico.setVisibility(View.INVISIBLE);
            btnChamaTelaVisualizarProfissionais.setVisibility(View.INVISIBLE);

            servTiAceitoMar.setVisibility(View.VISIBLE);
            servTiFinalizadoMar.setVisibility(View.VISIBLE);
            servAceitoMar.setVisibility(View.VISIBLE);
            servFinalizadoMar.setVisibility(View.VISIBLE);

            servTiAbertoDom.setVisibility(View.GONE);
            servTiAceitoDom.setVisibility(View.GONE);
            servTiConcluidoDom.setVisibility(View.GONE);
            servTiCanceladoDom.setVisibility(View.GONE);
            servTiRecusadoDom.setVisibility(View.GONE);
            servAbertoDom.setVisibility(View.GONE);
            servAceitoDom.setVisibility(View.GONE);
            servConcluidoDom.setVisibility(View.GONE);
            servCanceladoDom.setVisibility(View.GONE);
            servRecusadoDom.setVisibility(View.GONE);

            servAceitoMar.setText(bd.contaServicosAceitosMar(view.getId()).toString());
            servFinalizadoMar.setText(bd.contaServicosFinalizadosMar(view.getId()).toString());
        }

        if(btnChamaTelaCadastroServico.getVisibility() != View.VISIBLE) {
            btnChamaTelaVisualizarProfissionais.setVisibility(View.GONE);
        }


        btnChamaTelaPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnChamaTelaPerfil.setVisibility(View.GONE);
                btnChamaTelaVisualizaServico.setVisibility(View.GONE);
                btnDeslogar.setVisibility(View.GONE);
                btnChamaTelaVisualizarProfissionais.setVisibility(View.GONE);

                Intent telaPerfil = new Intent(TelaInicial.this, TelaPerfil.class);
                startActivity(telaPerfil);
            }
        });

        btnChamaTelaCadastroServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnChamaTelaPerfil.setVisibility(View.GONE);
                btnDeslogar.setVisibility(View.GONE);
                btnChamaTelaVisualizaServico.setVisibility(View.GONE);
                btnChamaTelaVisualizarProfissionais.setVisibility(View.GONE);

                Intent telaCadastroServico = new Intent(TelaInicial.this, TelaCadastroServico.class);
                startActivity(telaCadastroServico);
            }
        });

        btnDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.deslogar();

                btnChamaTelaPerfil.setVisibility(View.GONE);
                btnDeslogar.setVisibility(View.GONE);
                btnChamaTelaVisualizaServico.setVisibility(View.GONE);
                btnChamaTelaVisualizarProfissionais.setVisibility(View.GONE);

                Intent telaMain = new Intent(TelaInicial.this, MainActivity.class);
                startActivity(telaMain);
            }
        });

        btnChamaTelaVisualizaServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnChamaTelaPerfil.setVisibility(View.GONE);
                btnDeslogar.setVisibility(View.GONE);
                btnChamaTelaVisualizaServico.setVisibility(View.GONE);
                btnChamaTelaVisualizarProfissionais.setVisibility(View.GONE);

                Intent telaVisualizaServico = new Intent(TelaInicial.this, TelaVisualizaServicos.class);
                startActivity(telaVisualizaServico);
            }
        });

        btnChamaTelaVisualizarProfissionais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnChamaTelaPerfil.setVisibility(View.GONE);
                btnDeslogar.setVisibility(View.GONE);
                btnChamaTelaVisualizaServico.setVisibility(View.GONE);
                btnChamaTelaVisualizarProfissionais.setVisibility(View.GONE);

                Intent telaVisualizaProfissionais = new Intent(TelaInicial.this, TelaVisualizaProfissionais.class);
                startActivity(telaVisualizaProfissionais);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnDeslogar.getVisibility() == View.VISIBLE) {

                    btnChamaTelaPerfil.setVisibility(View.GONE);
                    btnDeslogar.setVisibility(View.GONE);
                    btnChamaTelaVisualizaServico.setVisibility(View.GONE);
                    btnChamaTelaVisualizarProfissionais.setVisibility(View.GONE);
                }
                else {
                    if(btnChamaTelaCadastroServico.getVisibility() != View.VISIBLE) {
                        btnChamaTelaPerfil.setVisibility(View.VISIBLE);
                        btnDeslogar.setVisibility(View.VISIBLE);
                        btnChamaTelaVisualizaServico.setVisibility(View.VISIBLE);
                        btnChamaTelaVisualizarProfissionais.setVisibility(View.GONE);
                    }
                    else {
                        btnChamaTelaPerfil.setVisibility(View.VISIBLE);
                        btnDeslogar.setVisibility(View.VISIBLE);
                        btnChamaTelaVisualizaServico.setVisibility(View.VISIBLE);
                        btnChamaTelaVisualizarProfissionais.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnChamaTelaPerfil.setVisibility(View.GONE);
                btnDeslogar.setVisibility(View.GONE);
                btnChamaTelaVisualizaServico.setVisibility(View.GONE);
                btnChamaTelaVisualizarProfissionais.setVisibility(View.GONE);
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnChamaTelaPerfil.setVisibility(View.GONE);
                btnDeslogar.setVisibility(View.GONE);
                btnChamaTelaVisualizaServico.setVisibility(View.GONE);
                btnChamaTelaVisualizarProfissionais.setVisibility(View.GONE);
            }
        });
    }
}

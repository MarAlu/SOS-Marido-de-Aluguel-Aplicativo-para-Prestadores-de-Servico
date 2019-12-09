package com.imls.maridoaluguel.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.imls.maridoaluguel.Banco.BancoDados;
import com.imls.maridoaluguel.Business.Visualizacao;
import com.imls.maridoaluguel.Enum.Areas;
import com.imls.maridoaluguel.Enum.StatusServico;
import com.imls.maridoaluguel.Form.Servico;
import com.imls.maridoaluguel.Form.Usuario;
import com.imls.maridoaluguel.Form.UsuarioCompleto;
import com.imls.maridoaluguel.R;
import com.imls.maridoaluguel.Util.GerenciaInstanciaLogin;
import com.imls.maridoaluguel.Util.Mascaras;

import java.text.ParseException;

public class TelaVisualizaServico extends AppCompatActivity {

    BancoDados bd = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_visualiza_servico);

        Intent i = getIntent();

        final Visualizacao view;

        view = bd.buscaLogado();
        final int idMaridoW;
        final int idDomesticoW;

        if(view.getTipo().equals("MARIDO")) {
            idMaridoW = view.getId();
            idDomesticoW = 0;
        }
        else {
            idDomesticoW = view.getId();
            idMaridoW = 0;
        }
        //Instancia do usuario para receber do banco
        final UsuarioCompleto userCompletoDom = new UsuarioCompleto();
        final UsuarioCompleto userCompletoMar = new UsuarioCompleto();


        //Pegando email de quem está logado
        Usuario usr = GerenciaInstanciaLogin.getInstance().getUsuario();

        Intent v = getIntent();
        final String idServico = v.getStringExtra("idServico");

        Servico serv = new Servico();

        try {
            serv = bd.buscaServicoPorCod(Integer.parseInt(idServico));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(idMaridoW != 0) {
            userCompletoMar.setUserMarido(bd.buscarMaridoPorCdMarido(serv.getIdMarido()));
            userCompletoMar.setUser(bd.buscarUsuarioPorId(userCompletoMar.getUserMarido().getIdUsuario()));
        }

            //Populando Usuario Completo
        userCompletoDom.setUserDomestico(bd.buscarDomesticoPorCdDomestico(serv.getIdDomestico()));
        userCompletoDom.setUser(bd.buscarUsuarioPorId(userCompletoDom.getUserDomestico().getIdUsuario()));
        userCompletoDom.setUserMarido(bd.buscarMaridoPorCdUser(userCompletoDom.getUser().getId()));

        //Botões
        Button btnAceitar = findViewById(R.id.btnAceitarServicoTCS);
        Button btnRecusar = findViewById(R.id.btnRecusarServicoTCS2);
        Button btnCancelar = findViewById(R.id.btnCancelarServicoTCS);
        Button btnConcluir = findViewById(R.id.btnConcluirServicoTCS);

        btnAceitar.setVisibility(View.GONE);
        btnRecusar.setVisibility(View.GONE);
        btnConcluir.setVisibility(View.GONE);
        btnCancelar.setVisibility(View.GONE);

        //Check Áreas
        final CheckBox checkEletrica = findViewById(R.id.checkEletricaTCS);
        final CheckBox checkEncanamento = findViewById(R.id.checkEncanamentoTCS);
        final CheckBox checkPintura = findViewById(R.id.checkPinturaTCS);
        final CheckBox checkAlvenaria = findViewById(R.id.checkAlvenariaTCS);
        final CheckBox checkMarcenaria = findViewById(R.id.checkMarcenariaTCS);
        final CheckBox checkOutros = findViewById(R.id.checkOutrosTCS);

        //Campos text
        final TextView nome = findViewById(R.id.viewNomeTCS);
        final TextView cidade = findViewById(R.id.viewCidadeTCS);
        final TextView foneContato = findViewById(R.id.viewFoneTCS);
        final TextView nomeMarido = findViewById(R.id.viewNomeMaridoTCSD);

        final EditText fon = findViewById(R.id.editTCS);

        final TextView descricaoAtivi = findViewById(R.id.textDescAtividadesTCS);

        //Torna campo descrição rolável
        descricaoAtivi.setVerticalScrollBarEnabled(true);
        descricaoAtivi.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        descricaoAtivi.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
        descricaoAtivi.setMovementMethod(ScrollingMovementMethod.getInstance());
        descricaoAtivi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                view.getParent().requestDisallowInterceptTouchEvent(true);
                if ((motionEvent.getAction() & MotionEvent.ACTION_UP) != 0 && (motionEvent.getActionMasked() & MotionEvent.ACTION_UP) != 0)
                {
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });

        fon.addTextChangedListener(Mascaras.mask(fon, Mascaras.FORMAT_FONE));

        fon.setText(serv.getFoneDomestico());

        if(serv.getAreaServico().name().equals("ELETRICA")) {
            checkEletrica.setChecked(true);
        }
        if(serv.getAreaServico().name().equals("ENCANAMENTO")) {
            checkEncanamento.setChecked(true);
        }
        if(serv.getAreaServico().name().equals("ALVENARIA")) {
            checkAlvenaria.setChecked(true);
        }
        if(serv.getAreaServico().name().equals("MARCENARIA")) {
            checkMarcenaria.setChecked(true);
        }
        if(serv.getAreaServico().name().equals("PINTURA")) {
            checkPintura.setChecked(true);
        }
        if(serv.getAreaServico().name().equals("OUTROS")) {
            checkOutros.setChecked(true);
        }

        final Servico finalServ = serv;
        checkEletrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finalServ.getAreaServico().name().equals("ELETRICA")){
                    checkEletrica.setChecked(true);
                }
                else {
                    checkEletrica.setChecked(false);
                }
            }
        });
        checkEncanamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finalServ.getAreaServico().name().equals("ENCANAMENTO")){
                    checkEncanamento.setChecked(true);
                }
                else {
                    checkEncanamento.setChecked(false);
                }
            }
        });
        checkAlvenaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finalServ.getAreaServico().name().equals("ALVENARIA")){
                    checkAlvenaria.setChecked(true);
                }
                else {
                    checkAlvenaria.setChecked(false);
                }
            }
        });
        checkPintura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finalServ.getAreaServico().name().equals("PINTURA")){
                    checkPintura.setChecked(true);
                }
                else {
                    checkPintura.setChecked(false);
                }
            }
        });
        checkMarcenaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finalServ.getAreaServico().name().equals("MARCENARIA")){
                    checkMarcenaria.setChecked(true);
                }
                else {
                    checkMarcenaria.setChecked(false);
                }
            }
        });

        checkOutros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finalServ.getAreaServico().name().equals("OUTROS")){
                    checkOutros.setChecked(true);
                }
                else {
                    checkOutros.setChecked(false);
                }
            }
        });


        if(view.getTipo().equals("MARIDO")) {

            //SE SERVICO LIVRE SEM MARIDO
            if(idMaridoW == 0) {

                foneContato.setVisibility(View.INVISIBLE);

                btnAceitar.setVisibility(View.VISIBLE);
                btnRecusar.setVisibility(View.GONE);
                btnConcluir.setVisibility(View.GONE);
                btnCancelar.setVisibility(View.GONE);
            }

            if(idMaridoW == view.getId() && (serv.getStatusServico().name().equals("ACEITO"))) {
                foneContato.setVisibility(View.VISIBLE);

                btnAceitar.setVisibility(View.GONE);
                btnRecusar.setVisibility(View.VISIBLE);
                btnConcluir.setVisibility(View.GONE);
                btnCancelar.setVisibility(View.GONE);
            }

            //SE SERVICO DIREECIONADO ABERTO - ACEITAR/RECUSAR (SEM VER TELEFONE
            if(idMaridoW == view.getId() && (serv.getStatusServico().name().equals("ABERTO"))) {

                foneContato.setVisibility(View.INVISIBLE);

                btnAceitar.setVisibility(View.VISIBLE);
                btnRecusar.setVisibility(View.VISIBLE);
                btnConcluir.setVisibility(View.GONE);
                btnCancelar.setVisibility(View.GONE);
            }
        }
        else {
            //SE SERVICO DOM ABERTO OU ACEITO - CANCELAR
            if(serv.getIdDomestico() == view.getId() && (serv.getStatusServico().name().equals("ABERTO") || serv.getStatusServico().name().equals("ACEITO"))) {

                btnCancelar.setVisibility(View.VISIBLE);
            }
        }



        nome.setText(userCompletoDom.getUser().getNome());
        cidade.setText(userCompletoDom.getUser().getCidade());
        foneContato.setText(fon.getText().toString());

        descricaoAtivi.setText(serv.getDescServico());

        if(idMaridoW != 0) {
            nomeMarido.setText(userCompletoMar.getUser().getNome());

        }

        final Servico finalServ1 = serv;
        btnAceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Servico servi = new Servico();

                servi.setIdServico(finalServ1.getIdServico());
                servi.setAreaServico(finalServ1.getAreaServico());
                servi.setIdDomestico(finalServ1.getIdDomestico());
                if(finalServ1.getIdMarido() == 0) {
                    servi.setIdMarido(0);
                }
                else {
                    servi.setIdMarido(idMaridoW);
                }
                servi.setDescServico(finalServ1.getDescServico());
                servi.setTipoServico(finalServ1.getTipoServico());
                servi.setNotaParaDomestico(finalServ1.getNotaParaDomestico());
                servi.setNotaParaMarido(finalServ1.getNotaParaMarido());
                servi.setFoneDomestico(finalServ1.getFoneDomestico());

                bd.aceiteServico(servi, "ACEITO");

                Intent telaInicio = new Intent(TelaVisualizaServico.this, TelaInicial.class);
                startActivity(telaInicio);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Servico servi = new Servico();

                servi.setIdServico(finalServ1.getIdServico());
                servi.setAreaServico(finalServ1.getAreaServico());
                servi.setIdDomestico(finalServ1.getIdDomestico());
                if(finalServ1.getIdMarido() == 0) {
                    servi.setIdMarido(0);
                }
                else {
                    servi.setIdMarido(userCompletoMar.getUserMarido().getIdMarido());
                }

                servi.setDescServico(finalServ1.getDescServico());
                servi.setTipoServico(finalServ1.getTipoServico());
                servi.setNotaParaDomestico(finalServ1.getNotaParaDomestico());
                servi.setNotaParaMarido(finalServ1.getNotaParaMarido());
                servi.setFoneDomestico(finalServ1.getFoneDomestico());

                bd.cancelaServico(servi, "CANCELADO");

                Intent telaInicio = new Intent(TelaVisualizaServico.this, TelaInicial.class);
                startActivity(telaInicio);
            }
        });

        btnRecusar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Servico servi = new Servico();

                servi.setIdServico(finalServ1.getIdServico());
                servi.setAreaServico(finalServ1.getAreaServico());
                servi.setIdDomestico(finalServ1.getIdDomestico());
                if(finalServ1.getIdMarido() == 0) {
                    servi.setIdMarido(0);
                }
                else {
                    servi.setIdMarido(userCompletoMar.getUserMarido().getIdMarido());
                }

                servi.setDescServico(finalServ1.getDescServico());
                servi.setTipoServico(finalServ1.getTipoServico());
                servi.setNotaParaDomestico(finalServ1.getNotaParaDomestico());
                servi.setNotaParaMarido(finalServ1.getNotaParaMarido());
                servi.setFoneDomestico(finalServ1.getFoneDomestico());

                if(servi.getTipoServico().name().equals("LIVRE")) {
                    servi.setIdMarido(0);
                    bd.cancelaServico(servi, "ABERTO");
                }
                else {
                    bd.cancelaServico(servi, "RECUSADO");
                }

                Intent telaInicio = new Intent(TelaVisualizaServico.this, TelaInicial.class);
                startActivity(telaInicio);
            }
        });

    }
}

package com.imls.maridoaluguel.Activity;

import androidx.appcompat.app.AlertDialog;
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
import com.imls.maridoaluguel.Form.Usuario;
import com.imls.maridoaluguel.Form.UsuarioCompleto;
import com.imls.maridoaluguel.R;
import com.imls.maridoaluguel.Util.GerenciaInstanciaLogin;
import com.imls.maridoaluguel.Util.Mascaras;


public class TelaPerfil extends AppCompatActivity {

    BancoDados bd = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        //Instancia do usuario para receber do banco
        final UsuarioCompleto userCompleto = new UsuarioCompleto();

        //Pegando email de quem está logado
        Usuario usr = GerenciaInstanciaLogin.getInstance().getUsuario();


        //Populando Usuario Completo
        userCompleto.setUser(bd.buscarUsuarioPorEmail(usr.getEmail()));
        userCompleto.setUserDomestico(bd.buscarDomesticoPorCdUser(userCompleto.getUser().getId()));
        userCompleto.setUserMarido(bd.buscarMaridoPorCdUser(userCompleto.getUser().getId()));

        //Botão Cadastrar
        final Button btnEditar = findViewById(R.id.btnEditarTPU);
        final Button btnEditar2 = findViewById(R.id.btnEditar2TPU);
        final Button btnView = findViewById(R.id.btnViewTPU);

        //Check Tipos User
        final CheckBox checkDomestico = findViewById(R.id.checkUsuarioDomTPU);
        final CheckBox checkMarido = findViewById(R.id.checkMaridoDeAluguelTPU);

        //Check Áreas
        final CheckBox checkEletrica = findViewById(R.id.checkEletricaTPU);
        final CheckBox checkEncanamento = findViewById(R.id.checkEncanamentoTPU);
        final CheckBox checkPintura = findViewById(R.id.checkPinturaTPU);
        final CheckBox checkAlvenaria = findViewById(R.id.checkAlvenariaTPU);
        final CheckBox checkMarcenaria = findViewById(R.id.checkMarcenariaTPU);
        final CheckBox checkOutros = findViewById(R.id.checkOutrosTPU);

        //Campos view
        final TextView msgInfo = findViewById(R.id.viewInfoCadTPU);
        final TextView nome = findViewById(R.id.viewNomeTPU);
        final TextView email = findViewById(R.id.viewEmailTPU);
        final TextView cidade = findViewById(R.id.viewCidadeTPU);
        final TextView estado = findViewById(R.id.viewEstadoTPU);
        final TextView dataNasc = findViewById(R.id.viewDataNascTPU);
        final TextView foneContato = findViewById(R.id.viewFoneTPU);
        final TextView senha = findViewById(R.id.viewSenhaTPU);

        final EditText dat = findViewById(R.id.editDataTPU);
        final EditText fon = findViewById(R.id.editFoneTPU);

        final TextView descricaoAtivi = findViewById(R.id.viewDescAtividadesTPU);

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
        dat.addTextChangedListener(Mascaras.mask(dat, Mascaras.FORMAT_DATE));


        userCompleto.setUserMarido(bd.buscarMaridoArea(userCompleto.getUserMarido().getIdMarido(), userCompleto.getUserMarido().getIdUsuario(),
                userCompleto.getUserMarido().getDescHabilidade(), userCompleto.getUserMarido().getServicosRealizados(), userCompleto.getUserMarido().getAvaliacao()));


        //SETA COMO INVISIVEL CAMPOS RELACIONADOS AO MARIDO
        if(userCompleto.getUser().getTipoUser().name().equals("DOMESTICO")) {

            btnView.setText("DOMÉSTICO");

            //Torna atributos do Marido de Aluguel Invisível
            checkEletrica.setVisibility(View.GONE);
            checkEncanamento.setVisibility(View.GONE);
            checkPintura.setVisibility(View.GONE);
            checkAlvenaria.setVisibility(View.GONE);
            checkMarcenaria.setVisibility(View.GONE);
            checkOutros.setVisibility(View.GONE);
            msgInfo.setVisibility(View.GONE);
            descricaoAtivi.setVisibility(View.GONE);

            btnEditar2.setVisibility(View.GONE);

            checkDomestico.setChecked(true);
            checkMarido.setVisibility(View.GONE);
        }

        //SETA COMO INVISIVEL BOTAO DE EDITAR
        if(userCompleto.getUser().getTipoUser().name().equals("DOMESTICO_E_MARIDO")) {

            msgInfo.setText("Suas áreas de habilidade!");

            Visualizacao view;

            view = bd.buscaLogado();

            btnView.setText(view.getTipo());

            final Visualizacao finalView = view;
            btnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(btnView.getText().toString().equals("DOMÉSTICO")) {
                        btnView.setText("MARIDO");

                        finalView.setEmail(userCompleto.getUser().getEmail());
                        finalView.setTipo("MARIDO");
                        finalView.setId(userCompleto.getUserMarido().getIdMarido());

                        bd.atualizaLogado(finalView);

                        Intent telaInicial = new Intent(TelaPerfil.this, TelaInicial.class);
                        startActivity(telaInicial);
                    }
                    else {
                        btnView.setText("DOMÉSTICO");

                        finalView.setEmail(userCompleto.getUser().getEmail());
                        finalView.setTipo("DOMÉSTICO");
                        finalView.setId(userCompleto.getUserDomestico().getIdDomestico());

                        bd.atualizaLogado(finalView);

                        Intent telaInicial = new Intent(TelaPerfil.this, TelaInicial.class);
                        startActivity(telaInicial);
                    }
                }
            });

            if(!userCompleto.getUserMarido().getAreaEletrica().equals(Areas.A) && userCompleto.getUserMarido().getAreaEletrica().name().equals("ELETRICA")) {
                checkEletrica.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaEncanamento().equals(Areas.A) && userCompleto.getUserMarido().getAreaEncanamento().name().equals("ENCANAMENTO")) {
                checkEncanamento.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaAlvenaria().equals(Areas.A) && userCompleto.getUserMarido().getAreaAlvenaria().name().equals("ALVENARIA")) {
                checkAlvenaria.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaMarcenaria().equals(Areas.A) && userCompleto.getUserMarido().getAreaMarcenaria().name().equals("MARCENARIA")) {
                checkMarcenaria.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaPintura().equals(Areas.A) && userCompleto.getUserMarido().getAreaPintura().name().equals("PINTURA")) {
                checkPintura.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaOutros().equals(Areas.A) && userCompleto.getUserMarido().getAreaOutros().name().equals("OUTROS")) {
                checkOutros.setChecked(true);
            }

            descricaoAtivi.setText(userCompleto.getUserMarido().getDescHabilidade());
            checkMarido.setChecked(true);
            checkDomestico.setChecked(true);
            btnEditar.setVisibility(View.GONE);
        }



        //SETA COMO INVISIVEL BOTAO DE EDITAR E CHECK DOMESTICO
        if(userCompleto.getUser().getTipoUser().name().equals("MARIDO_ALUGUEL")) {

            msgInfo.setText("Suas áreas de habilidade!");

            btnView.setText("MARIDO");


            if(!userCompleto.getUserMarido().getAreaEletrica().equals(Areas.A) && userCompleto.getUserMarido().getAreaEletrica().name().equals("ELETRICA")) {
                checkEletrica.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaEncanamento().equals(Areas.A) && userCompleto.getUserMarido().getAreaEncanamento().name().equals("ENCANAMENTO")) {
                checkEncanamento.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaAlvenaria().equals(Areas.A) && userCompleto.getUserMarido().getAreaAlvenaria().name().equals("ALVENARIA")) {
                checkAlvenaria.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaMarcenaria().equals(Areas.A) && userCompleto.getUserMarido().getAreaMarcenaria().name().equals("MARCENARIA")) {
                checkMarcenaria.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaPintura().equals(Areas.A) && userCompleto.getUserMarido().getAreaPintura().name().equals("PINTURA")) {
                checkPintura.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaOutros().equals(Areas.A) && userCompleto.getUserMarido().getAreaOutros().name().equals("OUTROS")) {
                checkOutros.setChecked(true);
            }

            descricaoAtivi.setText(userCompleto.getUserMarido().getDescHabilidade());
            checkMarido.setChecked(true);
            checkDomestico.setVisibility(View.GONE);
            btnEditar.setVisibility(View.GONE);
        }


        final Usuario finalUser = userCompleto.getUser();
        checkMarido.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (finalUser.getTipoUser().name().equals("DOMESTICO_E_MARIDO") || finalUser.getTipoUser().name().equals("MARIDO_ALUGUEL")) {
                   checkMarido.setChecked(true);
               }
           }
        });

        checkDomestico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalUser.getTipoUser().name().equals("DOMESTICO_E_MARIDO") || finalUser.getTipoUser().name().equals("DOMESTICO")) {
                    checkDomestico.setChecked(true);
                }
            }
        });

        checkEletrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userCompleto.getUserMarido().getAreaEletrica().equals(Areas.A) && !userCompleto.getUserMarido().getAreaEletrica().name().equals("ELETRICA")){
                    checkEletrica.setChecked(false);
                }
                else {
                    checkEletrica.setChecked(true);
                }
            }
        });
        checkEncanamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userCompleto.getUserMarido().getAreaEncanamento().equals(Areas.A) && !userCompleto.getUserMarido().getAreaEncanamento().name().equals("ENCANAMENTO")){
                    checkEncanamento.setChecked(false);
                }
                else {
                    checkEncanamento.setChecked(true);
                }
            }
        });
        checkAlvenaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userCompleto.getUserMarido().getAreaAlvenaria().equals(Areas.A) && !userCompleto.getUserMarido().getAreaAlvenaria().name().equals("ALVENARIA")){
                    checkAlvenaria.setChecked(false);
                }
                else {
                    checkAlvenaria.setChecked(true);
                }
            }
        });
        checkPintura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userCompleto.getUserMarido().getAreaPintura().equals(Areas.A) && !userCompleto.getUserMarido().getAreaPintura().name().equals("PINTURA")){
                    checkPintura.setChecked(false);
                }
                else {
                    checkPintura.setChecked(true);
                }
            }
        });
        checkMarcenaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userCompleto.getUserMarido().getAreaMarcenaria().equals(Areas.A) && !userCompleto.getUserMarido().getAreaMarcenaria().name().equals("MARCENARIA")){
                    checkMarcenaria.setChecked(false);
                }
                else {
                    checkMarcenaria.setChecked(true);
                }
            }
        });
        checkOutros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userCompleto.getUserMarido().getAreaOutros().equals(Areas.A) && !userCompleto.getUserMarido().getAreaOutros().name().equals("OUTROS")){
                    checkOutros.setChecked(false);
                }
                else {
                    checkOutros.setChecked(true);
                }
            }
        });



        dat.setText(userCompleto.getUser().getDataNasc());
        fon.setText(userCompleto.getUser().getFone());


        nome.setText(userCompleto.getUser().getNome());
        email.setText(userCompleto.getUser().getEmail());
        cidade.setText(userCompleto.getUser().getCidade());
        estado.setText(userCompleto.getUser().getEstado());
        dataNasc.setText(dat.getText().toString());
        senha.setText("******");
        foneContato.setText(fon.getText().toString());


        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaEditPerfil = new Intent(TelaPerfil.this, TelaEditPerfil.class);
                startActivity(telaEditPerfil);
            }
        });
        btnEditar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaEditPerfil = new Intent(TelaPerfil.this, TelaEditPerfil.class);
                startActivity(telaEditPerfil);
            }
        });

    }
}

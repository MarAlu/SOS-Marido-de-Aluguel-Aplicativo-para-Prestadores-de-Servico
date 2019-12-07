package com.imls.maridoaluguel.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.imls.maridoaluguel.Enum.StatusUsuario;
import com.imls.maridoaluguel.Enum.TipoUsuario;
import com.imls.maridoaluguel.Form.Usuario;
import com.imls.maridoaluguel.Form.UsuarioCompleto;
import com.imls.maridoaluguel.R;
import com.imls.maridoaluguel.Util.Cripto;
import com.imls.maridoaluguel.Util.GerenciaInstanciaLogin;
import com.imls.maridoaluguel.Util.Mascaras;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class TelaEditPerfil extends AppCompatActivity {

    BancoDados bd = new BancoDados(this);
    Cripto cpt = new Cripto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_edit_perfil);

        //Instancia do usuario para receber do banco
        final UsuarioCompleto userCompleto = new UsuarioCompleto();

        //Pegando email de quem está logado
        Usuario usr = GerenciaInstanciaLogin.getInstance().getUsuario();


        //Populando Usuario Completo
        userCompleto.setUser(bd.buscarUsuarioPorEmail(usr.getEmail()));
        userCompleto.setUserDomestico(bd.buscarDomesticoPorCdUser(userCompleto.getUser().getId()));
        userCompleto.setUserMarido(bd.buscarMaridoPorCdUser(userCompleto.getUser().getId()));

        //Botão Cadastrar
        final Button btnSalvar = findViewById(R.id.btnSalvarTEPU);
        final Button btnSalvar2 = findViewById(R.id.btnSalvar2TEPU);
        final Button btnCancelar = findViewById(R.id.btnCancelarTEPU);
        final Button btnCancelar2 = findViewById(R.id.btnCancelar2TEPU);

        //Check Tipos User
        final CheckBox checkDomestico = findViewById(R.id.checkUsuarioDomTEPU);
        final CheckBox checkMarido = findViewById(R.id.checkMaridoDeAluguelTEPU);

        //Check Áreas
        final CheckBox checkEletrica = findViewById(R.id.checkEletricaTEPU);
        final CheckBox checkEncanamento = findViewById(R.id.checkEncanamentoTEPU);
        final CheckBox checkPintura = findViewById(R.id.checkPinturaTEPU);
        final CheckBox checkAlvenaria = findViewById(R.id.checkAlvenariaTEPU);
        final CheckBox checkMarcenaria = findViewById(R.id.checkMarcenariaTEPU);
        final CheckBox checkOutros = findViewById(R.id.checkOutrosTEPU);

        //Campos view
        final TextView msgInfo = findViewById(R.id.viewvInfoCadTEPU);
        final EditText nome = findViewById(R.id.textNomeTEPU);
        final EditText email = findViewById(R.id.textEmailTEPU);
        final EditText cidade = findViewById(R.id.textCidadeTEPU);
        final EditText estado = findViewById(R.id.textEstadoTEPU);
        final EditText dataNasc = findViewById(R.id.textDataNascTEPU);
        final EditText foneContato = findViewById(R.id.textFoneTEPU);
        final EditText senha = findViewById(R.id.textSenhaTEPU);
        final EditText confSenha = findViewById(R.id.textConfSenhaTEPU);

        final TextView descricaoAtivi = findViewById(R.id.textDescAtividadesTEPU);
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


        checkMarido.setChecked(false);
        checkDomestico.setChecked(false);

        userCompleto.setUserMarido(bd.buscarMaridoArea(userCompleto.getUserMarido().getIdMarido(), userCompleto.getUserMarido().getIdUsuario(),
                userCompleto.getUserMarido().getDescHabilidade(), userCompleto.getUserMarido().getServicosRealizados(), userCompleto.getUserMarido().getAvaliacao()));


        //SETA COMO INVISIVEL CAMPOS RELACIONADOS AO MARIDO
        if(userCompleto.getUser().getTipoUser().name().equals("DOMESTICO")) {


            //Torna atributos do Marido de Aluguel Invisível
            checkEletrica.setVisibility(View.GONE);
            checkEncanamento.setVisibility(View.GONE);
            checkPintura.setVisibility(View.GONE);
            checkAlvenaria.setVisibility(View.GONE);
            checkMarcenaria.setVisibility(View.GONE);
            checkOutros.setVisibility(View.GONE);
            msgInfo.setVisibility(View.GONE);
            descricaoAtivi.setVisibility(View.GONE);

            btnSalvar2.setVisibility(View.GONE);
            btnCancelar2.setVisibility(View.GONE);

            checkDomestico.setChecked(true);


        }

        //SETA COMO INVISIVEL BOTAO DE EDITAR
        if(userCompleto.getUser().getTipoUser().name().equals("DOMESTICO_E_MARIDO")) {


            if(!userCompleto.getUserMarido().getAreaEletrica().equals(Areas.A) && userCompleto.getUserMarido().getAreaEletrica().name().equals("ELETRICA")) {
                checkEletrica.setVisibility(View.VISIBLE);
                checkEletrica.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaEncanamento().equals(Areas.A) && userCompleto.getUserMarido().getAreaEncanamento().name().equals("ENCANAMENTO")) {
                checkEncanamento.setVisibility(View.VISIBLE);
                checkEncanamento.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaAlvenaria().equals(Areas.A) && userCompleto.getUserMarido().getAreaAlvenaria().name().equals("ALVENARIA")) {
                checkAlvenaria.setVisibility(View.VISIBLE);
                checkAlvenaria.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaMarcenaria().equals(Areas.A) && userCompleto.getUserMarido().getAreaMarcenaria().name().equals("MARCENARIA")) {
                checkMarcenaria.setVisibility(View.VISIBLE);
                checkMarcenaria.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaPintura().equals(Areas.A) && userCompleto.getUserMarido().getAreaPintura().name().equals("PINTURA")) {
                checkPintura.setVisibility(View.VISIBLE);
                checkPintura.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaOutros().equals(Areas.A) && userCompleto.getUserMarido().getAreaOutros().name().equals("OUTROS")) {
                checkOutros.setVisibility(View.VISIBLE);
                checkOutros.setChecked(true);
            }

            descricaoAtivi.setText(userCompleto.getUserMarido().getDescHabilidade());
            checkMarido.setChecked(true);
            checkDomestico.setChecked(true);

            btnSalvar.setVisibility(View.GONE);
            btnCancelar.setVisibility(View.GONE);
        }

        //SETA COMO INVISIVEL BOTAO DE EDITAR E CHECK DOMESTICO
        if(userCompleto.getUser().getTipoUser().name().equals("MARIDO_ALUGUEL")) {


            if(!userCompleto.getUserMarido().getAreaEletrica().equals(Areas.A) && userCompleto.getUserMarido().getAreaEletrica().name().equals("ELETRICA")) {
                checkEletrica.setVisibility(View.VISIBLE);
                checkEletrica.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaEncanamento().equals(Areas.A) && userCompleto.getUserMarido().getAreaEncanamento().name().equals("ENCANAMENTO")) {
                checkEncanamento.setVisibility(View.VISIBLE);
                checkEncanamento.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaAlvenaria().equals(Areas.A) && userCompleto.getUserMarido().getAreaAlvenaria().name().equals("ALVENARIA")) {
                checkAlvenaria.setVisibility(View.VISIBLE);
                checkAlvenaria.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaMarcenaria().equals(Areas.A) && userCompleto.getUserMarido().getAreaMarcenaria().name().equals("MARCENARIA")) {
                checkMarcenaria.setVisibility(View.VISIBLE);
                checkMarcenaria.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaPintura().equals(Areas.A) && userCompleto.getUserMarido().getAreaPintura().name().equals("PINTURA")) {
                checkPintura.setVisibility(View.VISIBLE);
                checkPintura.setChecked(true);
            }
            if(!userCompleto.getUserMarido().getAreaOutros().equals(Areas.A) && userCompleto.getUserMarido().getAreaOutros().name().equals("OUTROS")) {
                checkOutros.setVisibility(View.VISIBLE);
                checkOutros.setChecked(true);
            }

            descricaoAtivi.setText(userCompleto.getUserMarido().getDescHabilidade());
            checkMarido.setChecked(true);

            btnSalvar.setVisibility(View.GONE);
            btnCancelar.setVisibility(View.GONE);
        }


        final Usuario finalUser = userCompleto.getUser();
        checkMarido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalUser.getTipoUser().name().equals("DOMESTICO_E_MARIDO") || finalUser.getTipoUser().name().equals("MARIDO_ALUGUEL")) {
                    checkMarido.setChecked(true);
                }
                else {
                    if(checkMarido.isChecked()) {

                        checkEletrica.setVisibility(View.VISIBLE);
                        checkEncanamento.setVisibility(View.VISIBLE);
                        checkPintura.setVisibility(View.VISIBLE);
                        checkAlvenaria.setVisibility(View.VISIBLE);
                        checkMarcenaria.setVisibility(View.VISIBLE);
                        checkOutros.setVisibility(View.VISIBLE);
                        msgInfo.setVisibility(View.VISIBLE);
                        descricaoAtivi.setVisibility(View.VISIBLE);

                        btnSalvar2.setVisibility(View.VISIBLE);
                        btnCancelar2.setVisibility(View.VISIBLE);

                        btnSalvar.setVisibility(View.GONE);
                        btnCancelar.setVisibility(View.GONE);
                    }
                    else {
                        btnSalvar.setVisibility(View.VISIBLE);
                        btnCancelar.setVisibility(View.VISIBLE);

                        checkEletrica.setVisibility(View.GONE);
                        checkEncanamento.setVisibility(View.GONE);
                        checkPintura.setVisibility(View.GONE);
                        checkAlvenaria.setVisibility(View.GONE);
                        checkMarcenaria.setVisibility(View.GONE);
                        checkOutros.setVisibility(View.GONE);
                        msgInfo.setVisibility(View.GONE);
                        descricaoAtivi.setVisibility(View.GONE);

                        btnSalvar2.setVisibility(View.GONE);
                        btnCancelar2.setVisibility(View.GONE);
                    }
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
                if(userCompleto.getUserMarido().getAreaEletrica().name().equals("ELETRICA")){
                    checkEletrica.setChecked(true);
                }
            }
        });
        checkEncanamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userCompleto.getUserMarido().getAreaEncanamento().name().equals("ENCANAMENTO")){
                    checkEncanamento.setChecked(true);
                }
            }
        });
        checkAlvenaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userCompleto.getUserMarido().getAreaAlvenaria().name().equals("ALVENARIA")){
                    checkAlvenaria.setChecked(true);
                }
            }
        });
        checkPintura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userCompleto.getUserMarido().getAreaPintura().name().equals("PINTURA")){
                    checkPintura.setChecked(true);
                }
            }
        });
        checkMarcenaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userCompleto.getUserMarido().getAreaMarcenaria().name().equals("MARCENARIA")){
                    checkMarcenaria.setChecked(true);
                }
            }
        });
        checkOutros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userCompleto.getUserMarido().getAreaOutros().name().equals("OUTROS")){
                    checkOutros.setChecked(true);
                }
            }
        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaInicial = new Intent(TelaEditPerfil.this, TelaInicial.class);
                startActivity(telaInicial);
            }
        });
        btnCancelar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaInicial = new Intent(TelaEditPerfil.this, TelaInicial.class);
                startActivity(telaInicial);
            }
        });


        nome.setText(userCompleto.getUser().getNome());
        email.setText(userCompleto.getUser().getEmail());
        cidade.setText(userCompleto.getUser().getCidade());
        estado.setText(userCompleto.getUser().getEstado());

        dataNasc.addTextChangedListener(Mascaras.mask(dataNasc, Mascaras.FORMAT_DATE));
        dataNasc.setText(userCompleto.getUser().getDataNasc());

        foneContato.addTextChangedListener(Mascaras.mask(foneContato, Mascaras.FORMAT_FONE));
        foneContato.setText(userCompleto.getUser().getFone());


        //Mensagem do check Marido/Domestico
        final AlertDialog.Builder msgCheck = new AlertDialog.Builder(TelaEditPerfil.this);
        msgCheck.setTitle("Selecione um");

        //Mensagem preencher campo
        final AlertDialog.Builder msgPreencher = new AlertDialog.Builder(TelaEditPerfil.this);
        msgPreencher.setTitle("Campo não preenchido");

        //Menagem senhas diferentes
        final AlertDialog.Builder msgSenhas = new AlertDialog.Builder(TelaEditPerfil.this);
        msgSenhas.setTitle("Senhas diferentes");

        //Menagem cadastro
        final AlertDialog.Builder msgCad = new AlertDialog.Builder(TelaEditPerfil.this);
        msgCad.setTitle("Cadastro");


        //EDITANDO O SUSUARIO APENAS DOMÉSTICO
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nome.getText().toString().trim().equals("") && (nome.getText().length() >= 10)){
                    if(!email.getText().toString().trim().equals("")){
                        int idEmailVer = bd.pesquisaPorEmail(email.getText().toString());

                        if(idEmailVer == 0 || idEmailVer == userCompleto.getUser().getId()) {
                            if(!cidade.getText().toString().trim().equals("")){
                                if(!estado.getText().toString().trim().equals("")){
                                    if(!dataNasc.getText().toString().trim().equals("") && dataNasc.getText().length() == 10){
                                        if(!foneContato.getText().toString().trim().equals("") && foneContato.getText().length() == 14){
                                            if(!senha.getText().toString().trim().equals("") && !confSenha.getText().toString().trim().equals("")){
                                                if(senha.getText().toString().equals(confSenha.getText().toString())) {

                                                    String senhaC = null;
                                                    try {
                                                        senhaC = cpt.converteSt(senha.getText().toString());
                                                    } catch (NoSuchAlgorithmException e) {
                                                        e.printStackTrace();
                                                    } catch (UnsupportedEncodingException e) {
                                                        e.printStackTrace();
                                                    }

                                                    Usuario user = new Usuario();

                                                    user.setNome(nome.getText().toString());
                                                    user.setEmail(email.getText().toString());
                                                    user.setCidade(cidade.getText().toString());
                                                    user.setEstado(estado.getText().toString());
                                                    user.setDataNasc(dataNasc.getText().toString().replace("/", ""));
                                                    user.setFone(foneContato.getText().toString().replace("(", "").replace(")", "").replace("-", ""));
                                                    user.setTipoUser(TipoUsuario.DOMESTICO);
                                                    user.setSenha(senhaC);
                                                    user.setAtivo(StatusUsuario.ATIVO);

                                                    Boolean bol = bd.atualizaUsuario(userCompleto.getUser().getId(), user);

                                                    if(bol.equals(true)) {
                                                        GerenciaInstanciaLogin.getInstance().setUsuario(user);

                                                        Visualizacao view = new Visualizacao();

                                                        view.setEmail(user.getEmail());

                                                        bd.atualizaEmailLogado(view, userCompleto.getUser().getEmail());

                                                        msgCad.setMessage("Atualizado com sucesso!");
                                                        msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface arg0, int arg1) {
                                                                bd.close();
                                                                Intent telaInicial = new Intent(TelaEditPerfil.this, TelaInicial.class);
                                                                startActivity(telaInicial);
                                                            }
                                                        });
                                                        msgCad.show();
                                                        bd.close();
                                                    }
                                                    else {
                                                        msgCad.setMessage("Erro ao Atualizar!");
                                                        msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface arg0, int arg1) {
                                                                bd.close();
                                                                Intent telaInicial = new Intent(TelaEditPerfil.this, TelaInicial.class);
                                                                startActivity(telaInicial);
                                                            }
                                                        });
                                                        msgCad.show();
                                                        bd.close();
                                                    }
                                                }
                                                else {
                                                    msgSenhas.setMessage("As senhas devem ser iguais!");
                                                    msgSenhas.setNeutralButton("OK", null);
                                                    msgSenhas.show();
                                                }
                                            }
                                            else if(!senha.getText().toString().trim().equals("") || !confSenha.getText().toString().trim().equals("")) {
                                                msgSenhas.setMessage("Para alterar a senha, os 2 campos devem ser preenchidos!");
                                                msgSenhas.setNeutralButton("OK", null);
                                                msgSenhas.show();
                                            }
                                            else {

                                                Usuario user = new Usuario();

                                                user.setNome(nome.getText().toString());
                                                user.setEmail(email.getText().toString());
                                                user.setCidade(cidade.getText().toString());
                                                user.setEstado(estado.getText().toString());
                                                user.setDataNasc(dataNasc.getText().toString().replace("/", ""));
                                                user.setFone(foneContato.getText().toString().replace("(", "").replace(")", "").replace("-", ""));
                                                user.setTipoUser(TipoUsuario.DOMESTICO);
                                                user.setSenha(userCompleto.getUser().getSenha());
                                                user.setAtivo(StatusUsuario.ATIVO);

                                                Boolean bol = bd.atualizaUsuario(userCompleto.getUser().getId(), user);

                                                if(bol.equals(true)) {
                                                    GerenciaInstanciaLogin.getInstance().setUsuario(user);

                                                    Visualizacao view = new Visualizacao();

                                                    view.setEmail(user.getEmail());

                                                    bd.atualizaEmailLogado(view, userCompleto.getUser().getEmail());

                                                    msgCad.setMessage("Atualizado com sucesso!");
                                                    msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface arg0, int arg1) {
                                                            bd.close();
                                                            Intent telaInicial = new Intent(TelaEditPerfil.this, TelaInicial.class);
                                                            startActivity(telaInicial);
                                                        }
                                                    });
                                                    msgCad.show();
                                                    bd.close();
                                                }
                                                else {
                                                    msgCad.setMessage("Erro ao Atualizar!");
                                                    msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface arg0, int arg1) {
                                                            bd.close();
                                                            Intent telaInicial = new Intent(TelaEditPerfil.this, TelaInicial.class);
                                                            startActivity(telaInicial);
                                                        }
                                                    });
                                                    msgCad.show();
                                                    bd.close();
                                                }
                                            }
                                        }
                                        else {
                                            msgPreencher.setMessage("O campo Fone Contato deve ser preenchido!");
                                            msgPreencher.setNeutralButton("OK", null);
                                            msgPreencher.show();
                                        }
                                    }
                                    else {
                                        msgPreencher.setMessage("O campo Data Nascimento deve ser preenchido!");
                                        msgPreencher.setNeutralButton("OK", null);
                                        msgPreencher.show();
                                    }
                                }
                                else {
                                    msgPreencher.setMessage("O campo Estado deve ser preenchido!");
                                    msgPreencher.setNeutralButton("OK", null);
                                    msgPreencher.show();
                                }
                            }
                            else {
                                msgPreencher.setMessage("O campo Cidade deve ser preenchido!");
                                msgPreencher.setNeutralButton("OK", null);
                                msgPreencher.show();
                            }
                        }
                        else {
                            msgPreencher.setMessage("Este E-mail já está sendo usado!");
                            msgPreencher.setNeutralButton("OK", null);
                            msgPreencher.show();
                        }
                    }
                    else {
                        msgPreencher.setMessage("O campo E-mail deve ser preenchido!");
                        msgPreencher.setNeutralButton("OK", null);
                        msgPreencher.show();
                    }
                }else {

                    msgPreencher.setMessage("Deve ser preenchido o nome completo!");
                    msgPreencher.setNeutralButton("OK", null);
                    msgPreencher.show();
                }
            }
        });

        //EDITANDO USUÁRIO MISTO / MARIDO / DOMÉSTICO>MISTO
        btnSalvar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nome.getText().toString().trim().equals("") && (nome.getText().length() >= 10)){
                    if(!email.getText().toString().trim().equals("")){
                        int idEmailVer = bd.pesquisaPorEmail(email.getText().toString());

                        if(idEmailVer == 0 || idEmailVer == userCompleto.getUser().getId()) {
                            if(!cidade.getText().toString().trim().equals("")){
                                if(!estado.getText().toString().trim().equals("")){
                                    if(!dataNasc.getText().toString().trim().equals("") && dataNasc.getText().length() == 10){
                                        if(!foneContato.getText().toString().trim().equals("") && foneContato.getText().length() == 14){
                                            if(!senha.getText().toString().trim().equals("") && !confSenha.getText().toString().trim().equals("")){
                                                if(checkEletrica.isChecked() || checkEncanamento.isChecked() || checkAlvenaria.isChecked() || checkMarcenaria.isChecked() || checkPintura.isChecked() || checkOutros.isChecked()) {
                                                    if((descricaoAtivi.getText().length() > 20) && !descricaoAtivi.getText().toString().trim().equals("")) {
                                                        if(senha.getText().toString().equals(confSenha.getText().toString())) {

                                                            String senhaC = null;
                                                            try {
                                                                senhaC = cpt.converteSt(senha.getText().toString());
                                                            } catch (NoSuchAlgorithmException e) {
                                                                e.printStackTrace();
                                                            } catch (UnsupportedEncodingException e) {
                                                                e.printStackTrace();
                                                            }

                                                            Usuario user = new Usuario();

                                                            user.setNome(nome.getText().toString());
                                                            user.setEmail(email.getText().toString());
                                                            user.setCidade(cidade.getText().toString());
                                                            user.setEstado(estado.getText().toString());
                                                            user.setDataNasc(dataNasc.getText().toString().replace("/", ""));
                                                            user.setFone(foneContato.getText().toString().replace("(", "").replace(")", "").replace("-", ""));
                                                            if(checkDomestico.isChecked() && checkMarido.isChecked()) {
                                                                user.setTipoUser(TipoUsuario.DOMESTICO_E_MARIDO);
                                                            }
                                                            else {
                                                                user.setTipoUser(TipoUsuario.MARIDO_ALUGUEL);
                                                            }
                                                            user.setSenha(senhaC);
                                                            user.setAtivo(StatusUsuario.ATIVO);

                                                            Boolean bol = bd.atualizaUsuario(userCompleto.getUser().getId(), user);

                                                            if(bol.equals(true)) {
                                                                GerenciaInstanciaLogin.getInstance().setUsuario(user);

                                                                Visualizacao view = new Visualizacao();

                                                                view.setEmail(user.getEmail());

                                                                bd.atualizaEmailLogado(view, userCompleto.getUser().getEmail());

                                                                if(userCompleto.getUser().getTipoUser().name().equals("DOMESTICO") && user.getTipoUser().name().equals("DOMESTICO_E_MARIDO")) {
                                                                    if(bd.addMarido(userCompleto.getUser().getId(), descricaoAtivi.getText().toString())) {
                                                                        int idMarido = bd.pesquisaIdMaridoPorCodigo(userCompleto.getUser().getId());

                                                                        if (checkAlvenaria.isChecked()) {
                                                                            bd.addAreaMarido(idMarido, Areas.ALVENARIA);
                                                                        }
                                                                        if (checkEletrica.isChecked()) {
                                                                            bd.addAreaMarido(idMarido, Areas.ELETRICA);
                                                                        }
                                                                        if (checkEncanamento.isChecked()) {
                                                                            bd.addAreaMarido(idMarido, Areas.ENCANAMENTO);
                                                                        }
                                                                        if (checkMarcenaria.isChecked()) {
                                                                            bd.addAreaMarido(idMarido, Areas.MARCENARIA);
                                                                        }
                                                                        if (checkPintura.isChecked()) {
                                                                            bd.addAreaMarido(idMarido, Areas.PINTURA);
                                                                        }
                                                                        if (checkOutros.isChecked()) {
                                                                            bd.addAreaMarido(idMarido, Areas.OUTROS);
                                                                        }

                                                                        msgCad.setMessage("Atualizado com sucesso!");
                                                                        msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface arg0, int arg1) {
                                                                                bd.close();
                                                                                Intent telaInicial = new Intent(TelaEditPerfil.this, TelaInicial.class);
                                                                                startActivity(telaInicial);
                                                                            }
                                                                        });
                                                                        msgCad.show();
                                                                        bd.close();
                                                                    }
                                                                }
                                                                else if(userCompleto.getUser().getTipoUser().name().equals("MARIDO") && user.getTipoUser().name().equals("DOMESTICO_E_MARIDO")) {

                                                                    bd.addDomestico(userCompleto.getUser().getId());

                                                                    bd.atualizaMarido(userCompleto.getUserMarido().getIdMarido(), descricaoAtivi.getText().toString());

                                                                    if(checkAlvenaria.isChecked() && userCompleto.getUserMarido().getAreaAlvenaria().name().equals("A")) {
                                                                        bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.ALVENARIA);
                                                                    }
                                                                    if(checkEletrica.isChecked() && userCompleto.getUserMarido().getAreaEletrica().name().equals("A")) {
                                                                        bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.ELETRICA);
                                                                    }
                                                                    if(checkEncanamento.isChecked() && userCompleto.getUserMarido().getAreaEncanamento().name().equals("A")) {
                                                                        bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.ENCANAMENTO);
                                                                    }
                                                                    if(checkMarcenaria.isChecked() && userCompleto.getUserMarido().getAreaMarcenaria().name().equals("A")) {
                                                                        bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.MARCENARIA);
                                                                    }
                                                                    if(checkPintura.isChecked() && userCompleto.getUserMarido().getAreaPintura().name().equals("A")) {
                                                                        bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.PINTURA);
                                                                    }
                                                                    if(checkOutros.isChecked() && userCompleto.getUserMarido().getAreaOutros().name().equals("A")) {
                                                                        bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.OUTROS);
                                                                    }

                                                                    msgCad.setMessage("Atualizado com sucesso!");
                                                                    msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface arg0, int arg1) {
                                                                            bd.close();
                                                                            Intent telaInicial = new Intent(TelaEditPerfil.this, TelaInicial.class);
                                                                            startActivity(telaInicial);
                                                                        }
                                                                    });
                                                                    msgCad.show();
                                                                    bd.close();
                                                                }
                                                                else {

                                                                    bd.atualizaMarido(userCompleto.getUserMarido().getIdMarido(), descricaoAtivi.getText().toString());

                                                                    if(checkAlvenaria.isChecked() && userCompleto.getUserMarido().getAreaAlvenaria().name().equals("A")) {
                                                                        bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.ALVENARIA);
                                                                    }
                                                                    if(checkEletrica.isChecked() && userCompleto.getUserMarido().getAreaEletrica().name().equals("A")) {
                                                                        bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.ELETRICA);
                                                                    }
                                                                    if(checkEncanamento.isChecked() && userCompleto.getUserMarido().getAreaEncanamento().name().equals("A")) {
                                                                        bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.ENCANAMENTO);
                                                                    }
                                                                    if(checkMarcenaria.isChecked() && userCompleto.getUserMarido().getAreaMarcenaria().name().equals("A")) {
                                                                        bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.MARCENARIA);
                                                                    }
                                                                    if(checkPintura.isChecked() && userCompleto.getUserMarido().getAreaPintura().name().equals("A")) {
                                                                        bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.PINTURA);
                                                                    }
                                                                    if(checkOutros.isChecked() && userCompleto.getUserMarido().getAreaOutros().name().equals("A")) {
                                                                        bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.OUTROS);
                                                                    }

                                                                    msgCad.setMessage("Atualizado com sucesso!");
                                                                    msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface arg0, int arg1) {
                                                                            bd.close();
                                                                            Intent telaInicial = new Intent(TelaEditPerfil.this, TelaInicial.class);
                                                                            startActivity(telaInicial);
                                                                        }
                                                                    });
                                                                    msgCad.show();
                                                                    bd.close();
                                                                }

                                                            }
                                                            else {
                                                                msgCad.setMessage("Erro ao Atualizar!");
                                                                msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                    public void onClick(DialogInterface arg0, int arg1) {
                                                                        bd.close();
                                                                        Intent telaInicial = new Intent(TelaEditPerfil.this, TelaInicial.class);
                                                                        startActivity(telaInicial);
                                                                    }
                                                                });
                                                                msgCad.show();
                                                                bd.close();
                                                            }
                                                        }
                                                        else {
                                                            msgSenhas.setMessage("As senhas devem ser iguais!");
                                                            msgSenhas.setNeutralButton("OK", null);
                                                            msgSenhas.show();
                                                        }
                                                    }
                                                    else {
                                                        msgPreencher.setMessage("Deve ser preenchido algumas habilidades que você possui!");
                                                        msgPreencher.setNeutralButton("OK", null);
                                                        msgPreencher.show();
                                                    }
                                                }
                                                else {
                                                    msgCheck.setMessage("Deve ser selecionado no mínimo uma das áreas de conhecimento!");
                                                    msgCheck.setNeutralButton("OK", null);
                                                    msgCheck.show();
                                                }
                                            }


                                            else if(!senha.getText().toString().trim().equals("") || !confSenha.getText().toString().trim().equals("")) {
                                                msgSenhas.setMessage("Para alterar a senha, os 2 campos devem ser preenchidos!");
                                                msgSenhas.setNeutralButton("OK", null);
                                                msgSenhas.show();
                                            }


                                            else {

                                                if(checkEletrica.isChecked() || checkEncanamento.isChecked() || checkAlvenaria.isChecked() || checkMarcenaria.isChecked() || checkPintura.isChecked() || checkOutros.isChecked()) {
                                                    if((descricaoAtivi.getText().length() > 20) && !descricaoAtivi.getText().toString().trim().equals("")) {

                                                        Usuario user = new Usuario();

                                                        user.setNome(nome.getText().toString());
                                                        user.setEmail(email.getText().toString());
                                                        user.setCidade(cidade.getText().toString());
                                                        user.setEstado(estado.getText().toString());
                                                        user.setDataNasc(dataNasc.getText().toString().replace("/", ""));
                                                        user.setFone(foneContato.getText().toString().replace("(", "").replace(")", "").replace("-", ""));
                                                        if(checkDomestico.isChecked() && checkMarido.isChecked()) {
                                                            user.setTipoUser(TipoUsuario.DOMESTICO_E_MARIDO);
                                                        }
                                                        else {
                                                            user.setTipoUser(TipoUsuario.MARIDO_ALUGUEL);
                                                        }
                                                        user.setAtivo(StatusUsuario.ATIVO);

                                                        Boolean bol = bd.atualizaUsuario(userCompleto.getUser().getId(), user);

                                                        if(bol.equals(true)) {
                                                            GerenciaInstanciaLogin.getInstance().setUsuario(user);

                                                            Visualizacao view = new Visualizacao();

                                                            view.setEmail(user.getEmail());

                                                            bd.atualizaEmailLogado(view, userCompleto.getUser().getEmail());

                                                            if(userCompleto.getUser().getTipoUser().name().equals("DOMESTICO") && user.getTipoUser().name().equals("DOMESTICO_E_MARIDO")) {
                                                                if(bd.addMarido(userCompleto.getUser().getId(), descricaoAtivi.getText().toString())) {
                                                                    int idMarido = bd.pesquisaIdMaridoPorCodigo(userCompleto.getUser().getId());


                                                                    if (checkAlvenaria.isChecked()) {
                                                                        bd.addAreaMarido(idMarido, Areas.ALVENARIA);
                                                                    }
                                                                    if (checkEletrica.isChecked()) {
                                                                        bd.addAreaMarido(idMarido, Areas.ELETRICA);
                                                                    }
                                                                    if (checkEncanamento.isChecked()) {
                                                                        bd.addAreaMarido(idMarido, Areas.ENCANAMENTO);
                                                                    }
                                                                    if (checkMarcenaria.isChecked()) {
                                                                        bd.addAreaMarido(idMarido, Areas.MARCENARIA);
                                                                    }
                                                                    if (checkPintura.isChecked()) {
                                                                        bd.addAreaMarido(idMarido, Areas.PINTURA);
                                                                    }
                                                                    if (checkOutros.isChecked()) {
                                                                        bd.addAreaMarido(idMarido, Areas.OUTROS);
                                                                    }

                                                                    msgCad.setMessage("Atualizado com sucesso!");
                                                                    msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface arg0, int arg1) {
                                                                            bd.close();
                                                                            Intent telaInicial = new Intent(TelaEditPerfil.this, TelaInicial.class);
                                                                            startActivity(telaInicial);
                                                                        }
                                                                    });
                                                                    msgCad.show();
                                                                    bd.close();
                                                                }
                                                            }
                                                            else if(userCompleto.getUser().getTipoUser().name().equals("MARIDO") && user.getTipoUser().name().equals("DOMESTICO_E_MARIDO")) {

                                                                bd.addDomestico(userCompleto.getUser().getId());

                                                                bd.atualizaMarido(userCompleto.getUserMarido().getIdMarido(), descricaoAtivi.getText().toString());

                                                                if(checkAlvenaria.isChecked() && userCompleto.getUserMarido().getAreaAlvenaria().name().equals("A")) {
                                                                    bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.ALVENARIA);
                                                                }
                                                                if(checkEletrica.isChecked() && userCompleto.getUserMarido().getAreaEletrica().name().equals("A")) {
                                                                    bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.ELETRICA);
                                                                }
                                                                if(checkEncanamento.isChecked() && userCompleto.getUserMarido().getAreaEncanamento().name().equals("A")) {
                                                                    bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.ENCANAMENTO);
                                                                }
                                                                if(checkMarcenaria.isChecked() && userCompleto.getUserMarido().getAreaMarcenaria().name().equals("A")) {
                                                                    bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.MARCENARIA);
                                                                }
                                                                if(checkPintura.isChecked() && userCompleto.getUserMarido().getAreaPintura().name().equals("A")) {
                                                                    bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.PINTURA);
                                                                }
                                                                if(checkOutros.isChecked() && userCompleto.getUserMarido().getAreaOutros().name().equals("A")) {
                                                                    bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.OUTROS);
                                                                }

                                                                msgCad.setMessage("Atualizado com sucesso!");
                                                                msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                    public void onClick(DialogInterface arg0, int arg1) {
                                                                        bd.close();
                                                                        Intent telaInicial = new Intent(TelaEditPerfil.this, TelaInicial.class);
                                                                        startActivity(telaInicial);
                                                                    }
                                                                });
                                                                msgCad.show();
                                                                bd.close();
                                                            }
                                                            else {

                                                                bd.atualizaMarido(userCompleto.getUserMarido().getIdMarido(), descricaoAtivi.getText().toString());

                                                                if(checkAlvenaria.isChecked() && userCompleto.getUserMarido().getAreaAlvenaria().name().equals("A")) {
                                                                    bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.ALVENARIA);
                                                                }
                                                                if(checkEletrica.isChecked() && userCompleto.getUserMarido().getAreaEletrica().name().equals("A")) {
                                                                    bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.ELETRICA);
                                                                }
                                                                if(checkEncanamento.isChecked() && userCompleto.getUserMarido().getAreaEncanamento().name().equals("A")) {
                                                                    bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.ENCANAMENTO);
                                                                }
                                                                if(checkMarcenaria.isChecked() && userCompleto.getUserMarido().getAreaMarcenaria().name().equals("A")) {
                                                                    bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.MARCENARIA);
                                                                }
                                                                if(checkPintura.isChecked() && userCompleto.getUserMarido().getAreaPintura().name().equals("A")) {
                                                                    bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.PINTURA);
                                                                }
                                                                if(checkOutros.isChecked() && userCompleto.getUserMarido().getAreaOutros().name().equals("A")) {
                                                                    bd.addAreaMarido(userCompleto.getUserMarido().getIdMarido(), Areas.OUTROS);
                                                                }


                                                                msgCad.setMessage("Atualizado com sucesso!");
                                                                msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                    public void onClick(DialogInterface arg0, int arg1) {
                                                                        bd.close();
                                                                        Intent telaInicial = new Intent(TelaEditPerfil.this, TelaInicial.class);
                                                                        startActivity(telaInicial);
                                                                    }
                                                                });
                                                                msgCad.show();
                                                                bd.close();
                                                            }

                                                        }
                                                        else {
                                                            msgCad.setMessage("Erro ao Atualizar!");
                                                            msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface arg0, int arg1) {
                                                                    bd.close();
                                                                    Intent telaInicial = new Intent(TelaEditPerfil.this, TelaInicial.class);
                                                                    startActivity(telaInicial);
                                                                }
                                                            });
                                                            msgCad.show();
                                                            bd.close();
                                                        }
                                                    }
                                                    else {
                                                        msgPreencher.setMessage("Deve ser preenchido algumas habilidades que você possui!");
                                                        msgPreencher.setNeutralButton("OK", null);
                                                        msgPreencher.show();
                                                    }
                                                }
                                                else {
                                                    msgCheck.setMessage("Deve ser selecionado no mínimo uma das áreas de conhecimento!");
                                                    msgCheck.setNeutralButton("OK", null);
                                                    msgCheck.show();
                                                }
                                            }
                                        }
                                        else {
                                            msgPreencher.setMessage("O campo Fone Contato deve ser preenchido!");
                                            msgPreencher.setNeutralButton("OK", null);
                                            msgPreencher.show();
                                        }
                                    }
                                    else {
                                        msgPreencher.setMessage("O campo Data Nascimento deve ser preenchido!");
                                        msgPreencher.setNeutralButton("OK", null);
                                        msgPreencher.show();
                                    }
                                }
                                else {
                                    msgPreencher.setMessage("O campo Estado deve ser preenchido!");
                                    msgPreencher.setNeutralButton("OK", null);
                                    msgPreencher.show();
                                }
                            }
                            else {
                                msgPreencher.setMessage("O campo Cidade deve ser preenchido!");
                                msgPreencher.setNeutralButton("OK", null);
                                msgPreencher.show();
                            }
                        }
                        else {
                            msgPreencher.setMessage("Este E-mail já está sendo usado!");
                            msgPreencher.setNeutralButton("OK", null);
                            msgPreencher.show();
                        }
                    }
                    else {
                        msgPreencher.setMessage("O campo E-mail deve ser preenchido!");
                        msgPreencher.setNeutralButton("OK", null);
                        msgPreencher.show();
                    }
                }else {

                    msgPreencher.setMessage("Deve ser preenchido o nome completo!");
                    msgPreencher.setNeutralButton("OK", null);
                    msgPreencher.show();
                }
            }
        });
    }
}

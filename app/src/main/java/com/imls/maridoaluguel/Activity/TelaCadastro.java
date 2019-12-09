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
import com.imls.maridoaluguel.Enum.Areas;
import com.imls.maridoaluguel.Util.Cripto;
import com.imls.maridoaluguel.Util.Mascaras;
import com.imls.maridoaluguel.Enum.StatusUsuario;
import com.imls.maridoaluguel.Enum.TipoUsuario;
import com.imls.maridoaluguel.Form.Usuario;
import com.imls.maridoaluguel.R;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class TelaCadastro extends AppCompatActivity {
    BancoDados bd = new BancoDados(this);
    Cripto cpt = new Cripto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        final AlertDialog.Builder ms = new AlertDialog.Builder(TelaCadastro.this);

        //Botão Cadastrar
        final Button btnCadastrar = findViewById(R.id.btnCadastrarTCU);
        final Button btnCadastrar2 = findViewById(R.id.btnCadastrar2TCU);

        //Check Tipos User
        final CheckBox checkDomestico = findViewById(R.id.checkUsuarioDomTCU);
        final CheckBox checkMarido = findViewById(R.id.checkMaridoDeAluguelTCU);

        //Check Áreas
        final CheckBox checkEletrica = findViewById(R.id.checkEletricaTCU);
        final CheckBox checkEncanamento = findViewById(R.id.checkEncanamentoTCU);
        final CheckBox checkPintura = findViewById(R.id.checkPinturaTCU);
        final CheckBox checkAlvenaria = findViewById(R.id.checkAlvenariaTCU);
        final CheckBox checkMarcenaria = findViewById(R.id.checkMarcenariaTCU);
        final CheckBox checkOutros = findViewById(R.id.checkOutrosTCU);

        final TextView msgInfo = findViewById(R.id.textvInfoCadTCU);

        final EditText descricaoAtivi = findViewById(R.id.textDescAtividadesTCU);

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

        //Torna atributos do Marido de Aluguel Invisível
        checkEletrica.setVisibility(View.GONE);
        checkEncanamento.setVisibility(View.GONE);
        checkPintura.setVisibility(View.GONE);
        checkAlvenaria.setVisibility(View.GONE);
        checkMarcenaria.setVisibility(View.GONE);
        checkOutros.setVisibility(View.GONE);
        msgInfo.setVisibility(View.GONE);
        descricaoAtivi.setVisibility(View.GONE);

        btnCadastrar2.setVisibility(View.GONE);

        //Campos text
        final EditText nome = findViewById(R.id.textNomeTCU);
        final EditText email = findViewById(R.id.textEmailTCU);
        final EditText cidade = findViewById(R.id.textCidadeTCU);
        final EditText estado = findViewById(R.id.textEstadoTCU);
        final EditText dataNasc = findViewById(R.id.textDataNascTCU);
        final EditText foneContato = findViewById(R.id.textFoneTCU);
        final EditText senha = findViewById(R.id.textSenhaTCU);
        final EditText confSenha = findViewById(R.id.textConfSenhaTCU);

        //Mensagem do check Marido/Domestico
        final AlertDialog.Builder msgCheck = new AlertDialog.Builder(TelaCadastro.this);
        msgCheck.setTitle("Selecione um");

        //Mensagem preencher campo
        final AlertDialog.Builder msgPreencher = new AlertDialog.Builder(TelaCadastro.this);
        msgPreencher.setTitle("Campo não preenchido");

        //Menagem senhas diferentes
        final AlertDialog.Builder msgSenhas = new AlertDialog.Builder(TelaCadastro.this);
        msgSenhas.setTitle("Senhas diferentes");

        //Menagem cadastro
        final AlertDialog.Builder msgCad = new AlertDialog.Builder(TelaCadastro.this);
        msgCad.setTitle("Cadastro");

        //Mascaras
        foneContato.addTextChangedListener(Mascaras.mask(foneContato, Mascaras.FORMAT_FONE));
        dataNasc.addTextChangedListener(Mascaras.mask(dataNasc, Mascaras.FORMAT_DATE));

        //Esconder-Mostrar campos ao clicar no check
        checkMarido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox) v).isChecked()) {

                    btnCadastrar.setVisibility(View.GONE);

                    btnCadastrar2.setVisibility(View.VISIBLE);

                    //Torna atribustos visíveis
                    checkEletrica.setVisibility(View.VISIBLE);
                    checkEncanamento.setVisibility(View.VISIBLE);
                    checkPintura.setVisibility(View.VISIBLE);
                    checkAlvenaria.setVisibility(View.VISIBLE);
                    checkMarcenaria.setVisibility(View.VISIBLE);
                    checkOutros.setVisibility(View.VISIBLE);
                    msgInfo.setVisibility(View.VISIBLE);
                    descricaoAtivi.setVisibility(View.VISIBLE);

                }
                else {

                    btnCadastrar.setVisibility(View.VISIBLE);

                    btnCadastrar2.setVisibility(View.GONE);

                    //Torna atribustos invisíveis
                    checkEletrica.setVisibility(View.GONE);
                    checkEncanamento.setVisibility(View.GONE);
                    checkPintura.setVisibility(View.GONE);
                    checkAlvenaria.setVisibility(View.GONE);
                    checkMarcenaria.setVisibility(View.GONE);
                    checkOutros.setVisibility(View.GONE);
                    msgInfo.setVisibility(View.GONE);
                    descricaoAtivi.setVisibility(View.GONE);
                }
            }
        });

        //Botão só com Doméstico selecionado
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkMarido.isChecked() && !checkDomestico.isChecked()) {

                    msgCheck.setMessage("Deve ser selecionado Usuário Doméstico ou Marido de Aluguel!");
                    msgCheck.setNeutralButton("OK", null);
                    msgCheck.show();
                }
                else {
                    if(!nome.getText().toString().trim().equals("") && (nome.getText().length() >= 10)){
                        if(!email.getText().toString().trim().equals("")){
                            if(!cidade.getText().toString().trim().equals("")){
                                if(!estado.getText().toString().trim().equals("")){
                                    if(!dataNasc.getText().toString().trim().equals("") && dataNasc.getText().length() == 10){
                                        if(!foneContato.getText().toString().trim().equals("") && foneContato.getText().length() == 14){
                                            if(!senha.getText().toString().trim().equals("")){
                                                if(!confSenha.getText().toString().trim().equals("")){
                                                    if(senha.getText().toString().equals(confSenha.getText().toString())) {
                                                        if(!bd.verificaEmail(email.getText().toString())) {
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

                                                            Boolean bol = bd.addUser(user);

                                                            //Verifica se foi criado o Usuario
                                                            if(bol) {
                                                                int idUser = 0;

                                                                idUser = bd.pesquisaPorEmail(email.getText().toString());

                                                                if(idUser != 0) {
                                                                    //Tendo o id, cadastra usuario domestico
                                                                    if(bd.addDomestico(idUser)) {
                                                                        msgCad.setMessage("Cadastrado com sucesso!");
                                                                        msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface arg0, int arg1) {
                                                                                bd.close();
                                                                                Intent telaLogin = new Intent(TelaCadastro.this, TelaLogin.class);
                                                                                startActivity(telaLogin);
                                                                            }
                                                                        });
                                                                        msgCad.show();
                                                                        bd.close();
                                                                    }
                                                                    else {
                                                                        try {
                                                                            bd.apagarUsuario(user);
                                                                            bd.apagarDomestico(idUser);
                                                                        }
                                                                        catch (Exception e) {
                                                                            e.printStackTrace();
                                                                        }

                                                                        msgCad.setMessage("Ocorreu um erro, tente novamente mais tarde!");
                                                                        msgCad.setNeutralButton("OK", null);
                                                                        msgCad.show();

                                                                        bd.close();
                                                                    }
                                                                }
                                                                else {
                                                                    try {
                                                                        bd.apagarUsuario(user);
                                                                    }
                                                                    catch (Exception e) {
                                                                        e.printStackTrace();
                                                                    }

                                                                    msgCad.setMessage("Ocorreu um erro, tente novamente mais tarde!");
                                                                    msgCad.setNeutralButton("OK", null);
                                                                    msgCad.show();

                                                                    bd.close();
                                                                }
                                                            }
                                                            else {
                                                                try {
                                                                    bd.apagarUsuario(user);
                                                                }
                                                                catch (Exception e) {
                                                                    e.printStackTrace();
                                                                }
                                                                msgCad.setMessage("Ocorreu um erro, tente novamente mais tarde!");
                                                                msgCad.setNeutralButton("OK", null);
                                                                msgCad.show();

                                                                bd.close();
                                                            }
                                                        }
                                                        else {
                                                            msgCad.setMessage("Já existe esse email cadastrado!\n" +
                                                                    "Deseja fazer login?");
                                                            msgCad.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface arg0, int arg1) {
                                                                    bd.close();
                                                                    Intent telaLogin = new Intent(TelaCadastro.this, TelaLogin.class);
                                                                    startActivity(telaLogin);
                                                                }
                                                            });
                                                            msgCad.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface arg0, int arg1) {
                                                                }
                                                            });
                                                            msgCad.show();
                                                        }
                                                    }
                                                    else {
                                                        msgSenhas.setMessage("As senhas devem ser iguais!");
                                                        msgSenhas.setNeutralButton("OK", null);
                                                        msgSenhas.show();
                                                    }
                                                }
                                                else {
                                                    msgPreencher.setMessage("O campo Confirmar Senha deve ser preenchido!");
                                                    msgPreencher.setNeutralButton("OK", null);
                                                    msgPreencher.show();
                                                }
                                            }
                                            else {
                                                msgPreencher.setMessage("O campo Senha deve ser preenchido!");
                                                msgPreencher.setNeutralButton("OK", null);
                                                msgPreencher.show();
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
            }
        });

        //Botão com Marido selecionado
        btnCadastrar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkMarido.isChecked() && !checkDomestico.isChecked()) {

                    msgCheck.setMessage("Deve ser selecionado Usuário Doméstico ou Marido de Aluguel!");
                    msgCheck.setNeutralButton("OK", null);
                    msgCheck.show();
                }
                else {
                    if(!nome.getText().toString().trim().equals("") && (nome.getText().length() >= 10)){
                        if(!email.getText().toString().trim().equals("")){
                            if(!cidade.getText().toString().trim().equals("")){
                                if(!estado.getText().toString().trim().equals("")){
                                    if(!dataNasc.getText().toString().trim().equals("") && dataNasc.getText().length() == 10){
                                        if(!foneContato.getText().toString().trim().equals("") && foneContato.getText().length() == 14){
                                            if(!senha.getText().toString().trim().equals("")){
                                                if(!confSenha.getText().toString().trim().equals("")){
                                                    if(checkEletrica.isChecked() || checkEncanamento.isChecked() || checkAlvenaria.isChecked() || checkMarcenaria.isChecked() || checkPintura.isChecked() || checkOutros.isChecked()) {
                                                        if((descricaoAtivi.getText().length() > 20) && !descricaoAtivi.getText().toString().trim().equals("")) {
                                                            if(senha.getText().toString().equals(confSenha.getText().toString())) {
                                                                if(!bd.verificaEmail(email.getText().toString())) {
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

                                                                    Boolean bol = bd.addUser(user);

                                                                    if(bol) {
                                                                        int idUser = 0;

                                                                        idUser = bd.pesquisaPorEmail(email.getText().toString());

                                                                        if(idUser != 0) {
                                                                            //Caso seja Marido e Domestico
                                                                            if(user.getTipoUser().name().equals("DOMESTICO_E_MARIDO")) {
                                                                                //Tendo o id, cadastra usuario domestico
                                                                                if(bd.addDomestico(idUser)) {

                                                                                    if(bd.addMarido(idUser, descricaoAtivi.getText().toString())) {
                                                                                        int idMarido = bd.pesquisaIdMaridoPorCodigo(idUser);
                                                                                        System.out.println(idMarido);

                                                                                        if(checkAlvenaria.isChecked()) {
                                                                                            bd.addAreaMarido(idMarido, Areas.ALVENARIA);
                                                                                        }
                                                                                        if(checkEletrica.isChecked()) {
                                                                                            bd.addAreaMarido(idMarido, Areas.ELETRICA);
                                                                                        }
                                                                                        if(checkEncanamento.isChecked()) {
                                                                                            bd.addAreaMarido(idMarido, Areas.ENCANAMENTO);
                                                                                        }
                                                                                        if(checkMarcenaria.isChecked()) {
                                                                                            bd.addAreaMarido(idMarido, Areas.MARCENARIA);
                                                                                        }
                                                                                        if(checkPintura.isChecked()) {
                                                                                            bd.addAreaMarido(idMarido, Areas.PINTURA);
                                                                                        }
                                                                                        if(checkOutros.isChecked()) {
                                                                                            bd.addAreaMarido(idMarido, Areas.OUTROS);
                                                                                        }

                                                                                        msgCad.setMessage("Cadastrado com sucesso!");
                                                                                        msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                                            public void onClick(DialogInterface arg0, int arg1) {
                                                                                                bd.close();
                                                                                                Intent telaLogin = new Intent(TelaCadastro.this, TelaLogin.class);
                                                                                                startActivity(telaLogin);
                                                                                            }
                                                                                        });
                                                                                        msgCad.show();
                                                                                        bd.close();

                                                                                    }
                                                                                    else {
                                                                                        try {
                                                                                            bd.apagarUsuario(user);
                                                                                            bd.apagarDomestico(idUser);
                                                                                            bd.apagarMarido(idUser);
                                                                                        }
                                                                                        catch (Exception e) {
                                                                                            e.printStackTrace();
                                                                                        }

                                                                                        msgCad.setMessage("Ocorreu um erro, tente novamente mais tarde!");
                                                                                        msgCad.setNeutralButton("OK", null);
                                                                                        msgCad.show();

                                                                                        bd.close();
                                                                                    }
                                                                                }
                                                                                else {
                                                                                    try {
                                                                                        bd.apagarUsuario(user);
                                                                                        bd.apagarDomestico(idUser);
                                                                                    }
                                                                                    catch (Exception e) {
                                                                                        e.printStackTrace();
                                                                                    }

                                                                                    msgCad.setMessage("Ocorreu um erro, tente novamente mais tarde!");
                                                                                    msgCad.setNeutralButton("OK", null);
                                                                                    msgCad.show();

                                                                                    bd.close();
                                                                                }
                                                                            }
                                                                            //Caso seja apenas Marido
                                                                            else {
                                                                                if(bd.addMarido(idUser, descricaoAtivi.getText().toString())) {
                                                                                    int idMarido = bd.pesquisaIdMaridoPorCodigo(idUser);

                                                                                    if(checkAlvenaria.isChecked()) {
                                                                                        bd.addAreaMarido(idMarido, Areas.ALVENARIA);
                                                                                    }
                                                                                    if(checkEletrica.isChecked()) {
                                                                                        bd.addAreaMarido(idMarido, Areas.ELETRICA);
                                                                                    }
                                                                                    if(checkEncanamento.isChecked()) {
                                                                                        bd.addAreaMarido(idMarido, Areas.ENCANAMENTO);
                                                                                    }
                                                                                    if(checkMarcenaria.isChecked()) {
                                                                                        bd.addAreaMarido(idMarido, Areas.MARCENARIA);
                                                                                    }
                                                                                    if(checkPintura.isChecked()) {
                                                                                        bd.addAreaMarido(idMarido, Areas.PINTURA);
                                                                                    }
                                                                                    if(checkOutros.isChecked()) {
                                                                                        bd.addAreaMarido(idMarido, Areas.OUTROS);
                                                                                    }

                                                                                    msgCad.setMessage("Cadastrado com sucesso!");
                                                                                    msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                                        public void onClick(DialogInterface arg0, int arg1) {
                                                                                            bd.close();
                                                                                            Intent telaLogin = new Intent(TelaCadastro.this, TelaLogin.class);
                                                                                            startActivity(telaLogin);
                                                                                        }
                                                                                    });
                                                                                    msgCad.show();
                                                                                    bd.close();
                                                                                }
                                                                                else {
                                                                                    try {
                                                                                        bd.apagarUsuario(user);
                                                                                        bd.apagarMarido(idUser);
                                                                                    }
                                                                                    catch (Exception e) {
                                                                                        e.printStackTrace();
                                                                                    }

                                                                                    msgCad.setMessage("Ocorreu um erro, tente novamente mais tarde!");
                                                                                    msgCad.setNeutralButton("OK", null);
                                                                                    msgCad.show();

                                                                                    bd.close();
                                                                                }
                                                                            }
                                                                        }
                                                                        else {
                                                                            try {
                                                                                bd.apagarUsuario(user);
                                                                            }
                                                                            catch (Exception e) {
                                                                                e.printStackTrace();
                                                                            }

                                                                            msgCad.setMessage("Ocorreu um erro, tente novamente mais tarde!");
                                                                            msgCad.setNeutralButton("OK", null);
                                                                            msgCad.show();

                                                                            bd.close();
                                                                        }
                                                                    }
                                                                    else {
                                                                        msgCad.setMessage("Ocorreu um erro, tente novamente mais tarde!");
                                                                        msgCad.setNeutralButton("OK", null);
                                                                        msgCad.show();

                                                                        bd.close();
                                                                    }
                                                                }
                                                                else {
                                                                    msgCad.setMessage("Já existe esse email cadastrado!\n" +
                                                                            "Deseja fazer login?");
                                                                    msgCad.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface arg0, int arg1) {
                                                                            bd.close();
                                                                            Intent telaLoguin = new Intent(TelaCadastro.this, TelaLogin.class);
                                                                            startActivity(telaLoguin);
                                                                        }
                                                                    });
                                                                    msgCad.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface arg0, int arg1) {
                                                                            bd.close();
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
                                                } else {
                                                    msgPreencher.setMessage("O campo Confirmar Senha deve ser preenchido!");
                                                    msgPreencher.setNeutralButton("OK", null);
                                                    msgPreencher.show();
                                                }
                                            } else {
                                                msgPreencher.setMessage("O campo Senha deve ser preenchido!");
                                                msgPreencher.setNeutralButton("OK", null);
                                                msgPreencher.show();
                                            }
                                        } else {
                                            msgPreencher.setMessage("O campo Fone Contato deve ser preenchido!");
                                            msgPreencher.setNeutralButton("OK", null);
                                            msgPreencher.show();
                                        }
                                    } else {
                                        msgPreencher.setMessage("O campo Data Nascimento deve ser preenchido!");
                                        msgPreencher.setNeutralButton("OK", null);
                                        msgPreencher.show();
                                    }
                                } else {
                                    msgPreencher.setMessage("O campo Estado deve ser preenchido!");
                                    msgPreencher.setNeutralButton("OK", null);
                                    msgPreencher.show();
                                }
                            } else {
                                msgPreencher.setMessage("O campo Cidade deve ser preenchido!");
                                msgPreencher.setNeutralButton("OK", null);
                                msgPreencher.show();
                            }
                        } else {
                            msgPreencher.setMessage("O campo E-mail deve ser preenchido!");
                            msgPreencher.setNeutralButton("OK", null);
                            msgPreencher.show();
                        }
                    } else {

                        msgPreencher.setMessage("Deve ser preenchido o nome completo!");
                        msgPreencher.setNeutralButton("OK", null);
                        msgPreencher.show();
                    }
                }
            }
        });
    }

    private boolean validateEmailFormat(String email) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }
}
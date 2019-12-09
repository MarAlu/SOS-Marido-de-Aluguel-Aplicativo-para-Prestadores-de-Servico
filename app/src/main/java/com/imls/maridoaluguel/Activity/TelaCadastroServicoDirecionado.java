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
import com.imls.maridoaluguel.Enum.StatusServico;
import com.imls.maridoaluguel.Enum.TipoServico;
import com.imls.maridoaluguel.Form.Servico;
import com.imls.maridoaluguel.Form.Usuario;
import com.imls.maridoaluguel.Form.UsuarioCompleto;
import com.imls.maridoaluguel.R;
import com.imls.maridoaluguel.Util.GerenciaInstanciaLogin;
import com.imls.maridoaluguel.Util.Mascaras;

public class TelaCadastroServicoDirecionado extends AppCompatActivity {

    BancoDados bd = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_servico_direcionado);

        //Instancia do usuario para receber do banco
        final UsuarioCompleto userCompleto = new UsuarioCompleto();
        final UsuarioCompleto userCompletoMar = new UsuarioCompleto();

        //Pegando email de quem está logado
        Usuario usr = GerenciaInstanciaLogin.getInstance().getUsuario();

        Intent v = getIntent();
        String nomeMa = v.getStringExtra("nomeMarido");
        final String idMa = v.getStringExtra("idMarido");


        //Populando Usuario logado Completo
        userCompleto.setUser(bd.buscarUsuarioPorEmail(usr.getEmail()));
        userCompleto.setUserDomestico(bd.buscarDomesticoPorCdUser(userCompleto.getUser().getId()));
        userCompleto.setUserMarido(bd.buscarMaridoPorCdUser(userCompleto.getUser().getId()));

        //Botões
        Button btnCriar = findViewById(R.id.btnAceitarServicoTCS);

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

        final EditText descricaoAtivi = findViewById(R.id.textDescAtividadesTCS);
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


        //Mensagem do check Marido/Domestico
        final AlertDialog.Builder msgCheck = new AlertDialog.Builder(TelaCadastroServicoDirecionado.this);
        msgCheck.setTitle("Selecione um");

        //Mensagem preencher campo
        final AlertDialog.Builder msgPreencher = new AlertDialog.Builder(TelaCadastroServicoDirecionado.this);
        msgPreencher.setTitle("Campo não preenchido");

        //Menagem cadastro
        final AlertDialog.Builder msgCad = new AlertDialog.Builder(TelaCadastroServicoDirecionado.this);
        msgCad.setTitle("Cadastro Serviço");

        fon.addTextChangedListener(Mascaras.mask(fon, Mascaras.FORMAT_FONE));

        fon.setText(userCompleto.getUser().getFone());

        nome.setText(userCompleto.getUser().getNome());
        cidade.setText(userCompleto.getUser().getCidade());
        foneContato.setText(fon.getText().toString());
        nomeMarido.setText(nomeMa);


        userCompletoMar.setUserMarido(bd.buscarMaridoPorCdMarido(Integer.parseInt(idMa)));
        userCompletoMar.setUser(bd.buscarUsuarioPorId(userCompletoMar.getUserMarido().getIdUsuario()));

        userCompletoMar.setUserMarido(bd.buscarMaridoArea(userCompletoMar.getUserMarido().getIdMarido(), userCompletoMar.getUserMarido().getIdUsuario(),
                userCompletoMar.getUserMarido().getDescHabilidade(), userCompletoMar.getUserMarido().getServicosRealizados(), userCompletoMar.getUserMarido().getAvaliacao()));


        checkAlvenaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(userCompletoMar.getUserMarido().getAreaAlvenaria().name().equals("ALVENARIA")) {
                checkAlvenaria.setChecked(true);

                checkEletrica.setChecked(false);
                checkEncanamento.setChecked(false);
                checkMarcenaria.setChecked(false);
                checkPintura.setChecked(false);
                checkOutros.setChecked(false);
            }
            else {
                checkAlvenaria.setChecked(false);
            }
            }
        });
        checkEletrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(userCompletoMar.getUserMarido().getAreaEletrica().name().equals("ELETRICA")) {
                checkEletrica.setChecked(true);

                checkAlvenaria.setChecked(false);
                checkEncanamento.setChecked(false);
                checkMarcenaria.setChecked(false);
                checkPintura.setChecked(false);
                checkOutros.setChecked(false);
            }
            else {
                checkEletrica.setChecked(false);
            }
            }
        });
        checkEncanamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(userCompletoMar.getUserMarido().getAreaEncanamento().name().equals("ENCANAMENTO")) {
                checkEncanamento.setChecked(true);

                checkEletrica.setChecked(false);
                checkAlvenaria.setChecked(false);
                checkMarcenaria.setChecked(false);
                checkPintura.setChecked(false);
                checkOutros.setChecked(false);
            }
            else {
                checkEncanamento.setChecked(false);
            }
            }
        });
        checkMarcenaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(userCompletoMar.getUserMarido().getAreaMarcenaria().name().equals("MARCENARIA")) {
                checkMarcenaria.setChecked(true);

                checkEletrica.setChecked(false);
                checkEncanamento.setChecked(false);
                checkAlvenaria.setChecked(false);
                checkPintura.setChecked(false);
                checkOutros.setChecked(false);
            }
            else {
                checkMarcenaria.setChecked(false);
            }
            }
        });
        checkPintura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(userCompletoMar.getUserMarido().getAreaPintura().name().equals("PINTURA")) {
                checkPintura.setChecked(true);

                checkEletrica.setChecked(false);
                checkEncanamento.setChecked(false);
                checkMarcenaria.setChecked(false);
                checkAlvenaria.setChecked(false);
                checkOutros.setChecked(false);
            }
            else {
                checkPintura.setChecked(false);
            }
            }
        });
        checkOutros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(userCompletoMar.getUserMarido().getAreaOutros().name().equals("OUTROS")) {
                checkOutros.setChecked(true);

                checkEletrica.setChecked(false);
                checkEncanamento.setChecked(false);
                checkMarcenaria.setChecked(false);
                checkPintura.setChecked(false);
                checkAlvenaria.setChecked(false);
            }
            else {
                checkOutros.setChecked(false);
            }
            }
        });


        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEletrica.isChecked() || checkEncanamento.isChecked() || checkAlvenaria.isChecked() || checkMarcenaria.isChecked() || checkPintura.isChecked() || checkOutros.isChecked()) {
                    if ((descricaoAtivi.getText().length() > 15) && !descricaoAtivi.getText().toString().trim().equals("")) {

                        Servico servico = new Servico();

                        if(checkAlvenaria.isChecked()) {
                            servico.setAreaServico(Areas.ALVENARIA);
                        }
                        if(checkEletrica.isChecked()) {
                            servico.setAreaServico(Areas.ELETRICA);
                        }
                        if(checkEncanamento.isChecked()) {
                            servico.setAreaServico(Areas.ENCANAMENTO);
                        }
                        if(checkMarcenaria.isChecked()) {
                            servico.setAreaServico(Areas.MARCENARIA);
                        }
                        if(checkPintura.isChecked()) {
                            servico.setAreaServico(Areas.PINTURA);
                        }
                        if(checkOutros.isChecked()) {
                            servico.setAreaServico(Areas.OUTROS);
                        }

                        servico.setDescServico(descricaoAtivi.getText().toString());
                        servico.setTipoServico(TipoServico.DIRECIONADO);
                        servico.setFoneDomestico(foneContato.getText().toString().replace("(", "").replace(")", "").replace("-", ""));
                        servico.setStatusServico(StatusServico.ABERTO);
                        servico.setIdDomestico(userCompleto.getUserDomestico().getIdDomestico());
                        servico.setIdMarido(Integer.parseInt(idMa));

                        bd.addServico(servico);

                        msgCad.setMessage("Serviço Cadastrado com sucesso!");
                        msgCad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                bd.close();
                                Intent telaInicial = new Intent(TelaCadastroServicoDirecionado.this, TelaInicial.class);
                                startActivity(telaInicial);
                            }
                        });
                        msgCad.show();
                        bd.close();

                    }
                    else {
                        msgPreencher.setMessage("Deve ser preenchido uma descrição do problema!");
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
        });
    }
}

package com.imls.maridoaluguel.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.imls.maridoaluguel.Banco.BancoDados;
import com.imls.maridoaluguel.Enum.Areas;
import com.imls.maridoaluguel.Form.UsuarioCompleto;
import com.imls.maridoaluguel.R;

public class TelaVisualizaProfissional extends AppCompatActivity {

    BancoDados bd = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_visualiza_profissional);

        final UsuarioCompleto userCompleto = new UsuarioCompleto();

        Intent i = getIntent();

        final TextView descricaoAtivi = findViewById(R.id.viewDescAtividadesTVM);

        //Botão Cadastrar
        final Button btnCadastrar = findViewById(R.id.btnCriarServicoTVM);

        //Check Áreas
        final CheckBox checkEletrica = findViewById(R.id.checkEletricaTVM);
        final CheckBox checkEncanamento = findViewById(R.id.checkEncanamentoTVM);
        final CheckBox checkPintura = findViewById(R.id.checkPinturaTVM);
        final CheckBox checkAlvenaria = findViewById(R.id.checkAlvenariaTPM);
        final CheckBox checkMarcenaria = findViewById(R.id.checkMarcenariaTVM);
        final CheckBox checkOutros = findViewById(R.id.checkOutrosTVM);

        //Campos view
        final TextView msgInfo = findViewById(R.id.viewInfoCadTVM);
        final TextView nome = findViewById(R.id.viewNomeTPM);
        final TextView cidade = findViewById(R.id.viewCidadeTVM);

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


        userCompleto.setUserMarido(bd.buscarMaridoPorCdMarido(Integer.parseInt(i.getStringExtra("idMarido"))));
        userCompleto.setUser(bd.buscarUsuarioPorId(userCompleto.getUserMarido().getIdUsuario()));

        userCompleto.setUserMarido(bd.buscarMaridoArea(userCompleto.getUserMarido().getIdMarido(), userCompleto.getUserMarido().getIdUsuario(),
                userCompleto.getUserMarido().getDescHabilidade(), userCompleto.getUserMarido().getServicosRealizados(), userCompleto.getUserMarido().getAvaliacao()));


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


        nome.setText(userCompleto.getUser().getNome());
        cidade.setText(userCompleto.getUser().getCidade());
        descricaoAtivi.setText(userCompleto.getUserMarido().getDescHabilidade());

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer idMa = userCompleto.getUserMarido().getIdMarido();

                Intent telaServicoDir = new Intent(TelaVisualizaProfissional.this, TelaCadastroServicoDirecionado.class);

                telaServicoDir.putExtra("nomeMarido", userCompleto.getUser().getNome());
                telaServicoDir.putExtra("idMarido", idMa.toString());
                startActivity(telaServicoDir);
            }
        });


    }
}

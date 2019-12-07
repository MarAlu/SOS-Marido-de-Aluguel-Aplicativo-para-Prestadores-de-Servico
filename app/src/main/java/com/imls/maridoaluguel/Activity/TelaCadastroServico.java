package com.imls.maridoaluguel.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.imls.maridoaluguel.Banco.BancoDados;
import com.imls.maridoaluguel.Form.Usuario;
import com.imls.maridoaluguel.Form.UsuarioCompleto;
import com.imls.maridoaluguel.R;
import com.imls.maridoaluguel.Util.GerenciaInstanciaLogin;
import com.imls.maridoaluguel.Util.Mascaras;

public class TelaCadastroServico extends AppCompatActivity {
    BancoDados bd = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_servico);

        //Instancia do usuario para receber do banco
        final UsuarioCompleto userCompleto = new UsuarioCompleto();

        //Pegando email de quem está logado
        Usuario usr = GerenciaInstanciaLogin.getInstance().getUsuario();


        //Populando Usuario Completo
        userCompleto.setUser(bd.buscarUsuarioPorEmail(usr.getEmail()));
        userCompleto.setUserDomestico(bd.buscarDomesticoPorCdUser(userCompleto.getUser().getId()));
        userCompleto.setUserMarido(bd.buscarMaridoPorCdUser(userCompleto.getUser().getId()));

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


        fon.addTextChangedListener(Mascaras.mask(fon, Mascaras.FORMAT_FONE));

        fon.setText(userCompleto.getUser().getFone());

        nome.setText(userCompleto.getUser().getNome());
        cidade.setText(userCompleto.getUser().getCidade());
        foneContato.setText(fon.getText().toString());

        checkAlvenaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAlvenaria.isChecked()) {
                    checkEletrica.setChecked(false);
                    checkEncanamento.setChecked(false);
                    checkMarcenaria.setChecked(false);
                    checkPintura.setChecked(false);
                    checkOutros.setChecked(false);
                }
            }
        });
        checkEletrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEletrica.isChecked()) {
                    checkAlvenaria.setChecked(false);
                    checkEncanamento.setChecked(false);
                    checkMarcenaria.setChecked(false);
                    checkPintura.setChecked(false);
                    checkOutros.setChecked(false);
                }
            }
        });
        checkEncanamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEncanamento.isChecked()) {
                    checkEletrica.setChecked(false);
                    checkAlvenaria.setChecked(false);
                    checkMarcenaria.setChecked(false);
                    checkPintura.setChecked(false);
                    checkOutros.setChecked(false);
                }
            }
        });
        checkMarcenaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkMarcenaria.isChecked()) {
                    checkEletrica.setChecked(false);
                    checkEncanamento.setChecked(false);
                    checkAlvenaria.setChecked(false);
                    checkPintura.setChecked(false);
                    checkOutros.setChecked(false);
                }
            }
        });
        checkPintura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPintura.isChecked()) {
                    checkEletrica.setChecked(false);
                    checkEncanamento.setChecked(false);
                    checkMarcenaria.setChecked(false);
                    checkAlvenaria.setChecked(false);
                    checkOutros.setChecked(false);
                }
            }
        });
        checkOutros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOutros.isChecked()) {
                    checkEletrica.setChecked(false);
                    checkEncanamento.setChecked(false);
                    checkMarcenaria.setChecked(false);
                    checkPintura.setChecked(false);
                    checkAlvenaria.setChecked(false);
                }
            }
        });
    }
}

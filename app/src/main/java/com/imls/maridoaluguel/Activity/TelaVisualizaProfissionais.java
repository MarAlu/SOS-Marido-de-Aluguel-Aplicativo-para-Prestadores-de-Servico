package com.imls.maridoaluguel.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.imls.maridoaluguel.Banco.BancoDados;
import com.imls.maridoaluguel.Business.Visualizacao;
import com.imls.maridoaluguel.Form.Servico;
import com.imls.maridoaluguel.Form.UsuarioCompleto;
import com.imls.maridoaluguel.Form.UsuarioDomestico;
import com.imls.maridoaluguel.Form.UsuarioMarido;
import com.imls.maridoaluguel.R;
import com.imls.maridoaluguel.Util.AdaptadorProfissionais;

import java.text.ParseException;
import java.util.ArrayList;

public class TelaVisualizaProfissionais extends AppCompatActivity {

    RecyclerView recyclerView;
    AdaptadorProfissionais adpt;

    BancoDados bd = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_visualiza_profissionais);

        final Visualizacao view;

        Intent busca = getIntent();
        ArrayList<UsuarioMarido> maridos = null;

        String caixaP = "";
        if(busca.getStringExtra("caixaP").length() > 0) {
            caixaP = busca.getStringExtra("caixaP");

            try {
                maridos = bd.buscaProfissaPesquisa(caixaP);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else {
            view = bd.buscaLogado();


            if(view.getTipo().equals("MARIDO")) {

                UsuarioMarido userMar = bd.buscarMaridoPorCdMarido(view.getId());
                UsuarioCompleto userComp = new UsuarioCompleto();
                userComp.setUser(bd.buscarUsuarioPorId(userMar.getIdUsuario()));
            }
            else {
                UsuarioDomestico userDom = bd.buscarDomesticoPorCdDomestico(view.getId());
                UsuarioCompleto userComp = new UsuarioCompleto();
                userComp.setUser(bd.buscarUsuarioPorId(userDom.getIdUsuario()));
                UsuarioMarido userM = bd.buscarMaridoPorCdUser(userComp.getUser().getId());

                if(userComp.getUser().getTipoUser().equals("DOMESTICO")) {
                    try {
                        maridos = bd.listaTodosProfissionais();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                else {

                    try {
                        maridos = bd.listaTodosProfissionaisMenosEu(userM.getIdMarido());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }



        recyclerView = findViewById(R.id.recycleServicos);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adpt = new AdaptadorProfissionais(this, maridos);
        recyclerView.setAdapter(adpt);

    }
}

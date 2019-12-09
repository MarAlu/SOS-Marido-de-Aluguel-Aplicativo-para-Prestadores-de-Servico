package com.imls.maridoaluguel.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.imls.maridoaluguel.Banco.BancoDados;
import com.imls.maridoaluguel.Business.Visualizacao;
import com.imls.maridoaluguel.Form.Servico;
import com.imls.maridoaluguel.Form.UsuarioCompleto;
import com.imls.maridoaluguel.Form.UsuarioDomestico;
import com.imls.maridoaluguel.Form.UsuarioMarido;
import com.imls.maridoaluguel.R;
import com.imls.maridoaluguel.Util.AdaptadorServico;

import java.text.ParseException;
import java.util.ArrayList;

public class TelaVisualizaServicos extends AppCompatActivity {

    RecyclerView recyclerView;
    AdaptadorServico adpt;

    BancoDados bd = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_visualiza_servicos);

        final Visualizacao view;

        ArrayList<Servico> serv = null;

        view = bd.buscaLogado();

        if(view.getTipo().equals("MARIDO")) {

            UsuarioMarido userMar = bd.buscarMaridoPorCdMarido(view.getId());
            UsuarioCompleto userComp = new UsuarioCompleto();
            userComp.setUser(bd.buscarUsuarioPorId(userMar.getIdUsuario()));

            if(userComp.getUser().getTipoUser().name().equals("MARIDO_ALUGUEL")) {
                try {
                    serv = bd.listaServicosAbertos(userMar.getIdMarido());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else {
                userComp.setUserDomestico(bd.buscarDomesticoPorCdUser(userComp.getUser().getId()));
                try {
                    serv = bd.listaServicosAbertosSemMeus(userComp.getUserDomestico().getIdDomestico(), userMar.getIdMarido());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
        else {

            UsuarioDomestico userDom = bd.buscarDomesticoPorCdDomestico(view.getId());
            UsuarioCompleto userComp = new UsuarioCompleto();
            userComp.setUser(bd.buscarUsuarioPorId(userDom.getIdUsuario()));

            if(userComp.getUser().getTipoUser().equals("DOMESTICO")) {
                try {
                    serv = bd.listaServicosMeus(userDom.getIdDomestico());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    serv = bd.listaServicosMeus(userDom.getIdDomestico());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        recyclerView = findViewById(R.id.recycleServicos);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adpt = new AdaptadorServico(this, serv);
        recyclerView.setAdapter(adpt);
    }
}

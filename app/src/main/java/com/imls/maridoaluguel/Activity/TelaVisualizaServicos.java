package com.imls.maridoaluguel.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.imls.maridoaluguel.Banco.BancoDados;
import com.imls.maridoaluguel.Form.Servico;
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

        recyclerView = findViewById(R.id.recycleServicos);

        ArrayList<Servico> serv = null;
        try {
            serv = bd.listaTodosServicos();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adpt = new AdaptadorServico(this, serv);
        recyclerView.setAdapter(adpt);
    }
}

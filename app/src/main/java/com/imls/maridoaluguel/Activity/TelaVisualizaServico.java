package com.imls.maridoaluguel.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.imls.maridoaluguel.R;

public class TelaVisualizaServico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_visualiza_servico);

        Intent i = getIntent();

    }
}

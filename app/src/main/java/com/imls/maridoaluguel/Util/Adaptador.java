package com.imls.maridoaluguel.Util;

import android.content.Context;
import android.os.TestLooperManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imls.maridoaluguel.Form.Servico;
import com.imls.maridoaluguel.R;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Servico> dados;

    public Adaptador(Context context, List<Servico> dados) {
        this.layoutInflater = LayoutInflater.from(context);
        this.dados = dados;
    }

    @NonNull
    @Override
    public Adaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view, viewGroup, false);
        ViewHolder  v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String a = dados.get(position).getAreaServico().name();
        holder.area.setText(a);
    }

    @Override
    public int getItemCount() {
        System.out.println(dados.size());
        return dados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nome, area, desc, nota;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

             nome = itemView.findViewById(R.id.viewNomeTVS);
             area = itemView.findViewById(R.id.viewAreaTVS);
             desc = itemView.findViewById(R.id.viewDescTVS);
             nota = itemView.findViewById(R.id.viewNotaTVS);
        }
    }
}

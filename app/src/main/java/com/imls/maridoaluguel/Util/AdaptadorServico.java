package com.imls.maridoaluguel.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imls.maridoaluguel.Banco.BancoDados;
import com.imls.maridoaluguel.Form.Servico;
import com.imls.maridoaluguel.Form.UsuarioDomestico;
import com.imls.maridoaluguel.R;

import java.text.ParseException;
import java.util.List;

public class AdaptadorServico extends RecyclerView.Adapter<AdaptadorServico.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Servico> dados;
    private BancoDados bd;

    public AdaptadorServico(Context context, List<Servico> dados) {
        this.bd = new BancoDados(context);
        this.layoutInflater = LayoutInflater.from(context);
        this.dados = dados;
    }

    @NonNull
    @Override
    public AdaptadorServico.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view_servicos, viewGroup, false);
        ViewHolder  v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UsuarioDomestico userDom = new UsuarioDomestico();

        userDom = bd.buscarDomesticoPorCdDomestico(dados.get(position).getIdDomestico());

        holder.area.setText(dados.get(position).getAreaServico().name());
        try {
            holder.nome.setText(bd.buscaNomePorIdDomestico(dados.get(position).getIdDomestico()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.desc.setText(dados.get(position).getDescServico());
        holder.nota.setText(converte(userDom.getAvaliacao()));
        holder.status.setText(dados.get(position).getStatusServico().name());
    }

    @Override
    public int getItemCount() {
        System.out.println(dados.size());
        return dados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nome, area, desc, nota, status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

             nome = itemView.findViewById(R.id.viewNomeTVS);
             area = itemView.findViewById(R.id.viewAreaTVS);
             desc = itemView.findViewById(R.id.viewDescTVS);
             nota = itemView.findViewById(R.id.viewNotaTVS);
             status = itemView.findViewById(R.id.viewStatusTVS);
        }
    }

    public String converte(Float a) {
        return a.toString();
    }

    public String converte(Integer a ) {
        return a.toString();
    }
}

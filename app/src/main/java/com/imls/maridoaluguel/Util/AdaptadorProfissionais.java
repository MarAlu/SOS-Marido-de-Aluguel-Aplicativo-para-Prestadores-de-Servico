package com.imls.maridoaluguel.Util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imls.maridoaluguel.Activity.TelaVisualizaProfissional;
import com.imls.maridoaluguel.Activity.TelaVisualizaServico;
import com.imls.maridoaluguel.Banco.BancoDados;
import com.imls.maridoaluguel.Form.UsuarioMarido;
import com.imls.maridoaluguel.R;

import java.text.ParseException;
import java.util.List;

public class AdaptadorProfissionais extends RecyclerView.Adapter<AdaptadorProfissionais.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<UsuarioMarido> dados;
    private BancoDados bd;

    public AdaptadorProfissionais(Context context, List<UsuarioMarido> dados) {
        this.bd = new BancoDados(context);
        this.layoutInflater = LayoutInflater.from(context);
        this.dados = dados;
    }

    @NonNull
    @Override
    public AdaptadorProfissionais.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view_profissionais, viewGroup, false);
        ViewHolder  v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String nomeMar = "";

        try {
            nomeMar = bd.buscaNomePorIdMarido(dados.get(position).getIdMarido());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.nome.setText(nomeMar);
        holder.desc.setText(dados.get(position).getDescHabilidade());
        holder.nota.setText(converte(dados.get(position).getAvaliacao()));
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nome, desc, nota;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent telaVisuProfi = new Intent(v.getContext(), TelaVisualizaProfissional.class);
                    Integer id = dados.get(getAdapterPosition()).getIdMarido();
                    telaVisuProfi.putExtra("idMarido", id.toString());

                    v.getContext().startActivity(telaVisuProfi);
                }
            });

            nome = itemView.findViewById(R.id.viewNomeTVS);
            desc = itemView.findViewById(R.id.viewDescTVS);
            nota = itemView.findViewById(R.id.viewNotaTVS);
        }
    }

    public String converte(Float a) {
        return a.toString();
    }

    public String converte(Integer a ) {
        return a.toString();
    }
}

package com.imls.maridoaluguel.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.Camera;

import com.imls.maridoaluguel.Business.Visualizacao;
import com.imls.maridoaluguel.Enum.Areas;
import com.imls.maridoaluguel.Enum.StatusServico;
import com.imls.maridoaluguel.Enum.StatusUsuario;
import com.imls.maridoaluguel.Enum.TipoServico;
import com.imls.maridoaluguel.Enum.TipoUsuario;
import com.imls.maridoaluguel.Form.Servico;
import com.imls.maridoaluguel.Form.Usuario;
import com.imls.maridoaluguel.Business.VerificaLogin;
import com.imls.maridoaluguel.Form.UsuarioDomestico;
import com.imls.maridoaluguel.Form.UsuarioMarido;

import java.text.ParseException;
import java.util.ArrayList;

public class BancoDados extends SQLiteOpenHelper {

    private static final int vesao_banco = 1;
    private static final String banco_sosmaridodealuguel = "bd_maridoss";

    //TABELAS
    private static final String tabela_usuario = "tb_user";
    private static final String tabela_marido = "tb_marido";
    private static final String tabela_domestico = "tb_domestico";
    private static final String tabela_marido_area = "tb_marido_area";
    private static final String tabela_servico = "tb_servico";
    private static final String tabela_logado = "tb_logado";
    private static final String tabela_ref_user = "tb_referencia_user";

    //TB_USER
    private static final String col_codigo = "idUser";
    private static final String col_nome = "nome";
    private static final String col_email = "email";
    private static final String col_cidade = "cidade";
    private static final String col_estado = "estado";
    private static final String col_fone = "fone";
    private static final String col_dataNasc = "dataNasc";
    private static final String col_senha = "senha";
    private static final String col_tipoUser = "tipoUser";
    private static final String col_ativo = "ativo";

    //TB_MARIDO
    private static final String col_codigo_in_mar = "idUserM";
    private static final String col_codigo_mar = "idMarido";
    private static final String col_habilidade = "descHabilidade";
    private static final String col_avaliacao_mar = "notaAvaliacao";
    private static final String col_servicos_rel = "servicosRealizados";

    //TB_DOMESTICO
    private static final String col_codigo_in_dom = "idUserD";
    private static final String col_codigo_dom = "idDomestico";
    private static final String col_avaliacao_dom = "notaAvaliacao";
    private static final String col_servicos_fin = "servicosFinalizados";

    //TB_MARIDO_AREAS
    private static final String col_codigo_mar_in_areas = "idMaridoA";
    private static final String col_codigo_area_mar = "idArea";
    private static final String col_area = "cdArea";

    //TB_SERVICO
    private static final String col_codigo_dom_in_serv = "idDomesticoS";
    private static final String col_codigo_mar_in_serv = "idMaridoS";
    private static final String col_codigo_servico = "idServico";
    private static final String col_descricao_serv = "descricaoServico";
    private static final String col_status_serv = "status";
    private static final String col_avaliacao_serv_mar = "notaAvaliacaoMar";
    private static final String col_avaliacao_serv_dom = "notaAvaliacaoDom";
    private static final String col_fone_domestico = "foneDomestico";
    private static final String col_tipo_servico = "tipoServico";


    //TB_LOGADO
    private static final String col_tipo_momento = "tipoUserMomento";
    private static final String col_id_momento = "idUserMomento";
    private static final String col_email_in_logado = "emailL";


    public BancoDados(Context context) {
        super(context, banco_sosmaridodealuguel, null, vesao_banco);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query_coluna_user = "create table " + tabela_usuario + "("
                + col_codigo + " integer primary key autoincrement, " + col_nome + " text, " + col_email + " text, "
                + col_cidade + " text, " + col_estado + " text, " + col_fone + " text, " + col_dataNasc + " text, "
                + col_senha + " text, " + col_tipoUser + " text, " + col_ativo + " text)";

        String query_coluna_mar = "create table " + tabela_marido + "("
                + col_codigo_mar + " integer primary key autoincrement, " + col_codigo_in_mar + " text, " + col_habilidade + " text, "
                + col_servicos_rel +" text, " + col_avaliacao_mar + " text)";

        String query_coluna_dom = "create table " + tabela_domestico + "("
                + col_codigo_dom + " integer primary key autoincrement, " + col_codigo_in_dom + " text, " + col_servicos_fin + " text, " + col_avaliacao_dom + " text)";

        String query_coluna_area = "create table " + tabela_marido_area + "("
                + col_codigo_area_mar + " integer primary key autoincrement, " + col_area + " text, " + col_codigo_mar_in_areas + " text)";

        String query_coluna_servico = "create table " + tabela_servico + "("
                + col_codigo_servico + " integer primary key autoincrement, " + col_status_serv + " text, " + col_codigo_dom_in_serv + " text, "
                + col_codigo_mar_in_serv + " text," + col_descricao_serv + " text, " + col_avaliacao_serv_dom + " text, "
                + col_avaliacao_serv_mar + " text, " + col_fone_domestico + " text, " + col_area + " text, " + col_tipo_servico + " text)";

        String query_coluna_referencia = "create table " + tabela_ref_user + "("
                + col_codigo + " text, " + col_codigo_dom + " text, " + col_codigo_mar + " text)";

        db.execSQL(query_coluna_user);
        db.execSQL(query_coluna_mar);
        db.execSQL(query_coluna_dom);
        db.execSQL(query_coluna_area);
        db.execSQL(query_coluna_servico);
        db.execSQL(query_coluna_referencia);

        System.out.println("CRIOU TABELAS");

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void dropBanco() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + tabela_usuario);
        db.execSQL("DROP TABLE IF EXISTS " + tabela_marido);
        db.execSQL("DROP TABLE IF EXISTS " + tabela_domestico);
        db.execSQL("DROP TABLE IF EXISTS " + tabela_marido_area);
        db.execSQL("DROP TABLE IF EXISTS " + tabela_servico);
        db.execSQL("DROP TABLE IF EXISTS " + tabela_ref_user);

        System.out.println("APAGOU TABELAS");

        onCreate(db);

    }

    ////////////////////////////////////////////////////////////////
    /*


    REFERENTE AO USARIO


    */
    ///////////////////////////////////////////////////////////////

    //INSERT USUARIO
    public Boolean addUser(Usuario user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(col_nome, user.getNome());
            values.put(col_email, user.getEmail());
            values.put(col_cidade, user.getCidade());
            values.put(col_estado, user.getEstado());
            values.put(col_fone, user.getFone());
            values.put(col_dataNasc, user.getDataNasc());
            values.put(col_senha, user.getSenha());
            values.put(col_tipoUser, user.getTipoUser().name());
            values.put(col_ativo, user.getAtivo().name());

            db.insert(tabela_usuario, null, values);
            db.close();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
        return false;
    }

    //APAGA USUARIO QUANDO DA ERRO
    public void apagarUsuario(Usuario user) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(tabela_usuario, col_codigo + " = ?", new String[]{String.valueOf(user.getId())});

        db.close();
    }

    //LISTAR TODOS OS USUARIO CADASTRADOS
    public ArrayList<Usuario> listaTodosUsuarios() throws ParseException {

        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "select * from " + tabela_usuario;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Usuario user = new Usuario();

                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setNome(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setCidade(cursor.getString(3));
                user.setEstado(cursor.getString(4));
                user.setFone(cursor.getString(5));
                user.setDataNasc(cursor.getString(6));
                user.setSenha(cursor.getString(7));
                user.setTipoUser(TipoUsuario.valueOf(cursor.getString(8)));
                user.setAtivo(StatusUsuario.valueOf(cursor.getString(9)));

                listaUsuarios.add(user);
            }
            while (cursor.moveToNext());
        }
        return listaUsuarios;
    }

    //BUSCAR USUARIO POR EMAIL
    public Usuario buscarUsuarioPorEmail(String email) {

        Usuario user = new Usuario();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tabela_usuario, new String[]{col_codigo, col_nome, col_email, col_cidade, col_estado,
                col_fone, col_dataNasc, col_senha, col_tipoUser, col_ativo},
                col_email + " = ?", new String[]{String.valueOf(email)},
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setNome(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setCidade(cursor.getString(3));
            user.setEstado(cursor.getString(4));
            user.setFone(cursor.getString(5));
            user.setDataNasc(cursor.getString(6));
            user.setSenha(cursor.getString(7));
            user.setTipoUser(TipoUsuario.valueOf(cursor.getString(8)));
            user.setAtivo(StatusUsuario.valueOf(cursor.getString(9)));



            db.close();
        }
        return user;
    }

    //VERIFICA LOGIN
    public VerificaLogin verificaLogin(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        VerificaLogin vf = null;

        Cursor cursor = db.query(tabela_usuario, new String[]{col_email, col_senha},
                col_email + " = ?", new String[]{String.valueOf(email)},
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            vf = new VerificaLogin(cursor.getString(0), cursor.getString(1));

            return vf;
        }
        return vf;
    }

    //VERIFICA SE EXISTE EMAIL CADASTRADO
    public Boolean verificaEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(tabela_usuario, new String[]{col_email},
                col_email + " = ?", new String[]{String.valueOf(email)},
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            return true;
        }
        return false;
    }

    //PESQUISA ID USER POR EMAIL
    public int pesquisaPorEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        int id = 0;

        Cursor cursor = db.query(tabela_usuario, new String[]{col_codigo, col_email},
                col_email + " = ?", new String[]{String.valueOf(email)},
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            return Integer.parseInt(cursor.getString(0));
        }
        return id;
    }

    //APAGA USUARIO (APENAS INATIVA)
    public void apagarUser(Usuario user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(col_ativo, user.getAtivo().name());

        db.update(tabela_usuario, values, user.getId() + " = ?", new String[]{String.valueOf(user.getId())});

        db.close();
    }

    //SELECIONA USUARIO POR CD
    public Usuario selecionarUser(int idUser) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        Usuario usuario = new Usuario();


        Cursor cursor = db.query(tabela_usuario, new String[]{col_nome, col_email, col_cidade, col_estado, col_fone, col_dataNasc},
                col_codigo + " = ?", new String[]{String.valueOf(idUser)},
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            usuario = new Usuario(cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));

            return usuario;
        }
        return usuario;
    }

    //ATUALIZA USUARIO
    public Boolean atualizaUsuario(int idU, Usuario user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(col_nome, user.getNome());
        values.put(col_email, user.getEmail());
        values.put(col_cidade, user.getCidade());
        values.put(col_estado, user.getEstado());
        values.put(col_fone, user.getFone());
        values.put(col_dataNasc, user.getDataNasc());
        values.put(col_senha, user.getSenha());
        values.put(col_tipoUser, user.getTipoUser().name());
        values.put(col_ativo, user.getAtivo().name());

        try {
            db.update(tabela_usuario, values, col_codigo + " = ?", new String[]{String.valueOf(idU)});
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    //CONTA USUÁRIOS
    public Boolean contaUsuarios() {

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "select count(*) from " + tabela_usuario;

        try {
            Cursor cursor = db.rawQuery(query, null);
            System.out.println(cursor.getColumnIndex(String.valueOf(0)));
        }
        catch (Exception e) {
            return false;
        }
        return true;

    }









    ////////////////////////////////////////////////////////////////
    /*


    REFERENTE AO USARIO DOMESTICO


    */
    ///////////////////////////////////////////////////////////////

    //INSERT DOMESTICO
    public Boolean addDomestico(int idUser) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(col_codigo_in_dom, idUser);
            values.put(col_avaliacao_dom, 0);
            values.put(col_servicos_fin, 0);

            db.insert(tabela_domestico, null, values);
            db.close();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
        return false;
    }

    //APAGA DOMESTICO
    public void apagarDomestico(int idUser) {
        SQLiteDatabase db = this.getWritableDatabase();

        //db.delete("tablename","id=? and name=?",new String[]{"1","jack"});

        db.delete(tabela_domestico, col_codigo_in_dom +" = ?", new String[]{String.valueOf(idUser)});

        db.close();
    }

    //BUSCAR USUARIO POR EMAIL
    public UsuarioDomestico buscarDomesticoPorCdUser(int idUser) {

        UsuarioDomestico userDom = new UsuarioDomestico();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tabela_domestico, new String[]{col_codigo_dom, col_codigo_in_dom, col_avaliacao_dom},
                col_codigo_in_dom + " = ?", new String[]{String.valueOf(idUser)},
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            userDom.setIdDomestico(Integer.parseInt(cursor.getString(0)));
            userDom.setIdUsuario(Integer.parseInt(cursor.getString(1)));
            userDom.setAvaliacao(Float.parseFloat(cursor.getString(2)));

            db.close();
        }
        return userDom;
    }

    //PESQUISA ID DOMESTICO POR CD USUARIO
    public int pesquisaIdDomesticoPorCodigo(int idUser) {
        SQLiteDatabase db = this.getWritableDatabase();

        int idDom = 0;

        Cursor cursor = db.query(tabela_domestico, new String[]{col_codigo_dom},
                col_codigo_in_dom + " = ?", new String[]{String.valueOf(idUser)},
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            return Integer.parseInt(cursor.getString(0));
        }
        return idDom;
    }

    //PESQUISA NOME COM ID DOMESTICO
    public String buscaNomePorIdDomestico(int idDom) throws ParseException {

        String nomeDom = "";
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "select "+ col_nome +" from "+ tabela_usuario +", "+ tabela_domestico +" where "+ col_codigo_dom +" = "+ idDom +" and "+ col_codigo_in_dom +" = "+ col_codigo;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        return nomeDom;
    }








    ////////////////////////////////////////////////////////////////
    /*


    REFERENTE AO USARIO MARIDO


    */
    ///////////////////////////////////////////////////////////////

    //INSERT MARIDO ALUGUEL
    public Boolean addMarido(int idUser, String descHab) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(col_codigo_in_mar, idUser);
            values.put(col_habilidade, descHab);
            values.put(col_servicos_rel, "0");
            values.put(col_avaliacao_mar, "0.0");

            db.insert(tabela_marido, null, values);
            db.close();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
        return false;
    }

    //ATUALIZA Marido
    public Boolean atualizaMarido(int idMar, String descHab) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(col_habilidade, descHab);

        try {
            db.update(tabela_marido, values, col_codigo_mar + " = ?", new String[]{String.valueOf(idMar)});
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    //INSERT MARIDO AREA
    public Boolean addAreaMarido(int idMarido, Areas dsArea) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(col_area, String.valueOf(dsArea));
            values.put(col_codigo_mar_in_areas, idMarido);

            db.insert(tabela_marido_area, null, values);
            db.close();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
        return false;
    }

    //BUSCAR AREA MARIDO POR CD MARIDO
    public UsuarioMarido buscarMaridoArea(int idMarido, int idUsuario, String descHab, int servReali, float avaliacao) {

        UsuarioMarido userMar = new UsuarioMarido();

        userMar.setIdMarido(idMarido);
        userMar.setIdUsuario(idUsuario);
        userMar.setDescHabilidade(descHab);
        userMar.setServicosRealizados(servReali);
        userMar.setAvaliacao(avaliacao);
        userMar.setAreaEletrica(Areas.A);
        userMar.setAreaEncanamento(Areas.A);
        userMar.setAreaPintura(Areas.A);
        userMar.setAreaAlvenaria(Areas.A);
        userMar.setAreaMarcenaria(Areas.A);
        userMar.setAreaOutros(Areas.A);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tabela_marido_area, new String[]{col_area},
                col_codigo_mar_in_areas + " = ?", new String[]{String.valueOf(idMarido)},
                null, null, null, null);

        if (cursor.moveToFirst()) {
             do {
                if(cursor.getString(0).equals("ELETRICA")) {
                    userMar.setAreaEletrica(Areas.valueOf(cursor.getString(0)));
                }
                if(cursor.getString(0).equals("ENCANAMENTO")) {
                    userMar.setAreaEncanamento(Areas.valueOf(cursor.getString(0)));
                }
                if(cursor.getString(0).equals("PINTURA")) {
                    userMar.setAreaPintura(Areas.valueOf(cursor.getString(0)));
                }
                if(cursor.getString(0).equals("ALVENARIA")) {
                    userMar.setAreaAlvenaria(Areas.valueOf(cursor.getString(0)));
                }
                if(cursor.getString(0).equals("MARCENARIA")) {
                    userMar.setAreaMarcenaria(Areas.valueOf(cursor.getString(0)));
                }
                if(cursor.getString(0).equals("OUTROS")) {
                    userMar.setAreaOutros(Areas.valueOf(cursor.getString(0)));
                }
            }
            while (cursor.moveToNext());
        }
        return userMar;
    }

    //APAGA MARIDO
    public void apagarMarido(int idUser) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(tabela_marido, col_codigo_in_mar +" = ?", new String[]{String.valueOf(idUser)});

        db.close();
    }

    //BUSCAR MARIDO POR CODIGO USER
    public UsuarioMarido buscarMaridoPorCdUser(int idUser) {

        UsuarioMarido userMar = new UsuarioMarido();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tabela_marido, new String[]{col_codigo_mar, col_codigo_in_mar, col_habilidade, col_servicos_rel,col_avaliacao_mar},
                col_codigo_in_mar + " = ?", new String[]{String.valueOf(idUser)},
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            userMar.setIdMarido(Integer.parseInt(cursor.getString(0)));
            userMar.setIdUsuario(Integer.parseInt(cursor.getString(1)));
            userMar.setDescHabilidade(cursor.getString(2));
            userMar.setServicosRealizados(Integer.parseInt(cursor.getString(3)));
            userMar.setAvaliacao(Float.parseFloat(cursor.getString(4)));

            db.close();
        }
        return userMar;
    }

    //PESQUISA ID MARIDO POR CD USUARIO
    public int pesquisaIdMaridoPorCodigo(int idUser) {
        SQLiteDatabase db = this.getWritableDatabase();

        int idMarido = 0;

        Cursor cursor = db.query(tabela_marido, new String[]{col_codigo_mar},
                col_codigo_in_mar + " = ?", new String[]{String.valueOf(idUser)},
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            return Integer.parseInt(cursor.getString(0));
        }
        return idMarido;
    }








    ////////////////////////////////////////////////////////////////
    /*


    REFERENTE AO SERVICO


    */
    ///////////////////////////////////////////////////////////////

    //INSERT SERVICO
    public Boolean addServico(Servico servico) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(col_status_serv, servico.getStatusServico().name());
            values.put(col_codigo_dom_in_serv, servico.getIdDomestico());
            values.put(col_codigo_mar_in_serv, servico.getIdMarido());
            values.put(col_descricao_serv, servico.getDescServico());
            values.put(col_avaliacao_serv_dom, 0);
            values.put(col_avaliacao_serv_mar, 0);
            values.put(col_fone_domestico, servico.getFoneDomestico());
            values.put(col_area, servico.getAreaServico().name());
            values.put(col_tipo_servico, servico.getTipoServico().name());

            db.insert(tabela_servico, null, values);
            db.close();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
        return false;
    }

    //ACEITE SERVICO
    public Boolean aceiteServico(Servico servico) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(col_status_serv, servico.getStatusServico().name());
            values.put(col_codigo_mar_in_serv, servico.getIdMarido());

            db.update(tabela_servico, values, col_codigo_servico + " = ?", new String[]{String.valueOf(servico.getIdServico())});
            db.close();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
        return false;
    }

    //RECUSA SERVICO
    public Boolean recusaServico(Servico servico) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(col_status_serv, servico.getStatusServico().name());
            values.put(col_codigo_dom_in_serv, servico.getIdDomestico());
            values.put(col_codigo_mar_in_serv, servico.getIdMarido());
            values.put(col_descricao_serv, servico.getDescServico());
            values.put(col_avaliacao_serv_dom, 0);
            values.put(col_avaliacao_serv_mar, 0);
            values.put(col_fone_domestico, servico.getFoneDomestico());
            values.put(col_area, servico.getAreaServico().name());

            db.update(tabela_servico, values, col_codigo_servico + " = ?", new String[]{String.valueOf(servico.getIdServico())});
            db.close();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
        return false;
    }

    //LISTAR TODOS OS SERVIÇOS CADASTRADOS
    public ArrayList<Servico> listaTodosServicos() throws ParseException {

        ArrayList<Servico> listaServicos = new ArrayList<Servico>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "select * from " + tabela_servico;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Servico serv = new Servico();

                serv.setIdServico(Integer.parseInt(cursor.getString(0)));
                serv.setStatusServico(StatusServico.valueOf(cursor.getString(1)));
                serv.setIdDomestico(Integer.parseInt(cursor.getString(2)));
                serv.setIdMarido(Integer.parseInt(cursor.getString(3)));
                serv.setDescServico(cursor.getString(4));
                serv.setNotaParaDomestico(Integer.parseInt(cursor.getString(5)));
                serv.setNotaParaMarido(Integer.parseInt(cursor.getString(6)));
                serv.setFoneDomestico(cursor.getString(7));
                serv.setAreaServico(Areas.valueOf(cursor.getString(8)));
                serv.setTipoServico(TipoServico.valueOf(cursor.getString(9)));

                listaServicos.add(serv);
            }
            while (cursor.moveToNext());
        }
        return listaServicos;
    }







    ////////////////////////////////////////////////////////////////
    /*


    REFERENTE AO MANTER LOGADO


    */
    ///////////////////////////////////////////////////////////////

    //INSERIR LOGADO
    public void addLogado(Visualizacao v) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        String query_coluna_logado = "create table " + tabela_logado + "("
                + col_tipo_momento + " text, " + col_id_momento + " text, " + col_email_in_logado + " text)";

        db.execSQL(query_coluna_logado);

        try {
            values.put(col_id_momento, v.getId());
            values.put(col_tipo_momento, v.getTipo());
            values.put(col_email_in_logado, v.getEmail());

            db.insert(tabela_logado, null, values);
            db.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
    }

    //BUSCA LOGADO
    public Boolean verificaLogado() {

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "select count(*) from " + tabela_logado;

        try {
            Cursor cursor = db.rawQuery(query, null);
        }
        catch (Exception e) {
            return false;
        }
        return true;

    }

    //BUSCA LOGADO
    public Visualizacao buscaLogado() {

        Visualizacao v = new Visualizacao();

        v.setTipo("");

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "select * from " + tabela_logado;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            v.setTipo(cursor.getString(0));
            v.setId(Integer.parseInt(cursor.getString(1)));
            v.setEmail(cursor.getString(2));
        }
        return v;
    }

    //ATUALIZA LOGADO
    public void atualizaLogado(Visualizacao v) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(col_tipo_momento, v.getTipo());
            values.put(col_id_momento, v.getId());

            db.update(tabela_logado, values, col_email_in_logado + " = ?", new String[]{String.valueOf(v.getEmail())});
            db.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }
    public void atualizaEmailLogado(Visualizacao v, String emailAnt) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(col_email, v.getEmail());

            db.update(tabela_logado, values, col_email_in_logado + " = ?", new String[]{String.valueOf(emailAnt)});
            db.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    //DESLOGAR
    public void deslogar() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + tabela_logado);

        db.close();
    }

}

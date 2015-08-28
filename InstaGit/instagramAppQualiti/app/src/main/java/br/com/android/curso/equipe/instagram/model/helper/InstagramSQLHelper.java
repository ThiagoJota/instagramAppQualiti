package br.com.android.curso.equipe.instagram.model.helper;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pitang.mobile.infraestrutura.abstracts.ISQLHelper;

public class InstagramSQLHelper implements ISQLHelper {
    public static final String NOME_COLUNA_ID_INSTAGRAM = "ID";
    public static final String NOME_COLUNA_IMAGEM = "IMAGEM";
    public static final String NOME_COLUNA_IMAGEM_MINIATURA = "IMAGEM_MINIATURA";
    public static final String NOME_COLUNA_TEXTO = "TEXTO";
    public static final String NOME_COLUNA_USUARIO_COMENTARIO = "USUARIO_COMENTARIO";
    public static final String NOME_COLUNA_ULTIMO_COMENTARIO = "ULTIMO_COMENTARIO";
    public static final String NOME_TABELA_INSTAGRAM = "INSTAGRAM";
    public static final String TAG = "INSTAGRAM";

    @Override
    public void criarTabelas(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder("");
        sql.append("CREATE TABLE ").append(NOME_TABELA_INSTAGRAM).append(" (");
        sql.append(NOME_COLUNA_ID_INSTAGRAM).append(" INTEGER NOT NULL, ");
        sql.append(NOME_COLUNA_IMAGEM).append(" TEXT NOT NULL, ");
        sql.append(NOME_COLUNA_IMAGEM_MINIATURA).append(" TEXT NOT NULL, ");
        sql.append(NOME_COLUNA_TEXTO).append(" TEXT NULL, ");
        sql.append(NOME_COLUNA_USUARIO_COMENTARIO).append(" TEXT NULL, ");
        sql.append(NOME_COLUNA_ULTIMO_COMENTARIO).append(" TEXT NULL, ");

        sql.append("PRIMARY KEY (").append(NOME_COLUNA_ID_INSTAGRAM).append(")");

        sql.append(");");

        Log.v(TAG, sql.toString());
        db.execSQL(sql.toString());

    }

    @Override
    public void atualizarTabelas(SQLiteDatabase db, int versaoAntiga, int novaVersao) {

    }

    @Override
    public String getNomeTabela() {
        return NOME_TABELA_INSTAGRAM;
    }
}

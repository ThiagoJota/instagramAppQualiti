package br.com.android.curso.equipe.instagram.model.repository;


import android.content.ContentValues;
import android.database.Cursor;

import com.pitang.mobile.infraestrutura.abstracts.AbstractRepository;

import java.util.List;

import br.com.android.curso.equipe.instagram.model.Instagram;

import static br.com.android.curso.equipe.instagram.model.helper.InstagramSQLHelper.NOME_COLUNA_ID_INSTAGRAM;
import static br.com.android.curso.equipe.instagram.model.helper.InstagramSQLHelper.NOME_COLUNA_IMAGEM;
import static br.com.android.curso.equipe.instagram.model.helper.InstagramSQLHelper.NOME_COLUNA_IMAGEM_MINIATURA;
import static br.com.android.curso.equipe.instagram.model.helper.InstagramSQLHelper.NOME_COLUNA_TEXTO;
import static br.com.android.curso.equipe.instagram.model.helper.InstagramSQLHelper.NOME_COLUNA_USUARIO_COMENTARIO;
import static br.com.android.curso.equipe.instagram.model.helper.InstagramSQLHelper.NOME_COLUNA_ULTIMO_COMENTARIO;
import static br.com.android.curso.equipe.instagram.model.helper.InstagramSQLHelper.NOME_TABELA_INSTAGRAM;


public class InstagramRepository extends AbstractRepository<Instagram> {

    public InstagramRepository() {
        super(NOME_TABELA_INSTAGRAM);
    }

    @Override
    protected Instagram criarObjeto(Cursor cursor) {
        Instagram instagram = new Instagram();
        instagram.setId(cursor.getInt(cursor.getColumnIndex(NOME_COLUNA_ID_INSTAGRAM)));
        instagram.setImagem(cursor.getString(cursor.getColumnIndex(NOME_COLUNA_IMAGEM)));
        instagram.setImagemMiniatura(cursor.getString(cursor.getColumnIndex(NOME_COLUNA_IMAGEM_MINIATURA)));
        instagram.setTexto(cursor.getString(cursor.getColumnIndex(NOME_COLUNA_TEXTO)));
        instagram.setUsuarioultimoComentario(cursor.getString(cursor.getColumnIndex(NOME_COLUNA_USUARIO_COMENTARIO)));
        instagram.setUltimoComentario(cursor.getString(cursor.getColumnIndex(NOME_COLUNA_ULTIMO_COMENTARIO)));
        return instagram;
    }

    @Override
    protected ContentValues criarContentValues(Instagram objeto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOME_COLUNA_ID_INSTAGRAM, objeto.getId());
        contentValues.put(NOME_COLUNA_IMAGEM, objeto.getImagem());
        contentValues.put(NOME_COLUNA_IMAGEM_MINIATURA, objeto.getImagemMiniatura());
        contentValues.put(NOME_COLUNA_TEXTO, objeto.getTexto());
        contentValues.put(NOME_COLUNA_USUARIO_COMENTARIO, objeto.getUsuarioultimoComentario());
        contentValues.put(NOME_COLUNA_ULTIMO_COMENTARIO, objeto.getUltimoComentario());
        return contentValues;
    }

    public List<Instagram> listar() {
        String sql = montarListar();
        return consultar(sql, null);
    }

    private String montarListar() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(NOME_COLUNA_ID_INSTAGRAM).append(", ");
        sql.append(NOME_COLUNA_IMAGEM).append(", ");
        sql.append(NOME_COLUNA_IMAGEM_MINIATURA).append(", ");
        sql.append(NOME_COLUNA_TEXTO).append(", ");
        sql.append(NOME_COLUNA_USUARIO_COMENTARIO).append(", ");
        sql.append(NOME_COLUNA_ULTIMO_COMENTARIO);
        sql.append(" FROM ").append(NOME_TABELA_INSTAGRAM);
        return sql.toString();
    }
}

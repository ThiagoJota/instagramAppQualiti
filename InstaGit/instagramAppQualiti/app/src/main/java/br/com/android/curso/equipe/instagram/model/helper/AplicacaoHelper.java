package br.com.android.curso.equipe.instagram.model.helper;

import android.content.Context;

import com.pitang.mobile.infraestrutura.abstracts.AbstractSQLHelper;

public class AplicacaoHelper extends AbstractSQLHelper {

    private static AplicacaoHelper aplicacaoHelper;
    private static final String NOME_BANCO = "INSTAGRAM";
    private static final int VERSAO_BANCO = 1;

    protected AplicacaoHelper(Context context) {
        super(NOME_BANCO, VERSAO_BANCO, context);
        adicionarTabelas();
    }

    private void adicionarTabelas() {
        addSqlHelper(new InstagramSQLHelper());
    }

    public static AplicacaoHelper inicializar(Context context){
        return aplicacaoHelper = new AplicacaoHelper(context);
    }

    public static AplicacaoHelper getInstancia(Context context){
        if(aplicacaoHelper == null){
            inicializar(context);
            return aplicacaoHelper;
        }else{
            return aplicacaoHelper;
        }
    }


}

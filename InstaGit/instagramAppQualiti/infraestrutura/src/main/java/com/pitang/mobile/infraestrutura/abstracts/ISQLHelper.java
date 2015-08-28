package com.pitang.mobile.infraestrutura.abstracts;

import android.database.sqlite.SQLiteDatabase;

public interface ISQLHelper {

    void criarTabelas(SQLiteDatabase db) ;

    void atualizarTabelas(SQLiteDatabase db, int versaoAntiga, int novaVersao);

    String getNomeTabela();
}

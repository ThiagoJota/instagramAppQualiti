package com.pitang.mobile.infraestrutura.abstracts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSQLHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;
    private final Context context;

    protected static List<ISQLHelper> sqlHelpers = new ArrayList<>();

    protected AbstractSQLHelper(String nomeBanco, Integer versaoBanco, Context context) {
        super(context, nomeBanco, null, versaoBanco);
        this.context = context;
    }

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        for (ISQLHelper sqlHelper : sqlHelpers) {
            sqlHelper.criarTabelas(database);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int versaoAntiga, int novaVersao) {
        for (ISQLHelper sqlHelper : sqlHelpers) {
            sqlHelper.atualizarTabelas(database, versaoAntiga, novaVersao);
        }
    }

    public void addSqlHelper(ISQLHelper isqlHelper) {
        if(isqlHelper != null){
            sqlHelpers.add(isqlHelper);
        }
    }

    public void abrirTransacao() {
        getDatabase().beginTransaction();
    }

    public void commitFecharTransacao() {
        commit();
        fecharTransacao();
    }

    public void commit() {
        getDatabase().setTransactionSuccessful();
    }

    public void fecharTransacao() {
        getDatabase().endTransaction();
    }

    public SQLiteDatabase getDatabase() {
        if(database == null){
            database = getWritableDatabase();
        }
        return database;
    }

}

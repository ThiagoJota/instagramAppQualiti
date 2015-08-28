package com.pitang.mobile.infraestrutura.util;

import android.content.ContentValues;
import android.database.sqlite.SQLiteStatement;

import org.apache.commons.lang.StringUtils;

import java.util.Set;
import java.util.TreeSet;

public class BulkSQLUtils {

    public static void bindValores(SQLiteStatement statement, ContentValues contentValues) {
        Set<String> chaves = new TreeSet<>(contentValues.keySet());
        int index = 1;

        for (String chave : chaves) {
            Object valor = contentValues.get(chave);

            if (valor == null) {
                statement.bindNull(index);

            } else if (valor instanceof String) {
                statement.bindString(index, (String) valor);

            } else if (valor instanceof Double || valor instanceof Float) {
                statement.bindDouble(index, Double.valueOf(String.valueOf(valor)));

            } else if (valor instanceof Integer || valor instanceof Long) {
                statement.bindLong(index, Long.valueOf(String.valueOf(valor)));
            } else if (valor instanceof byte[]) {
            statement.bindBlob(index, (byte[]) valor);
        }
            index++;
        }
    }

    public static String construirInserirSQL(String nomeTabela, ContentValues contentValues) {
        return construirBulkSQL("INSERT INTO", nomeTabela, contentValues);
    }

    public static String construirInserirOuAtualizarSQL(String nomeTabela, ContentValues contentValues) {
        return construirBulkSQL("INSERT OR REPLACE INTO", nomeTabela, contentValues);
    }

    private static String construirBulkSQL(String operacaoSQL, String nomeTabela, ContentValues contentValues) {
        int tamanho = contentValues.size();
        Set<String> chaves = new TreeSet<>(contentValues.keySet());

        StringBuilder sql = new StringBuilder(String.format("%s %s (", operacaoSQL, nomeTabela));
        sql.append(StringUtils.join(chaves, ","));

        sql.append(") VALUES(");

        for (int i = 0; i < tamanho; i++) {
            sql.append("?");

            if (i < tamanho - 1) {
                sql.append(",");
            }
        }

        sql.append(");");
        return sql.toString();
    }
}

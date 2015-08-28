package com.pitang.mobile.infraestrutura.abstracts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.pitang.mobile.infraestrutura.dominio.ObjetoPersistente;
import com.pitang.mobile.infraestrutura.exception.InfraException;
import com.pitang.mobile.infraestrutura.util.BulkSQLUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<T extends ObjetoPersistente> {

    private static final String TAG = "AbstractRepository";
    private static AbstractDataBaseAplicacao aplicacao;

    private AbstractSQLHelper sqLiteOpenHelper;

    private Context context;
    private String nomeTabela;

    protected AbstractRepository(String nomeTabela) {
        if(AbstractAplicacao.aplicacao == null || !(AbstractAplicacao.aplicacao instanceof AbstractDataBaseAplicacao)){
            throw new InfraException("Classe Application não estende AbstractDataBaseAplicacao.");
        }

        this.aplicacao = (AbstractDataBaseAplicacao) AbstractDataBaseAplicacao.aplicacao;
        this.context = aplicacao.getApplicationContext();
        this.sqLiteOpenHelper = aplicacao.getSQlHelper();
        this.nomeTabela = nomeTabela;
    }

    public Context getContext() {
        return context;
    }

    /**
     * Persiste os valores informados nos atributos do objeto no banco de dados da aplicação.
     *
     * @param objeto Objeto com valores a serem persistidos.
     * @return Chave primária do objeto.
     */
    public long salvar(T objeto) {
        ContentValues valores = criarContentValues(objeto);
        return getDB(false).insertOrThrow(getNomeTabela(), null, valores);
        //TODO poderia já configurar a chave no objeto passado, mas o que fazer quando a chave for composta?
    }

    /**
     * Altera os dados de um registro se já existir no banco de dados de aplicação, se não é inserido um novo registro.
     *
     * @param objeto Objeto com valores a serem persistidos.
     * @return Chave primária do objeto.
     */
    public long salvarSubstituir(T objeto) {
        ContentValues valores = criarContentValues(objeto);
        return getDB(false).replaceOrThrow(getNomeTabela(), null, valores);
        //TODO poderia já configurar a chave no objeto passado, mas o que fazer quando a chave for composta?
    }

    public void salvarSubstituirRapido(List<T> objetos) {
        if (objetos.size() > 0) {
            T primeiroObjeto = objetos.get(0);

            ContentValues contentValuesPrimeiroObjeto = criarContentValues(primeiroObjeto);

            String sql = BulkSQLUtils.construirInserirOuAtualizarSQL(getNomeTabela(), contentValuesPrimeiroObjeto);
            Log.v(TAG, sql);

            executarBulkQuery(sql, objetos);
        }
    }

    public void salvarRapido(List<T> objetos) {
        if (objetos.size() > 0) {
            T primeiroObjeto = objetos.get(0);

            ContentValues contentValuesPrimeiroObjeto = criarContentValues(primeiroObjeto);

            String sql = BulkSQLUtils.construirInserirSQL(getNomeTabela(), contentValuesPrimeiroObjeto);
            Log.v(TAG, sql);

            executarBulkQuery(sql, objetos);
        }
    }

    public void salvarAtualizar(T objeto, String where, String[] valoresWhere){
        ContentValues valores = criarContentValues(objeto);
        int linhasAlteradas = getDB(false).update(getNomeTabela(), valores, where, valoresWhere);
        if(linhasAlteradas == 0){
            getDB(false).insert(getNomeTabela(), null, valores);
        }
    }

    public void delete(){
        getDB(false).execSQL(" DELETE FROM " + getNomeTabela());
    }

    private void executarBulkQuery(String sqlQuery, List<T> objetos) {
        SQLiteStatement statement = getDB(false).compileStatement(sqlQuery);

        for (T objeto : objetos) {
            ContentValues contentValues = criarContentValues(objeto);

            statement.clearBindings();
            BulkSQLUtils.bindValores(statement, contentValues);
            statement.execute();
        }
    }

    /**
     * Altera os valores informados nos atributos do objeto no banco de dados da aplicação.
     *
     * @param objeto        Objeto com valores a serem alterados.
     * @param clausulaWhere Cláusula where a ser executada para encontrar as informações referentes ao objeto.
     * @param valoresWhere  Valores a serem utilizados na cláusula where.
     * @return Número de linhas afetada pela alteração.
     */
    public int alterar(T objeto, String clausulaWhere, String[] valoresWhere) {
        ContentValues valores = criarContentValues(objeto);
        return getDB(false).update(getNomeTabela(), valores, clausulaWhere, valoresWhere);
    }

    public int alterarPropriedade(ContentValues valores, String clausulaWhere, String[] valoresWhere) {
        return getDB(false).update(getNomeTabela(), valores, clausulaWhere, valoresWhere);
    }

    /**
     * Remove as informações encontradas pela cláusula where.
     *
     * @param clausulaWhere Cláusula where a ser executada para encontrar as informações referentes ao objeto.
     * @param valoresWhere  Valores a serem utilizados na cláusula where.
     * @return Número de linhas afetada pela remoção.
     */
    public int remover(String clausulaWhere, String[] valoresWhere) {
        return getDB(false).delete(getNomeTabela(), clausulaWhere, valoresWhere);
    }

    //TODO buscar e listarTodos

    public List<T> consultar(String query, String[] parametros) {
        Log.i(TAG, query);

        List<T> objetos = new ArrayList<>();
        Cursor cursor = getDB(true).rawQuery(query, parametros);
        if (cursor.moveToFirst()) {
            do {
                T objeto = criarObjeto(cursor);
                objetos.add(objeto);
            } while (cursor.moveToNext());
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        return objetos;
    }

    public T buscar(String query, String[] parametros) {
        Cursor cursor = getDB(true).rawQuery(query, parametros);
        T objeto = null;
        if (cursor.moveToFirst()) {
            do {
                objeto = criarObjeto(cursor);
            } while (cursor.moveToNext());
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        return objeto;
    }

    protected abstract T criarObjeto(Cursor cursor);

    protected abstract ContentValues criarContentValues(T objeto);

    /**
     * Recupera o nome da tabela utilizada pela entidade a ser manipulada.
     *
     * @return Nome da tabela da entidade manipulada.
     */
    private String getNomeTabela() {
        return this.nomeTabela;
    }

    protected SQLiteDatabase getDB(boolean somenteLeitura) {
        return (somenteLeitura) ? sqLiteOpenHelper.getReadableDatabase() : sqLiteOpenHelper.getWritableDatabase();
    }

    protected StringBuilder appendSelectAlias(StringBuilder stringBuilder, String alias) {
        if (alias != null) {
            stringBuilder.append(alias).append(".");
        }
        return stringBuilder;
    }

    public AbstractSQLHelper getSqLiteOpenHelper() {
        return sqLiteOpenHelper;
    }


}
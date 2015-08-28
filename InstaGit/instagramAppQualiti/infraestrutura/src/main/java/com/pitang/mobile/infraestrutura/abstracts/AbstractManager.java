package com.pitang.mobile.infraestrutura.abstracts;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.pitang.mobile.infraestrutura.R;
import com.pitang.mobile.infraestrutura.dominio.ObjetoPersistente;
import com.pitang.mobile.infraestrutura.exception.InfraException;
import com.pitang.mobile.infraestrutura.exception.NegocioException;

import java.util.List;

public abstract class AbstractManager<F extends ObjetoPersistente,T extends AbstractRepository> {

    private AbstractAplicacao aplicacao = AbstractAplicacao.aplicacao;
    private Context context;

    private T repository;
    private AbstractSQLHelper aplicacaoSQLHelper;

    public AbstractManager() {
        if(aplicacao == null){
            throw new InfraException("Classe Application não estende AbstractAplicacao.");
        }

        this.context = aplicacao.getApplicationContext();
        this.aplicacaoSQLHelper = getRepository().getSqLiteOpenHelper();
    }

    protected  void preSalvar(F objeto){
        //TODO implementar logica antes de executar o salvar
    }

    protected  void posSalvar(F objeto){
        //TODO implementar logica apos executar o salvar
    }

    private void preSalvar(List<F> objetos){
        for(F objeto : objetos){
            preSalvar(objeto);
        }
    }

    private void posSalvar(List<F> objetos){
        for(F objeto : objetos){
            posSalvar(objeto);
        }
    }

    protected abstract T newRepository();

    public T getRepository(){
        if(repository == null){
            repository = newRepository();
        }
        return repository;
    }

    public Long salvar(F objeto){
        preSalvar(objeto);
        Long id = null;
        try {
            getSQLHelper().abrirTransacao();
            id =  getRepository().salvar(objeto);
            getSQLHelper().commit();

        } catch (Exception e) {
            throw new NegocioException(getContext().getString(R.string.erro_inesperado),e);

        } finally {
            getSQLHelper().fecharTransacao();
        }

        posSalvar(objeto);
        return id;
    }

    public void salvarRapido(List<F> objetos){
        preSalvar(objetos);
        try {
            getSQLHelper().abrirTransacao();
            getRepository().salvarRapido(objetos);
            getSQLHelper().commit();

        } catch (Exception e) {
        Log.e("TAG","ERRO",e);
            throw new NegocioException(getContext().getString(R.string.erro_inesperado));

        } finally {
            getSQLHelper().fecharTransacao();
        }
        posSalvar(objetos);
    }

    public Long salvarSubstituir(F objeto){
        preSalvar(objeto);
        Long id = null;
        try {
            getSQLHelper().abrirTransacao();
            id = getRepository().salvarSubstituir(objeto);
            getSQLHelper().commit();

        } catch (Exception e) {
            Log.e("TAG","ERRO",e);
            throw new NegocioException(getContext().getString(R.string.erro_inesperado));

        } finally {
            getSQLHelper().fecharTransacao();
        }
        posSalvar(objeto);

        return id;
    }

    public void salvarSubstituirRapido(List<F> objetos){
        preSalvar(objetos);
        try {
            getSQLHelper().abrirTransacao();
            getRepository().salvarSubstituirRapido(objetos);
            getSQLHelper().commit();

        } catch (Exception e) {
            Log.e("AbstractManager","salvarSubstituirRapido",e);
            throw new NegocioException(getContext().getString(R.string.erro_inesperado));

        } finally {
            getSQLHelper().fecharTransacao();
        }
        posSalvar(objetos);
    }

    public void alterarPropriedade(ContentValues valores, String clausulaWhere, String[] valoresWhere){
        try {
            getSQLHelper().abrirTransacao();
            getRepository().alterarPropriedade(valores, clausulaWhere, valoresWhere);
            getSQLHelper().commit();

        } catch (Exception e) {
            throw new NegocioException(getContext().getString(R.string.erro_inesperado));

        } finally {
            getSQLHelper().fecharTransacao();
        }
    }

    public void alterar(F objeto, String clausulaWhere, String[] valoresWhere){
        try {
            getSQLHelper().abrirTransacao();
            getRepository().alterar(objeto, clausulaWhere, valoresWhere);
            getSQLHelper().commit();

        } catch (Exception e) {
            throw new NegocioException(getContext().getString(R.string.erro_inesperado));

        } finally {
            getSQLHelper().fecharTransacao();
        }
    }

    public void validarTextoVazio(String texto, String mensagemException){
        if(TextUtils.isEmpty(texto)){
            throw new NegocioException(mensagemException);
        }
    }

    public void validarListaVazia(List lista, String mensagemException){
        if(lista.isEmpty()){
            throw new NegocioException(mensagemException);
        }
    }

    public void validarListaNaoVazia(List lista, String mensagemException){
        if(!lista.isEmpty()){
            throw new NegocioException(mensagemException);
        }
    }

    public Context getContext() {
        return context;
    }

    public AbstractSQLHelper getSQLHelper() {
        return aplicacaoSQLHelper;
    }


}

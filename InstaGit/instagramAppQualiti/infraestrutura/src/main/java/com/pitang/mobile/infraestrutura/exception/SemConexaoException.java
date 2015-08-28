package com.pitang.mobile.infraestrutura.exception;

import android.content.Context;

import com.pitang.mobile.infraestrutura.R;

public class SemConexaoException extends NegocioException {

    public SemConexaoException(Context context) {
        super(context.getString(R.string.sem_conexao));
    }
}
package com.pitang.mobile.infraestrutura.exception;

import android.content.Context;

import com.pitang.mobile.infraestrutura.R;

public class BateriaBaixaException extends NegocioException {

    public BateriaBaixaException(Context context) {
        super(context.getString(R.string.bateria_fraca));
    }
}
package com.pitang.mobile.infraestrutura.abstracts;

import android.app.Application;
import android.content.Context;

public abstract class AbstractAplicacao extends Application {

    public static Context context;
    public static AbstractAplicacao aplicacao;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        this.aplicacao = this;
    }
}

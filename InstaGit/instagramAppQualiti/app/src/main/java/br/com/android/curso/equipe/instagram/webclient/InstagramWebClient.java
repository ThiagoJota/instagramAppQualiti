package br.com.android.curso.equipe.instagram.webclient;


import android.content.Context;

import com.pitang.mobile.infraestrutura.abstracts.AbstractWebClient;

import java.util.List;

import br.com.android.curso.equipe.instagram.BuildConfig;
import br.com.android.curso.equipe.instagram.model.Instagram;

public class InstagramWebClient extends AbstractWebClient<InstagramWebApi> {


    public InstagramWebClient(Context context) {
        super(context, InstagramWebApi.class);
    }

    @Override
    public String getBaseUrl() {
        return BuildConfig.BASE_URL;
    }

    public Instagram listar(){
        return getService().listar();
    }
}
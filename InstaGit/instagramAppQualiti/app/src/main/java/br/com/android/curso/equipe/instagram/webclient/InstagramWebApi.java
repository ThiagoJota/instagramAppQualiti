package br.com.android.curso.equipe.instagram.webclient;


import java.util.List;

import br.com.android.curso.equipe.instagram.model.Instagram;
import retrofit.http.GET;

public interface InstagramWebApi {

    @GET("/recent?access_token=212184892.5b9e1e6.25b47ade30f246cfb0840e8c8fcaa26a")
    public Instagram listar();
}

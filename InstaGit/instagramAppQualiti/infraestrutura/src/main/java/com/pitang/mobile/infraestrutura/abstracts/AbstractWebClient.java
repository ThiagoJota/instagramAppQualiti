package com.pitang.mobile.infraestrutura.abstracts;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public abstract class AbstractWebClient<T> {

    private final T service;

    protected Context context;

    public AbstractWebClient(Context context, Class<T> serviceClass) {
        this(context, serviceClass, null);
    }

    public AbstractWebClient(Context context, Class<T> serviceClass, ErrorHandler errorHandler) {
        this.context = context;

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);

        RestAdapter.Builder restBuilder = new RestAdapter.Builder()
                .setEndpoint(getBaseUrl())
                .setClient(new OkClient(okHttpClient))
                .setLogLevel(RestAdapter.LogLevel.FULL);

        if(getInteceptor() != null){
            restBuilder.setRequestInterceptor(getInteceptor());
        }

        if (errorHandler != null) {
            restBuilder.setErrorHandler(errorHandler);
        }

        RestAdapter restAdapter = restBuilder.build();
        this.service = restAdapter.create(serviceClass);
    }

    public RequestInterceptor getInteceptor(){
        return null;
    }

    public abstract String getBaseUrl();
    public T getService() {
        return service;
    }
}
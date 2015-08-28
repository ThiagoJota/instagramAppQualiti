package com.pitang.mobile.infraestrutura.util;

import android.app.Activity;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameAnimacao {

    private Activity activity;
    private Runnable runnable;

    private TypedArray typedArray;
    private List<Integer> drawablesIds;
    private ImageView imageView;

    private int contador;
    private int delay;

    private boolean oneShot;
    private boolean executar;
    private BitmapReused bitmapReused;

    public FrameAnimacao(Activity activity,int delay, ImageView imageView, int stringArray) {
        this(activity, false, delay, imageView, stringArray);
    }

    public FrameAnimacao(Activity activity,boolean oneShot,int delay, ImageView imageView,int stringArray) {
        this.activity = activity;
        this.typedArray = activity.getResources().obtainTypedArray(stringArray);
        this.drawablesIds = new ArrayList<>(this.typedArray.length());
        this.imageView = imageView;
        this.delay = delay;
        this.oneShot = oneShot;

        carregarDrawablesIds();
    }

    private void carregarDrawablesIds() {
        for (int i = 0; i < this.typedArray.length(); i++) {
            int resourceId = this.typedArray.getResourceId(i, -1);
            this.drawablesIds.add(resourceId);
        }
        typedArray.recycle();
    }

    public void iniciar(){
        executar = true;
        runnable = new Runnable() {

            @Override
            public void run() {
                while(executar) {

                    verificarOneShot();
                    if (contador < drawablesIds.size()) {
                        setImageView(contador, imageView);

                        contador++;
                    }

                    try {
                        Thread.sleep(delay);
                    }catch (InterruptedException e){

                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void parar(){
        executar = false;
    }

    private void verificarOneShot() {
        if(!oneShot && contador >= drawablesIds.size()){
            contador = 0;
            Collections.reverse(drawablesIds);
        }
    }

    private void setImageView(int contador, final ImageView imageView) {
        final int resourceId = drawablesIds.get(contador);

        criarBitmapReused(resourceId);

        if (resourceId >= 0) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmapReused.changeImage(resourceId));
                }
            });
        }
    }

    public void criarBitmapReused( @DrawableRes int resource){
        if(bitmapReused == null) {
            bitmapReused = new BitmapReused(activity, resource);
        }
    }
}

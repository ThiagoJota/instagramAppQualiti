package br.com.android.curso.equipe.instagram.view;


import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.pitang.mobile.infraestrutura.abstracts.ActivityRecursos;

import br.com.android.curso.equipe.instagram.R;
import br.com.android.curso.equipe.instagram.model.Instagram;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class DetalheInstagramActivityView extends ActivityRecursos {

    private static final int LAYOUT_ID = R.layout.activity_detalhe_instagram;

    @InjectView(R.id.imagem)
    ImageView imagem;

    @InjectView(R.id.usuarioComentario)
    TextView usuarioComentario;

    @InjectView(R.id.ultimoComentario)
    TextView ultimoComentario;

    public DetalheInstagramActivityView(AppCompatActivity activity) {
        super(activity);

        activity.setContentView(LAYOUT_ID);
        ButterKnife.inject(this, activity);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    public void preencherDetalheInstagram(Instagram instagram) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(instagram.getImagemMiniatura(), imagem, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Animation animation = AnimationUtils.loadAnimation(activity, R.anim.fade_in);
                view.startAnimation(animation);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });

        imagem.setImageURI(Uri.parse(instagram.getImagemMiniatura()));
        usuarioComentario.setText(instagram.getUsuarioultimoComentario());
        ultimoComentario.setText(instagram.getUltimoComentario());

    }
}

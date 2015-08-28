package br.com.android.curso.equipe.instagram.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

import br.com.android.curso.equipe.instagram.R;
import br.com.android.curso.equipe.instagram.model.Instagram;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class ListaInstagramAdapter extends BaseAdapter {

    private List<Instagram> instagrams;
    private Context context;

    public ListaInstagramAdapter(List<Instagram> instagrams, Context context) {
        this.instagrams = instagrams;
        this.context = context;
    }

    @Override
    public int getCount() {
        return instagrams.size();
    }

    @Override
    public Object getItem(int posicao) {
        return instagrams.get(posicao);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.instagram_item, viewGroup, false);
            viewHolder = new ViewHolder();
            ButterKnife.inject(viewHolder, view);
            view.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) view.getTag();

        Instagram instagram = (Instagram) getItem(posicao);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(instagram.getImagemMiniatura(), viewHolder.imagemMiniatura, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
                view.startAnimation(animation);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
        viewHolder.imagemMiniatura.setImageURI(Uri.parse(instagram.getImagemMiniatura()));
        viewHolder.texto.setText(instagram.getTexto());


        return view;
    }

    class ViewHolder{

        @InjectView(R.id.imagemMiniatura)
        ImageView imagemMiniatura;

        @InjectView(R.id.texto)
        TextView texto;
    }
}

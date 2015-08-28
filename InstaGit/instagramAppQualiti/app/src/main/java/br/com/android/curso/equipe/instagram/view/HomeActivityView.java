package br.com.android.curso.equipe.instagram.view;


import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;

import com.pitang.mobile.infraestrutura.abstracts.ActivityRecursos;

import br.com.android.curso.equipe.instagram.R;
import br.com.android.curso.equipe.instagram.adapter.ListaInstagramAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivityView extends ActivityRecursos {

    private static final int LAYOUT_ID = R.layout.activity_home;

    @InjectView(R.id.helloWorld)
    TextView helloWorld;

    @InjectView(R.id.listaInstagrams)
    ListView listaInstagrams;

    public HomeActivityView(FragmentActivity activity) {
        super(activity);
        activity.setContentView(LAYOUT_ID);
        ButterKnife.inject(this, activity);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        activity.getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void alterarHelloWorld(String mensagem){
        helloWorld.setText(mensagem);
    }

    public void esconderHelloWord(){
        helloWorld.setVisibility(View.GONE);
    }

    public void configurarListaInstagram(ListaInstagramAdapter adapter){
        listaInstagrams.setAdapter(adapter);
    }

    public void onListaInstagramClick(View view, Animation.AnimationListener animationListener) {
        Animation animation = AnimationUtils.loadAnimation(activity, R.anim.fade);
        animation.setAnimationListener(animationListener);
        view.startAnimation(animation);
    }
}

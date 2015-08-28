package br.com.android.curso.equipe.instagram.view.presenter;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;

import com.pitang.mobile.infraestrutura.abstracts.ActivityRecursos;
import com.pitang.mobile.infraestrutura.util.SharePreferenceUtils;

import java.util.List;

import br.com.android.curso.equipe.instagram.Aplicacao;
import br.com.android.curso.equipe.instagram.R;
import br.com.android.curso.equipe.instagram.adapter.ListaInstagramAdapter;
import br.com.android.curso.equipe.instagram.model.Instagram;
import br.com.android.curso.equipe.instagram.model.manager.InstagramManager;
import br.com.android.curso.equipe.instagram.view.HomeActivityView;
import br.com.android.curso.equipe.instagram.view.activity.DetalheInstagramActivity;

public class HomeActivityPresenter extends ActivityRecursos {

    private HomeActivityView view;
    private InstagramManager instagramManager;

    public HomeActivityPresenter(FragmentActivity activity) {
        super(activity);
        view = new HomeActivityView(activity);
        configurarHelloWorld();
        instagramManager = new InstagramManager();
    }

    private void configurarHelloWorld() {
        SharePreferenceUtils utils = Aplicacao.preferenceUtils;
        if(utils.getBoolean("primeiraVez", true)){
            view.alterarHelloWorld(activity.getString(R.string.ola_amigo));
            utils.put("primeiraVez", false);
        }else{
            view.esconderHelloWord();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return view.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.baixar:
                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        List<Instagram> instagrams = inserirInstagrams().getInstagrams();
                        if(instagrams != null && !instagrams.isEmpty()){
                            deletarInstagram();
                            salvarRapidoInstagram(instagrams);
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        super.onPostExecute(o);
                        preencherListaInstagram();
                    }
                };
                asyncTask.execute(null);
                break;

        }
        return true;
    }

    public void salvarRapidoInstagram(List<Instagram> instagrams){
        instagramManager.salvarRapido(instagrams);
    }


    public Instagram inserirInstagrams() {
        return instagramManager.listarWs();
    }

    public void deletarInstagram(){
        instagramManager.deletarInstagram();
    }

    public void preencherListaInstagram() {
        view.configurarListaInstagram(new ListaInstagramAdapter(instagramManager.listar(), activity));
    }

    public void onListaInstagramsClick(final View view, final Instagram instagram) {
        this.view.onListaInstagramClick(view, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent it = new Intent(activity, DetalheInstagramActivity.class);
                it.putExtra("instagram", instagram);
                activity.startActivity(it);
                activity.overridePendingTransition(R.anim.right_left, R.anim.up_down);
                animation.setAnimationListener(null);
                view.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

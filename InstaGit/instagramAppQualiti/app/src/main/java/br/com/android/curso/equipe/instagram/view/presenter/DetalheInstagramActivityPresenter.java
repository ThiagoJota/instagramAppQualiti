package br.com.android.curso.equipe.instagram.view.presenter;


import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.pitang.mobile.infraestrutura.abstracts.ActivityRecursos;

import br.com.android.curso.equipe.instagram.R;
import br.com.android.curso.equipe.instagram.model.Instagram;
import br.com.android.curso.equipe.instagram.view.DetalheInstagramActivityView;

public class DetalheInstagramActivityPresenter extends ActivityRecursos {

    private DetalheInstagramActivityView view;

    private Instagram instagram;

    public DetalheInstagramActivityPresenter(AppCompatActivity activity) {
        super(activity);

        view = new DetalheInstagramActivityView(activity);

        instagram = (Instagram) activity.getIntent().getParcelableExtra("instagram");

        preencherDetalheInstagram(instagram);
    }


    public void preencherDetalheInstagram(Instagram instagram){
        view.preencherDetalheInstagram(instagram);
    }


    public boolean onOptionItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                activity.finish();
                activity.overridePendingTransition(R.anim.down_up, R.anim.left_right);
                return true;
        }
        return false;
    }
}

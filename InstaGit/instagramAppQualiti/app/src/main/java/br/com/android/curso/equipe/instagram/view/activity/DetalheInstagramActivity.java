package br.com.android.curso.equipe.instagram.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import br.com.android.curso.equipe.instagram.R;
import br.com.android.curso.equipe.instagram.view.presenter.DetalheInstagramActivityPresenter;

public class DetalheInstagramActivity extends AppCompatActivity {

    private DetalheInstagramActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DetalheInstagramActivityPresenter(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        return presenter.onOptionItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.down_up, R.anim.left_right);
    }
}

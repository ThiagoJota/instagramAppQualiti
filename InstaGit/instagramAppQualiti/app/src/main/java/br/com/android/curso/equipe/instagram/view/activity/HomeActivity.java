package br.com.android.curso.equipe.instagram.view.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import br.com.android.curso.equipe.instagram.R;
import br.com.android.curso.equipe.instagram.model.Instagram;
import br.com.android.curso.equipe.instagram.view.presenter.HomeActivityPresenter;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class HomeActivity extends AppCompatActivity {

    private HomeActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomeActivityPresenter(this);
        ButterKnife.inject(this);

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                presenter.salvarRapidoInstagram(presenter.inserirInstagrams().getInstagrams());
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                presenter.preencherListaInstagram();
            };
        };
        asyncTask.execute(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return presenter.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item);
    }

    @OnItemClick(R.id.listaInstagrams)
    public void onListaInstagramsClick(AdapterView<?> adapterView, View view, int position, long id){
        Instagram instagram = (Instagram) adapterView.getItemAtPosition(position);
        presenter.onListaInstagramsClick(view, instagram);
    }
}

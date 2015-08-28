package br.com.android.curso.equipe.instagram.model.manager;


import com.pitang.mobile.infraestrutura.abstracts.AbstractManager;

import java.util.List;

import br.com.android.curso.equipe.instagram.model.Instagram;
import br.com.android.curso.equipe.instagram.model.repository.InstagramRepository;
import br.com.android.curso.equipe.instagram.webclient.InstagramWebClient;

public class InstagramManager extends AbstractManager<Instagram, InstagramRepository> {

    InstagramWebClient instagramWebClient;

    public InstagramManager(){
        instagramWebClient = new InstagramWebClient(getContext());
    }

    @Override
    protected InstagramRepository newRepository() {
        return new InstagramRepository();
    }

    public List<Instagram> listar(){
        return getRepository().listar();
    }

    public Instagram listarWs(){
        return instagramWebClient.listar();
    }

    public void salvarRapido(List<Instagram> instagrams) {
        getRepository().salvarSubstituirRapido(instagrams);
    }

    public void deletarInstagram() {
        getRepository().delete();
    }
}

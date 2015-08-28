package br.com.android.curso.equipe.instagram.model;


import android.os.Parcel;

import com.google.gson.annotations.JsonAdapter;
import com.pitang.mobile.infraestrutura.dominio.ObjetoPersistente;

import java.util.List;

import br.com.android.curso.equipe.instagram.adapter.PostsJsonAdapter;

@JsonAdapter(PostsJsonAdapter.class)
public class Instagram extends ObjetoPersistente {

    private Integer id;
    private String imagem;
    private String imagemMiniatura;
    private String texto;
    private String usuarioultimoComentario;
    private String ultimoComentario;

    private List<Instagram> instagrams;


    public List<Instagram> getInstagrams() {
        return instagrams;
    }

    public void setInstagrams(List<Instagram> instagrams) {
        this.instagrams = instagrams;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagemMiniatura() {
        return imagemMiniatura;
    }

    public void setImagemMiniatura(String imagemMiniatura) {
        this.imagemMiniatura = imagemMiniatura;
    }

    public String getUsuarioultimoComentario() {
        return usuarioultimoComentario;
    }

    public void setUsuarioultimoComentario(String usuarioultimoComentario) {
        this.usuarioultimoComentario = usuarioultimoComentario;
    }

    public String getUltimoComentario() {
        return ultimoComentario;
    }

    public void setUltimoComentario(String ultimoComentario) {
        this.ultimoComentario = ultimoComentario;
    }

    public Instagram() {
    }

    public Instagram (String imagem, String imagemMiniatura, String texto, String usuarioultimoComentario, String ultimoComentario){
        this.imagem = imagem;
        this.imagemMiniatura = imagemMiniatura;
        this.texto = texto;
        this.usuarioultimoComentario = usuarioultimoComentario;
        this.ultimoComentario = ultimoComentario;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.imagem);
        dest.writeString(this.imagemMiniatura);
        dest.writeString(this.texto);
        dest.writeString(this.usuarioultimoComentario);
        dest.writeString(this.ultimoComentario);
        dest.writeTypedList(instagrams);
    }

    protected Instagram(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.imagem = in.readString();
        this.imagemMiniatura = in.readString();
        this.texto = in.readString();
        this.usuarioultimoComentario = in.readString();
        this.ultimoComentario = in.readString();
        this.instagrams = in.createTypedArrayList(Instagram.CREATOR);
    }

    public static final Creator<Instagram> CREATOR = new Creator<Instagram>() {
        public Instagram createFromParcel(Parcel source) {
            return new Instagram(source);
        }

        public Instagram[] newArray(int size) {
            return new Instagram[size];
        }
    };
}

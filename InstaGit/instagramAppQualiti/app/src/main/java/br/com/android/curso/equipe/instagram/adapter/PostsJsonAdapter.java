package br.com.android.curso.equipe.instagram.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.android.curso.equipe.instagram.model.Instagram;


public class PostsJsonAdapter extends TypeAdapter<Instagram> {
    @Override
    public void write(JsonWriter out, Instagram value) throws IOException {

    }

    @Override
    public Instagram read(JsonReader in) throws IOException {
        Instagram instagramGeral = new Instagram();
        List<Instagram> instagrams = new ArrayList<>();
        in.beginObject();

        while (in.hasNext()) {
            switch (in.nextName()) {
                case "data":
                    in.beginArray();
                    while (in.hasNext()) {
                        Instagram instagram = new Instagram();
                        in.beginObject();
                        while (in.hasNext()) {
                            switch (in.nextName()) {
                                case "images":
                                    buscarImagem(instagram, in);
                                    break;
                                case "caption":
                                    buscarCaption(instagram, in);
                                    break;
                                case "comments":
                                    buscarComentario(instagram, in);
                                    break;

                                default:
                                    in.skipValue();
                            }
                        }
                        in.endObject();
                        instagrams.add(instagram);
                    }
                    in.endArray();
                    break;

                default:
                    in.skipValue();
            }
        }

        in.endObject();
        instagramGeral.setInstagrams(instagrams);
        return instagramGeral;
    }

    private void buscarCaption(Instagram instagram, JsonReader in) throws IOException {
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "text":
                    instagram.setTexto(in.nextString());
                    break;
                default:
                    in.skipValue();
            }
        }
        in.endObject();
    }

    private void buscarComentario(Instagram instagram, JsonReader in) throws IOException {
        in.beginObject();
        while (in.hasNext()){
            switch (in.nextName()){
                case "data":
                    in.beginArray();
                    while (in.hasNext()) {
                        in.beginObject();
                        while (in.hasNext()) {
                            switch (in.nextName()) {
                                case "text":
                                    instagram.setUltimoComentario(in.nextString());
                                    break;
                                case "from":
                                    in.beginObject();
                                    while (in.hasNext()) {
                                        switch (in.nextName()) {
                                            case "username":
                                                instagram.setUsuarioultimoComentario(in.nextString());
                                                break;
                                            default:
                                                in.skipValue();
                                        }
                                    }
                                    in.endObject();
                                    break;
                                default:
                                    in.skipValue();
                            }
                        }
                        in.endObject();

                        while (in.hasNext()){
                            in.skipValue();
                        }
                    }
                    in.endArray();
                    break;

                default:
                    in.skipValue();
            }
        }
        in.endObject();
    }

    public void buscarImagem(Instagram instagram, JsonReader in) throws IOException {
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "low_resolution":
                    instagram.setImagem(buscarImagemUrl(in));
                    break;
                case "thumbnail":
                    instagram.setImagemMiniatura(buscarImagemUrl(in));
                    break;

                default:
                    in.skipValue();
            }
        }
        in.endObject();
    }

    private String buscarImagemUrl(JsonReader in) throws IOException {
        String resultado = "";
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "url":
                    resultado = in.nextString();
                    break;

                default:
                    in.skipValue();
            }
        }
        in.endObject();
        return resultado;
    }
}
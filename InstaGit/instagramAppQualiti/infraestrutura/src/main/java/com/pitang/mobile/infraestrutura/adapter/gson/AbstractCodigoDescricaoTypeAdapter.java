package com.pitang.mobile.infraestrutura.adapter.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCodigoDescricaoTypeAdapter extends TypeAdapter<Integer> {

    protected static Map<Integer, String> opcoes = new HashMap<Integer, String>();

    @Override
    public void write(JsonWriter out, Integer value) throws IOException {
        out.beginObject();
        out.name(getNomeAtributo());
        out.value(value);
        out.endObject();
    }

    @Override
    public Integer read(JsonReader in) throws IOException {
        String dscricao = in.nextString();
        return getCodigoPorDescricao(dscricao);
    }

    public static int getCodigoPorDescricao(String descricao) {
        int codigo = -1;

        for (Map.Entry<Integer, String> entry : opcoes.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(descricao)) {
                codigo = entry.getKey();
            }
        }

        return codigo;
    }

    public static String getDescricaoPorCodigo(int codigo) {
        String descricao = null;

        for (Map.Entry<Integer, String> entry : opcoes.entrySet()) {
            if (entry.getKey() == (codigo)) {
                descricao = entry.getValue();
            }
        }

        return descricao;
    }

    protected abstract String getNomeAtributo();
}
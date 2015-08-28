package com.pitang.mobile.infraestrutura.util;

public final class NumeroUtils {

    public static boolean isNumero(String valor) {
        String valorSemFormatacao = valor.replace(".", "").replace(",", "");

        try {
            Long.valueOf(valorSemFormatacao);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static String preencherComZerosEsquerda(int valor, int tamanhoFinal) {
        return String.format("%0" + tamanhoFinal + "d", valor);
    }
}
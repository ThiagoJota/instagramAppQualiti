package com.pitang.mobile.infraestrutura.util;

import android.content.Context;
import android.net.ConnectivityManager;

public final class NetworkUtils {

    public static boolean estaConectado(Context context) {
        return estaConectadoWifi(context) || estaConectadoMobile(context);
    }

    public static boolean estaConectadoWifi(Context context) {
        return estaConectado(context, ConnectivityManager.TYPE_WIFI);
    }

    public static boolean estaConectadoMobile(Context context) {
        return estaConectado(context, ConnectivityManager.TYPE_MOBILE);
    }

    private static boolean estaConectado(Context context, int tipoConexao) {
        boolean estaConectado = false;
        ConnectivityManager manager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (manager != null) {
            switch (tipoConexao) {
                case ConnectivityManager.TYPE_WIFI:
                    estaConectado = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    estaConectado = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
                    break;
                default:
                    estaConectado = manager.getActiveNetworkInfo().isConnected();
            }
        }
        return estaConectado;
    }

}

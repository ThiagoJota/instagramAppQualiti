package com.pitang.mobile.infraestrutura.util;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pitang.mobile.infraestrutura.R;

public class ToastUtil {
    private static ToastUtil instancia = new ToastUtil();
    private static Toast toast;

    public static void setInstance(ToastUtil toastUtil){
        instancia = toastUtil;
    }

    public static  void preto(String texto, Context context, int gravity){
        instancia.show(texto, R.color.infra_preto, context, gravity);
    }

    public static  void laranja(String texto, Context context, int gravity){
        instancia.show(texto, R.color.infra_laranja, context, gravity);
    }

    public static  void branco(String texto, Context context, int gravity){
        instancia.show(texto, R.color.infra_branco, context, gravity);
    }
    public void show(String texto,int color, Context context, int gravity){
        if(toast != null){
            toast.cancel();
        }

        toast = criarToast(texto,color, context, gravity);
        toast.show();
    }

    private static Toast criarToast(String texto,  int cor,Context context,int gravity){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.toast, null);
        TextView text = (TextView) layout.findViewById(R.id.toastTexto);


        LinearLayout toastLayout = (LinearLayout) layout.findViewById(R.id.toast);
        GradientDrawable background = (GradientDrawable) toastLayout.getBackground();
        background.setColor(context.getResources().getColor(cor));

        text.setText(texto);
        Toast toast = new Toast(context);
        toast.setGravity(gravity, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setMargin(0, 0);
        return toast;
    }

    public static Toast getToast() {
        return toast;
    }
}

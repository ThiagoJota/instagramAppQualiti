package com.pitang.mobile.infraestrutura.util;

import android.app.Activity;
import android.content.Intent;

public class IntentUtils {

    public static void intent(Activity activity, Class clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        ((Activity) activity).finish();
    }
}

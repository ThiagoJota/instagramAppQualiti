package com.pitang.mobile.infraestrutura.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class FragmentUtils {

    public static void replaceFragment(FragmentActivity activity, Fragment fragment, int idLayout) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(idLayout, fragment).commit();
    }

}

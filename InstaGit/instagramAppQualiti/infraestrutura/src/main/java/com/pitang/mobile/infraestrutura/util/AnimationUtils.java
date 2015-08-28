package com.pitang.mobile.infraestrutura.util;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class AnimationUtils {

    public static Animation rotacionarAoCentro(float degreeFrom, float degreeTo, long duration){
        RotateAnimation animation = new RotateAnimation(
                degreeFrom,
                degreeTo,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        animation.setFillEnabled(true);

        return animation;
    }
}

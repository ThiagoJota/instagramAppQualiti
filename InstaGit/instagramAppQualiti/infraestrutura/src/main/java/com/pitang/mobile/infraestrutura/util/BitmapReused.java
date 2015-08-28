package com.pitang.mobile.infraestrutura.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;

public class BitmapReused {
    private static final Integer sampleSize = 1;
    private BitmapFactory.Options options = new BitmapFactory.Options();
    private Context context;
    private Bitmap bitmap;

    public BitmapReused(Context context, @DrawableRes int resource){
        this.context = context;
        setUpBitmap(resource);

    }

    private void setUpBitmap(int resource){
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resource, options);
        bitmap = Bitmap.createBitmap(options.outWidth, options.outHeight, Bitmap.Config.ARGB_8888);
        options.inJustDecodeBounds = false;
        options.inBitmap = bitmap;
        options.inSampleSize = sampleSize;
    }

    public Bitmap changeImage( @DrawableRes int resource){
        BitmapFactory.decodeResource(context.getResources(), resource, options);
        return bitmap;
    }

}

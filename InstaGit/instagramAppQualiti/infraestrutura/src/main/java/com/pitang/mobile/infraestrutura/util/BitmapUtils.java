package com.pitang.mobile.infraestrutura.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtils {

    public static Bitmap toBitmap(Context context,byte[] bytes,int placeHolderResourceId){
        Bitmap decodedByte = null;
        if(bytes != null) {
            decodedByte = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }else{
            decodedByte = BitmapFactory.decodeResource(context.getResources(),placeHolderResourceId);
        }

        return decodedByte;
    }
}

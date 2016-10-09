package com.saroshmadara.root.premiotravelsandtours.Helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by root on 10/9/16.
 */
public class Methods {

    public static void contactUs(Context context){
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        Uri uri = Uri.parse("tel:0300-0773646");
        callIntent.setData(uri);
        context.startActivity(callIntent);
    }
}

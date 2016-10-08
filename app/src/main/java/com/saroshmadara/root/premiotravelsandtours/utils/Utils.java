package com.saroshmadara.root.premiotravelsandtours.utils;

import android.content.Context;
import android.content.res.TypedArray;

import com.saroshmadara.root.premiotravelsandtours.R;

/**
 * Created by root on 10/9/16.
 */
public class Utils {
    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    public static int getTabsHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.tabsHeight);
    }
}

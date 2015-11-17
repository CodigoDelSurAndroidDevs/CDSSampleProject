package com.codigodelsur.androidsampleproject.util;

import android.databinding.BindingAdapter;
import android.widget.TextView;

/**
 * Created by marcosambrosi on 11/17/15.
 */
public class Bindings {

    @BindingAdapter({"bind:font"})
    public static void setFont(TextView textView, String fontName) {
        textView.setTypeface(FontCache.getInstance().get(fontName));
    }
}

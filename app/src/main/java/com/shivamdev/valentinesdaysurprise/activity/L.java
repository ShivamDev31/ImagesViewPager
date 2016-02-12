package com.shivamdev.valentinesdaysurprise.activity;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by shivamchopra on 11/02/16.
 */
public class L {

    public static final String TAG = L.class.getSimpleName();

    public static void t(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void l(String text){
        l(TAG, text);
    }

    public static void l(String tag, String text) {
        Log.d(tag, text);
    }

}

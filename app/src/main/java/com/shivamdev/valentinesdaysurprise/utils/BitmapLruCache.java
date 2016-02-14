package com.shivamdev.valentinesdaysurprise.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

import com.shivamdev.valentinesdaysurprise.activity.L;

/**
 * Created by shivamchopra on 12/02/16.
 */
public class BitmapLruCache extends LruCache<String, Bitmap> {

    private static final String TAG = BitmapLruCache.class.getName();

    private static BitmapLruCache bitmapCache;


    private BitmapLruCache() {
        this(getDefaultLruCacheSize());
    }

    public BitmapLruCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }

    public static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        return cacheSize;
    }

    public static BitmapLruCache getInstance() {
        if (bitmapCache == null) {
            bitmapCache = new BitmapLruCache();
        }
        return bitmapCache;
    }


    public Bitmap getBitmap(String key, Resources resources, int imageInt) {
        if (get(key) == null) {
            L.l(TAG, "create new  bitmap");
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = BitmapFactory.decodeResource(resources, imageInt, opt);
            put(key, bitmap);
        }
        L.l(TAG, "load from cachce");
        return get(key);
    }


    @Override
    protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);
        L.l(TAG, "entry removed: " + evicted + " : " + key + " : " + oldValue + " : " + newValue);
        if (evicted) {
            oldValue.recycle();
        }
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getByteCount() / 1024;
    }

}
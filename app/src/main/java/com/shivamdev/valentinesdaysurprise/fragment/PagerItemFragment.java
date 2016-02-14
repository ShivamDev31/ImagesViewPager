package com.shivamdev.valentinesdaysurprise.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shivamdev.valentinesdaysurprise.R;
import com.shivamdev.valentinesdaysurprise.utils.BitmapLruCache;

import java.util.ArrayList;

/**
 * Created by shivamchopra on 12/02/16.
 */
public class PagerItemFragment extends Fragment {

    private static final String IMAGES_KEY = "images";
    private static final String POS_KEY = "pos";
    private static final String IMAGE_KEY = "image_drawable";
    private ArrayList<Integer> images;

    private int imageDrawable;
    private int pos;

    public static PagerItemFragment newInstance(int imageDrawable) {
        PagerItemFragment fragment = new PagerItemFragment();

        Bundle args = new Bundle();
        //args.putIntegerArrayList(IMAGES_KEY, images);
        //args.putInt(POS_KEY, position);
        args.putInt(IMAGE_KEY, imageDrawable);

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_item_fragment, container, false);
        //images = getArguments().getIntegerArrayList(IMAGES_KEY);
        //pos = getArguments().getInt(POS_KEY);
        imageDrawable = getArguments().getInt(IMAGE_KEY);
        ImageView ivImage = (ImageView) view.findViewById(R.id.iv_images);
        BitmapLruCache cache = BitmapLruCache.getInstance();
        Bitmap image = cache.getBitmap(getResources().getResourceEntryName(imageDrawable), getResources(), imageDrawable);
        ivImage.setImageBitmap(image);
        return view;
    }
}

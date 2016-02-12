package com.shivamdev.valentinesdaysurprise.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shivamdev.valentinesdaysurprise.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivamchopra on 12/02/16.
 */
public class ContentFragment extends Fragment {


    private static final String TAG = ContentFragment.class.getSimpleName();
    private static final String POS_KEY = "pos";
    private static final String TITLE_KEY = "title";

    private int pos;
    private String title;
    private int[] images;

    private List<Integer> imagesArray = new ArrayList<>();

    private TextView tvTitle;
    private ViewPager vpImage;

    public static ContentFragment newInstance(int position, String title) {

        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();

        args.putInt(POS_KEY, position);
        args.putString(TITLE_KEY, title);

        fragment.setArguments(args);

        return fragment;
    }

    private void getImagesArray(int position) {

        imagesArray.clear();

        if(position == 0) {
            for (int i = 0; i < 4; i++) {
                imagesArray.add(getResources().getIdentifier("dubai_atlantis" + (i + 1), "drawable", getActivity().getPackageName()));
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_content_fragment, container, false);

        pos = getArguments().getInt(POS_KEY);
        title = getArguments().getString(TITLE_KEY);

        getImagesArray(pos);


        tvTitle = (TextView) view.findViewById(R.id.tv_header_text);

        tvTitle.setText(title);
        vpImage = (ViewPager) view.findViewById(R.id.vp_pager);

        ImagePagerAdapter pagerAdapter = new ImagePagerAdapter(getChildFragmentManager());
        vpImage.setAdapter(pagerAdapter);

        return view;
    }

    public class ImagePagerAdapter extends FragmentPagerAdapter {
        public ImagePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PagerItemFragment.newInstance(imagesArray.get(position));
        }

        @Override
        public int getCount() {
            return imagesArray.size();
        }
    }
}

package edu.cdp.cooker.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class FenleiAtapter extends PagerAdapter {
    private ArrayList<View> flviews;

    @Override
    public int getCount() {
        return flviews.size ();
    }

    public FenleiAtapter(ArrayList<View> viewLists){
        this.flviews = viewLists;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(flviews.get(position));
        return flviews.get(position);
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView (flviews.get(position));
    }
}

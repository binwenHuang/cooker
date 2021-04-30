package edu.cdp.cooker.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class DinnerAdapter extends PagerAdapter {
    private ArrayList<View> diviews;

    @Override
    public int getCount() {
        return diviews.size ();
    }

    public DinnerAdapter(ArrayList<View> viewLists){
        this.diviews = viewLists;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(diviews.get(position));
        return diviews.get(position);
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView (diviews.get(position));
    }
}

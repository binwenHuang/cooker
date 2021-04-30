package edu.cdp.cooker.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import edu.cdp.cooker.Adapter.FenleiAtapter;
import edu.cdp.cooker.R;
import edu.cdp.cooker.view.Fenleicaishi;
import edu.cdp.cooker.view.Fenleihongpei;
import edu.cdp.cooker.view.Fenleiremeng;
import edu.cdp.cooker.view.Fenleiroulei;
import edu.cdp.cooker.view.Fenleishucai;

public class FenleiActivity extends BaseActivity{
    private ViewPager flpagers;
    private Fenleishucai fenleishucai;
    private Fenleiroulei fenleiroulei;
    private Fenleiremeng fenleiremeng;
    private Fenleihongpei fenleihongpei;
    private Fenleicaishi fenleicaishi;
    private TextView flshucai, flroulei, flremeng, flhongpei, flcaishi;


    @Override
    protected void intViews() {
        setTitleVisiable("分类");
        getView();
        flsetStatus ( 0 );
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_fenlei;
    }

    private void getView() {
        flremeng = findViewById ( R.id.fl_remeng );
        flshucai = findViewById ( R.id.fl_shucai );
        flhongpei = findViewById ( R.id.fl_hongpei );
        flcaishi = findViewById ( R.id.fl_caishi );
        flroulei = findViewById ( R.id.fl_roulei );
        flpagers = findViewById ( R.id.fl_pager );
        fenleiremeng = new Fenleiremeng ( this );
        View flv1 = fenleiremeng.getView ();
        fenleishucai = new Fenleishucai ( this );
        View flv2 = fenleishucai.getView ();
        fenleihongpei = new Fenleihongpei ( this );
        View flv3 = fenleihongpei.getView ();
        fenleicaishi = new Fenleicaishi ( this );
        View flv4 = fenleicaishi.getView ();
        fenleiroulei = new Fenleiroulei ( this );
        View flv5 = fenleiroulei.getView ();

        ArrayList<View> flviews = new ArrayList<View> ();
        flviews.add ( flv1 );
        flviews.add ( flv2 );
        flviews.add ( flv3 );
        flviews.add ( flv4 );
        flviews.add ( flv5 );
        FenleiAtapter fenleiAdapter = new FenleiAtapter ( flviews );
        flpagers.setAdapter ( fenleiAdapter );
        flpagers.addOnPageChangeListener ( new ViewPager.OnPageChangeListener () {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                flsetStatus ( position );
                Log.i ( "", "onPageSelected: " + position );
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }

        } );
        flpagers.setCurrentItem ( 0 );
    }

    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.fl_remeng:
                flpagers.setCurrentItem ( 0 );
                flsetStatus ( 0 );
                break;
            case R.id.fl_shucai:
                flpagers.setCurrentItem ( 1 );
                flsetStatus ( 1 );
                break;
            case R.id.fl_hongpei:
                flpagers.setCurrentItem ( 2 );
                flsetStatus ( 2 );
                break;

            case R.id.fl_caishi:
                flpagers.setCurrentItem ( 3 );
                flsetStatus ( 3 );
                break;

            case R.id.fl_roulei:
                flpagers.setCurrentItem ( 4 );
                flsetStatus ( 4 );
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.recai:
                Intent intent = new Intent(FenleiActivity.this,JiachangcaiActivity.class);
                startActivity(intent);
                break;

        }
    }

    private void flsetStatus(int i) {
        flclearstatus ();
        switch (i) {
            case 0:
                flremeng.setTextColor ( Color.parseColor ( "#DB5860" ) );
                flremeng.setTextSize ( 18 );
                flremeng.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 1:
                flshucai.setTextColor ( Color.parseColor ( "#DB5860" ) );
                flshucai.setTextSize ( 18 );
                flshucai.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 2:
                flhongpei.setTextColor ( Color.parseColor ( "#DB5860" ) );
                flhongpei.setTextSize ( 18 );
                flhongpei.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 3:
                flcaishi.setTextColor ( Color.parseColor ( "#DB5860" ) );
                flcaishi.setTextSize ( 18 );
                flcaishi.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 4:
                flroulei.setTextColor ( Color.parseColor ( "#DB5860" ) );
                flroulei.setTextSize ( 18 );
                flroulei.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
        }

    }

    private void flclearstatus() {
        flremeng.setTextColor ( Color.parseColor ( "#000000" ) );
        flremeng.setTextSize ( 16 );
        flremeng.setBackgroundColor(Color.parseColor("#F1EFE9"));

        flroulei.setTextColor ( Color.parseColor ( "#000000" ) );
        flroulei.setTextSize ( 16 );
        flroulei.setBackgroundColor(Color.parseColor("#F1EFE9"));

        flcaishi.setTextColor ( Color.parseColor ( "#000000" ) );
        flcaishi.setTextSize ( 16 );
        flcaishi.setBackgroundColor(Color.parseColor("#F1EFE9"));

        flhongpei.setTextColor ( Color.parseColor ( "#000000" ) );
        flhongpei.setTextSize ( 16 );
        flhongpei.setBackgroundColor(Color.parseColor("#F1EFE9"));

        flshucai.setTextColor ( Color.parseColor ( "#000000" ) );
        flshucai.setTextSize ( 16 );
        flshucai.setBackgroundColor(Color.parseColor("#F1EFE9"));
    }
}
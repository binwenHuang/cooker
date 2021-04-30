package edu.cdp.cooker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.cdp.cooker.R;

public class JiachangcaiActivity extends BaseActivity{


    @Override
    protected void intViews() {
        setTitleVisiable("家常菜");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_jiachangcai ;
    }
}
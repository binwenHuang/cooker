package edu.cdp.cooker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.cdp.cooker.R;

public class ZuixintuijianActivity extends BaseActivity {


    @Override
    protected void intViews() {
        setTitleVisiable("最新菜谱");
    }

    @Override
    protected int getLayout() {
        return  R.layout.activity_zuixintuijian;
    }
}
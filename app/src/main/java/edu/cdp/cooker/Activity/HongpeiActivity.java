package edu.cdp.cooker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.cdp.cooker.R;

public class HongpeiActivity extends BaseActivity{



    @Override
    protected void intViews() {
        setTitleVisiable("烘焙");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_hongpei;
    }
}
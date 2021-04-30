package edu.cdp.cooker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.cdp.cooker.R;

public class XiafancaiActivity extends BaseActivity {


    @Override
    protected void intViews() {
        setTitleVisiable("下饭菜");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_xiafancai;
    }
}
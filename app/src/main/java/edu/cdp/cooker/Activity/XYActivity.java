package edu.cdp.cooker.Activity;

import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import edu.cdp.cooker.R;


public class XYActivity extends BaseActivity{
    private TextView tvXY;

    @Override
    protected void intViews() {
        setTitleVisiable("服务条款");
        TextView mytext = (TextView)findViewById(R.id.xy_content);
        String myname = getString(R.string.privacy_policy);
        Spanned textspan = Html.fromHtml(myname);
        mytext.setText(textspan);
        mytext.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_xy;
    }
}
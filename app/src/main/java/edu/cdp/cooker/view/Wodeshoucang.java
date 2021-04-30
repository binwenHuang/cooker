package edu.cdp.cooker.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import edu.cdp.cooker.R;

public class Wodeshoucang {
    private Context context;
    private LayoutInflater myli;

    public  Wodeshoucang(Context context){
        this.context = context;
        this.myli = LayoutInflater.from(context);

    }
    public View getView(){
        return myli.inflate( R.layout.wd_shoucang,null);
    }

}

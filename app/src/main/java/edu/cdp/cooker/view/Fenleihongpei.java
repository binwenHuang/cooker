package edu.cdp.cooker.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import edu.cdp.cooker.R;

public class Fenleihongpei {
    private Context context;
    private LayoutInflater myli;

    public  Fenleihongpei(Context context){
        this.context = context;
        this.myli = LayoutInflater.from(context);

    }
    public View getView(){
        return myli.inflate( R.layout.fl_hongpei,null);
    }

}

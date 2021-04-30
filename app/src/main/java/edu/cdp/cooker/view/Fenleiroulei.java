package edu.cdp.cooker.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import edu.cdp.cooker.R;

public class Fenleiroulei {
    private Context context;
    private LayoutInflater myli;

    public  Fenleiroulei(Context context){
        this.context = context;
        this.myli = LayoutInflater.from(context);

    }
    public View getView(){
        return myli.inflate( R.layout.fl_roulei,null);
    }

}

package edu.cdp.cooker.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import edu.cdp.cooker.R;

public class Shouyecaixi {
    private Context context;
    private LayoutInflater myli;

    public  Shouyecaixi(Context context){
        this.context = context;
        this.myli = LayoutInflater.from(context);

    }
    public View getView(){
        return myli.inflate( R.layout.sy_caixi,null);
    }

}

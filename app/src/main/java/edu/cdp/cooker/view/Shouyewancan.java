package edu.cdp.cooker.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import edu.cdp.cooker.R;


public class Shouyewancan {
    private Context context;
    private LayoutInflater myli;

    public  Shouyewancan(Context context){
        this.context = context;
        this.myli = LayoutInflater.from(context);

    }
    public View getView(){
        return myli.inflate( R.layout.sy_wancan,null);
    }

}

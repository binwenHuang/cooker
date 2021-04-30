package edu.cdp.cooker.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import edu.cdp.cooker.R;

public class Shipingshucai {
    private Context context;
    private LayoutInflater spmyli;

    public  Shipingshucai(Context context){
        this.context = context;
        this.spmyli = LayoutInflater.from(context);

    }
    public View getView(){
        return spmyli.inflate( R.layout.sp_shucai,null);
    }

}

package edu.cdp.cooker.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import edu.cdp.cooker.R;

public class Shipinghongpei {
    private Context context;
    private LayoutInflater spmyli;

    public  Shipinghongpei(Context context){
        this.context = context;
        this.spmyli = LayoutInflater.from(context);

    }
    public View getView(){
        return spmyli.inflate( R.layout.sp_hongpei,null);
    }

}

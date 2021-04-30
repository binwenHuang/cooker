package edu.cdp.cooker.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import edu.cdp.cooker.R;

public class Shipingjianfei {
    private Context context;
    private LayoutInflater spmyli;

    public  Shipingjianfei(Context context){
        this.context = context;
        this.spmyli = LayoutInflater.from(context);

    }
    public View getView(){
        return spmyli.inflate( R.layout.sp_jianfeican,null);
    }

}

package edu.cdp.cooker.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.cdp.cooker.MyApplication;
import edu.cdp.cooker.R;
import edu.cdp.cooker.utils.ConstentUtils;
import edu.cdp.cooker.utils.Params;
import edu.cdp.cooker.utils.URLConnection;
import edu.cdp.cooker.utils.VolleyInterface;
import edu.cdp.cooker.utils.VolleyRequest;
import edu.cdp.cooker.view.Loading_view;


public class LoadingActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "1";
    private Button btn_loading;
    private TextView pswfoeget,Xy,edt_zhuce;
    private TextView editText_id,editText_psw;
    private CheckBox cb;
    private URLConnection urlConnection;
    private ImageView ivpg;

    String id_user = null;
    String psw_user =null;


    boolean flag = true;


    private Loading_view loading_view;

    private AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        //判断上次的登录
        setPerfrence();

        //实验
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.tv_user);
        initAutoComplete("history", autoCompleteTextView);
        findViewById(R.id.loading_btn).getBackground().setAlpha(180);
        initView();
    }




    private void initView(){
        editText_id = findViewById(R.id.tv_user);
        editText_psw = findViewById(R.id.edit_psw);

        ivpg = findViewById(R.id.iv_pg);

        //获得Intent
        Intent intent = this.getIntent();
        //从Intent获得额外信息，设置为TextView的文本
        editText_id.setText(intent.getStringExtra("id"));
        editText_psw.setText(intent.getStringExtra("pwd"));

        cb = findViewById(R.id.tv_notice);

        btn_loading=findViewById(R.id.loading_btn);
        btn_loading.setOnClickListener(this);

        pswfoeget=findViewById(R.id.psw_forget);
        pswfoeget.setOnClickListener(this);

        edt_zhuce=findViewById(R.id.zhuce_tv);
        edt_zhuce.setOnClickListener(this);

        Xy=findViewById(R.id.tv_XY);
        Xy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.loading_btn:
//                getImage();
//                getCacheImg();
                loading(view);
                break;
            case R.id.tv_XY:
                Intent intent_xy = new Intent(LoadingActivity.this,XYActivity.class);
                startActivity(intent_xy);
                cb.setChecked(true);
                break;
            case R.id.psw_forget:
                Intent intent_forget=new Intent(LoadingActivity.this, ForgetActivity.class);
                startActivityForResult(intent_forget,1111);
                break;
            case R.id.zhuce_tv:
                Intent intent_zhuce = new Intent(LoadingActivity.this, RegisterActivity.class);
                startActivity(intent_zhuce);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }





    //登录方法
    private void loading(View view) {
        loading_view = new Loading_view(this, R.style.CustomDialog);

        if (cb.isChecked()){
            Log.i(TAG, "loading: 调用验证");

            //获取文本框输入内容
            id_user = editText_id.getText().toString();
            psw_user = editText_psw.getText().toString();

            if (id_user.length() !=0  && psw_user.length() !=0) {
                //显示等待登录的弹窗
                loading_view.show();

                //连接服务器验证密码
                netLoading(id_user,psw_user);

            }else {
                Toast.makeText(LoadingActivity.this,"用户名或密码不能为空",Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(LoadingActivity.this,"请勾选同意《服务条款》",Toast.LENGTH_LONG).show();
        }
    }


    private void netLoading(final String id_user, String psw_user){
        VolleyInterface vif = null;
        Log.i(TAG, "netLoading: "+id_user);
        VolleyRequest.requestPost(ConstentUtils.LOGINAPI, "login", Params.login(id_user, psw_user),
                new VolleyInterface(LoadingActivity.this,VolleyInterface.listener,VolleyInterface.errorListener,VolleyInterface.imageListener) {
                    @Override
                    public void onMysuccess(String result) {
                        loading_view.dismiss();
                        if (result.indexOf("error")!= -1){
                            Toast.makeText(LoadingActivity.this,"用户名密码错误",Toast.LENGTH_LONG).show();;
                        }
                        else {
                            Toast.makeText(LoadingActivity.this,"登录成功",Toast.LENGTH_LONG).show();;
                            setZhuangtai();
                            saveHistory("history", autoCompleteTextView);
                            Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                            startActivity(intent);

                        }
//                        Toast.makeText(LoadingActivity.this,result,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onMyerror(VolleyError error) {
                        loading_view.dismiss();
                        Toast.makeText(LoadingActivity.this,"服务器请求失败",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onImageSuccese(Bitmap bitmap) {

                    }
                });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getHttpQueue().cancelAll("login");
    }

    //保存登录历史
    private void saveHistory(String field, AutoCompleteTextView autoCompleteTextView) {
        String text = autoCompleteTextView.getText().toString();
        SharedPreferences sp = getSharedPreferences("network_url", 0);
        String longhistory = sp.getString(field, "nothing");
        if (!longhistory.contains(text + ",")) {
            StringBuilder sb = new StringBuilder(longhistory);
            sb.insert(0, text + ",");
            sp.edit().putString("history", sb.toString()).commit();
        }
    }
    private void initAutoComplete(String field, AutoCompleteTextView autoCompleteTextView) {
        SharedPreferences sp = getSharedPreferences("network_url", 0);
        String longhistory = sp.getString("history", "nothing");
        String[] histories = longhistory.split(",");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, histories);
        // 只保留最近的50条的记录
        if (histories.length > 50) {
            String[] newHistories = new String[50];
            System.arraycopy(histories, 0, newHistories, 0, 50);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, newHistories);
        }
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) { AutoCompleteTextView view = (AutoCompleteTextView) v;
                        if (hasFocus) {
                            view.showDropDown();
                        }
                    }
        });
    }


    //创建一个文件，并保存一个值，判断是否第一次打开
    private void setPerfrence(){

//                //清空文件
//        SharedPreferences sp=getSharedPreferences("loding_success",MODE_PRIVATE);
//        if(sp!=null) {
//            sp.edit().clear().commit();
//            Toast.makeText(LoadingActivity.this, "数据已清空", Toast.LENGTH_LONG).show();
//        }

        SharedPreferences setting = getSharedPreferences("loding_success", 0);
        Boolean user_first = setting.getBoolean("success",false);

        if(user_first){
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
            //Toast.makeText(welcomeActivity.this, "上次登录过", Toast.LENGTH_LONG).show();
        }
    }


    //设置状态文件，用于判断登录状态
    private void setZhuangtai(){
        SharedPreferences setting = getSharedPreferences("loding_success", 0);
        setting.edit().putBoolean("success", true).commit();
    }


}
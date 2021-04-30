package edu.cdp.cooker.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.Random;

import edu.cdp.cooker.R;
import edu.cdp.cooker.utils.ConstentUtils;
import edu.cdp.cooker.utils.Params;
import edu.cdp.cooker.utils.VolleyInterface;
import edu.cdp.cooker.utils.VolleyRequest;
import edu.cdp.cooker.view.Loading_view;


public class ForgetActivity extends BaseActivity implements View.OnClickListener {
    EditText edt_yanzhen,edt_id,newPwd;
    TextView tv_yz;
    private Loading_view loading_view;
    private Button btn_put;


    @Override
    protected void intViews() {
        loading_view = new Loading_view(this, R.style.CustomDialog);
        tv_yz= findViewById(R.id.yanzhen);
        //生成随机数
        int flag = new Random().nextInt(999999);
        for (int i = 0; i <100; i++){
            if (flag < 100000) {
                flag += 100000;
            }
        }
        tv_yz.setText(""+flag);
        btn_put = findViewById(R.id.putup);

        setTitleVisiable("忘记密码");

        //设置监听
        btn_put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yanZhen();
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_forget;
    }

    //验证判断，保存新密码
    private void yanZhen(){
        edt_yanzhen = findViewById(R.id.yanzhen_input);
        edt_id= findViewById(R.id.user_id);
        String id_input = edt_id.getText().toString().trim();

        newPwd = findViewById(R.id.pwd_new);
        String new_pwd = newPwd.getText().toString().trim();

        String yanzhen_give = tv_yz.getText().toString().trim();
        String yanzhen_input = edt_yanzhen.getText().toString().trim();

        if(yanzhen_give.equals(yanzhen_input)){
            loading_view.show();
            netUpdate(id_input,new_pwd);

        }else {
                Toast.makeText(ForgetActivity.this, "账户或验证码有误!!!", Toast.LENGTH_LONG).show();
        }

    }


    private void netUpdate(final String id_user, final String psw_user){
        VolleyInterface vif = null;
        Log.i("", "regist账户 ："+id_user+"......密码："+psw_user);
        VolleyRequest.requestPost(ConstentUtils.UPDATEAPI, "login", Params.login(id_user, psw_user),
                new VolleyInterface(ForgetActivity.this,VolleyInterface.listener,VolleyInterface.errorListener,VolleyInterface.imageListener) {
                    @Override
                    public void onMysuccess(String result) {
                        loading_view.dismiss();
                        if (result.indexOf("error")!= -1){
                            Toast.makeText(ForgetActivity.this,"请求失败，请检查用户名是否正确",Toast.LENGTH_LONG).show();;
                        }
                        else {
                            Toast.makeText(ForgetActivity.this,"修改成功",Toast.LENGTH_LONG).show();;
                            Intent intent = new Intent(ForgetActivity.this, LoadingActivity.class);
                            intent.putExtra("id",id_user);
                            intent.putExtra("pwd", psw_user);
                            startActivity(intent);
                            finish();

                        }

                    }

                    @Override
                    public void onMyerror(VolleyError error) {
                        loading_view.dismiss();
                        Toast.makeText(ForgetActivity.this,"服务器请求失败",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onImageSuccese(Bitmap bitmap) {

                    }
                });
    }

}
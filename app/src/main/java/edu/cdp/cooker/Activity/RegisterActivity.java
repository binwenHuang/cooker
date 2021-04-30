package edu.cdp.cooker.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import edu.cdp.cooker.R;
import edu.cdp.cooker.utils.ConstentUtils;
import edu.cdp.cooker.utils.Params;
import edu.cdp.cooker.utils.VolleyInterface;
import edu.cdp.cooker.utils.VolleyRequest;
import edu.cdp.cooker.view.Loading_view;


public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    EditText username;
    EditText password;
    Button register;
    private Loading_view loading_view;

    @Override
    protected void intViews() {
        username=(EditText) findViewById(R.id.edt_user);
        password=(EditText) findViewById(R.id.edt_pass);
        register=(Button) findViewById(R.id.Register);

        loading_view = new Loading_view(this, R.style.CustomDialog);
        setTitleVisiable("新用户注册");

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name=username.getText().toString().trim();
                String pass=password.getText().toString().trim();
                if (name.length() !=0  && password.length() !=0) {
                    loading_view.show();
                    netRegister(name, pass);
                }
                else {
                    Toast.makeText(RegisterActivity.this, "账户和密码不能为空！！！", Toast.LENGTH_LONG).show();
                }


            }
        });
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_zhuce;
    }


    private void netRegister(final String id_user, final String psw_user){
        VolleyInterface vif = null;
        Log.i("", "regist账户 ："+id_user+"......密码："+psw_user);
        VolleyRequest.requestPost(ConstentUtils.REGISTEAPI, "login", Params.login(id_user, psw_user),
                new VolleyInterface(RegisterActivity.this,VolleyInterface.listener,VolleyInterface.errorListener,VolleyInterface.imageListener) {
                    @Override
                    public void onMysuccess(String result) {
                        loading_view.dismiss();
                        if (result.equals("FAILE")){
                            Toast.makeText(RegisterActivity.this,"用户名密码错误！",Toast.LENGTH_LONG).show();;
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegisterActivity.this,LoadingActivity.class);
                            intent.putExtra("id",id_user);
                            intent.putExtra("pwd", psw_user);
                            startActivity(intent);
                            finish();

                        }

                    }

                    @Override
                    public void onMyerror(VolleyError error) {
                        loading_view.dismiss();
                        Toast.makeText(RegisterActivity.this,"服务器请求失败",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onImageSuccese(Bitmap bitmap) {

                    }
                });
    }


}
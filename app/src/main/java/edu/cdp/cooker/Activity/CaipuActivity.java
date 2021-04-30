package edu.cdp.cooker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import edu.cdp.cooker.R;
import edu.cdp.cooker.utils.ConstentUtils;
import edu.cdp.cooker.utils.Params;
import edu.cdp.cooker.utils.VolleyInterface;
import edu.cdp.cooker.utils.VolleyRequest;

public class CaipuActivity extends BaseActivity implements View.OnClickListener {
    private String img_id;
    private ImageView like;
    @Override
    protected void intViews() {
        like = findViewById(R.id.like);
        Intent intent = this.getIntent();
        //从Intent获得额外信息，设置为TextView的文本
        setTitleVisiable(intent.getStringExtra("caipu"));
        img_id = intent.getStringExtra("img_id");
        likeSetStatu(img_id);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_caipu;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId ()) {
            case R.id.caipu1_video:
                Intent intent = new Intent(CaipuActivity.this, VideoActicity.class);
                intent.putExtra("requst", ConstentUtils.VIDEO_API);
                startActivity(intent);
                break;
            case R.id.like:
                likePandan(img_id);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }


    private void likeSetStatu(final String img_id){
        VolleyInterface vif = null;

        VolleyRequest.requestPost(ConstentUtils.PANDUANAPI, "login", Params.shoucang(img_id),
                new VolleyInterface(CaipuActivity.this,VolleyInterface.listener,VolleyInterface.errorListener,VolleyInterface.imageListener) {
                    @Override
                    public void onMysuccess(String result) {

                        if (result.indexOf("error")!= -1){
                            like.setImageDrawable(getResources().getDrawable(R.drawable.like));
                        }
                        else {
                            like.setImageDrawable(getResources().getDrawable(R.drawable.islike));
                        }
//
                    }

                    @Override
                    public void onMyerror(VolleyError error) {

                        Toast.makeText(CaipuActivity.this,"服务器请求失败",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onImageSuccese(Bitmap bitmap) {

                    }
                });
    }

    private void likePandan(final String img_id){
        VolleyInterface vif = null;

        VolleyRequest.requestPost(ConstentUtils.PANDUANAPI, "login", Params.shoucang(img_id),
                new VolleyInterface(CaipuActivity.this,VolleyInterface.listener,VolleyInterface.errorListener,VolleyInterface.imageListener) {
                    @Override
                    public void onMysuccess(String result) {

                        if (result.indexOf("error")!= -1){
                            netLike(img_id);
                        }
                        else {
                            netLikeDelet(img_id);
                        }
//
                    }

                    @Override
                    public void onMyerror(VolleyError error) {

                        Toast.makeText(CaipuActivity.this,"服务器请求失败",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onImageSuccese(Bitmap bitmap) {

                    }
                });
    }

    private void netLike(final String id_user){
        VolleyInterface vif = null;
        VolleyRequest.requestPost(ConstentUtils.SHOUCANGAPI, "login", Params.shoucang(id_user),
                new VolleyInterface(CaipuActivity.this,VolleyInterface.listener,VolleyInterface.errorListener,VolleyInterface.imageListener) {
                    @Override
                    public void onMysuccess(String result) {
                        if (result.equals("FAILE")){
                            Toast.makeText(CaipuActivity.this,"添加至我的收藏失败",Toast.LENGTH_LONG).show();;
                        }
                        else {
                            Toast.makeText(CaipuActivity.this, "收藏成功", Toast.LENGTH_LONG).show();
                            like.setImageDrawable(getResources().getDrawable(R.drawable.islike));
                        }

                    }

                    @Override
                    public void onMyerror(VolleyError error) {

                        Toast.makeText(CaipuActivity.this,"服务器请求失败",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onImageSuccese(Bitmap bitmap) {

                    }
                });
    }

    private void netLikeDelet(final String id_user){
        VolleyInterface vif = null;
        VolleyRequest.requestPost(ConstentUtils.LIKEDELETAPI, "login", Params.shoucang(id_user),
                new VolleyInterface(CaipuActivity.this,VolleyInterface.listener,VolleyInterface.errorListener,VolleyInterface.imageListener) {
                    @Override
                    public void onMysuccess(String result) {
                        if (result.equals("FAILE")){
                            Toast.makeText(CaipuActivity.this,"移出收藏列表失败",Toast.LENGTH_LONG).show();;
                        }
                        else {
                            Toast.makeText(CaipuActivity.this, "移出收藏列表成功", Toast.LENGTH_LONG).show();
                            like.setImageDrawable(getResources().getDrawable(R.drawable.like));
                        }

                    }

                    @Override
                    public void onMyerror(VolleyError error) {

                        Toast.makeText(CaipuActivity.this,"服务器请求失败",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onImageSuccese(Bitmap bitmap) {

                    }
                });
    }

}
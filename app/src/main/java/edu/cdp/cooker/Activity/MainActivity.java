package edu.cdp.cooker.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.cdp.cooker.Adapter.CardRecyclerAdapter;
import edu.cdp.cooker.Adapter.DinnerAdapter;
import edu.cdp.cooker.Adapter.FaxianAdapter;
import edu.cdp.cooker.Adapter.MyPagerAdapter;
import edu.cdp.cooker.Adapter.ShipingAdapter;
import edu.cdp.cooker.Adapter.ShouyeAdapter;
import edu.cdp.cooker.R;
import edu.cdp.cooker.bean.CustomViewPager;
import edu.cdp.cooker.utils.ConstentUtils;
import edu.cdp.cooker.utils.Params;
import edu.cdp.cooker.utils.VolleyInterface;
import edu.cdp.cooker.utils.VolleyRequest;
import edu.cdp.cooker.view.Faxianguangzhu;
import edu.cdp.cooker.view.Faxianmingchucai;
import edu.cdp.cooker.view.Faxianremeng;
import edu.cdp.cooker.view.Shipinghongpei;
import edu.cdp.cooker.view.Shipingjianfei;
import edu.cdp.cooker.view.Shipingjiqiao;
import edu.cdp.cooker.view.Shipingshucai;
import edu.cdp.cooker.view.Shipingtuijian;
import edu.cdp.cooker.view.Shouyecaishi;
import edu.cdp.cooker.view.Shouyecaixi;
import edu.cdp.cooker.view.Shouyeremeng;
import edu.cdp.cooker.view.Shouyewancan;
import edu.cdp.cooker.view.Shouyewucan;
import edu.cdp.cooker.view.Shouyezaocan;
import edu.cdp.cooker.view.Viewfaxian;
import edu.cdp.cooker.view.Viewshiping;
import edu.cdp.cooker.view.Viewwode;
import edu.cdp.cooker.view.Viewshouye;
import edu.cdp.cooker.view.Wodeguanzhu;
import edu.cdp.cooker.view.Wodeshoucang;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnBannerListener {
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private ViewPager vpagers;
    private Viewfaxian viewfaxian;
    private Viewshiping viewshiping;
    private Viewwode viewwode;
    private Viewshouye viewshouye;
    private ImageView ivfaxian,guanzhu1,guanzhu2,tuijian1,tuijian2,tuijian3,tuijian4,tuijian5,tuijian6,tuijian7,tuijian8,guanzhu3,guanzhu4 ,ivshiping, ivwode, ivshouye,p1,p2,p3,p4,p5,p6,wucan1,wucan2,wucan3,wucan4,wucan5,
            wucan6,wancan1,wancan2,wancan3,wancan4,wancan5,wancan6,remen1,remen2,remen3,remen4,remen5,remen6,remen7,remen8,like1;
    private TextView tvfaxian, tvshiping, tvwode, tvshouye;
    private LinearLayout llfaxian, llshiping, llwode, llshouye;
    private Banner mBanner;
    private ArrayList<String> images,imageTitle;
    private ViewPager spagers;
    private Shipingjianfei shipingjianfei;
    private Shipingtuijian shipingtuijian;
    private Shipinghongpei shipinghongpei;
    private Shipingshucai shipingshucai;
    private Shipingjiqiao shipingjiqiao;

    private TextView sptuijian,spshucan,sphongpei,spjianfei,spjiqiao;
    private List<String> tvList;
    private RecyclerView recyler;
    private ImageView iv_search,iv_local,iv_like,iv_download,iv_recent,iv_library,iv_daily;

    private ViewPager fxpager;
    private Faxianremeng faxianremeng;
    private Faxianmingchucai faxianmingchucai;
    private Faxianguangzhu faxianguangzhu;
    private TextView fxguanzhu,fxremeng,fxmingchucai;

    private ViewPager wdpager;
    private Wodeguanzhu wodeguanzhu;
    private Wodeshoucang wodeshoucang;
    private TextView wdguanzhu,wdshoucang;

    private ViewPager sypager;
    private Shouyeremeng shouyeremeng;
    private Shouyecaixi shouyecaixi;
    private Shouyecaishi shouyecaishi;
    private TextView syremeng,sycaishi,sycaixi;

    private ViewPager dipager;
    private Shouyezaocan shouyezaocan;
    private Shouyewucan shouyewucan;
    private Shouyewancan shouyewancan;
    private TextView syzaocan,sywucan,sywancan;
    private SwipeRefreshLayout swipeRefreshLayout;

    private CustomViewPager homeViewPager = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
//    mBanner = findViewById ( R.id.banner );
        initData ();
        getView ();
        initview ();
        initToolBar ();
        setDrawerToggle ();
        setNavigationViewListener ();
        setToolbarListener ();
        gettab ();
        getCacheImg();
        setStatus ( 0 );
        spsetStatus ( 0 );
        wdsetStatus ( 0 );
        fxsetStatus ( 0 );
        sysetStatus ( 0 );
        disetStatus (0);
        initCardview ();
    }



    //设置cardview
    private void initCardview(){
        int[] imgList = {R.drawable.card1, R.drawable.card2, R.drawable.card3, R.drawable.card4, R.drawable.card5};
        tvList = new ArrayList<>();
        tvList.add("粥");
        tvList.add("排骨");
        tvList.add("荤菜");
        tvList.add("烘焙");
        tvList.add("川菜");
        GridLayoutManager layoutManager = new GridLayoutManager(this,5);
        recyler.setLayoutManager(layoutManager);
        LinearLayoutManager m=new LinearLayoutManager(this);
        m.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyler.setLayoutManager(m);
        recyler.setAdapter( new CardRecyclerAdapter(imgList, tvList,this));
        recyler.setItemAnimator(new DefaultItemAnimator());
    }


    //初始化轮播数据
    private void initData() {
        //设置图片资源:url或本地资源
        images = new ArrayList<>();
        //设置图片标题:自动对应
        imageTitle = new ArrayList<>();
        images.add("http://m.qpic.cn/psc?/V10ymKRQ4Rxt9D/ruAMsa53pVQWN7FLK88i5txHnWUSmLb5t2JaV00i6Jv3Y66ua7CujLndlLTtISxDgI4M5E5oBXMmKNwSE9gD7jvY0MP7OWv4eT98bO9LfIA!/b&bo=tgI2AbYCNgEBByA!&rf=viewer_4");
        images.add("http://m.qpic.cn/psc?/V10ymKRQ4Rxt9D/ruAMsa53pVQWN7FLK88i5txHnWUSmLb5t2JaV00i6Jt8.PxXgL6uERYj8NMQJ9zxApysHNYdPReE8bqrCT989gfEP.y9iH9Ey0TQaCespEw!/b&bo=rAIyAawCMgEBByA!&rf=viewer_4");
        images.add("http://m.qpic.cn/psc?/V10ymKRQ4Rxt9D/ruAMsa53pVQWN7FLK88i5txHnWUSmLb5t2JaV00i6JvcYaQPCfZNZglCxGAqOmylCI7ZdyzBytTNTdjt3Yu3u1Mgtrs8ihwmN6mglnxf68k!/b&bo=sgI0AbICNAEBByA!&rf=viewer_4");
        images.add("http://m.qpic.cn/psc?/V10ymKRQ4Rxt9D/ruAMsa53pVQWN7FLK88i5txHnWUSmLb5t2JaV00i6JuQkS9YiGpvGbYYmCDXQtBNmYnmNYIJpkKW53iucbVezvpCn02Cc66G3x6oybXDkqo!/b&bo=tAI1AbQCNQEBByA!&rf=viewer_4");
        images.add("http://m.qpic.cn/psc?/V10ymKRQ4Rxt9D/45NBuzDIW489QBoVep5mcfAP3LuPRgCboZoPGJIijaobyNWjvYE8sztQOAlaOrzHfc*MguDDex6jUM.70O*6V9yBsOLoNgxUU2dE4QOmRn4!/b&bo=tAI1AbQCNQEBFzA!&rf=viewer_4");
        imageTitle.add("简单0失败的家常菜");
        imageTitle.add("爆款美食");
        imageTitle.add("春日限定");
        imageTitle.add("一周热门菜谱");
        imageTitle.add("吃不胖的美食秘籍");
    }


    //初始化窗口
    private void initview() {
        toolbar = findViewById ( R.id.toolbar );
        navigationView = findViewById ( R.id.nav_view );
        drawer = findViewById ( R.id.drawlayout );
        View viewPager = LayoutInflater.from ( this ).inflate ( R.layout.view_shouye, null );
        mBanner = (Banner)viewPager.findViewById (R.id.banner );

    }


    //实例化viewpager子布局
    private void getView() {
        vpagers = findViewById ( R.id.iv_vpager );
        viewshouye = new Viewshouye ( this );
        View v1 = viewshouye.getView ();
        //首页下拉刷新的监听
        swipeRefreshLayout = v1.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
                getCacheImg();
                swipeRefreshLayout.setEnabled(true);
            }
        });
        mBanner = v1.findViewById ( R.id.banner);
        sypager = v1.findViewById ( R.id.sy_pager );
        sycaishi = v1.findViewById ( R.id.sy_caishi );
        sycaixi = v1.findViewById ( R.id.sy_caixi );
        syremeng = v1.findViewById ( R.id.sy_remeng );
        dipager = v1.findViewById ( R.id.sy_dinner );
        sywucan = v1.findViewById ( R.id.sy_wucan );
        syzaocan = v1.findViewById ( R.id.sy_zaocan );
        sywancan = v1.findViewById ( R.id.sy_wancan );
        viewshiping = new Viewshiping ( this );
        View v2 = viewshiping.getView ();
        spagers = v2.findViewById ( R.id.spviewpager );
        sptuijian = v2.findViewById ( R.id.sp_tuijian );
        spshucan = v2.findViewById ( R.id.sp_shucai );
        sphongpei = v2.findViewById ( R.id.sp_hongpei );
        spjianfei = v2.findViewById ( R.id.sp_jianfeican );
        spjiqiao = v2.findViewById ( R.id.sp_xiaojiqiao );
        viewfaxian = new Viewfaxian ( this );
        View v3 = viewfaxian.getView ();
        recyler = v3.findViewById(R.id.recyclerview);
        viewwode = new Viewwode ( this );
        fxpager = v3.findViewById ( R.id.fx_pager );
        fxguanzhu = v3.findViewById ( R.id.fx_guanzhu );
        fxremeng = v3.findViewById ( R.id.fx_remeng );
        fxmingchucai = v3.findViewById ( R.id.fx_mingchucai );
        View v4 = viewwode.getView ();
        wdpager = v4.findViewById ( R.id.wd_pager );
        wdguanzhu = v4.findViewById ( R.id.wd_guanzhu );
        wdshoucang = v4.findViewById ( R.id.wd_shoucang );

        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        mBanner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        mBanner.setImages(images);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        mBanner.setBannerAnimation( Transformer.Default);
        //设置轮播图的标题集合
        mBanner.setBannerTitles(imageTitle);
        //设置轮播间隔时间
        mBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        mBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        mBanner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();

        ArrayList<View> views = new ArrayList<View> ();
        views.add ( v1 );
        views.add ( v2 );
        views.add ( v3 );
        views.add ( v4 );
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter ( views );
        vpagers.setAdapter ( myPagerAdapter );
        vpagers.addOnPageChangeListener ( new ViewPager.OnPageChangeListener () {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setStatus ( position );
                Log.i ("" , "onPageSelected: " +position);
                if (position == 1){
                    getTuijian();
                }
                if (position == 2){
                    getGuanzhu();
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }

        } );
        vpagers.setCurrentItem ( 0 );


        shipingtuijian = new Shipingtuijian ( this );
        View spv1 = shipingtuijian.getView ();

        tuijian1 = spv1.findViewById(R.id.tj1);
        tuijian2 = spv1.findViewById(R.id.tj2);
        tuijian3 = spv1.findViewById(R.id.tj3);
        tuijian4 = spv1.findViewById(R.id.tj4);
        tuijian5 = spv1.findViewById(R.id.tj5);
        tuijian6 = spv1.findViewById(R.id.tj6);
        tuijian7 = spv1.findViewById(R.id.tj7);
        tuijian8 = spv1.findViewById(R.id.tj8);

        shipingshucai = new Shipingshucai ( this );
        View spv2 = shipingshucai.getView ();
        shipinghongpei = new Shipinghongpei ( this );
        View spv3 = shipinghongpei.getView ();
        shipingjianfei = new Shipingjianfei ( this );
        View spv4 = shipingjianfei.getView ();
        shipingjiqiao = new Shipingjiqiao ( this );
        View spv5 = shipingjiqiao.getView ();
        ArrayList<View> spviews = new ArrayList<View> ();
        spviews.add ( spv1 );
        spviews.add ( spv2 );
        spviews.add ( spv3 );
        spviews.add ( spv4 );
        spviews.add ( spv5 );
        ShipingAdapter shipingAdapter = new ShipingAdapter ( spviews );
        spagers.setAdapter ( shipingAdapter );
        spagers.addOnPageChangeListener ( new ViewPager.OnPageChangeListener () {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                spsetStatus ( position );

            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }

        } );

        //设置首先显示为首页
        spagers.setCurrentItem ( 0 );

        faxianguangzhu = new Faxianguangzhu ( this );
        View fxv1 = faxianguangzhu.getView ();
        guanzhu1 = fxv1.findViewById(R.id.gz1);
        guanzhu2 = fxv1.findViewById(R.id.gz2);
        guanzhu3 = fxv1.findViewById(R.id.gz3);
        guanzhu4 = fxv1.findViewById(R.id.gz4);
        faxianremeng = new Faxianremeng ( this );
        View fxv2 = faxianremeng.getView ();
        faxianmingchucai = new Faxianmingchucai ( this );
        View fxv3 = faxianmingchucai.getView ();
        ArrayList<View> fxviews = new ArrayList<View> ();
        fxviews.add ( fxv1 );
        fxviews.add ( fxv2 );
        fxviews.add ( fxv3 );
        FaxianAdapter faxianAdapter = new FaxianAdapter ( fxviews );
        fxpager.setAdapter ( faxianAdapter );
        fxpager.addOnPageChangeListener ( new ViewPager.OnPageChangeListener () {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fxsetStatus ( position );
            }



            @Override
            public void onPageScrollStateChanged(int state) {

            }

        } );

        //设置首先显示为首页
        fxpager.setCurrentItem ( 0 );

        wodeguanzhu = new Wodeguanzhu ( this );
        View wdv1 = wodeguanzhu.getView ();
        wodeshoucang = new Wodeshoucang ( this );
        View wdv2 = wodeshoucang.getView ();
        like1 = wdv2.findViewById(R.id.like1);

        ArrayList<View> wdviews = new ArrayList<View> ();
        wdviews.add ( wdv1 );
        wdviews.add ( wdv2 );
        FaxianAdapter wodeAdapter = new FaxianAdapter ( wdviews );
        wdpager.setAdapter ( wodeAdapter );
        wdpager.addOnPageChangeListener ( new ViewPager.OnPageChangeListener () {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //调用一个方法，获取收藏的内容
//                VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI_SC, "log",like1);
                wdsetStatus ( position );
                if(position==1){
                    netLike_Get();
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }

        } );

        //设置首先显示为首页
        wdpager.setCurrentItem ( 0 );
        shouyeremeng = new Shouyeremeng ( this );
        View syv1 = shouyeremeng.getView ();
        remen1 = syv1.findViewById(R.id.m1);
        remen2 = syv1.findViewById(R.id.rm2);
        remen3 = syv1.findViewById(R.id.rm3);
        remen4 = syv1.findViewById(R.id.rm4);
        remen5 = syv1.findViewById(R.id.rm5);
        remen6 = syv1.findViewById(R.id.rm6);
        remen7 = syv1.findViewById(R.id.rm7);
        remen8 = syv1.findViewById(R.id.rm8);

        shouyewancan = new Shouyewancan ( this );
        View div3 = shouyewancan.getView ();
        wancan1 = div3.findViewById(R.id.wancan1);
        wancan2 = div3.findViewById(R.id.wancan2);
        wancan3 = div3.findViewById(R.id.wancan3);
        wancan4 = div3.findViewById(R.id.wancan4);
        wancan5 = div3.findViewById(R.id.wancan5);
        wancan6 = div3.findViewById(R.id.wancan6);






        shouyecaishi = new Shouyecaishi ( this );
        View syv2 = shouyecaishi.getView ();

        shouyecaixi = new Shouyecaixi ( this );
        View syv3 = shouyecaixi.getView ();
        ArrayList<View> syviews = new ArrayList<View> ();
        syviews.add ( syv1 );
        syviews.add ( syv2 );
        syviews.add ( syv3 );
        ShouyeAdapter shouyeAdapter = new ShouyeAdapter( syviews );
        sypager.setAdapter ( shouyeAdapter );
        sypager.addOnPageChangeListener ( new ViewPager.OnPageChangeListener () {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                sysetStatus ( position );

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        } );

        //设置首先显示为首页
        sypager.setCurrentItem ( 0 );

        shouyezaocan = new Shouyezaocan ( this );
        View div1 = shouyezaocan.getView ();
        p1 = div1.findViewById(R.id.p1);
        p2 = div1.findViewById(R.id.p2);
        p3 = div1.findViewById(R.id.p3);
        p4 = div1.findViewById(R.id.p4);
        p5 = div1.findViewById(R.id.p5);
        p6 = div1.findViewById(R.id.p6);

        shouyewucan = new Shouyewucan ( this );
        View div2 = shouyewucan.getView ();
        wucan1 = div2.findViewById(R.id.wucan1);
        wucan2 = div2.findViewById(R.id.wucan2);
        wucan3 = div2.findViewById(R.id.caipu1);
        wucan4 = div2.findViewById(R.id.wucan4);
        wucan5 = div2.findViewById(R.id.wucan5);
        wucan6 = div2.findViewById(R.id.wucan6);

        ArrayList<View> diviews = new ArrayList<View> ();
        diviews.add ( div1 );
        diviews.add ( div2 );
        diviews.add ( div3 );
        DinnerAdapter dinnerAdapter = new DinnerAdapter ( diviews );
        dipager.setAdapter ( dinnerAdapter );
        dipager.addOnPageChangeListener ( new ViewPager.OnPageChangeListener () {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                disetStatus ( position );
                if (position==1){
                    getWucan();
                }
                if (position==2){
                    getWancan();
                }
            }



            @Override
            public void onPageScrollStateChanged(int state) {

            }

        } );

        //设置首先显示为首页
        dipager.setCurrentItem ( 0 );
    }

    //获取子布局文件控件
    private void gettab() {

        llshiping = findViewById ( R.id.ll_shiping );
        llshiping.setOnClickListener ( this );

        llfaxian = findViewById ( R.id.ll_faxian );
        llfaxian.setOnClickListener ( this );

        llwode = findViewById ( R.id.ll_wode );
        llwode.setOnClickListener ( this );

        llshouye = findViewById ( R.id.ll_shouye );
        llshouye.setOnClickListener ( this );

        ivshiping = findViewById ( R.id.iv_shiping );

        ivfaxian = findViewById ( R.id.iv_faxian );

        ivwode = findViewById ( R.id.iv_wode );

        ivshouye = findViewById ( R.id.iv_shouye );

        tvshiping = findViewById ( R.id.tv_shiping );

        tvfaxian = findViewById ( R.id.tv_faxian );

        tvwode = findViewById ( R.id.tv_wode );

        tvshouye = findViewById ( R.id.tv_shouye );

    }

    //设置页面切换时改变图标状态
    private void setStatus(int index) {
        clearstatus ();
        switch (index) {
            case 0:
                tvshouye.setTextColor ( Color.parseColor ( "#DB5860" ) );
                ivshouye.setImageResource ( R.drawable.shouye1);
                break;
            case 1:
                tvshiping.setTextColor ( Color.parseColor ( "#DB5860" ) );
                ivshiping.setImageResource ( R.drawable.shiping1 );
                break;
            case 2:
                ivfaxian.setImageResource ( R.drawable.faxian1);
                tvfaxian.setTextColor ( Color.parseColor ( "#DB5860" ) );
                break;
            case 3:
                tvwode.setTextColor ( Color.parseColor ( "#DB5860" ) );
                ivwode.setImageResource ( R.drawable.yonghu1 );
                break;

        }

    }

    //清除点击后的改变
    private void clearstatus() {
        tvfaxian.setTextColor ( Color.parseColor ( "#666666" ) );
        ivfaxian.setImageResource ( R.drawable.faxian );
        tvshiping.setTextColor ( Color.parseColor ( "#666666" ) );
        ivshiping.setImageResource ( R.drawable.shiping );
        tvwode.setTextColor ( Color.parseColor ( "#666666" ) );
        ivwode.setImageResource ( R.drawable.yonghu);
        tvshouye.setTextColor ( Color.parseColor ( "#666666" ) );
        ivshouye.setImageResource ( R.drawable.shouye );
    }

    //初始化标题栏
    private void initToolBar() {
        setSupportActionBar ( toolbar );
        getSupportActionBar ().setDisplayShowHomeEnabled ( true );
        getSupportActionBar ().setDisplayShowTitleEnabled ( true );
        getSupportActionBar ().setTitle ( "美味厨房" );
        TextView textView = (TextView) toolbar.getChildAt ( 0 );
        textView.getLayoutParams ().width = LinearLayout.LayoutParams.MATCH_PARENT;
        textView.setGravity ( Gravity.CENTER_HORIZONTAL );
    }

    //设置DrawerToggle
    private void setDrawerToggle() {
        toggle = new ActionBarDrawerToggle ( this, drawer, toolbar, 0, 0 );
        drawer.addDrawerListener ( toggle );
        toggle.syncState ();
    }

    //创建一个右上角的菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.toolbar_menu, menu );
        return true;
    }

    //设置左边侧滑栏的监听事件
    private void setNavigationViewListener() {
        navigationView.setNavigationItemSelectedListener ( new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId ()) {
                    case R.id.nav_swer:
                        //                //清空文件
                        SharedPreferences sp=getSharedPreferences("loding_success",MODE_PRIVATE);
                        if(sp!=null) {
                            sp.edit().clear().commit();
                        }
                        Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                drawer.closeDrawer ( GravityCompat.START );
                return true;
            }
        } );
    }

    //设置右上角菜单的监听事件
    private void setToolbarListener() {
        toolbar.setOnMenuItemClickListener ( new Toolbar.OnMenuItemClickListener () {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId ()) {
                    case R.id.setting:
                        Toast.makeText ( MainActivity.this, "设置", Toast.LENGTH_LONG ).show ();
                        break;
                }
                drawer.closeDrawer ( GravityCompat.START );
                return true;
            }
        } );
    }

    //监听点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.zuixin:
                Intent intent_zuixin = new Intent(MainActivity.this,ZuixintuijianActivity.class);
                startActivity(intent_zuixin);
                break;
            case R.id.xiafancai:
                Intent intent_xiafancai = new Intent(MainActivity.this,XiafancaiActivity.class);
                startActivity(intent_xiafancai);
                break;
            case R.id.jiachangcai:
                Intent intent_jiachang = new Intent(MainActivity.this,JiachangcaiActivity.class);
                startActivity(intent_jiachang);
                break;
            case R.id.ll_shouye:
                vpagers.setCurrentItem ( 0 );
                setStatus ( 0 );
                break;
            case R.id.ll_shiping:
                vpagers.setCurrentItem ( 1 );
                setStatus ( 1 );
                break;
            case R.id.ll_faxian:
                vpagers.setCurrentItem ( 2 );
                setStatus ( 2 );
                break;

            case R.id.ll_wode:
                vpagers.setCurrentItem ( 3 );
                setStatus ( 3 );
                break;

            case R.id.sp_tuijian:
                spagers.setCurrentItem ( 0 );
                spsetStatus ( 0 );
                break;
            case R.id.sp_shucai:
                spagers.setCurrentItem ( 1 );
                spsetStatus ( 1 );
                break;
            case R.id.sp_hongpei:
                spagers.setCurrentItem ( 2 );
                spsetStatus ( 2 );
                break;
            case R.id.sp_jianfeican:
                spagers.setCurrentItem ( 3 );
                spsetStatus ( 3 );
                break;
            case R.id.sp_xiaojiqiao:
                spagers.setCurrentItem ( 4 );
                spsetStatus ( 4 );
                break;

            case R.id.fx_guanzhu:
                fxpager.setCurrentItem ( 0 );
                fxsetStatus ( 0 );
                break;
            case R.id.fx_remeng:
                fxpager.setCurrentItem ( 1 );
                fxsetStatus ( 1 );
                break;
            case R.id.fx_mingchucai:
                fxpager.setCurrentItem ( 2 );
                fxsetStatus ( 2 );
                break;

            case R.id.wd_guanzhu:
                wdpager.setCurrentItem ( 0 );
                wdsetStatus ( 0 );
                break;
            case R.id.wd_shoucang:
                wdpager.setCurrentItem ( 1 );
                wdsetStatus ( 1 );
                break;

            case R.id.sy_remeng:
                sypager.setCurrentItem ( 0 );
                sysetStatus ( 0 );
                break;
            case R.id.sy_caishi:
                sypager.setCurrentItem ( 1 );
                sysetStatus ( 1 );
                break;
            case R.id.sy_caixi:
                sypager.setCurrentItem ( 2 );
                sysetStatus ( 2 );
                break;

            case R.id.sy_zaocan:
                dipager.setCurrentItem ( 0 );
                disetStatus ( 0 );
                break;
            case R.id.sy_wucan:
                dipager.setCurrentItem ( 1 );
                disetStatus ( 1 );
                break;
            case R.id.sy_wancan:
                dipager.setCurrentItem ( 2 );
                disetStatus ( 2 );
                break;
            case R.id.fenlei:
                Intent intent_fenlei = new Intent(MainActivity.this, FenleiActivity.class);
                intent_fenlei.putExtra("fenlei", "分类");
                startActivity(intent_fenlei);
                break;
            case R.id.m1:
                Intent intent = new Intent(MainActivity.this, VideoActicity.class);
                intent.putExtra("requst", ConstentUtils.VIDEO_API);
                startActivity(intent);
                break;
            case R.id.caipu1:
                Intent intent1 = new Intent(MainActivity.this, CaipuActivity.class);
                intent1.putExtra("caipu", "香干炒腊肉");
                intent1.putExtra("img_id", "like1");
                startActivity(intent1);
                break;
            case R.id.hongbei:
                Intent intent_hongbei = new Intent(MainActivity.this, HongpeiActivity.class);
                startActivity(intent_hongbei);
                break;
        }
    }

    private void disetStatus(int k) {
        diclearstatus ();
        switch (k) {
            case 0:
                syzaocan.setTextColor ( Color.parseColor ( "#FF0000" ) );
                syzaocan.setTextSize ( 18 );
                break;
            case 1:
                sywucan.setTextColor ( Color.parseColor ( "#FF0000" ) );
                sywucan.setTextSize ( 18 );
                break;
            case 2:
                sywancan.setTextColor ( Color.parseColor ( "#FF0000" ) );
                sywancan.setTextSize ( 18 );
                break;
        }
    }
    private void diclearstatus() {
        syzaocan.setTextColor ( Color.parseColor ( "#000000" ) );
        syzaocan.setTextSize ( 16 );
        sywucan.setTextColor ( Color.parseColor ( "#000000" ) );
        sywucan.setTextSize ( 16 );
        sywancan.setTextColor ( Color.parseColor ( "#000000" ) );
        sywancan.setTextSize ( 16 );
    }
    //设置首页的view pager变化的状态
    private void sysetStatus(int m) {
        syclearstatus ();
        switch (m) {
            case 0:
                syremeng.setTextColor ( Color.parseColor ( "#FF0000" ) );
                syremeng.setTextSize ( 18 );
                break;
            case 1:
                sycaishi.setTextColor ( Color.parseColor ( "#FF0000" ) );
                sycaishi.setTextSize ( 18 );
                break;
            case 2:
                sycaixi.setTextColor ( Color.parseColor ( "#FF0000" ) );
                sycaixi.setTextSize ( 18 );
                break;
        }
    }
    //清除首页变化状态
    private void syclearstatus() {
        syremeng.setTextColor ( Color.parseColor ( "#000000" ) );
        syremeng.setTextSize ( 16 );
        sycaishi.setTextColor ( Color.parseColor ( "#000000" ) );
        sycaishi.setTextSize ( 16 );
        sycaixi.setTextColor ( Color.parseColor ( "#000000" ) );
        sycaixi.setTextSize ( 16 );
    }
    //设置我的页面的view pager变化的状态
    private void wdsetStatus(int n) {
        wdclearstatus ();
        switch (n) {
            case 0:
                wdguanzhu.setTextColor ( Color.parseColor ( "#FF0000" ) );
                wdguanzhu.setTextSize ( 18 );
                break;
            case 1:
                wdshoucang.setTextColor ( Color.parseColor ( "#FF0000" ) );
                wdshoucang.setTextSize ( 18 );
                break;
        }
    }
    //清除的我的页面view pager变化的状态
    private void wdclearstatus() {
        wdguanzhu.setTextColor ( Color.parseColor ( "#000000" ) );
        wdguanzhu.setTextSize ( 16 );
        wdshoucang.setTextColor ( Color.parseColor ( "#000000" ) );
        wdshoucang.setTextSize ( 16 );
    }
    //设置发现页面导航栏点击后效果
    private void fxsetStatus(int j) {
        fxclearstatus ();
        switch (j) {
            case 0:
                fxguanzhu.setTextColor ( Color.parseColor ( "#FF0000" ) );
                fxguanzhu.setTextSize ( 18 );
                break;
            case 1:
                fxremeng.setTextColor ( Color.parseColor ( "#FF0000" ) );
                fxremeng.setTextSize ( 18 );
                break;
            case 2:
                fxmingchucai.setTextColor ( Color.parseColor ( "#FF0000" ) );
                fxmingchucai.setTextSize ( 18 );
                break;
        }
    }
    //清除发现页面导航栏点击后效果
    private void fxclearstatus() {
        fxguanzhu.setTextColor ( Color.parseColor ( "#000000" ) );
        fxguanzhu.setTextSize ( 16 );
        fxremeng.setTextColor ( Color.parseColor ( "#000000" ) );
        fxremeng.setTextSize ( 16 );
        fxmingchucai.setTextColor ( Color.parseColor ( "#000000" ) );
        fxmingchucai.setTextSize ( 16 );
    }

    //设置视屏页面导航栏点击后效果
    private void spsetStatus(int i) {
        spclearstatus ();
        switch (i) {
            case 0:
                sptuijian.setTextColor ( Color.parseColor ( "#FF0000" ) );
                sptuijian.setTextSize ( 18 );
                break;
            case 2:
                sphongpei.setTextColor ( Color.parseColor ( "#FF0000" ) );
                sphongpei.setTextSize ( 18 );
                break;
            case 1:
                spshucan.setTextColor ( Color.parseColor ( "#FF0000" ) );
                spshucan.setTextSize ( 18 );
                break;
            case 3:
                spjianfei.setTextColor ( Color.parseColor ( "#FF0000" ) );
                spjianfei.setTextSize ( 18 );
                break;
            case 4:
                spjiqiao.setTextColor ( Color.parseColor ( "#FF0000" ) );
                spjiqiao.setTextSize ( 18 );
                break;
        }
    }
    //清除视屏页面导航栏点击后效果
    private void spclearstatus() {
        sptuijian.setTextColor ( Color.parseColor ( "#000000" ) );
        sptuijian.setTextSize ( 16 );
        sphongpei.setTextColor ( Color.parseColor ( "#000000" ) );
        sphongpei.setTextSize ( 16 );
        spshucan.setTextColor ( Color.parseColor ( "#000000" ) );
        spshucan.setTextSize ( 16 );
        spjianfei.setTextColor ( Color.parseColor ( "#000000" ) );
        spjianfei.setTextSize ( 16 );
        spjiqiao.setTextColor ( Color.parseColor ( "#000000" ) );
        spjiqiao.setTextSize ( 16 );
    }

    //设置轮播广告的点击事件
    @Override
    public void OnBannerClick(int position) {
        position = position+1;
        Toast.makeText(MainActivity.this, "你点击了第"+position+"张广告", Toast.LENGTH_LONG).show();
    }

    //加载轮播图
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }



    //下载图片到缓存，并读取
    public void getCacheImg(){
        //加载首页早餐图片
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI, "log",p1);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI2, "log",p2);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI3, "log",p3);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI4, "log",p4);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI5, "log",p5);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI6, "log",p6);



        //加载首页热门图片
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI_re1, "log",remen1);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI2_re2, "log",remen2);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI3_re3, "log",remen3);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI4_re4, "log",remen4);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI5_re5, "log",remen5);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI6_re6, "log",remen6);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI7_re7, "log",remen7);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI8_re8, "log",remen8);

    }

    public void getWucan(){
        //加载首页午餐图片
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI_wu1, "log",wucan1);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI2_wu2, "log",wucan2);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI3_wu3, "log",wucan3);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI4_wu4, "log",wucan4);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI5_wu5, "log",wucan5);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI6_wu6, "log",wucan6);
    }

    public void getWancan(){
        //加载首页晚餐图片
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI_wan1, "log",wancan1);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI2_wan2, "log",wancan2);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI3_wan3, "log",wancan3);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI4_wan4, "log",wancan4);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI5_wan5, "log",wancan5);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI6_wan6, "log",wancan6);
    }

    public void getTuijian(){
        //加载视频推荐页图片
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI_tj1, "log",tuijian1);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI2_tj2, "log",tuijian2);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI3_tj3, "log",tuijian3);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI4_tj4, "log",tuijian4);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI5_tj5, "log",tuijian5);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI6_tj6, "log",tuijian6);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI7_tj7, "log",tuijian7);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI8_tj8, "log",tuijian8);
    }

    public void getGuanzhu(){
        //加载发现页关注图片
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI_gz1, "log",guanzhu1);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI2_gz2, "log",guanzhu2);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI3_gz3, "log",guanzhu3);
        VolleyRequest.getCacheImage(ConstentUtils.IMAGEAPI4_gz4, "log",guanzhu4);
    }

    private void netLike_Get(){
        VolleyInterface vif = null;
        VolleyRequest.requestPost(ConstentUtils.GETSHOUCANGAPI, "login", Params.shoucang("获取收藏"),
                new VolleyInterface(MainActivity.this,VolleyInterface.listener,VolleyInterface.errorListener,VolleyInterface.imageListener) {
                    @Override
                    public void onMysuccess(String result) {
                        if (result == null){
                            Toast.makeText(MainActivity.this,"添加至我的收藏失败",Toast.LENGTH_LONG).show();
                        }
                        else {
                            List<String> arr = new ArrayList<String>();
                            arr.add(result);
                            for(String tmp:arr){
                                Log.i("", "onMysuccess: "+tmp);
                                String Api = "http://" + ConstentUtils.SERVER_Image_ADDRESS + ":8005/myservice/img/"+tmp.toString().trim()+".png";
                                Log.i("", "onMysuccess: "+Api);
                                VolleyRequest.getCacheImage(Api, "log",like1);
                            }
                        }

                    }

                    @Override
                    public void onMyerror(VolleyError error) {

                        Toast.makeText(MainActivity.this,"服务器请求失败",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onImageSuccese(Bitmap bitmap) {

                    }
                });
    }

}
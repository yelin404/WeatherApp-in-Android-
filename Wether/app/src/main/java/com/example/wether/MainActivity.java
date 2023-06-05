package com.example.wether;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.wether.CityManager.CityManagerActivity;
import com.example.wether.adapter.CityFragmentPagerAdapter;
import com.example.wether.db.DBManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView addCityIv,moreIv;
    ViewPager mainVP;
    LinearLayout pointLayout;
    RelativeLayout bgLayout;

    //viewpager的数据源
    List<Fragment>fragmentList;

    //表示ViewPager的页数指数器显示集合
    List<ImageView>imgList;

    //表示需要显示的城市的集合
    List<String>cityList;
    private CityFragmentPagerAdapter cityFragmentPagerAdapter;
    private SharedPreferences pref;
    private int bg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //初始化组件


        //给部件分配各自的变量名
        addCityIv = findViewById(R.id.main_iv_add);
        moreIv = findViewById(R.id.main_iv_more);
        mainVP = findViewById(R.id.main_vp);
        pointLayout = findViewById(R.id.main_layout_point);
        bgLayout = findViewById(R.id.main_out_layout);

        //开始绑定点击事件
        addCityIv.setOnClickListener(this);
        moreIv.setOnClickListener(this);

        //改变壁纸
        exchangeBg();


        //
        fragmentList = new ArrayList<>();
        imgList = new ArrayList<>();
        cityList = DBManager.getAllCityName();


        if (cityList.size()==0) {
            cityList.add("北京");
            cityList.add("上海");
            cityList.add("深圳");

        }

        //获取搜索界面传来的城市名称
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        if (!cityList.contains(city) && !TextUtils.isEmpty(city)) {
            cityList.add(city);
        }


        //将页面第一次加载进入ViewPager当中
        initPager();

        cityFragmentPagerAdapter = new CityFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        mainVP.setAdapter(cityFragmentPagerAdapter);

        //将小圆点加入到主界面中
        initPoint();
        mainVP.setCurrentItem(fragmentList.size()-1);

        //监听ViewPager，当页面发生变化时，小圆点也要发生变化
        setPagerListener();

    }

    //换壁纸函数
    public  void exchangeBg() {
        pref = getSharedPreferences("bg_pref", MODE_PRIVATE);
        bg = pref.getInt("bg", 2);
        switch (bg) {
            case 0:
                bgLayout.setBackgroundResource(R.mipmap.bg);
                break;
            case 1:
                bgLayout.setBackgroundResource(R.mipmap.bg2);
                break;
            case 2:
                bgLayout.setBackgroundResource(R.mipmap.bg3);
                break;

        }

    }

    private void setPagerListener() {
        mainVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < imgList.size(); i++) {
                    imgList.get(i).setImageResource(R.mipmap.a1);
                }
                imgList.get(position).setImageResource(R.mipmap.a2);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initPoint() {
        for (int i = 0; i < fragmentList.size(); i++) {
            ImageView point = new ImageView(this);
            point.setImageResource(R.mipmap.a1);
            point.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)point.getLayoutParams();
            lp.setMargins(0,0,20,0);
            imgList.add(point);
            pointLayout.addView(point);
        }
        imgList.get(imgList.size()-1).setImageResource(R.mipmap.a2);
    }

    private void initPager() {

        //创建Fragment，并将他添加近fragmentlist当中
        for (int i = 0; i < cityList.size(); i++) {
            CityWetherFragment cityWetherFragment = new CityWetherFragment();
            Bundle bundle = new Bundle();
            bundle.putString("city",cityList.get(i));
            cityWetherFragment.setArguments(bundle);
            fragmentList.add(cityWetherFragment);
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.main_iv_add:
                intent.setClass(this, CityManagerActivity.class);
                break;

            case R.id.main_iv_more:
                intent.setClass(this,MoreActivity.class);
                break;

        }
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        //这里就是为了当城市删除的时候，主界面也能跟着删除相关信息，其实就一个方法，那就是所有东西重新加载一遍就可以了

        //获取数据库中剩下的城市名称列表
        List<String> list = DBManager.getAllCityName();
        if (list.size()==0) {
            list.add("北京");
        }
        cityList.clear();
        cityList.addAll(list);
        fragmentList.clear();
        initPager();
        cityFragmentPagerAdapter.notifyDataSetChanged();

        imgList.clear();
        pointLayout.removeAllViews();
        initPoint();
        mainVP.setCurrentItem(fragmentList.size()-1);

    }
}
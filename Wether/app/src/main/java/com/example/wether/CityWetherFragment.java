package com.example.wether;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.wether.Base.BaseFragment;
import com.example.wether.bean.LifeIndexBean;
import com.example.wether.bean.WeatherBean;
import com.example.wether.db.DBManager;
import com.example.wether.juhe.HttpUtils;
import com.example.wether.juhe.URLUtils;
import com.google.gson.Gson;

import java.util.List;


public class CityWetherFragment extends BaseFragment implements View.OnClickListener {

    TextView tempTv,cityTv,conditionTv,windTv,tempRangeTv,dateTv,clothIndexTv,carIndexTv,coldIndexTv,sportIndexTv,raysIndexTv,airIndexTv;
    ImageView dayIv;

    private SharedPreferences pref;
    private int bg;

    LinearLayout futureLayout;
    ScrollView bgLayout;

    String city,url;
    private WeatherBean weatherBean;
    public LifeIndexBean.ResultBean.LifeBean lifeBean;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_city_wether, container, false);

        //初始华组件，由于初始内容太多，故写成函数
        initView(view);

        exchangeBg();

        //允许另一个Activity向这个fragment传值,其目的是获取某地的天气抢矿
        Bundle bundle = getArguments();
        city = bundle.getString("city");

        //*************************************************************************************************
        //注意，这里有很大概率和小慧的代码不一样，如果出现天气数据查询不到，请优先排查这一段代码！！！！！！！！！！！
//        获取省份
        url = URLUtils.getURL(city);
        loadData(url);

        String index_url = URLUtils.getIndexURL(city);
        loadIndexData(index_url);


        //***********************************************************************************************
//          调用父类获取数据的方法

        return view;
    }


    //换壁纸函数
    public  void exchangeBg() {
        pref = getActivity().getSharedPreferences("bg_pref", MODE_PRIVATE);
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

    private void loadIndexData(String index_url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonContent = HttpUtils.getJsonContent(index_url);
                LifeIndexBean TempLifeIndexBean = new Gson().fromJson(jsonContent, LifeIndexBean.class);
                if (TempLifeIndexBean.getResult() != null) {
                    lifeBean = TempLifeIndexBean.getResult().getLife();
                }

            }
        }).start();


    }

    @Override
    public void onSuccess(String result) {
        //你要把获取的数据进行解析和展示
        parseShowData(result);

        //将数据存储到数据库中
        long l = DBManager.insertCityInfo(city, result);
        if (l <= 0 ) {
            //说明压根就没有这条数据，调用插入方法
            DBManager.insertCityInfo(city,result);
        }

    }

    private void parseShowData(String result) {

        //获取到了结果类，该类包含三样东西，第一个是城市名字，第二个是实时天气，第三个是未来五天的天气，其中未来5天的天气是一个list
        weatherBean = new Gson().fromJson(result, WeatherBean.class);
        WeatherBean.ResultBean resultBean = weatherBean.getResult();

        //获取实时天气类和未来天气类,注意：：未来天气类是一个list
        WeatherBean.ResultBean.RealtimeBean realtimeBean = resultBean.getRealtime();
        List<WeatherBean.ResultBean.FutureBean> futureBeanList = resultBean.getFuture();
        WeatherBean.ResultBean.FutureBean futureTodayBean = futureBeanList.get(0);

        //获取实时天气，其内容包括温度，地点，天气情况，日期，风力，温度范围
        tempTv.setText(realtimeBean.getTemperature()+"℃");
        cityTv.setText(resultBean.getCity());
        conditionTv.setText(realtimeBean.getInfo());
        dateTv.setText(futureTodayBean.getDate());
        windTv.setText(realtimeBean.getDirect()+realtimeBean.getPower());
        tempRangeTv.setText(futureTodayBean.getTemperature());

        //获取未来三天的天气情况，其内容包括日期，天气情况，温度范围
        futureBeanList.remove(0);
        for (int i = 0; i < futureBeanList.size(); i++) {

            //完成向linearout中动态添加view
            View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_center, null);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            futureLayout.addView(itemView);

            //绑定组件
            TextView idateTv = itemView.findViewById(R.id.item_center_tv_date);
            TextView iconditionTv = itemView.findViewById(R.id.item_center_tv_con);
            TextView itemprangeTv = itemView.findViewById(R.id.item_center_tv_temp);
            ImageView iIv = itemView.findViewById(R.id.item_center_iv);

            //获取日期，天气情况，温度范围
            WeatherBean.ResultBean.FutureBean futureBean = futureBeanList.get(i);
            idateTv.setText(futureBean.getDate());
            iconditionTv.setText(futureBean.getWeather());
            itemprangeTv.setText(futureBean.getTemperature());




        }




    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        super.onError(ex, isOnCallback);
        //数据库当中查找上一次信息显示在Fragment当中
        String s = DBManager.queryCityInfo(city);
        if (!TextUtils.isEmpty(s)) {
            parseShowData(s);
        }

    }

    public void initView(View view) {

        //绑定组件
        tempTv = view.findViewById(R.id.frag_tv_currenttemp);
        cityTv = view.findViewById(R.id.frag_tv_city);
        conditionTv = view.findViewById(R.id.frag_tv_condition);
        windTv = view.findViewById(R.id.frag_tv_wind);
        tempRangeTv = view.findViewById(R.id.frag_tv_temprange);
        dateTv = view.findViewById(R.id.frag_tv_date);
        clothIndexTv = view.findViewById(R.id.frag_index_tv_dress);
        carIndexTv = view.findViewById(R.id.frag_index_tv_washcar);
        coldIndexTv = view.findViewById(R.id.frag_index_tv_cold);
        sportIndexTv = view.findViewById(R.id.frag_index_tv_sport);
        raysIndexTv = view.findViewById(R.id.frag_index_tv_rays);
        airIndexTv = view.findViewById(R.id.frag_index_tv_air);
        dayIv = view.findViewById(R.id.frag_iv_today);
        futureLayout = view.findViewById(R.id.frag_center_layout);
        bgLayout = view.findViewById(R.id.out_layout);

        //绑定点击事件
        clothIndexTv.setOnClickListener(this);
        carIndexTv.setOnClickListener(this);
        coldIndexTv.setOnClickListener(this);
        sportIndexTv.setOnClickListener(this);
        raysIndexTv.setOnClickListener(this);
        airIndexTv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        switch (v.getId()) {
            case R.id.frag_index_tv_dress:
                builder.setTitle("穿衣指数");
                String msg = "暂无信息";
                if (lifeBean != null) {
                    msg = lifeBean.getChuanyi().getV() + "\n" + lifeBean.getChuanyi().getDes();
                }
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_washcar:
                builder.setTitle("洗车指数");
                msg = "暂无信息";
                if (lifeBean != null) {
                    msg = lifeBean.getXiche().getV() + "\n" + lifeBean.getXiche().getDes();
                }
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_cold:
                builder.setTitle("感冒指数");
                msg = "暂无信息";
                if (lifeBean != null) {
                    msg = lifeBean.getGanmao().getV() + "\n" + lifeBean.getGanmao().getDes();
                }
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_sport:
                builder.setTitle("运动指数");
                msg = "暂无信息";
                if (lifeBean != null) {
                    msg = lifeBean.getYundong().getV() + "\n" + lifeBean.getYundong().getDes();
                }
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_rays:
                builder.setTitle("紫外线指数");
                msg = "暂无信息";
                if (lifeBean != null) {
                    msg = lifeBean.getZiwaixian().getV() + "\n" + lifeBean.getZiwaixian().getDes();
                }
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_air:
                builder.setTitle("空调指数");
                msg = "暂无信息";
                if (lifeBean != null) {
                    msg = lifeBean.getKongtiao().getV() + "\n" + lifeBean.getKongtiao().getDes();
                }
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
        }
        builder.create().show();
    }
}
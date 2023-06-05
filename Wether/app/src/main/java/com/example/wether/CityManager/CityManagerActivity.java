package com.example.wether.CityManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wether.R;
import com.example.wether.db.DBManager;
import com.example.wether.db.DataBaseBean;

import java.util.ArrayList;
import java.util.List;

public class CityManagerActivity extends AppCompatActivity implements View.OnClickListener {
    ListView cityLv;
    ImageView addIv,deleteIv,backIv;
    List<DataBaseBean>mDatas;
    private CityManagerAdapter cityManagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manager);
        //将变量绑定组件
        initView();

        //设置adapter
        cityManagerAdapter = new CityManagerAdapter(this, mDatas);
        cityLv.setAdapter(cityManagerAdapter);

    }

    /*  获取数据库当中真实数据源，添加到原有数据源当中，提示适配器更新*/
    @Override
    protected void onResume() {
        super.onResume();
        List<DataBaseBean> list = DBManager.getAllInfo();
        mDatas.clear();
        mDatas.addAll(list);
        cityManagerAdapter.notifyDataSetChanged();
    }

    private void initView() {
        cityLv = findViewById(R.id.city_lv);
        addIv = findViewById(R.id.city_iv_add);
        deleteIv = findViewById(R.id.city_iv_delete);
        backIv = findViewById(R.id.city_iv_back);
        mDatas = new ArrayList<>();

        //添加点击事件
        addIv.setOnClickListener(this);
        deleteIv.setOnClickListener(this);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.city_iv_back:
                finish();
                break;
            case R.id.city_iv_delete:
                intent = new Intent(this,DeleteCityActivity.class);
                startActivity(intent);
                break;
            case R.id.city_iv_add:
                int cityCount = DBManager.getCityCount();
                if (cityCount < 5) {
                    intent = new Intent(this,SearchCityActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "不好意思，城市数量存储已经达到最大值5个，无法存储", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
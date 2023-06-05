package com.example.wether.CityManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wether.Base.BaseActivity;
import com.example.wether.MainActivity;
import com.example.wether.R;
import com.example.wether.bean.WeatherBean;
import com.example.wether.juhe.URLUtils;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class SearchCityActivity extends BaseActivity implements View.OnClickListener {

    EditText searchEt;
    ImageView submitIv;
    GridView cityGv;
    String city;
    String[] hotCities = {"北京","上海","广州","深圳"};
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        //初始化组件
        initView();

        //设置适配器
        adapter = new ArrayAdapter<>(this, R.layout.item_hotcity, hotCities);
        cityGv.setAdapter(adapter);

        //设置GridView每一个item的监听事件
        setListener();

    }


    //设置GridView每一个item的监听事件
    private void setListener() {
        cityGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city = hotCities[position];
                String url = URLUtils.getURL(city);
                loadData(url);
            }
        });

    }

    private void initView() {
        searchEt = findViewById(R.id.search_et);
        submitIv = findViewById(R.id.search_iv_submit);
        cityGv = findViewById(R.id.search_gv);
        submitIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.search_iv_submit:
                city = searchEt.getText().toString();
                if (!TextUtils.isEmpty(city)) {
                    String url = URLUtils.getURL(city);
                    loadData(url);
                } else {
                    Toast.makeText(this, "输入内容不能为空!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onSuccess(String result) {
        WeatherBean weatherBean = new Gson().fromJson(result, WeatherBean.class);
        if (weatherBean.getError_code() == 0) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("city",city);
            startActivity(intent);
        } else {
            Toast.makeText(this,"暂时未收入此城市天气信息...",Toast.LENGTH_SHORT).show();
        }

    }
}
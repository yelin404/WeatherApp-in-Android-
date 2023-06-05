package com.example.wether.CityManager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wether.R;
import com.example.wether.db.DBManager;

import java.util.ArrayList;
import java.util.List;


public class DeleteCityActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView errorIv,rightIv;
    ListView deleteLv;
    List<String> mDatas;
    private DeleteCityAdapter adapter;
    List<String>deleteCitys;  //表示存储了删除的城市信息



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_city);

        //绑定组件
        initView();

        //设置适配器
        adapter = new DeleteCityAdapter(this, mDatas,deleteCitys);
        deleteLv.setAdapter(adapter);
    }

    private void initView() {
        errorIv = findViewById(R.id.delete_iv_error);
        rightIv = findViewById(R.id.delete_iv_right);
        deleteLv = findViewById(R.id.delete_lv);
        mDatas = DBManager.getAllCityName();
        deleteCitys = new ArrayList<>();
        errorIv.setOnClickListener(this);
        rightIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_iv_error:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示信息").setMessage("你确定想要退出吗？")
                                .setPositiveButton("舍弃更改", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();

                                    }
                                });
                builder.setNegativeButton("取消",null);
                builder.create().show();
                break;
            case R.id.delete_iv_right:
                for (int i = 0; i < deleteCitys.size(); i++) {
                    String city = deleteCitys.get(i);
//                    调用删除城市的函数
                  DBManager.deleteCityInfo(city);
                }
                finish();
                break;
        }
    }
}
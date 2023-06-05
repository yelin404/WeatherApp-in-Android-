package com.example.wether.Base;

import androidx.fragment.app.Fragment;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


//这个类其实就是使用外部框架xutils去联网获取天气数据，但是这各类并没有对获取的数据进行处理，这要在另一个类中完成
public class BaseFragment extends Fragment implements Callback.CommonCallback<String>{

    public void loadData(String path) {
        RequestParams params = new RequestParams(path);
        x.http().get(params,this);

    }



    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}

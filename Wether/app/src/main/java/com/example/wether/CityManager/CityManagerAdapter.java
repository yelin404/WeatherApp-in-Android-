package com.example.wether.CityManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wether.R;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wether.bean.WeatherBean;
import com.example.wether.db.DataBaseBean;
import com.google.gson.Gson;

import java.util.List;

public class CityManagerAdapter extends BaseAdapter {

    Context context;
    List<DataBaseBean> mDatas;

    public CityManagerAdapter(Context context, List<DataBaseBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_city_manager,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //设置城市名称
        holder.cityTv.setText(mDatas.get(position).getCity());
        WeatherBean weatherBean = new Gson().fromJson(mDatas.get(position).getContent(), WeatherBean.class);
        WeatherBean.ResultBean result = weatherBean.getResult();
        WeatherBean.ResultBean.RealtimeBean realtime = result.getRealtime();
        WeatherBean.ResultBean.FutureBean today = result.getFuture().get(0);

        //设置天气，当前温度，风力，温度范围
        holder.conTv.setText("天气" + realtime.getInfo());
        holder.currentTempTv.setText(realtime.getTemperature() + "℃");
        holder.windTv.setText(realtime.getDirect() + realtime.getPower());
        holder.tempRangeTv.setText(today.getTemperature());
        return convertView;
    }
    class ViewHolder {
        TextView cityTv,conTv,currentTempTv,windTv,tempRangeTv;
        public ViewHolder(View itemView) {
            cityTv = itemView.findViewById(R.id.item_city_tv_city);
            conTv = itemView.findViewById(R.id.item_city_tv_condition);
            currentTempTv = itemView.findViewById(R.id.item_city_tv_temp);
            windTv = itemView.findViewById(R.id.item_city_wind);
            tempRangeTv = itemView.findViewById(R.id.item_city_temprange);
        }
    }
}

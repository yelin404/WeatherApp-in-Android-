package com.example.mediaplayer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.mediaplayer.Bean.Constant.songName;
import  static com.example.mediaplayer.Bean.Constant.videoPic;

import com.example.mediaplayer.R;


public class VideoAdapter extends BaseAdapter {

    Context mContext;

    public VideoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return videoPic.length;
    }

    @Override
    public Object getItem(int position) {
        return videoPic[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        ImageView picIv;
        TextView vidTv;

        public ViewHolder(View view) {
            this.picIv = view.findViewById(R.id.item_list_iv);
            this.vidTv = view.findViewById(R.id.item_list_tv);
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_video,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.picIv.setImageResource(videoPic[position]);
        viewHolder.vidTv.setText(songName[position]);
        return convertView;
    }
}

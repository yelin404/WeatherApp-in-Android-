package com.example.mediaplayer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.mediaplayer.Bean.Constant.songName;
import  static com.example.mediaplayer.Bean.Constant.songPic;

import com.example.mediaplayer.R;

public class MusicAdapter extends BaseAdapter {

    Context mContext;

    public MusicAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return songName.length;
    }

    @Override
    public Object getItem(int position) {
        return songName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        ImageView picIv;
        TextView nameTv;

        public ViewHolder(View view) {
            picIv = view.findViewById(R.id.item_list_iv);
            nameTv = view.findViewById(R.id.item_list_tv);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            //这里是获取了item_music布局
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_music,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.nameTv.setText(songName[position]);
        viewHolder.picIv.setImageResource(songPic[position]);
        return convertView;
    }

}

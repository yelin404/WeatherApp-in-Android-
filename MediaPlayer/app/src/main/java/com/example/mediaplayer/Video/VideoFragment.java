package com.example.mediaplayer.Video;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mediaplayer.Adapter.VideoAdapter;
import com.example.mediaplayer.R;


public class VideoFragment extends Fragment {
    private ListView listView;


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video,null);
        listView = view.findViewById(R.id.video_lv);

        //设置适配器
        VideoAdapter adapter = new VideoAdapter(getActivity());
        listView.setAdapter(adapter);

        //点击每一项侯进入Video播放界面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VideoFragment.this.getContext(),VideoActivity.class);
                intent.putExtra("position",position + "");
                startActivity(intent);
            }
        });
        return view;
    }
}
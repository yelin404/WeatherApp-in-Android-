package com.example.mediaplayer.Music;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mediaplayer.Adapter.MusicAdapter;
import com.example.mediaplayer.R;


public class MusicFragment extends Fragment {
    private ListView listView;


    public MusicFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_music,null);
        listView = view.findViewById(R.id.music_lv);

        //设置适配器
        MusicAdapter adapter = new MusicAdapter(getActivity());
        listView.setAdapter(adapter);

        //点击每一项侯进入Music播放界面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MusicFragment.this.getContext(),MusicActivity.class);
                intent.putExtra("position",position + "");
                startActivity(intent);
            }
        });
        return view;
    }
}
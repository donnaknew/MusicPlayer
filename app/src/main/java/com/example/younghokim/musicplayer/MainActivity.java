package com.example.younghokim.musicplayer;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer;
    Button startBtn;
    Button stopBtn;
    EditText singerET;
    EditText titleET;
    ListView list_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer = new MediaPlayer();

        startBtn = (Button)findViewById(R.id.Start_Button);
        stopBtn = (Button)findViewById(R.id.Stop_Button);
        list_music = (ListView)findViewById(R.id.listView_1);
/*
        try{
            AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.ireallylikeyou);
            AssetFileDescriptor af = getResources().openRawResourceFd(R.raw.lionheart);
           // AssetFileDescriptor a = getResources().openRawResourceFd(R.raw.boysandgirls);
            mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mPlayer.setDataSource(af.getFileDescriptor(), af.getStartOffset(), af.getLength());
           // mPlayer.setDataSource(a.getFileDescriptor(), a.getStartOffset(), a.getLength());
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
*/
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    mPlayer.prepare();
                    mPlayer.start();
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.stop();
            }
        });
        final String[] musicList = {"Boys and Girls", "Lion Heart", "I Really Like You"};
        ArrayAdapter<String> musicAdapter;
        musicAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, musicList);
        list_music.setAdapter(musicAdapter);
        list_music.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String fname = list_music.getTextFilter().toString().toLowerCase();
                //int resId = getResources().getIdentifier(fname, "raw", getPackageName());
                try{
                    if(position == 0){
                        mPlayer.reset();
                        Toast.makeText(getApplicationContext(), "positon 0000", Toast.LENGTH_SHORT).show();
                        AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.boysandgirls);
                        mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    }
                    else if(position == 1) {
                        mPlayer.reset();
                        Toast.makeText(getApplicationContext(), "positon 1111", Toast.LENGTH_SHORT).show();
                        AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.lionheart);
                        mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    }else if(position == 2){
                        mPlayer.reset();
                        Toast.makeText(getApplicationContext(), "positon 2222", Toast.LENGTH_SHORT).show();
                        AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.ireallylikeyou);
                        mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    }
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "ERROR!", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

                Toast.makeText(getApplicationContext(), "click: " + position, Toast.LENGTH_LONG).show();
            }
        });
    }
}

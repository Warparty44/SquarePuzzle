package com.badlogic.androidgames.ch04androidbasics;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by Josh on 6/19/2017.
 */

public class SoundPoolTest extends AppCompatActivity implements View.OnTouchListener {

    SoundPool soundPool;
    int explosionId = -1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        setContentView(textView);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);

        try {
            AssetManager assetManager = this.getAssets();
            AssetFileDescriptor descriptor = assetManager.openFd("explosion.ogg");
            explosionId = soundPool.load(descriptor,1);
        } catch (IOException e) {
            textView.setText("Couldn't load sound effect from asset, " + e.getMessage());
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (explosionId != -1) {
                soundPool.play(explosionId, 1, 1, 0, 0, 1);
            }
        }
        return true;
    }

}

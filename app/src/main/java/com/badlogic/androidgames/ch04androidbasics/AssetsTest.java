package com.badlogic.androidgames.ch04androidbasics;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by pippijr0 on 6/7/2017.
 */

public class AssetsTest extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        setContentView(textView);

        AssetManager assetManager = this.getAssets();
        InputStream inputStream = null;

        try {
            inputStream = assetManager.open("dir/dir2/texts/myawesometext.txt");
            String text = loadTextFile(inputStream);
            textView.setText(text);

        } catch (IOException e) {
           textView.setText("Couldn't load file");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    textView.setText("Couldn't close file");
                }
            }
        }
    }

    public String loadTextFile(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int len = 0;

        while ((len = inputStream.read(bytes)) > 0)
            byteStream.write(bytes,0,len);
        return new String(byteStream.toByteArray(), "UTF8");
    }


}

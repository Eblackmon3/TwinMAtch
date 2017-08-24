package com.example.ericblackmon.twinmatch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author: Eric Blackmon
 * Date: 24/08/2017
 */

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image_view);
        textView = (TextView) findViewById(R.id.image_stream_indicator);
        // width and height will be at least 600px long (optional).
        PickImage.setMinQuality(600, 600);
    }

    //Bitmap is what will be grabbing and placing into the database
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap = PickImage.getImageFromResult(this, requestCode, resultCode, data);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        InputStream is = PickImage.getInputStreamFromResult(this, requestCode, resultCode, data);
        if (is != null) {
            textView.setText("Got input stream!");
            try {
                is.close();
            } catch (IOException ex) {
                // ignore
            }
        } else {
            textView.setText("Failed to get input stream!");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onPickImage(View view) {
        PickImage.pickImage(this, "Select your image:");
    }
}
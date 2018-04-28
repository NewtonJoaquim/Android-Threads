package com.example.newton.threads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDownload;
    ImageView imgView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDownload = (Button)findViewById(R.id.downloadButton);
        imgView = (ImageView) findViewById(R.id.imgView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        btnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.downloadButton:
                progressBar.setVisibility(View.VISIBLE);
                DoOnBack loadImage = new DoOnBack();
                loadImage.execute("http://inacio.com.br/wp-content/uploads/2013/02/logo_ufc-virtual.jpg");
                break;
        }
    }

    private class DoOnBack extends AsyncTask<String, Void, Bitmap> {


        private Bitmap loadImageFromNetwork(String url) {
            try {
                Bitmap bitmap = BitmapFactory
                        .decodeStream((InputStream) new URL(url)
                                .getContent());
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected Bitmap doInBackground(String[] params) {
            return loadImageFromNetwork(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            imgView.setImageBitmap(result);
            progressBar.setVisibility(View.INVISIBLE);
            Log.v("tag","Rodando após o back");
        }

    }
}



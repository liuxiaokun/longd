package com.fred.longd.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.fred.longd.R;
import com.fred.longd.task.DownloadTask;


public class SplashActivity extends Activity implements View.OnClickListener {

    private Button download;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        download = (Button) findViewById(R.id.download);
        download.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.download:

                DownloadTask downloadTask = new DownloadTask(progressBar, download);
                downloadTask.execute("www.baidu.com");
                break;
            case R.id.progress_bar:
                break;

            default:
                break;
        }
    }
}

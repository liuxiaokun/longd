package com.fred.longd.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fred.longd.R;
import com.fred.longd.task.DownloadTask;


public class SplashActivity extends Activity implements View.OnClickListener {

    private Button download;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        download = (Button) findViewById(R.id.download);
        download.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("Downloading...");
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.download:

                DownloadTask downloadTask = new DownloadTask(progressDialog, download);
                downloadTask.execute("www.baidu.com");
                break;
            case R.id.progress_bar:
                break;

            default:
                break;
        }
    }
}

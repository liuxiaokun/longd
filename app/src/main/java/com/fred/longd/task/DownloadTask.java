package com.fred.longd.task;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Button;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Fred Liu(liuxiaokun0410@gmail.com)
 * @version 1.0
 * @date 2015/8/4
 */
public class DownloadTask extends AsyncTask<String, Integer, String> {

    private ProgressDialog progressDialog;
    private Button download;

    public DownloadTask(ProgressDialog progressDialog, Button download) {
        this.progressDialog = progressDialog;
        this.download = download;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
        download.setBackgroundColor(Color.parseColor("#FF000000"));
        download.setClickable(false);
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            URI uri = new URI(params[0]);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        int i = 0;

        while (i <= 100) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i++);
        }
        return "SUCCESS";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if ("SUCCESS".equals(s)) {
            download.setText("COMPLETED!");
        }
        download.setClickable(true);
        download.setBackgroundColor(Color.parseColor("#F5F5DC"));
        progressDialog.dismiss();
    }


    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

}

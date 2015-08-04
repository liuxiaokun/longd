package com.fred.longd.task;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ProgressBar;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Fred Liu(liuxiaokun0410@gmail.com)
 * @version 1.0
 * @date 2015/8/4
 */
public class DownloadTask extends AsyncTask<String, Integer, String> {

    private ProgressBar progressBar;
    private Button download;

    public DownloadTask(ProgressBar progressBar, Button download) {
        this.progressBar = progressBar;
        this.download = download;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
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
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if ("SUCCESS".equals(s)) {
            download.setText("COMPLETED!");
        }
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

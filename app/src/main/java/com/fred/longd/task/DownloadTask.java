package com.fred.longd.task;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Button;

import com.fred.longd.utils.LogUtil;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

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
            URL url = new URL(params[0]);
            URLConnection connection = url.openConnection();
            connection.connect();
            //这将是有用的，这样你可以显示一个典型的0-100%的进度条
            int fileLength = connection.getContentLength();

            // 下载文件
            InputStream input = new BufferedInputStream(url.openStream());
            OutputStream output = new FileOutputStream("mnt/sdcard/aaa.apk");

            byte data[] = new byte[1024];
            long total = 0;
            int count;

            while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
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

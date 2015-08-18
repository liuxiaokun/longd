package com.fred.longd.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.fred.longd.R;
import com.fred.longd.task.DownloadTask;
import com.fred.longd.utils.ScreenUtil;
import com.fred.longd.view.SimpleToast;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button download;
    private ProgressDialog progressDialog;
    private DatePicker datePicker;
    private Button other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        download = (Button) findViewById(R.id.download);
        download.setOnClickListener(this);
        datePicker = (DatePicker) findViewById(R.id.date_picker);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setIcon(R.drawable.logo);
        progressDialog.setMessage("提示信息");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("正在升级");

        other = (Button) findViewById(R.id.other);
        other.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.download:

                DownloadTask downloadTask = new DownloadTask(progressDialog, download);
                downloadTask.execute("http://gdown.baidu.com/data/wisegame/3f2c972459995277/jifengshichang.apk");
                break;
            case R.id.other:

                String str = ScreenUtil.getScreenHeight(this) + " :" + ScreenUtil.getScreenWidth(this);
                Toast.makeText(this, str, Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(this, OtherActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // disable the back button.
        SimpleToast.shortShow(this, "退出退出退出退出");
        super.onBackPressed();
    }
}

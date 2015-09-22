package com.fred.longd.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import com.fred.longd.utils.UpgradeUtil;
import com.fred.longd.view.SimpleToast;

import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button download;
    private ProgressDialog progressDialog;
    private Button datePickerDialog;
    private Button other;
    private Button popupWindow;
    private Button install;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        addShortcut("fred");

        download = (Button) findViewById(R.id.download);
        download.setOnClickListener(this);
        datePickerDialog = (Button) findViewById(R.id.date_picker_dialog);
        datePickerDialog.setOnClickListener(this);


        other = (Button) findViewById(R.id.other);
        other.setOnClickListener(this);


        popupWindow = (Button) findViewById(R.id.popup_window);
        popupWindow.setOnClickListener(this);

        install = (Button) findViewById(R.id.install);
        install.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.download:

                DownloadTask downloadTask = new DownloadTask(this, download);
                downloadTask.execute("http://gdown.baidu.com/data/wisegame/3f2c972459995277/jifengshichang.apk");
                break;
            case R.id.other:

                String str = ScreenUtil.getScreenHeight(this) + " :" + ScreenUtil.getScreenWidth(this);
                Toast.makeText(this, str, Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(this, OtherActivity.class);
                startActivity(intent);
                break;

            case R.id.date_picker_dialog:

                Calendar calendar = Calendar.getInstance(Locale.CHINA);

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                    }
                }, year, month, day);
                dpd.show();

                break;
            case R.id.popup_window:

                break;

            case R.id.install:
                String path = "mnt/sdcard/aaa.apk";

                if (UpgradeUtil.install(path, getApplicationContext())) {
                    SimpleToast.shortShow(this, "success install");
                } else {
                    SimpleToast.shortShow(this, "success fail");
                }
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


    private void addShortcut(String name) {

        String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
        Intent addShortcutIntent = new Intent(ACTION_ADD_SHORTCUT);

        // 不允许重复创建
        addShortcutIntent.putExtra("duplicate", false);// 经测试不是根据快捷方式的名字判断重复的
        // 应该是根据快链的Intent来判断是否重复的,即Intent.EXTRA_SHORTCUT_INTENT字段的value
        // 但是名称不同时，虽然有的手机系统会显示Toast提示重复，仍然会建立快链
        // 屏幕上没有空间时会提示
        // 注意：重复创建的行为MIUI和三星手机上不太一样，小米上似乎不能重复创建快捷方式

        // 名字
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // 图标
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(MainActivity.this,
                        R.drawable.ic_launcher));

        // 设置关联程序
        Intent launcher = new Intent(this, SplashActivity.class);
        launcher.setAction(Intent.ACTION_MAIN);
        launcher.addCategory(Intent.CATEGORY_LAUNCHER);

        addShortcutIntent
                .putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcher);

        sendBroadcast(addShortcutIntent);
    }


}

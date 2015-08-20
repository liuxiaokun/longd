package com.fred.longd.activities;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import com.fred.longd.R;
import com.fred.longd.view.CustomizedProgressDialog;

/**
 * @author Fred Liu(liuxiaokun0410@gmail.com)
 * @version v1.0
 * @since 2015/8/12
 */
public class OtherActivity extends Activity implements View.OnClickListener {

    private Button vibrtorButton;

    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        vibrtorButton = (Button) findViewById(R.id.vibrator);
        vibrtorButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {


            case R.id.vibrator:

                vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                vibrator.vibrate(500);

                new CustomizedProgressDialog(this, "loading...").show();
                break;
        }
    }
}

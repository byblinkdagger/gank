package com.blink.dagger.gank.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.blink.dagger.gank.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void finishSelf () {
        finish();
        overridePendingTransition(R.anim.ac_still,R.anim.ac_center_out);
    }
}

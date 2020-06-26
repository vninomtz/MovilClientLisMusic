package com.uv.lismusicjava.ui.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.uv.lismusicjava.R;

public class PopUpTermsAndConditions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_terms_and_conditions);

        DisplayMetrics metricsWindow  = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metricsWindow);

        int width = metricsWindow.widthPixels;
        int height = metricsWindow.heightPixels;

        getWindow().setLayout((int)(width * 0.6), (int)(height * 0.5));
    }
}

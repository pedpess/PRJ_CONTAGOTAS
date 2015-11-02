package com.contagotas.contagotas.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.contagotas.contagotas.R;

/**
 * Created by admin on 10/22/15.
 */
public class BaseActivity extends AppCompatActivity {

    public Toolbar toolbar;

    public Toolbar createToolbar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.getBackground().setAlpha(255);
        toolbar.setTitle(title);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        setSupportActionBar(toolbar);
        return toolbar;
    }


}

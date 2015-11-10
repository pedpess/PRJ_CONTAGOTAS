package com.contagotas.contagotas.activities;

import android.content.res.Resources;
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
        setSupportActionBar(toolbar);
        return toolbar;
    }

    public Toolbar createToolbarWithBack(String title){
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.getBackground().setAlpha(255);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return toolbar;
    }


}

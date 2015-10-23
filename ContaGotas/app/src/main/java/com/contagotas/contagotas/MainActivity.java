package com.contagotas.contagotas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.contagotas.contagotas.Util.SessionManager;
import com.contagotas.contagotas.activities.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.imgLogoWelcome)
    ImageView imgLogoWelcome;
    @InjectView(R.id.txtWelcome)
    TextView txtWelcome;
    @InjectView(R.id.btn_welcome_next)
    Button btnWelcomeNext;
    @InjectView(R.id.container_welcome)
    RelativeLayout containerWelcome;
    @InjectView(R.id.container_data)
    RelativeLayout containerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        createToolbar("Conta Gotas");

        if (SessionManager.getFromPrefs(getApplicationContext(), SessionManager.PREFS_FIRST_TIME_OPEN, "0").equals("0")) {
            containerWelcome.setVisibility(View.VISIBLE);
            containerData.setVisibility(View.GONE);
        }

        btnWelcomeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager.saveToPrefs(getApplicationContext(), SessionManager.PREFS_FIRST_TIME_OPEN, "1");
                containerWelcome.setVisibility(View.GONE);
                containerData.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

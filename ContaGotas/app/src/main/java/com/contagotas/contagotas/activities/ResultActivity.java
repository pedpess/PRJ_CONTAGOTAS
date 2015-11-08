package com.contagotas.contagotas.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.contagotas.contagotas.MainActivity;
import com.contagotas.contagotas.MyApplication;
import com.contagotas.contagotas.R;
import com.contagotas.contagotas.data.DAO.MediaGastos;
import com.contagotas.contagotas.data.DAO.MediaGastosDao;

import at.grabner.circleprogress.CircleProgressView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class ResultActivity extends BaseActivity {


    private int contador = 0;

    @InjectView(R.id.txtTitleResult)
    TextView txtTitleResult;
    @InjectView(R.id.txtDescriptionResult)
    TextView txtDescriptionResult;
    @InjectView(R.id.progressBarResult)
    CircleProgressView progressBarResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.inject(this);

        createToolbarWithBack(getResources().getString(R.string.activity_result_toolbar));

        long idResult = getIntent().getExtras().getLong(MainActivity.EXTRA_ID_RESULT_MEDIA_GASTOS);
        setDataProgressBar(idResult);
    }

    private void setDataProgressBar(long idResult) {
        progressBarResult.setMaxValue(6000);

        MediaGastosDao mediaGastosDao = MyApplication.mDaoSession.getMediaGastosDao();
        MediaGastos mediaGastos = mediaGastosDao.load(idResult);
        progressBarResult.setTextColor(getResources().getColor(R.color.primary_text));
        progressBarResult.setShowUnit(true);
        progressBarResult.setUnit("L");
        progressBarResult.setValueAnimated(0, mediaGastos.getTotal(), 1000);
        progressBarResult.setAutoTextSize(true);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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

package com.contagotas.contagotas.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.contagotas.contagotas.MainActivity;
import com.contagotas.contagotas.MyApplication;
import com.contagotas.contagotas.R;
import com.contagotas.contagotas.activities.Adapters.RecyclerViewHistoricAdapter;
import com.contagotas.contagotas.data.DAO.MediaGastos;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HistoricActivity extends BaseActivity {

    @InjectView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);
        ButterKnife.inject(this);

        createToolbarWithBack(getResources().getString(R.string.title_activity_historic));

        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        List<MediaGastos> listMediaGastos = MyApplication.mDaoSession.getMediaGastosDao().loadAll();
        RecyclerViewHistoricAdapter recyclerViewHistoricAdapter = new RecyclerViewHistoricAdapter(getApplicationContext(), listMediaGastos);
        rv.setAdapter(recyclerViewHistoricAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_historic, menu);
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

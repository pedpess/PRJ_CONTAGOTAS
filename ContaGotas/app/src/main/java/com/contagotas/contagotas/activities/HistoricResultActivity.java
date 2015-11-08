package com.contagotas.contagotas.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.contagotas.contagotas.MyApplication;
import com.contagotas.contagotas.R;
import com.contagotas.contagotas.activities.Adapters.RecyclerViewHistoricAdapter;
import com.contagotas.contagotas.data.DAO.DetalheGastos;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HistoricResultActivity extends AppCompatActivity {

    @InjectView(R.id.textview_lavadeira)
    TextView textviewLavadeira;
    @InjectView(R.id.textview_tanque)
    TextView textviewTanque;
    @InjectView(R.id.textview_privada)
    TextView textviewPrivada;
    @InjectView(R.id.textview_pia_banheiro)
    TextView textviewPiaBanheiro;
    @InjectView(R.id.textview_chuveiro)
    TextView textviewChuveiro;
    @InjectView(R.id.textview_pia_cozinha)
    TextView textviewPiaCozinha;
    @InjectView(R.id.textview_lava_louca)
    TextView textviewLavaLouca;
    @InjectView(R.id.txtResultHistoric)
    TextView txtResultHistoric;
    @InjectView(R.id.ll_input_data)
    LinearLayout llInputData;
    @InjectView(R.id.container_data)
    RelativeLayout containerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic_result);
        ButterKnife.inject(this);

        int key = getIntent().getExtras().getInt(RecyclerViewHistoricAdapter.TAG_POSITION);

        DetalheGastos detalheGastos = MyApplication.mDaoSession.getDetalheGastosDao().load(Long.parseLong(String.valueOf(key)));

        textviewLavadeira.setText(detalheGastos.getMaquina().toString());
        textviewChuveiro.setText(detalheGastos.getChuveiro().toString());
        textviewLavaLouca.setText(detalheGastos.getLava_louca().toString());
        textviewPiaBanheiro.setText(detalheGastos.getTorneira().toString());
        textviewPrivada.setText(detalheGastos.getPrivada().toString());
        textviewPiaCozinha.setText(detalheGastos.getPia().toString());
        textviewTanque.setText(detalheGastos.getTanque().toString());

        txtResultHistoric.setText(detalheGastos.getMediaGastos().getTotal().toString() + "l");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_historic_result, menu);
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

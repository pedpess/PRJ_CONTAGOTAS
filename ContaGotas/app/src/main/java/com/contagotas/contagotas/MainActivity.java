package com.contagotas.contagotas;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.contagotas.contagotas.Util.DateHelper;
import com.contagotas.contagotas.Util.SessionManager;
import com.contagotas.contagotas.activities.BaseActivity;
import com.contagotas.contagotas.activities.HistoricActivity;
import com.contagotas.contagotas.activities.ResultActivity;
import com.contagotas.contagotas.data.DAO.DetalheGastos;
import com.contagotas.contagotas.data.DAO.DetalheGastosDao;
import com.contagotas.contagotas.data.DAO.MediaGastos;
import com.contagotas.contagotas.data.DAO.MediaGastosDao;

import java.util.Date;

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
    @InjectView(R.id.ll_text_input_data)
    LinearLayout llTextInputData;
    @InjectView(R.id.edit_text_lavadeira)
    EditText editTextLavadeira;
    @InjectView(R.id.edit_text_tanque)
    EditText editTextTanque;
    @InjectView(R.id.edit_text_privada)
    EditText editTextPrivada;
    @InjectView(R.id.edit_text_pia_banheiro)
    EditText editTextPiaBanheiro;
    @InjectView(R.id.edit_text_chuveiro)
    EditText editTextChuveiro;
    @InjectView(R.id.edit_text_pia_cozinha)
    EditText editTextPiaCozinha;
    @InjectView(R.id.edit_text_lava_louca)
    EditText editTextLavaLouca;
    @InjectView(R.id.ll_input_data)
    LinearLayout llInputData;
    @InjectView(R.id.btnSaveData)

    Button btnSaveData;
    public static final String EXTRA_ID_RESULT_MEDIA_GASTOS = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        createToolbar(getResources().getString(R.string.app_name));

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

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int maquina = (TextUtils.isEmpty(editTextLavadeira.getText().toString()) ? 0 : Integer.parseInt(editTextLavadeira.getText().toString()));
                int tanque = (TextUtils.isEmpty(editTextTanque.getText().toString()) ? 0 : Integer.parseInt(editTextTanque.getText().toString()));
                int privada = (TextUtils.isEmpty(editTextPrivada.getText().toString()) ? 0 : Integer.parseInt(editTextPrivada.getText().toString()));
                int torneira = (TextUtils.isEmpty(editTextPiaBanheiro.getText().toString()) ? 0 : Integer.parseInt(editTextPiaBanheiro.getText().toString()));
                int chuveiro = (TextUtils.isEmpty(editTextChuveiro.getText().toString()) ? 0 : Integer.parseInt(editTextChuveiro.getText().toString()));
                int pia = (TextUtils.isEmpty(editTextPiaCozinha.getText().toString()) ? 0 : Integer.parseInt(editTextPiaCozinha.getText().toString()));
                int lavaLouca = (TextUtils.isEmpty(editTextLavaLouca.getText().toString()) ? 0 : Integer.parseInt(editTextLavaLouca.getText().toString()));

                MediaGastos mediaGastos = new MediaGastos(null, calculaMediaGastos(maquina, tanque, privada, torneira, chuveiro, pia, lavaLouca), DateHelper.getDateCurret(), false);
                MediaGastosDao mediaGastosDao = MyApplication.mDaoSession.getMediaGastosDao();
                long idMediaGastos = mediaGastosDao.insert(mediaGastos);
                DetalheGastos detalheGastos = new DetalheGastos(null, idMediaGastos, maquina, tanque, privada, torneira, chuveiro, pia, lavaLouca, false);
                DetalheGastosDao detalheGastosDao = MyApplication.mDaoSession.getDetalheGastosDao();
                detalheGastosDao.insert(detalheGastos);

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra(EXTRA_ID_RESULT_MEDIA_GASTOS, idMediaGastos);
                startActivity(intent);

            }
        });

    }

    private float calculaMediaGastos(int maquina, int tanque, int privada, int torneira, int chuveiro, int pia, int lavaLouca){

        float resultado = (maquina * 19) + (tanque * 15) + (privada * 16) * (torneira * 15) + (chuveiro * 12) + (pia * 15) + (lavaLouca * 2);

        return resultado;

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
        if (id == R.id.action_history) {
            startActivity(new Intent(this, HistoricActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


}

package br.com.academia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AulasActivity extends AppCompatActivity {
    private Button btPilates, btLuta, btTreinos, btVoltarAulas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aulas);

            btPilates = findViewById(R.id.btPilates);
            btLuta = findViewById(R.id.btLuta);
            btTreinos = findViewById(R.id.btTreinos);
            btVoltarAulas = findViewById(R.id.btVoltar);

            btPilates.setOnClickListener ( v->
                    startActivity(new Intent(AulasActivity.this, PilatesActivity.class))
            );
            btLuta.setOnClickListener(v ->
                startActivity(new Intent(AulasActivity.this, LutaActivity.class))
            );
            btTreinos.setOnClickListener(v ->
                startActivity(new Intent(AulasActivity.this, TreinosActivity.class))
            );

            btVoltarAulas.setOnClickListener(v -> startActivity(
                    new Intent(AulasActivity.this, MainActivity.class)));
            }

    }

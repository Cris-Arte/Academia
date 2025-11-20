package br.com.academia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btAulas, btCadastro, btFuncionarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btAulas = findViewById(R.id.btAulas);
        btAulas.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AulasActivity.class))
        );

        btCadastro = findViewById(R.id.btCadastro);
        btCadastro.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CadastroActivity.class))
        );

        //Quando clica no botão Funcionarios na tela principal
        //Cria um novo objeto SenhaDialog e mostra o dialog na tela
        btFuncionarios = findViewById(R.id.btVisualizar);
        btFuncionarios.setOnClickListener(v -> {
            SenhaDialog senhaDialog = new SenhaDialog(MainActivity.this);
            senhaDialog.show();
        });

    }
}

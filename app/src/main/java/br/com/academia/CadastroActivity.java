package br.com.academia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {
    private EditText etNome;
    private EditText etTelefone;
    private RadioGroup rgPlanos;
    private Button btEnviar;
    private Button btVoltar;

    private String planoSelecionado = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        rgPlanos = findViewById(R.id.rgPlanos);
        btEnviar = findViewById(R.id.btEnviar);
        btVoltar = findViewById(R.id.btVoltar);

        rgPlanos.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int btSelecionadoId) {

                RadioButton radioSelecionado = findViewById(btSelecionadoId);
                planoSelecionado = radioSelecionado.getText().toString();
            }
        });

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString().trim();
                String telefone = etTelefone.getText().toString().trim();

                if (nome.isEmpty()) {
                    etNome.setError("Digite o nome");
                    return;
                }

                if (telefone.isEmpty() || telefone.length() < 11 || telefone.length() > 15) {
                    etTelefone.setError("Digite um telefone válido");
                    return;
                }

                if (planoSelecionado.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Selecione um plano", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cliente novoCliente = new Cliente(nome, telefone, planoSelecionado);
                CriarBancoDados banco = new CriarBancoDados(CadastroActivity.this);
                banco.adicionarCliente(novoCliente);
                Toast.makeText(CadastroActivity.this, "Cadastro enviado com sucesso!", Toast.LENGTH_LONG).show();
                limparCampos();
            }
        });

        btVoltar.setOnClickListener(v -> finish());
    }
    private void limparCampos() {
        etNome.setText("");
        etTelefone.setText("");
        rgPlanos.clearCheck();
        planoSelecionado = "";
    }

}



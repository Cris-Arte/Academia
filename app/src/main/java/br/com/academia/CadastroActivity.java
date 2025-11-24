package br.com.academia;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.academia.AssistenteDB;

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
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                // group: é o próprio RadioGroup (rgPlanos)
                // checkedId: é o ID do RadioButton que foi selecionado

                RadioButton radioSelecionado = findViewById(checkedId);
                planoSelecionado = radioSelecionado.getText().toString();

                System.out.println("Usuário selecionou: " + planoSelecionado);
            }
        });

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString().trim();
                String telefone = etTelefone.getText().toString().trim();

                // Validar campos
                if (nome.isEmpty()) {
                    etNome.setError("Digite o nome");
                    return;
                }

                if (telefone.isEmpty() || telefone.length() < 14 || telefone.length() > 15) {
                    etTelefone.setError("Digite um telefone válido");
                    return;
                }

                if (planoSelecionado.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Selecione um plano", Toast.LENGTH_SHORT).show();
                    return;
                }

                AssistenteDB assistente = new AssistenteDB(CadastroActivity.this);
                long id = assistente.adicionarCliente(nome, telefone, planoSelecionado);

                if (id != -1) {
                    Toast.makeText(CadastroActivity.this, "Cadastro enviado com sucesso! ID: " + id, Toast.LENGTH_LONG).show();
                    limparCampos();
                } else {
                    Toast.makeText(CadastroActivity.this, "Erro ao salvar cadastro", Toast.LENGTH_SHORT).show();
                }
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



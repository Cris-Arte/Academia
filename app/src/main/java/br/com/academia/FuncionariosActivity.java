package br.com.academia;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FuncionariosActivity extends AppCompatActivity {
    private Button btVisualizar, btExcluir, btVoltarFunc;
    private ListView lvVisualizar;
    private List<Integer> idsClientes = new ArrayList<>();
    private int clienteSelecionado = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_funcionarios);
        btVisualizar = findViewById(R.id.btVisualizar);
        lvVisualizar = findViewById(R.id.lvVisualizar);
        btExcluir = findViewById(R.id.btExcluir);
        btVoltarFunc = findViewById(R.id.btVoltar);

        btVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarClientes();
            }
        });

        lvVisualizar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clienteSelecionado = position;
                int idCliente = idsClientes.get(clienteSelecionado);
                Toast.makeText(FuncionariosActivity.this, "Cliente Selecionado: " + idCliente,
                         Toast.LENGTH_SHORT).show();
            }
        });
        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clienteSelecionado == -1) {
                    Toast.makeText(FuncionariosActivity.this,
                            "Selecione um cliente primeiro",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                int idCliente = idsClientes.get(clienteSelecionado);
                CriarBancoDados banco = new CriarBancoDados(FuncionariosActivity.this);
                banco.excluirCliente(idCliente);
                Toast.makeText(FuncionariosActivity.this,
                        "Cliente excluído com sucesso!",
                        Toast.LENGTH_SHORT).show();
                carregarClientes();
            }
        });
        btVoltarFunc.setOnClickListener(v -> startActivity(new Intent(FuncionariosActivity.this, MainActivity.class)));
    }

    private void carregarClientes(){
        CriarBancoDados banco = new CriarBancoDados(this);
        List<Cliente> clientes = banco.getAllClientesAsObjects();

        List<String> clientesFormatados = new ArrayList<>();
        idsClientes.clear();
        clienteSelecionado = -1;

        for (Cliente cliente : clientes){
            String clienteFormatado = cliente.getNome() + " - " +
                                      cliente.getPlano() + " - " +
                                      cliente.getTelefone();
            clientesFormatados.add(clienteFormatado);
            idsClientes.add(cliente.getId());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, clientesFormatados);
        lvVisualizar.setAdapter(adapter);
    }


}
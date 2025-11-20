package br.com.academia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TreinosActivity extends AppCompatActivity {
    private Button btAnterior, btProximo, btVoltar, btCadastro;
    private ImageView ivImagem;

    private int[] imagens = {
            R.drawable.treino,
            R.drawable.treino1,
            R.drawable.treino2,
            R.drawable.treino3,
            R.drawable.treino4
    };

    int imagemAtual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_luta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btAnterior = findViewById(R.id.btAnterior);
        btProximo = findViewById(R.id.btProximo);
        ivImagem = findViewById(R.id.ivImagem);
        btVoltar = findViewById(R.id.btVoltar);
        btCadastro = findViewById(R.id.btCadastro);

        ivImagem.setBackgroundResource(imagens[imagemAtual]);

        btAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anterior();
            }
        });
        btProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proximo();
            }
        });
        btVoltar.setOnClickListener(v -> finish());
        btCadastro.setOnClickListener(v -> startActivity(
                new Intent(TreinosActivity.this, CadastroActivity.class)));
    }

    private void proximo() {
        imagemAtual++;
        if (imagemAtual >= imagens.length) {
            imagemAtual = 0;
        }
        ivImagem.setBackgroundResource(imagens[imagemAtual]);
    }

    private void anterior() {
        imagemAtual--;
        if (imagemAtual < 0) {
            imagemAtual = imagens.length - 1;
        }
        ivImagem.setBackgroundResource(imagens[imagemAtual]);
    }
}
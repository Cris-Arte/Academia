package br.com.academia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LutaActivity extends AppCompatActivity {
    private Button btAnterior, btProximo, btVoltar, btCadastro;
    private ImageView ivImagem;
    private int[] imagens = {
            R.drawable.luta,
            R.drawable.luta1,
            R.drawable.luta2,
            R.drawable.luta3,
            R.drawable.luta4,
            R.drawable.luta5,
            R.drawable.luta6
    };
    private int imagemAtual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_luta);

        btProximo = findViewById(R.id.btProximo);
        btAnterior = findViewById(R.id.btAnterior);
        btVoltar = findViewById(R.id.btVoltar);
        btCadastro = findViewById(R.id.btCadastro);
        ivImagem = findViewById(R.id.ivImagem);

        ivImagem.setBackgroundResource(imagens[imagemAtual]);

        btProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proximo();
            }
        });
        btAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anterior();
            }
        });
        btVoltar.setOnClickListener(v -> finish());
        btCadastro.setOnClickListener(v -> startActivity(
                new Intent(LutaActivity.this, CadastroActivity.class)));
    }
    private void proximo(){
        imagemAtual++;
        if (imagemAtual >= imagens.length) {
            imagemAtual = 0;
        }
        ivImagem.setBackgroundResource(imagens[imagemAtual]);
    }
    private void anterior(){
        imagemAtual--;
        if (imagemAtual < 0 ) {
            imagemAtual = imagens.length -1;
        }
        ivImagem.setBackgroundResource(imagens[imagemAtual]);
        //Toast.makeText(this, "Imagem " + (imagemAtual + 1) + " de " + imagens.length, Toast.LENGTH_SHORT).show();
    }
}
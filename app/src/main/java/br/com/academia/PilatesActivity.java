package br.com.academia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PilatesActivity extends AppCompatActivity {
    private Button btAnterior, btProximo, btVoltar, btCadastro;
    private ImageView ivImagem;

    private int[] imagens = {
            R.drawable.pilates,
            R.drawable.pilates1,
            R.drawable.pilates2,
            R.drawable.pilates3,
            R.drawable.pilates4
    };
    private int imagemAtual = 0;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pilates);

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
        btCadastro.setOnClickListener(v -> startActivity(
                new Intent(PilatesActivity.this, CadastroActivity.class)));
        btVoltar.setOnClickListener(v -> finish());
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
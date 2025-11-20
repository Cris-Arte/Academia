package br.com.academia;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SenhaDialog extends Dialog {
    private EditText etSenha;
    private Button btAcessar, btCancelar;
    private static final String SENHA_PADRAO = "123456";

    public SenhaDialog (Context context){
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_senha);
        //encontrar os componentes
        etSenha = findViewById(R.id.etSenha);
        btAcessar = findViewById(R.id.btAcessar);
        btCancelar = findViewById(R.id.btCancelar);
        // Configurar eventos dos botões
        btAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String senhaDigitada = etSenha.getText().toString().trim();
                if (senhaDigitada.equals(SENHA_PADRAO)) {
                    // o dismiss fecha a caixa de dialogo
                    dismiss();
                    Intent intent = new Intent(getContext(), FuncionariosActivity.class);
                    getContext().startActivity(intent);
                } else {
                    etSenha.setError("Senha incorreta!");
                    etSenha.setText("");
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fecha e destrói o Dialog
                dismiss();
                if (getContext() instanceof FuncionariosActivity) {
                    ((FuncionariosActivity) getContext()).finish();
                }
            }
        });
    }
}
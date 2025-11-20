package br.edu.cruzeirodosul.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Constante com o nome totalmente qualificado do nosso aplicativo
    public static final String EXTRA_MESSAGE = "br.edu.cruzeirodosul.intentexample.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    } // onCreate()

    /**
     * Método chamado quando o usuário clica no botão Enviar
     * @param view O botão Enviar
     */
    public void envieMensagem(View view) {
        Intent intent = new Intent(this, MostreMensagemActivity.class);
        EditText textoEditText = findViewById(R.id.textoEditText);
        String mensagem = textoEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, mensagem);
        startActivity(intent);
    }
} // class MainActivity











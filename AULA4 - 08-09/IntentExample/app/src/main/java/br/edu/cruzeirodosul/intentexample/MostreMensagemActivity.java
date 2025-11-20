package br.edu.cruzeirodosul.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MostreMensagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostre_mensagem);

        // Obtém o intent que iniciou esta activity e extrai o string
        Intent intent = getIntent();
        String mensagem = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Captura o textoTextView do layout e define o string como seu Text
        TextView textoTextView = findViewById(R.id.textoTextView);
        textoTextView.setText(mensagem);
    } //onCreate()
} // class MostreMensagemActivity






package br.edu.cruzeirodosul.calculadoradegorjetas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    // Constantes usadas ao se salvar/restaurar o estado do app:
    private static final String CONTA = "CONTA";
    private static final String PERCENTUAL = "PERCENTUAL";

    // Atributos que armazenam os valores que devem ser mantidos quando o app reinicia:
    private double conta;
    private double percentual;

    // Atributos que armazenarão referências aos componentes gráficos (views) da interface gráfica (activity)
    private EditText contaEditText;
    private EditText gorjeta5EditText;
    private EditText gorjeta10EditText;
    private EditText gorjeta15EditText;
    private SeekBar percentualSeekBar;
    private EditText percentualEditText;
    private EditText gorjetaEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenção das referências das views da activity (GUI)
        contaEditText = findViewById(R.id.contaEditText);
        gorjeta5EditText = findViewById(R.id.gorjeta5EditText);
        gorjeta10EditText = findViewById(R.id.gorjeta10EditText);
        gorjeta15EditText = findViewById(R.id.gorjeta15EditText);
        percentualSeekBar = findViewById(R.id.percentualSeekBar);
        percentualEditText = findViewById(R.id.percentualEditText);
        gorjetaEditText = findViewById((R.id.gorjetaEditText));

        // Registra os ouvintes de eventos para as views interativas
        contaEditText.addTextChangedListener(ouvinteContaEditText);
        percentualSeekBar.setOnSeekBarChangeListener(ouvintePercentualSeekBar);

        // Verifica se o app acabou de ser iniciado ou se está sendo restaurado
        if(savedInstanceState == null) {
            conta = 0;
            percentual = 7;
        } else {
            // O app está sendo restaurado da memória, ou seja, não está sendo executado a partir do zero. Assim, os valores de conta e percentual são restaurados.
            conta = savedInstanceState.getDouble(CONTA);
            percentual = savedInstanceState.getDouble(PERCENTUAL);
        }
        // Atualiza os componentes gráficos com os valores atualizados:
        contaEditText.setText(String.format("%.2f", conta));
        percentualSeekBar.setProgress((int)percentual);
    } // onCreate()

    /**
     * Atualiza o valor das gorjetas padrão
     */
    private void atualizaGorjetas() {
        double [] gorjetas = Calculadora.gorjeta(conta);
        gorjeta5EditText.setText(String.format("%.2f", gorjetas[0]));
        gorjeta10EditText.setText(String.format("%.2f", gorjetas[1]));
        gorjeta15EditText.setText(String.format("%.2f", gorjetas[2]));
    }

    /**
     * Atualiza o valor da gorjeta personalizada
     */
    private void atualizaGorjetaPersonalizada(){
    gorjetaEditText.setText(String.format(
            "%.2f", Calculadora.gorjeta(conta, percentual)));
    }
    // Define o objeto de mudança de texto do ContaEditText
    private TextWatcher ouvinteContaEditText = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
                conta = Double.parseDouble(contaEditText.getText().toString());
            } catch (NumberFormatException e) {
                conta = 0;
            }
            atualizaGorjetas();
            atualizaGorjetaPersonalizada();
        }
    }; //ouvinteContaEditText
    // Define o objeto ouvinte de mudanças no percentual SeekBar
    private SeekBar.OnSeekBarChangeListener ouvintePercentualSeekBar =
        new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percentual = (double)percentualSeekBar.getProgress();
                percentualEditText.setText(String.format("%.1f", percentual));
                atualizaGorjetaPersonalizada();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        }; //ouvintePercentualSeekBar
    // Este método é chamado quando o app é interrompido. Nele criamos nossa lógica  para armazenar as informações que devem ser recuperadas quando o app é restaurado
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState,
                                   @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putDouble(CONTA, conta);
        outState.putDouble(PERCENTUAL, percentual);
    }
} // class MainActivity

















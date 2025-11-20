package com.example.menuexample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity responsável por exibir o carrinho de compras.
 * Aqui a lógica de cálculo de totais, atualização de quantidade
 * e checkout seria implementada.
 */
public class CartActivity extends AppCompatActivity {

    private static final String TAG = "CartActivity";

    // Componentes da interface do usuário
    private ImageButton backButton;
    private Button checkoutButton;
    private CheckBox selectAllCheckbox;
    // VARIÁVEL CORRIGIDA: Agora corresponde ao ID "totalValueTextView" no XML
    private TextView totalValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define o layout XML para esta Activity
        setContentView(R.layout.activity_cart);

        // 1. Inicializa os componentes (Binding)
        initializeViews();

        // 2. Configura os ouvintes de eventos
        setupListeners();

        // 3. Carrega e calcula os dados iniciais do carrinho
        loadCartData();
    }

    /**
     * Obtém referências a todos os componentes importantes do layout.
     */
    private void initializeViews() {
        backButton = findViewById(R.id.backButton);

        // CORREÇÃO: Busca direta pelo ID único do botão.
        checkoutButton = findViewById(R.id.checkoutButton);

        selectAllCheckbox = findViewById(R.id.selectAllCheckbox);

        // CORREÇÃO: Busca direta pelo ID único do TextView de valor total.
        totalValueTextView = findViewById(R.id.totalValueTextView);
    }

    /**
     * Configura os ouvintes de clique para os botões principais.
     */
    private void setupListeners() {
        // Listener para o botão Voltar
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simplesmente finaliza esta activity para voltar à tela anterior
                finish();
            }
        });

        // Listener para o botão Finalizar Compra
        if (checkoutButton != null) {
            checkoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Botão Finalizar Compra clicado.");
                    // Aqui você iniciaria uma nova Activity ou fragmento de pagamento/checkout.
                    // Exemplo: startActivity(new Intent(CartActivity.this, CheckoutActivity.class));
                }
            });
        }

        // Listener para o checkbox "Tudo"
        selectAllCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = selectAllCheckbox.isChecked();
                Log.d(TAG, "Checkbox 'Tudo' alterado para: " + isChecked);
                // Lógica para marcar/desmarcar todos os itens na lista.
            }
        });
    }

    /**
     * Simula o carregamento dos dados do carrinho.
     */
    private void loadCartData() {
        // Em uma implementação real, você atualizaria dinamicamente:
        // 1. Os itens dentro do ScrollView (usando RecyclerView/Adapter).
        // 2. A contagem total de itens e o valor total na checkoutBar.
        Log.i(TAG, "Dados do carrinho carregados. Total: R$ 129,80");
    }

    // --- Métodos de Lógica (Exemplo) ---

    /**
     * Atualiza o valor total do carrinho e a contagem de itens.
     */
    public void updateCartTotal(double newTotal, int itemCount) {
        // Usando o nome de variável corrigido
        if (totalValueTextView != null) {
            totalValueTextView.setText("R$ " + String.format("%.2f", newTotal));
        }

        Log.d(TAG, "Novo total do carrinho: R$ " + newTotal);
    }
}

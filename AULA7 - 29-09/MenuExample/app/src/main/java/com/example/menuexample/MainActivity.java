package com.example.menuexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }// onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    } // onCreateOptionsMenu()

//    @Override onOp
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean saida = true;

        if(item.getItemId() == R.id.menuItemMensagem){
            exibaMensagem();
        } else if (item.getItemId() == R.id.menuItemNavegador) {
            exibaNavegador();
        } else if (item.getItemId() == R.id.menuItemSobre) {
            exibaSobre();
        } else{
            saida = super.onOptionsItemSelected(item);
        }
        return saida;
    } // onOptionsItemSelected()
    private void exibaMensagem(){
        Toast toast = Toast.makeText(this, getResources().getString(R.string.mensagem),
                Toast.LENGTH_SHORT);
        toast.show();
    }
    private void exibaNavegador(){
        Uri uri = Uri.parse("https://www.cruzeirodosul.edu.br");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    } // exibaNavegador
    private void exibaSobre(){
        Intent intent = new Intent(this, SobreActivity.class);
        startActivity(intent);
    } //exibaSobre()
} // class MainActivity
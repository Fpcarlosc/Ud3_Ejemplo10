package com.example.ud3_ejemplo10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Actividad2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad2);
        
        // Recogemos el Intent y le asignamos el valor al TextView
        TextView texto = findViewById(R.id.texto);

        if (getIntent() != null && getIntent().hasExtra(Intent.EXTRA_TEXT)) {
            texto.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
        }
    }
}

package com.example.ud3_ejemplo10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    // Variable para almacenar el elemento seleccionado del spinner.
    public static String item = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creación del spinner

        // Buscamos el spinner en nuestro layout.
        Spinner spinner = findViewById(R.id.spinner);

        // Creamos un adaptador de tipo Arrayadapter usando el string-array creado en el fichero strings.xml
        // y seleccionamos el layout simple_spinner_item.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numeros, android.R.layout.simple_spinner_item);

        // Especificamos el layout a usar cuando se despliega la lista.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplicamos el adaptador al spinner.
        spinner.setAdapter(adapter);

        // Funcionalidad del spinner

        // Asignamos el Listener(OnItemSelectedListener) al spinner para recoger el item seleccionado
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Funcionalidad del botón
        Button boton = findViewById(R.id.boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creamos y enviamos el Intent con el item elegido
                Intent intent = new Intent(MainActivity.this, Actividad2.class);
                intent.putExtra(Intent.EXTRA_TEXT, MainActivity.item);

                startActivity(intent);
            }
        });
    }
}

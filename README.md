# Ud3_Ejemplo10
_Ejemplo 10 de la Unidad 3._ Uso de Spinner y envío entre Actividades.

Vamos a crear un Spinner y pasar el dato seleccionado a una Actividad usando _Intets_ explícitos con datos extras.

## _activity_main.xml_ y _actividad2.xml_
En el fichero _activity_main.xml_ definiremos el Spinner y en el layout _actividad2_ símplemente tendremos un TextView donde 
mostraremos el dato seleccionado del Spinner.

_activity_main.xml_:
```
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Spinner
        android:id="@+id/spinner"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/boton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/enviar"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/spinner" />


</androidx.constraintlayout.widget.ConstraintLayout>
```
_actividad2.xml_:
```
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".Actividad2">

    <TextView
        android:id="@+id/texto"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:text="texto" />
</androidx.constraintlayout.widget.ConstraintLayout>
```
## _strings.xml_
Deberemos rellenar los valores del Spinner en el fichero _strings.xml_ utilizando un _string-array_:
```
<resources>
    <string name="app_name">Ud3_Ejemplo10</string>
    <string name="enviar">Enviar</string>
    <string-array name="numeros">
        <item>Uno</item>
        <item>Dos</item>
        <item>Tres</item>
    </string-array>
</resources>
```
## _MainActivity.java_
En el fichero _MainActivity.java_ deberemos crear el adaptador para utilizar el spinner y añadir los datos extra que el _Intent_ pasará
```
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

```
## _Actividad2.java_
Por último, Actividad2 recogerá el dato seleccionado del Spinner y lo mostrará en el TextView.
```
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
```

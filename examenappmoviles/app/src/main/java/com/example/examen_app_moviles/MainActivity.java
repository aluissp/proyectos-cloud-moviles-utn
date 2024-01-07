package com.example.examen_app_moviles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examen_app_moviles.controller.GenerosController;
import com.example.examen_app_moviles.controller.PeliculasController;
import com.example.examen_app_moviles.models.Genero;
import com.example.examen_app_moviles.models.Pelicula;

public class MainActivity extends AppCompatActivity {

    private EditText txtId, txtTitulo, txtDirector, txtAnio, txtIdGenero, txtNombreGenero, txtPopularidadGenero;
    private PeliculasController peliculasController;
    private GenerosController generosController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtId = findViewById(R.id.txtId);
        this.txtTitulo = findViewById(R.id.txtTitulo);
        this.txtDirector = findViewById(R.id.txtDirector);
        this.txtAnio = findViewById(R.id.txtAnio);
        this.txtIdGenero = findViewById(R.id.txtGeneroId);
        this.txtNombreGenero = findViewById(R.id.txtNombreGenero);
        this.txtPopularidadGenero = findViewById(R.id.txtPopularidadGenero);

        peliculasController = new PeliculasController(this, "pelicula.db", 1);
        generosController = new GenerosController(this, "pelicula.db", 1);

    }

    //    FUNCIONES DE PELICULAS
    public void handleCrear(View v) {
        try {
            int generoId = Integer.parseInt(txtIdGenero.getText().toString());

            Pelicula pelicula = peliculasController.crear(
                    txtTitulo.getText().toString(),
                    txtDirector.getText().toString(),
                    txtAnio.getText().toString(),
                    generoId
            );

            if (pelicula != null) {
                mostrarAlerta("Pelicula creado!");
            } else {
                mostrarAlerta("Ocurrio un error!");
            }
        } catch (Exception e) {
            mostrarAlerta("Ingrese un género válido!");
        }
    }

    public void handleLeerId(View v) {
        Pelicula pelicula = peliculasController.leerPorId(Integer.parseInt(txtId.getText().toString()));

        if (pelicula != null) {
            txtTitulo.setText(pelicula.titulo);
            txtDirector.setText(pelicula.director);
            txtAnio.setText(pelicula.anio);
            txtIdGenero.setText(String.valueOf(pelicula.genero_id));

            mostrarAlerta("Película encontrado");
        } else {
            mostrarAlerta("Película no encontrado");
        }
    }

    public void handleLeerTodo(View v) {
        Pelicula[] peliculas = peliculasController.leerTodo();

        String lista[] = new String[peliculas.length];

        for (int i = 0; i < peliculas.length; i++) {
            lista[i] = "Id: " + peliculas[i].id + ", " + peliculas[i].titulo;
        }

        mostrarDialogo("Listado de películas", lista);
    }


    public void handleActualizar(View v) {
        peliculasController.actualizar(
                Integer.parseInt(txtId.getText().toString()),
                txtTitulo.getText().toString(),
                txtDirector.getText().toString(),
                txtAnio.getText().toString(),
                Integer.parseInt(txtIdGenero.getText().toString())
        );

        mostrarAlerta("Película actualizado!");
    }

    public void handleEliminar(View v) {
        peliculasController.eliminar(
                Integer.parseInt(txtId.getText().toString())
        );

        limpiarTodo(true);
        mostrarAlerta("Película eliminado!");
    }

    public void handleLimpiarTodo(View v) {
        limpiarTodo(true);
    }

    private void mostrarAlerta(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void limpiarTodo(boolean incluirId) {

        if (incluirId) txtId.setText("");
        if (incluirId) txtIdGenero.setText("");

        txtTitulo.setText("");
        txtDirector.setText("");
        txtAnio.setText("");
        txtIdGenero.setText("");
        txtNombreGenero.setText("");
        txtPopularidadGenero.setText("");
    }

    private void mostrarDialogo(String titulo, String[] lista) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setItems(lista, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position
                // of the selected item
            }
        });

        builder.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Acciones a realizar cuando se presione el botón "Cancelar"
            }
        });

        // Crear y mostrar el AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //    FUNCIONES DE GENEROS
    public void handleCrearGenero(View v) {
        try {

            Genero genero = generosController.crear(
                    txtNombreGenero.getText().toString(),
                    Integer.parseInt(txtPopularidadGenero.getText().toString())
            );

            if (genero != null) {
                mostrarAlerta("Genero creado!");
            } else {
                mostrarAlerta("Ocurrio un error!");
            }

        } catch (Exception e) {
            Log.e("ERROR", e.toString());
            mostrarAlerta("Ingrese un género válido!");
        }
    }

    public void handleLeerIdGenero(View v) {
        try {
            Genero pelicula = generosController
                    .leerPorId(Integer.parseInt(txtIdGenero.getText().toString()));

            if (pelicula != null) {
                txtNombreGenero.setText(pelicula.nombre);
                txtPopularidadGenero.setText(String.valueOf(pelicula.popularidad));

                mostrarAlerta("Género encontrado");
            } else {
                mostrarAlerta("Género no encontrado");
            }

        } catch (Exception e) {
            Log.e("ERROR", e.toString());
            mostrarAlerta("Ingrese un género válido!");
        }
    }

    public void handleLeerTodoGeneros(View v) {
        Genero[] peliculas = generosController.leerTodo();

        String lista[] = new String[peliculas.length];

        for (int i = 0; i < peliculas.length; i++) {
            lista[i] = "Id: " + peliculas[i].id + ", " + peliculas[i].nombre;
        }

        mostrarDialogo("Listado de géneros", lista);
    }


    public void handleActualizarGenero(View v) {
        generosController.actualizar(
                Integer.parseInt(txtIdGenero.getText().toString()),
                txtNombreGenero.getText().toString(),
                Integer.parseInt(txtPopularidadGenero.getText().toString())
        );

        mostrarAlerta("Género actualizado!");
    }

    public void handleEliminarGenero(View v) {
        peliculasController.eliminar(
                Integer.parseInt(txtIdGenero.getText().toString())
        );

        limpiarTodo(true);
        mostrarAlerta("Genero eliminado!");
    }
}
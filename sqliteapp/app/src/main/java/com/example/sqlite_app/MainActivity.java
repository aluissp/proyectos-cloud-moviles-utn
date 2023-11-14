package com.example.sqlite_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite_app.controller.ProductosController;
import com.example.sqlite_app.models.Producto;

public class MainActivity extends AppCompatActivity {

    private EditText txtId, txtNombre, txtDescripcion, txtStock, txtPrecioUnitario, txtTasaIva;
    private ProductosController productosController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtId = findViewById(R.id.txtId);
        this.txtNombre = findViewById(R.id.txtNombre);
        this.txtDescripcion = findViewById(R.id.txtDescripcion);
        this.txtStock = findViewById(R.id.txtStock);
        this.txtPrecioUnitario = findViewById(R.id.txtPrecioUnitario);
        this.txtTasaIva = findViewById(R.id.txtTasaIva);

        productosController = new ProductosController(this, "productos.db", 1);
    }

    public void handleCrear(View v) {
        Producto producto = productosController.crearProducto(
                Integer.parseInt(txtId.getText().toString()),
                txtNombre.getText().toString(),
                txtDescripcion.getText().toString(),
                Float.parseFloat(txtStock.getText().toString()),
                Float.parseFloat(txtPrecioUnitario.getText().toString()),
                Float.parseFloat(txtTasaIva.getText().toString())
        );

        if (producto != null) {
            mostrarAlerta("Producto creado!");
        } else {
            mostrarAlerta("Ocurrio un error!");
        }
    }

    public void handleLeerId(View v) {
        Producto producto = productosController.leerPorId(Integer.parseInt(txtId.getText().toString()));

        if (producto != null) {
            txtNombre.setText(producto.nombre);
            txtDescripcion.setText(producto.descripcion);
            txtStock.setText(String.valueOf(producto.stock));
            txtPrecioUnitario.setText(String.valueOf(producto.precioUnitario));
            txtTasaIva.setText(String.valueOf(producto.tasaIva));

            mostrarAlerta("Producto encontrado");
        } else {
            mostrarAlerta("Producto no encontrado");
        }
    }

    public void handleActualizar(View v) {
        productosController.actualizar(
                Integer.parseInt(txtId.getText().toString()),
                txtNombre.getText().toString(),
                txtDescripcion.getText().toString(),
                Float.parseFloat(txtStock.getText().toString()),
                Float.parseFloat(txtPrecioUnitario.getText().toString()),
                Float.parseFloat(txtTasaIva.getText().toString())
        );

        mostrarAlerta("Producto actualizado!");
    }

    public void handleEliminar(View v) {
        productosController.eliminar(
                Integer.parseInt(txtId.getText().toString())
        );

        limpiarTodo(true);
        mostrarAlerta("Producto eliminado!");
    }

    public void handleLimpiarTodo(View v){
        limpiarTodo(true);
    }

    private void mostrarAlerta(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void limpiarTodo(boolean incluirId) {

        if (incluirId) txtId.setText("");

        txtNombre.setText("");
        txtDescripcion.setText("");
        txtStock.setText("");
        txtPrecioUnitario.setText("");
        txtTasaIva.setText("");

    }
}